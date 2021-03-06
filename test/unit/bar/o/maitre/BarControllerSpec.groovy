package bar.o.maitre

import grails.plugin.springsecurity.SpringSecurityService
import grails.test.mixin.Mock
import grails.test.mixin.TestFor
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.support.GrailsUnitTestMixin} for usage instructions
 */
@TestFor(BarController)
@Mock(Bar)
class BarControllerSpec extends Specification {
    SpringSecurityService springSecurityService
    Member member = Mock(Member)

    def populateValidParams(params) {
        assert params != null
        params["barName"] = 'test'
        params["description"] = 'test'
        params["address"] = 'test'
        params["type"] = 'test'
        params["price"] = 'test'
        params["admin"] = member
    }

    def setup() {
        member.id >> 1337

        springSecurityService = Mock(SpringSecurityService)
        springSecurityService.currentUser >> member
        controller.springSecurityService = springSecurityService
    }

    void "Test the index action returns the correct model"() {

        when:"The index action is executed"
        controller.index()

        then:"The model is correct"
        !model.barInstanceList
        model.barInstanceCount == 0
    }

    void "Test the index action with a param max returns the correct model"() {
        params.max = 20
        when:"The index action is executed"
        controller.index()

        then:"The model is correct"
        !model.barInstanceList
        model.barInstanceCount == 0
    }

    void "Test the create action returns the correct model"() {
        when:"The create action is executed"
        controller.create()

        then:"The model is correctly created"
        model.barInstance!= null
    }

    void "Test the save action correctly persists an instance"() {

        when:"The save action is executed with an invalid instance"
        request.contentType = FORM_CONTENT_TYPE
        request.method = 'POST'
        def bar = new Bar()
        bar.validate()

        controller.save(bar)

        then:"The create view is rendered again with the correct model"
        model.barInstance!= null
        view == 'create'

        when:"The save action is executed with a valid instance"
        response.reset()
        populateValidParams(params)
        bar = new Bar(params)

        controller.save(bar)

        then:"A redirect is issued to the show action"
        response.redirectedUrl == '/bar/show/1'
        controller.flash.message != null
        Bar.count() == 1
    }

    void "Test that the show action returns the correct model"() {
        when:"The show action is executed with a null domain"
        controller.show(null)

        then:"A 404 error is returned"
        response.status == 404

        when:"A domain instance is passed to the show action"
        populateValidParams(params)
        def bar = new Bar(params)
        controller.show(bar)

        then:"A model is populated containing the domain instance"
        model.barInstance == bar
    }

    void "Test that the edit action returns the correct model"() {
        when:"The edit action is executed with a null domain"
        controller.edit(null)

        then:"A 404 error is returned"
        response.status == 404

        when:"A domain instance is passed to the edit action"
        populateValidParams(params)
        def bar = new Bar(params)
        controller.edit(bar)

        then:"A model is populated containing the domain instance"
        model.barInstance == bar
    }

    void "Test the update action performs an update on a valid domain instance"() {
        when:"Update is called for a domain instance that doesn't exist"
        request.contentType = FORM_CONTENT_TYPE
       request.method = 'PUT'
        controller.update(null)

        then:"A 404 error is returned"
        response.redirectedUrl == '/bar/index'
        flash.message != null


        when:"An invalid domain instance is passed to the update action"
        response.reset()
        def bar = new Bar()
        bar.validate()
        controller.update(bar)

        then:"The edit view is rendered again with the invalid instance"
        view == 'edit'
        model.barInstance == bar

        when:"A valid domain instance is passed to the update action"
        response.reset()
        populateValidParams(params)
        bar = new Bar(params).save(flush: true)
        controller.update(bar)

        then:"A redirect is issues to the show action"
        response.redirectedUrl == "/bar/show/$bar.id"
        flash.message != null
    }

    void "Test that the delete action deletes an instance if it exists"() {
        when:"The delete action is called for a null instance"
        request.contentType = FORM_CONTENT_TYPE
        request.method = 'DELETE'
        controller.delete(null)

        then:"A 404 is returned"
        response.redirectedUrl == '/bar/index'
        flash.message != null

        when:"A domain instance is created"
        response.reset()
        populateValidParams(params)
        def bar = new Bar(params).save(flush: true)

        then:"It exists"
        Bar.count() == 1

        when:"The domain instance is passed to the delete action"
        controller.delete(bar)

        then:"The instance is deleted"
        Bar.count() == 0
        response.redirectedUrl == '/bar/show/1'
        flash.message != null
    }

    void "save a null barInstance = fail"()
    {

        when:"the null bar is saved"
        request.contentType = FORM_CONTENT_TYPE
        controller.save(null)

        then:"A 404 is returned"
        response.redirectedUrl == '/bar/index'
        flash.message != null
    }

    /*void "like a barInstance with a new member"()
    {
        given: "a Bar"
        populateValidParams(params)
        def barTest = new Bar(params)
        // Member member1 = new Member()Member member2 = Mock(Member)
        member.validate()

        mockDomain(Member,[[member:member]])


        when:"the bar is liked"
        controller.like(barTest)
        then:"A showPage is returned"
        response.redirectedUrl == '/bar/show'
        flash.message == "+1 Like"
    }

    void "like a barInstance with a member who has already liked this bar"()
    {
        populateValidParams(params)
        def bar = new Bar(params).save(flush: true)

        controller.like(bar)

        when:"the bar is liked again"
        controller.like(bar)
        then:"A showPage is returned"
        response.redirectedUrl == '/bar/show'
        flash.message == "Vous avez déjà liké ce bar"
    }*/
}

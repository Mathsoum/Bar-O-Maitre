package bar.o.maitre



import grails.test.mixin.*
import spock.lang.*

@TestFor(MemberController)
@Mock(Member)
class MemberControllerSpec extends Specification {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void "Test the index action returns the correct model"() {

        when:"The index action is executed"
        controller.index()

        then:"The model is correct"
        !model.memberInstanceList
        model.memberInstanceCount == 0
    }

    void "Test the create action returns the correct model"() {
        when:"The create action is executed"
        controller.create()

        then:"The model is correctly created"
        model.memberInstance!= null
    }


    void "Test that the show action returns the correct model"() {
        when:"The show action is executed with a null domain"
        controller.show(null)

        then:"A 404 error is returned"
        response.status == 404

        when:"A domain instance is passed to the show action"
        populateValidParams(params)
        def member = new Member(params)
        controller.show(member)

        then:"A model is populated containing the domain instance"
        model.memberInstance == member
    }

    void "Test that the edit action returns the correct model"() {
        when:"The edit action is executed with a null domain"
        controller.edit(null)

        then:"A 404 error is returned"
        response.status == 404

        when:"A domain instance is passed to the edit action"
        populateValidParams(params)
        def member = new Member(params)
        controller.edit(member)

        then:"A model is populated containing the domain instance"
        model.memberInstance == member
    }
}

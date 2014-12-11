package bar.o.maitre

import grails.plugin.springsecurity.SpringSecurityService
import grails.test.mixin.Mock
import grails.test.mixin.TestFor
import grails.test.mixin.TestMixin
import grails.test.mixin.domain.DomainClassUnitTestMixin
import grails.validation.ValidationException
import org.springframework.security.crypto.password.PasswordEncoder
import spock.lang.Specification


/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(Bar)
@Mock(Bar)
class BarIntegrationSpec extends Specification {
    Member member = Mock(Member)

    def setup() {
        member.id >> 1337
    }
        void "test getNbLikes 0 like"() {
        given:"A bar with 0 like"
        Bar barTest = new Bar(
                barName : "Acuda",
                description : "Poisson",
                address : "3rd street tuna",
                type : "Bar tapas",
                price : "very expensive",
                admin : new Member(username: 'admin', password: 'admin', firstName: 'toto', lastName:'tata', mail:'toto@gmail.com', birthDate: new Date("1985/10/10"))
        )
        //barTest .save(flush: true)

        when:"The number of like is asked"
        def res = barTest.getNbLike()

        then:"0 like"
        res == "0"
    }

  /*  void "test getNbLikes 1 like"() {
        given:"A bar with 1 like"
        Bar barTest = new Bar(
                barName : "Acuda",
                description : "Poisson",
                address : "3rd street tuna",
                type : "Bar tapas",
                price : "very expensive",
                admin : new Member(username: 'admin', password: 'admin', firstName: 'toto', lastName:'tata', mail:'toto@gmail.com', birthDate: new Date("1985/10/10"))
        )
       // barTest .save(flush: true)
        barTest.addToLikers(new Member(username: 'admin', password: 'admin', firstName: 'toto', lastName:'tata', mail:'toto@gmail.com', birthDate: new Date("1985/10/10")))
        //barTest.getLikers().add(memberTest)

        when:"The number of like is asked"
        def res = barTest.getNbLike()

        then:"1 like"
        res == "1"
    }*/
}

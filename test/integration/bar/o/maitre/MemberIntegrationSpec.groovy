package bar.o.maitre

import grails.plugin.springsecurity.SpringSecurityService
import grails.test.mixin.TestFor
import grails.validation.ValidationException
import org.springframework.security.crypto.password.PasswordEncoder
import spock.lang.Specification


/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(Member)
class MemberIntegrationSpec extends Specification {

   def "test add two users with the same nick"() {
        given: "two users with the same nick"
        def firstUser = new Member(
                username:"mynick",
                firstName:"firstUser",
                lastName:"mylastname",
                mail:"user1@mail.mail",
                birthDate:new Date("1/1/1980"),
                password: "coucou"
        )

        def secondUser = new Member(
                username:"mynick",
                firstName:"secondUser",
                lastName:"mylastname",
                mail:"user2@mail.mail",
                birthDate:new Date("2/1/1980"),
                password: "coucou"
        )

        mockForConstraintsTests(Member, [firstUser, secondUser])

        when: "we add them to the database"
            firstUser.save(failOnError: true, flush: true)
            secondUser.save(flush: true)


        then: "saving the second user throw a ValidationException"
            thrown ValidationException
    }

    def "test add two users with the same mail"() {
        given: "two users with the same mail"
            def firstUser = new Member(
                    username:"firstUser",
                    firstName:"firstUser",
                    lastName:"firstUser",
                    mail:"user@mail.mail",
                    birthDate:new Date("1/1/1980"),
                    password: "coucou"
            )

            def secondUser = new Member(
                    username:"secondUser",
                    firstName:"secondUser",
                    lastName:"secondUser",
                    mail:"user@mail.mail",
                    birthDate:new Date("2/1/1980"),
                    password: "coucou"
            )

            mockForConstraintsTests(Member, [firstUser, secondUser])

        when: "we add them to the database"
            firstUser.save(failOnError: true, flush: true)
            secondUser.save(flush: true)

        then: "saving the second user throw a ValidationException"
            thrown ValidationException
    }

    def "test that update a user password scrambles it before saving it"() {
        given: "a user with a password"
        Member member = new Member(
            username: "member",
            password: "password",
            firstName: "John",
            lastName: "Smith",
            mail: "john.smith@domain.com",
            birthDate: new Date("1/1/1980")
        )
        member.save(flush: true)

        and: "the spring security service"
        member.springSecurityService = Mock(SpringSecurityService)
        member.springSecurityService.passwordEncoder >> Mock(PasswordEncoder)

        and: "a new password"
        String newPassword = "newPassword"

        when: "updating his password"
        member.password = newPassword
        member.save(flush: true)

        then: "password has been scrambled"
        1 * member.springSecurityService.encodePassword(newPassword)

        when: "updating member without changing the password"
        member.firstName = "Jane"
        member.save(flush: true)

        then: "no need to scrambled anything"
        0 * member.springSecurityService.encodePassword(newPassword)
    }

}

package bar.o.maitre

import grails.test.mixin.TestFor
import grails.validation.ValidationException
import spock.lang.Specification


/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(Member)
class MemberIntegrationSpec extends Specification {
    Rank userRole

    def setup() {
        userRole = new Rank(authority: 'ROLE_USER')
        userRole.save(flush: true)
    }

    def cleanup() {
    }

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
}

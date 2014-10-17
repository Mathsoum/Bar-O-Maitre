package bar.o.maitre

import grails.test.mixin.TestFor
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(User)
class UserIntegrationSpec extends Specification {
    User user1, userWithSameNick, userWithSameMail

    def setup() {
        user1 = new User(nickname:"mynick", firstName:"user1", lastName:"mylastname", mail:"user1@mail.mail", birthDate:new Date("1/1/1980"))
        userWithSameNick = new User(nickname:"mynick", firstName:"myfirstname", lastName:"mylastname", mail:"user2@mail.mail", birthDate:new Date("2/1/1980"))
        userWithSameMail = new User(nickname:"userWithSameMailThanUser1", firstName:"myfirstname", lastName:"mylastname", mail:"user1@mail.mail", birthDate:new Date("2/1/1980"))
    }

    def cleanup() {
    }

    def "test add two users with the same nick"() {
        given: "two users with the same nick"
        user1
        userWithSameNick

        when: "we add them to the database"
        user1.save(failOnError: true,flush: true)
        userWithSameNick.save(flush: true)

        then: "the first user was saved but the second user was not saved"
        User.findByFirstName("user1") != null
        User.findById(2) == null

    }

    def "test add two users with the same mail"() {
        given: "two users with the same mail"
        user1
        userWithSameMail

        when: "we add them to the database"
        user1.save(failOnError: true, flush: true)
        userWithSameMail.save(flush: true)

        then: "the first user was saved but the second user was not saved"
        User.findByNickname("mynick") != null
        User.findByNickname("userWithSameMailThanUser1") == null
    }
}

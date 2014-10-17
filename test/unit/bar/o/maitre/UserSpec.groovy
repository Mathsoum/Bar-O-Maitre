package bar.o.maitre

import grails.test.mixin.TestFor
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(User)
class UserSpec extends Specification {
    User user1, user2

    def setup() {
        user1 = new User(nickname:"mynick", firstName:"myfirstname", lastName:"mylastname", mail:"user1@mail.mail", birthDate:new Date("1/1/1980"))
        user2 = new User(nickname:"mynick", firstName:"myfirstname", lastName:"mylastname", mail:"user2@mail.mail", birthDate:new Date("2/1/1980"))
    }

    def cleanup() {
    }

    def "test add two users with the same nick"() {
        given: "two users with the same nick"
        user1
        user2

        when: "we add them to the database"
        user1.save(failOnError: true)
        user2.save()

        then: "the first user was saved but the second user was not saved"
        User.findByNickname("mynick") != null
        User.findById(2) == null

    }
}

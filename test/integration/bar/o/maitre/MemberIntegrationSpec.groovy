package bar.o.maitre

import grails.test.mixin.TestFor
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(Member)
class MemberIntegrationSpec extends Specification {
    Member user1, userWithSameNick, userWithSameMail
    Rank userRole

    def setup() {
        user1 = new Member(username:"mynick", firstName:"user1", lastName:"mylastname", mail:"user1@mail.mail", birthDate:new Date("1/1/1980"), password: "coucou")
        userWithSameNick = new Member(username:"mynick", firstName:"myfirstname", lastName:"mylastname", mail:"user2@mail.mail", birthDate:new Date("2/1/1980"),password: "coucou")
        userWithSameMail = new Member(username:"userWithSameMailThanUser1", firstName:"myfirstname", lastName:"mylastname", mail:"user1@mail.mail", birthDate:new Date("2/1/1980"),password: "coucou")
        userRole = new Rank(authority: 'ROLE_USER')
        userRole.save(flush: true)
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
        Member.findByFirstName("user1") != null
        Member.findById(2) == null

    }

    def "test add two users with the same mail"() {
        given: "two users with the same mail"
        user1
        userWithSameMail

        when: "we add them to the database"
        user1.save(failOnError: true, flush: true)
        userWithSameMail.save(flush: true)

        then: "the first user was saved but the second user was not saved"
        Member.findByUsername("mynick") != null
        Member.findByUsername("userWithSameMailThanUser1") == null
    }

}

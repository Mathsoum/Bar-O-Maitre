package bar.o.maitre

import grails.plugin.springsecurity.SpringSecurityService
import grails.test.mixin.Mock
import grails.test.mixin.TestFor
import groovy.time.TimeCategory
import org.springframework.security.crypto.password.PasswordEncoder
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(Member)
@Mock([MemberRankService, Rank, SpringSecurityService])
class MemberSpec extends Specification {

    def ranks

    def "test that member must be at least 18 years old"() {
        given: "a member"
        Member member = new Member(
                username: "member",
                password: "password",
                firstName: "John",
                lastName: "Smith",
                mail: "john.smith@domain.com"
        )

        when: "he is underage (<18)"
        member.birthDate = new Date()

        then: "the validation shouldn't pass"
        !member.validate()

        when: "he is over 18 years old"
        use(TimeCategory) {
            member.birthDate = new Date() - 20.years
        }

        then: "the validation shouldn't pass"
        member.validate()
    }

    def "test toString"() {
        given: "a member with username and mail"
        Member member = new Member(
                username: "nickname",
                mail: "john.smith@domain.com"
        )

        when: "getting related string"
        String string = member.toString()

        then: "the string generated is correct"
        string == "nickname"
    }

    def "test equals"(Object member1, Object member2, boolean areEquals) {
        given: "two members given as parameter"
        member1
        member2

        expect: "the members to be equals (or not)"
        member1.equals(member2) == areEquals
        member2.equals(member1) == areEquals

        and: "equals to be reflexive"
        member1.equals(member1)
        member2.equals(member2)

        where:
        member1                                                             | member2                                                           | areEquals
        new Member(username: "nickname", mail: "john.smith@domain.com")     | new Member(username: "nickname", mail: "john.smith@domain.com")   | true
        new Member(username: "username", mail: "jsmith@domain.com")         | new Member(username: "nickname", mail: "john.smith@domain.com")   | false
        new Member(username: "nickname", mail: "jsmith@domain.com")         | new Member(username: "nickname", mail: "john.smith@domain.com")   | false
        new Member(username: "username", mail: "john.smith@domain.com")     | new Member(username: "nickname", mail: "john.smith@domain.com")   | false
        new Integer(42)                                                     | new Member(username: "nickname", mail: "john.smith@domain.com")   | false
    }

    def "test getAuthorities"() {
        given: "a member with username and mail and a rank"
        Member member = new Member(
                username: "nickname",
                mail: "john.smith@domain.com"
        )
        Rank rank = new Rank(authority: "admin")
        rank.validate()

        mockDomain(MemberRank,[[member: member, rank: rank]])

        when: "getting rank"
        ranks = member.getAuthorities()

        then: "the number of rank returned is correct"
        ranks.size() == 1
    }

    def " test before update"(){
        given:"a member with username and mail and password"
        Member member = new Member(
                username: "member",
                password: "password",
                firstName: "John",
                lastName: "Smith",
                mail: "john.smith@domain.com",
                birthDate: new Date("1/1/1980")
        )
        String newPassword = "newpassword"
        def springSecurityService = mockFor(SpringSecurityService,true)
        springSecurityService.demand.encodePassword(){String pwd -> return "ENCODED_PWD"}

        member.springSecurityService = springSecurityService.createMock()

        when:" Update password"
        member.password = newPassword
        member.validate()
        member.save(flush: true, failOnError: true)

        then:"password is encoded"
        member.springSecurityService != null
        member.springSecurityService.encodePassword(newPassword) == "ENCODED_PWD"
    }
}

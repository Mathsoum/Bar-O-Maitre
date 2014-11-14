package bar.o.maitre

import grails.test.mixin.TestFor
import groovy.time.TimeCategory
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(Member)
class MemberSpec extends Specification {

    MemberRankService memberRankService

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
        string == "nickname (john.smith@domain.com)"
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

    /*def "test getAuthorities"() {
        given: "a member with username and mail and a rank"
        Member member = new Member(
                username: "nickname",
                mail: "john.smith@domain.com"
        )

        memberRankService.create(member, adminRole, true)

        when: "getting rank"
        Set<Rank> rank = member.getAuthorities()

        then: "the number of rank returned is correct"
        rank.size() == 1
    }*/
}

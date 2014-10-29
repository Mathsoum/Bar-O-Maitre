package bar.o.maitre

import grails.test.mixin.TestMixin
import grails.test.mixin.support.GrailsUnitTestMixin
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.support.GrailsUnitTestMixin} for usage instructions
 */
@TestMixin(GrailsUnitTestMixin)
class MemberRankSpec extends Specification {

    def "test toString"() {
        given: "a rank"
        Rank rank = new Rank(
            authority: 'ROLE_ADMIN'
        )

        and: "a member"
        Member member = new Member(
            username: "nickname",
            mail: "john.smith@domain.com"
        )

        and: "member-rank relation"
        MemberRank memberRank = new MemberRank(
            member: member,
            rank: rank
        )

        when: "getting related string"
        String string = memberRank.toString()

        then: "the string generated is correct"
        string == "$member / $rank"
    }
}

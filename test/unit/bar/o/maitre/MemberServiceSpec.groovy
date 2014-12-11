package bar.o.maitre

import grails.test.mixin.TestFor
import spock.lang.Specification

/**
 * Created by Joris on 11/12/2014.
 */

@TestFor(MemberService)
class MemberServiceSpec extends Specification {

    MemberRankService memberRankService

    def setup() {
        memberRankService = Mock(MemberRankService)
        service.memberRankService = memberRankService
    }

    def "test updateRank user"() {
        given:"A member"
        Member memberTest = new Member(reputation: 0)

        when:"The bar is save on database"
        service.updateRank(memberTest, 2)

        then:""
        1 * memberRankService.attribute_user_role(memberTest)
    }

    def "test updateRank adept"() {
        given:"A member with a reputation"
        Member memberTest = new Member(reputation: 0)

        when:"The bar is save on database"
        service.updateRank(memberTest, 6)

        then:""
        1 * memberRankService.attribute_adept_role(memberTest)
    }

    def "test updateRank confirmed"() {
        given:"A member"
        Member memberTest = new Member(reputation: 0)

        when:"The bar is save on database"
        service.updateRank(memberTest, 11)

        then:""
        1 * memberRankService.attribute_confirmed_role(memberTest)
    }

    def "test updateRank expert"() {
        given:"A member"
        Member memberTest = new Member(reputation: 0)

        when:"The bar is save on database"
        service.updateRank(memberTest, 16)

        then:""
        1 * memberRankService.attribute_expert_role(memberTest)
    }

    def "test updateRank admin"() {
        given:"A member"
        Member memberTest = new Member(reputation: 0)

        when:"The bar is save on database"
        service.updateRank(memberTest, 24)

        then:""
        1 * memberRankService.attribute_admin_role(memberTest)
    }
}

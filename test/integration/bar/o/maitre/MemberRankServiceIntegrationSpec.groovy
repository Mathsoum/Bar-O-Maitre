package bar.o.maitre

import grails.test.mixin.TestFor
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.services.ServiceUnitTestMixin} for usage instructions
 */
@TestFor(MemberRankService)
class MemberRankServiceIntegrationSpec extends Specification {
    Member member
    Rank rank

    def setup() {
        member = new Member(
            username: "member",
            password: "password",
            firstName: "John",
            lastName: "Smith",
            mail: "john.smith@domain.com",
            birthDate: new Date("1/1/1980")
        ).save()

        rank = new Rank(
            authority: "ROLE"
        ).save()
    }

    def cleanup() {
        MemberRank.list().each {
            it.delete()
        }
        member.delete()
        rank.delete(flush: true)
    }

    def "test get"() {
        given: "a persisted MemberRank"
        MemberRank memberRank = new MemberRank(
                member: member,
                rank: rank
        ).save(flush: true)

        when: "getting it from member and rank"
        def mr = service.get(member.id, rank.id)

        then: "memberRank is returned"
        mr == memberRank

        when: "getting it from member and rank"
        def mr_fail = service.get(23, 42)

        then: "memberRank is returned"
        mr_fail == null
    }

    def "test exists"() {
        given: "an existing MemberRank"
        new MemberRank(
            member: member,
            rank: rank
        ).save(flush: true)

        when: "checking if it exists"
        def itExists = service.exists(member.id, rank.id)

        then: "it's OK"
        itExists

        when: "checking if it exists"
        itExists = service.exists(23, 42)

        then: "it's KO"
        !itExists
    }

    def "test create"() {
        given: "a member and a rank"
        member
        rank

        and: "the number of instances before creation"
        def count = MemberRank.count

        when: "creating MemberRank"
        def instance = service.create(
            member,
            rank,
            true
        )

        then: "a new instance is created and returned"
        instance
        MemberRank.count == count + 1
    }

    def "test remove"() {
        given: "a persisted instance"
        new MemberRank(
            member: member,
            rank: rank
        ).save(flush: true)

        and: "the number of instances before creation"
        def count = MemberRank.count

        when: "removing that instance"
        service.remove(member, rank, true)

        then: "the instance is removed from base"
        MemberRank.count == count - 1
    }

    def "test remove with member null"() {
        given: "a persisted instance"
        new MemberRank(
                member: member,
                rank: rank
        ).save(flush: true)

        and: "the number of instances before creation"
        def count = MemberRank.count

        when: "removing that instance"
        service.remove(null, rank, true)

        then: "any instance is removed"
        MemberRank.count == count
    }

    def "test remove with rank null"() {
        given: "a persisted instance"
        new MemberRank(
                member: member,
                rank: rank
        ).save(flush: true)

        and: "the number of instances before creation"
        def count = MemberRank.count

        when: "removing that instance"
        service.remove(member, null, true)

        then: "any instance is removed"
        MemberRank.count == count
    }

    def "test remove with flush false"() {
        given: "a persisted instance"
        new MemberRank(
                member: member,
                rank: rank
        ).save(flush: true)

        and: "the number of instances before creation"
        def count = MemberRank.count

        when: "removing that instance"
        service.remove(member, rank, false)

        then: "the instance is removed from base"
        MemberRank.count == count-1
    }

    def "test remove with unknow member"() {
        given: "a persisted instance"
        new MemberRank(
                member: member,
                rank: rank
        ).save(flush: true)

        and: "the number of instances before creation"
        def count = MemberRank.count

        when: "removing that instance"
        service.remove(new Member(), rank, true)

        then: "the instance is removed from base"
        MemberRank.count == count
    }

    def "test removeAll member"() {
        given: "a persisted instance"
        new MemberRank(
                member: member,
                rank: rank
        ).save(flush: true)

        when: "removing that instance"
        service.removeAll(member, true)

        then: "the instance is removed from base"
        !MemberRank.findByMember(member)
    }

    def "test removeAll member with u null"() {
        given: "a persisted instance"
        new MemberRank(
                member: member,
                rank: rank
        ).save(flush: true)

        and: "the number of instances before creation"
        def count = MemberRank.count

        when: "removing that instance"
        service.removeAll((Member)null, true)

        then: "any member was removed"
        count == MemberRank.count
    }

    def "test removeAll member with flush false"() {
        given: "a persisted instance"
        new MemberRank(
                member: member,
                rank: rank
        ).save(flush: true)

        when: "removing that instance"
        service.removeAll(member, false)

        then: "the instance is removed from base"
        !MemberRank.findByMember(member)
    }

    def "test removeAll rank"() {
        given: "a persisted instance"
        new MemberRank(
            member: member,
            rank: rank
        ).save(flush: true)

        when: "removing that instance"
        service.removeAll(rank, true)

        then: "the instance is removed from base"
        !MemberRank.findByRank(rank)
    }

    def "test removeAll rank with r null"() {
        given: "a persisted instance"
        new MemberRank(
                member: member,
                rank: rank
        ).save(flush: true)

        and: "the number of instances before creation"
        def count = MemberRank.count

        when: "removing that instance"
        service.removeAll((Rank)null, true)

        then: "any rank was removed"
        count == MemberRank.count
    }

    def "test removeAll rank with flush false"() {
        given: "a persisted instance"
        new MemberRank(
                member: member,
                rank: rank
        ).save(flush: true)

        when: "removing that instance"
        service.removeAll(rank, false)

        then: "the instance is removed from base"
        !MemberRank.findByRank(rank)
    }

    def "test attribute_user_role"() {
        given: "a member"
        member

        when: "attributing user rank to this member"
        service.attribute_user_role(member)

        then: "rank is assigned"
        MemberRank.findByMember(member).rank.authority == "ROLE_USER"

    }

    def "test attribute_adept_role"() {
        given: "a member"
        member

        when: "attributing adept rank to this member"
        service.attribute_adept_role(member)

        then: "rank is assigned"
        MemberRank.findByMember(member).rank.authority == "ROLE_ADEPT"

    }

    def "test attribute_confirmed_role"() {
        given: "a member"
        member

        when: "attributing confirmed rank to this member"
        service.attribute_confirmed_role(member)

        then: "rank is assigned"
        MemberRank.findByMember(member).rank.authority == "ROLE_CONFIRMED"

    }

    def "test attribute_expert_role"() {
        given: "a member"
        member

        when: "attributing expert rank to this member"
        service.attribute_expert_role(member)

        then: "rank is assigned"
        MemberRank.findByMember(member).rank.authority == "ROLE_EXPERT"

    }

    def "test attribute_admin_role"() {
        given: "a member"
        member

        when: "attributing admin rank to this member"
        service.attribute_admin_role(member)

        then: "rank is assigned"
        MemberRank.findByMember(member).rank.authority == "ROLE_ADMIN"

    }

    /*
    def "test attribute_user_role with member who already have a rank"() {
        given: "a member with a rank"
        member
        service.attribute_user_role(member)

        when: "attributing again user rank to this member"
        service.attribute_user_role(member)

        then: "he keep his rank"
        MemberRank.findByMember(member).rank.authority == "ROLE_USER"

    }*/

}


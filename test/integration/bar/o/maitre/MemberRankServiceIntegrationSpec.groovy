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

    def "test attribute_user_role"() {
        given: "a member"
        member

        when: "attributing user rank to this member"
        service.attribute_user_role(member)

        then: "rank is assigned"
        MemberRank.findByMember(member).rank.authority == "ROLE_USER"

    }

}


package bar.o.maitre

import grails.transaction.Transactional

@Transactional
class MemberRankService {

    MemberRank get(long memberId, long rankId) {
        MemberRank.where {
            member == Member.load(memberId) &&
                rank == Rank.load(rankId)
        }.get()
    }

    boolean exists(long memberId, long rankId) {
        MemberRank.where {
            member == Member.load(memberId) &&
                rank == Rank.load(rankId)
        }.count() > 0
    }

    MemberRank create(Member member, Rank rank, boolean flush = false) {
        MemberRank instance = new MemberRank(member: member, rank: rank)
        instance.save(flush: flush, insert: true)
        instance
    }

    boolean remove(Member u, Rank r, boolean flush = false) {
        if (u == null || r == null) return false

        int rowCount = MemberRank.where {
            member == Member.load(u.id) &&
                rank == Rank.load(r.id)
        }.deleteAll()

        if (flush) { MemberRank.withSession { it.flush() } }

        rowCount > 0
    }

    void removeAll(Member u, boolean flush = false) {
        if (u == null) return

        MemberRank.where {
            member == Member.load(u.id)
        }.deleteAll()

        if (flush) { MemberRank.withSession { it.flush() } }
    }

    void removeAll(Rank r, boolean flush = false) {
        if (r == null) return

        MemberRank.where {
            rank == Rank.load(r.id)
        }.deleteAll()

        if (flush) { MemberRank.withSession { it.flush() } }
    }

    void attribute_user_role(Member member) {
        this.removeAll(member)
        MemberRank mr = new MemberRank(member:member, rank:Rank.findByAuthority("ROLE_USER"))
        mr.save(flush:true)
    }

    void attribute_adept_role(Member member) {
        this.removeAll(member)
        MemberRank mr = new MemberRank(member:member, rank:Rank.findByAuthority("ROLE_ADEPT"))
        mr.save(flush:true)
    }

    void attribute_confirmed_role(Member member) {
        this.removeAll(member)
        MemberRank mr = new MemberRank(member:member, rank:Rank.findByAuthority("ROLE_CONFIRMED"))
        mr.save(flush:true)

    }

    void attribute_expert_role(Member member) {
        this.removeAll(member)
        MemberRank mr = new MemberRank(member:member, rank:Rank.findByAuthority("ROLE_EXPERT"))
        mr.save(flush:true)
    }

    void attribute_admin_role(Member member) {
        this.removeAll(member)
        MemberRank mr = new MemberRank(member:member, rank:Rank.findByAuthority("ROLE_ADMIN"))
        mr.save(flush:true)
    }
}

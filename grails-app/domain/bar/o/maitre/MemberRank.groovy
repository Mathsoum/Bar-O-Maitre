package bar.o.maitre

import org.apache.commons.lang.builder.HashCodeBuilder

class MemberRank implements Serializable {

	private static final long serialVersionUID = 1

	Member member
	Rank rank

	boolean equals(other) {
		if (!(other instanceof MemberRank)) {
			return false
		}

		other.member?.id == member?.id &&
		other.rank?.id == rank?.id
	}

	int hashCode() {
		def builder = new HashCodeBuilder()
		if (member) builder.append(member.id)
		if (rank) builder.append(rank.id)
		builder.toHashCode()
	}

	static MemberRank get(long memberId, long rankId) {
		MemberRank.where {
			member == Member.load(memberId) &&
			rank == Rank.load(rankId)
		}.get()
	}

	static boolean exists(long memberId, long rankId) {
		MemberRank.where {
			member == Member.load(memberId) &&
			rank == Rank.load(rankId)
		}.count() > 0
	}

	static MemberRank create(Member member, Rank rank, boolean flush = false) {
		def instance = new MemberRank(member: member, rank: rank)
		instance.save(flush: flush, insert: true)
		instance
	}

	static boolean remove(Member u, Rank r, boolean flush = false) {
		if (u == null || r == null) return false

		int rowCount = MemberRank.where {
			member == Member.load(u.id) &&
			rank == Rank.load(r.id)
		}.deleteAll()

		if (flush) { MemberRank.withSession { it.flush() } }

		rowCount > 0
	}

	static void removeAll(Member u, boolean flush = false) {
		if (u == null) return

		MemberRank.where {
			member == Member.load(u.id)
		}.deleteAll()

		if (flush) { MemberRank.withSession { it.flush() } }
	}

	static void removeAll(Rank r, boolean flush = false) {
		if (r == null) return

		MemberRank.where {
			rank == Rank.load(r.id)
		}.deleteAll()

		if (flush) { MemberRank.withSession { it.flush() } }
	}

	static constraints = {
		rank validator: { Rank r, MemberRank ur ->
			if (ur.member == null) return
			boolean existing = false
			MemberRank.withNewSession {
				existing = MemberRank.exists(ur.member.id, r.id)
			}
			if (existing) {
				return 'userRole.exists'
			}
		}
	}

	static mapping = {
		id composite: ['rank', 'member']
		version false
	}

    @Override
    public String toString() {
        return "MemberRank{" +
            "member=" + member +
            ", rank=" + rank +
            '}';
    }
}

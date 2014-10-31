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
//
//	static boolean exists(long memberId, long rankId) {
//		MemberRank.where {
//			member == Member.load(memberId) &&
//			rank == Rank.load(rankId)
//		}.count() > 0
//	}

	static constraints = {
//		rank validator: { Rank r, MemberRank ur ->
//			if (ur.member == null) return
//			boolean existing = false
//			MemberRank.withNewSession {
//				existing = MemberRank.exists(ur.member.id, r.id)
//			}
//			if (existing) {
//				return 'userRole.exists'
//			}
//		}
	}

	static mapping = {
		id composite: ['rank', 'member']
		version false
	}

    @Override
    public String toString() {
        return "$member / $rank"
    }
}

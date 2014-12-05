package bar.o.maitre

import groovy.transform.EqualsAndHashCode

@EqualsAndHashCode
class MemberRank implements Serializable {

    private static final long serialVersionUID = 1

    Member member
    Rank rank

    static belongsTo = [Member, Rank]

    static constraints = {
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

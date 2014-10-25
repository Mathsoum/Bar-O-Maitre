package bar.o.maitre

import grails.test.mixin.TestMixin
import grails.test.mixin.support.GrailsUnitTestMixin
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.support.GrailsUnitTestMixin} for usage instructions
 */
@TestMixin(GrailsUnitTestMixin)
class RankSpec extends Specification {

    def "test toString"() {
        given: "a rank"
        Rank rank = new Rank(
            authority: 'ROLE_ADMIN'
        )

        when: "getting related string"
        String string = rank.toString()

        then: "the string generated is correct"
        string == "ROLE_ADMIN"
    }

    def "test equals"(String authority1, String authority2, boolean areEquals) {
        given: "two ranks with same authority"
        Rank one = new Rank(
            authority: authority1
        )
        Rank other = new Rank(
            authority: authority2
        )

        expect: "these ranks to be equals (or not)"
        one.equals(other) == areEquals
        other.equals(one) == areEquals

        and: "equals method to be recursive"
        one.equals(one)
        other.equals(other)

        where:
        authority1          | authority2        | areEquals
        "ROLE_ADMIN"        | "ROLE_ADMIN"      | true
        "UNKNOWN"           | "ROLE_ADMIN"      | false
        new Integer(42)     | "ROLE_ADMIN"      | false
        null                | "ROLE_ADMIN"      | false
    }
}

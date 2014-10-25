package bar.o.maitre

import grails.test.mixin.TestFor
import groovy.time.TimeCategory
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(Member)
class MemberSpec extends Specification {

    def "test that member must be at least 18 years old"() {
        given: "a member"
        Member member = new Member(
            username: "member",
            password: "password",
            firstName: "John",
            lastName: "Smith",
            mail: "john.smith@domain.com",
        )

        when: "he is underage (<18)"
        member.birthDate = new Date()

        then: "the validation shouldn't pass"
        !member.validate()

        when: "he is underage (<18)"
        use(TimeCategory) {
            member.birthDate = new Date() - 20.years
        }

        then: "the validation shouldn't pass"
        member.validate()
    }

}

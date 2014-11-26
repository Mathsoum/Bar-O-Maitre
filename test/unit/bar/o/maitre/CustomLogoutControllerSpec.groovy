package bar.o.maitre

import grails.plugin.springsecurity.SpringSecurityUtils
import grails.test.mixin.TestFor
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.web.ControllerUnitTestMixin} for usage instructions
 */
@TestFor(CustomLogoutController)
class CustomLogoutControllerSpec extends Specification {
    void "test logout"() {
        when: "user logging out"
        controller.index()

        then: "no user must be logged in"
        response.redirectedUrl == SpringSecurityUtils.securityConfig.logout.filterProcessesUrl
    }
}

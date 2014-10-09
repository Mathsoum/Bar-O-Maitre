package bar.o.maitre

import grails.test.mixin.TestFor
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.services.ServiceUnitTestMixin} for usage instructions
 */
@TestFor(DummyService)
class DummyServiceSpec extends Specification {

  def setup() {
  }

  def cleanup() {
  }

  void "test something"() {
    expect: "Dummy test"
    true
  }
}

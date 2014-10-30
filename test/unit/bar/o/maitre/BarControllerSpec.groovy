package bar.o.maitre



import grails.test.mixin.*
import spock.lang.*

@TestFor(BarController)
@Mock(Bar)
class BarControllerSpec extends Specification {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

}

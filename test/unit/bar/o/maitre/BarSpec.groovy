package bar.o.maitre

import grails.test.mixin.TestFor
import spock.lang.Specification
import spock.lang.Unroll

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(Bar)
class BarSpec extends Specification {

    def setup() {

    }

    def cleanup() {
    }

    void "test creation of bar with correct attributes "() {
        given:"A correct bar"
        Bar bar = new Bar(barName: "Acuda",description:"Poisson" ,type:"Bar tapas" ,
                price:"tres cher", address: "3 rue du thon" )
        when:"The bar is save on database"
        bar.save()
        then:"The bar is not null"
        bar != null
    }
    @Unroll
    void "test Create of bar with incorrect attributes "() {
        given:"An incorrect bar"
        Bar barTest = new Bar()

        when:"The bar is save on database"
        barTest.save()
        then:""
        barTest == resultat

        where:
        barName | description | address | type | price | resultat
        "test" | "On decrit" | "address" | "Bar a vin" | "" | null
        "test" | "On decrit" | "address" | "Bar a vin" | null | null
        "test" | "On decrit" | "address" | "Bar a vin" | "  " | null


    }
}

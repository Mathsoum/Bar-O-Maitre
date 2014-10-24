package bar.o.maitre

import grails.test.mixin.TestFor
import spock.lang.Specification
import spock.lang.Unroll

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(Bar)
class BarSpec extends Specification {
    Bar barTest

    def setup() {
        barTest = new Bar()
    }

    def cleanup() {
    }

    void "test creation of bar with correct attributes "() {
        given:"A correct bar"
        barTest.barName = "Acuda"
        barTest.description = "Poisson"
        barTest.type = "Bar tapas"
        barTest.price = "very expensive"
        barTest.address = "3rd street tuna"

        when:"The bar is save on database"
        def res = barTest.save()

        then:"The bar is not null"
        res != null
    }

    @Unroll
    void "test Create of bar with incorrect attributes "() {
        given:"An incorrect bar"

        barTest.barName = name
        barTest.description = desc
        barTest.type = typ
        barTest.price = pric
        barTest.address = addr

        when:"The bar is save on database"
        def res = barTest.save()

        then:""
        res == resultat

        where:
        name | desc | addr | typ | pric | resultat
        "test" | "On decrit" | "address" | "Bar a vin" | "" | null
        "test" | "On decrit" | "address" | "Bar a vin" | null | null
        "test" | "On decrit" | "address" | "Bar a vin" | "  " | null


    }
}

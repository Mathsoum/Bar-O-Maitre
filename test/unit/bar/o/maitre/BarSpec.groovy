package bar.o.maitre

import grails.test.mixin.Mock
import grails.test.mixin.TestFor
import spock.lang.Specification
import spock.lang.Unroll

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(Bar)
@Mock([Member])
class BarSpec extends Specification {
    Bar barTest
    Member member = Mock(Member)
    def like

    def setup() {
        barTest = new Bar()
    }

    void "test creation of bar with correct attributes "() {
        given:"A correct bar"
        barTest.barName = "Acuda"
        barTest.description = "Poisson"
        barTest.type = "Bar tapas"
        barTest.price = "very expensive"
        barTest.address = "3rd street tuna"
        barTest.admin = new Member(username: 'admin', password: 'admin', firstName: 'toto', lastName:'tata', mail:'toto@gmail.com', birthDate: new Date("1985/10/10"))

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
        barTest.admin = adm
        when:"The bar is save on database"
        def res = barTest.save()

        then:""
        res == resultat

        where:
        name | desc | addr | typ | pric | adm | resultat
        "test" | "On decrit" | "address" | "Bar a vin" | "" | new Member(username: 'admin', password: 'admin', firstName: 'toto', lastName:'tata', mail:'toto@gmail.com', birthDate: new Date("1985/10/10")) | null
        "test" | "On decrit" | "address" | "Bar a vin" | null | new Member(username: 'admin', password: 'admin', firstName: 'toto', lastName:'tata', mail:'toto@gmail.com', birthDate: new Date("1985/10/10")) | null
        "test" | "On decrit" | "address" | "Bar a vin" | "  " | new Member(username: 'admin', password: 'admin', firstName: 'toto', lastName:'tata', mail:'toto@gmail.com', birthDate: new Date("1985/10/10")) | null
    }

    @Unroll
    void "test toString"() {
        given:"A bar"
        Bar barTest = new Bar(
                barName : "Acuda",
                description : "Poisson",
                address : "3rd street tuna",
                type : "Bar tapas",
                price : "very expensive",
                admin : new Member(username: 'admin', password: 'admin', firstName: 'toto', lastName:'tata', mail:'toto@gmail.com', birthDate: new Date("1985/10/10"))
        )

        when:"The bar's name"
        def res = barTest.toString()

        then:"Acuda"
        res == "Acuda"
    }

    def "test likers"() {
        given: "a Bar"
        barTest.barName = "Acuda"
        barTest.description = "Poisson"
        barTest.type = "Bar tapas"
        barTest.price = "very expensive"
        barTest.address = "3rd street tuna"
        barTest.admin = new Member(username: 'admin', password: 'admin', firstName: 'toto', lastName:'tata', mail:'toto@gmail.com', birthDate: new Date("1985/10/10"))


        // Member member1 = new Member()Member member2 = Mock(Member)
        member.validate()

        mockDomain(Member,[[member:member]])

        when: "getting rank"
        barTest.likers.add(member)
        like = barTest.getNbLike()


        then: "the number of rank returned is correct"
        like.size() == 1
    }
}

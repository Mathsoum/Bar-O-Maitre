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
    Member member = Mock(Member)

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

    /*@Unroll
    void "test getNbLikes 0 like"() {
        given:"A bar with 0 like"
        Bar barTest = new Bar(
                barName : "Acuda",
                description : "Poisson",
                address : "3rd street tuna",
                type : "Bar tapas",
                price : "very expensive",
                admin : new Member(username: 'admin', password: 'admin', firstName: 'toto', lastName:'tata', mail:'toto@gmail.com', birthDate: new Date("1985/10/10"))
        )
        barTest.validate()
        barTest .save(flush: true)

        when:"The number of like is asked"
        def res = barTest.getNbLike()

        then:"0 like"
        res == 0
    }

    @Unroll
    void "test getNbLikes 1 like"() {
        given:"A bar with 1 like"
        Bar barTest = new Bar(
                barName : "Acuda",
                description : "Poisson",
                address : "3rd street tuna",
                type : "Bar tapas",
                price : "very expensive",
                admin : new Member(username: 'admin', password: 'admin', firstName: 'toto', lastName:'tata', mail:'toto@gmail.com', birthDate: new Date("1985/10/10"))
        )
        barTest.validate()
        barTest .save(flush: true)
        member.validate()
        //barTest.addToLikers(member)
        barTest.getLikers().add(member)

        when:"The number of like is asked"
        def res = barTest.getNbLike()

        then:"1 like"
        res == 1
    }*/

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
}

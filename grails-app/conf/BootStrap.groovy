import bar.o.maitre.Bar
import bar.o.maitre.Member
import bar.o.maitre.MemberRank
import bar.o.maitre.MemberRankService
import bar.o.maitre.Rank

class BootStrap {

    MemberRankService memberRankService

    def init = { servletContext ->

        def adminRole = new Rank(authority: 'ROLE_ADMIN').save(flush: true)
        def expertRole =  new Rank(authority: 'ROLE_EXPERT').save(flush: true)
        def confirmedRole = new Rank(authority: 'ROLE_CONFIRMED').save(flush: true)
        def adeptRole = new Rank(authority: 'ROLE_ADEPT').save(flush: true)
        def userRole = new Rank(authority: 'ROLE_USER').save(flush: true)


        def testAdmin = new Member(username: 'admin', password: 'admin', firstName: 'toto', lastName:'tata', mail:'toto@gmail.com', birthDate: new Date("1985/10/10"), reputation: 50)
        testAdmin.save(flush: true, failOnError: true)

        def testUser = new Member(username: 'user', password: 'user', firstName: 'titi', lastName:'tutu', mail:'titi@gmail.com',birthDate: new Date("1988/10/10"), reputation: 4)
        testUser.save(flush: true, failOnError: true)

        memberRankService.create(testAdmin, adminRole, true)
        memberRankService.create(testUser, userRole, true)

        def testBar = new Bar(barName: "Babar", description: "Bilbo", type: "vador", address: "Loin", price: "cher", admin: testAdmin)
        testBar.save(flush: true, failOnError: true)
    }

    def destroy = {
    }
}

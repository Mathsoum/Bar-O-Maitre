import bar.o.maitre.Member
import bar.o.maitre.MemberRank
import bar.o.maitre.MemberRankService
import bar.o.maitre.Rank

class BootStrap {

    MemberRankService memberRankService

    def init = { servletContext ->

        def admin = new Rank(authority: 'ROLE_ADMIN').save(flush: true)
        def expert = new Rank(authority: 'ROLE_EXPERT').save(flush: true)
        def confirme = new Rank(authority: 'ROLE_CONFIRME').save(flush: true)
        def adepte = new Rank(authority: 'ROLE_ADEPTE').save(flush: true)
        def membre = new Rank(authority: 'ROLE_USER').save(flush: true)


        def testAdmin = new Member(username: 'admin', password: 'admin', firstName: 'toto', lastName:'tata', mail:'toto@gmail.com', birthDate: new Date("1985/10/10"))
        testAdmin.save(flush: true, failOnError: true)

        def testUser = new Member(username: 'user', password: 'user', firstName: 'titi', lastName:'tutu', mail:'titi@gmail.com',birthDate: new Date("1988/10/10"))
        testUser.save(flush: true, failOnError: true)

        memberRankService.create(testAdmin, admin, true)
        memberRankService.create(testUser, membre, true)
    }

    def destroy = {
    }
}

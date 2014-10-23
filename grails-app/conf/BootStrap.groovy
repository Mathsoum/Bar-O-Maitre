import bar.o.maitre.Member
import bar.o.maitre.MemberRank
import bar.o.maitre.Rank

class BootStrap {

    def init = { servletContext ->

        def adminRole = new Rank(authority: 'ROLE_ADMIN').save(flush: true)
        def userRole = new Rank(authority: 'ROLE_USER').save(flush: true)

        def testAdmin = new Member(username: 'admin', password: 'admin')
        testAdmin.save(flush: true)

        def testUser = new Member(username: 'user', password: 'user')
        testUser.save(flush: true)

        MemberRank.create testAdmin, adminRole, true
        MemberRank.create testUser, userRole, true
    }

    def destroy = {
    }
}

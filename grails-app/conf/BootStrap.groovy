
class BootStrap {

    def init = { servletContext ->

        def adminRole = new Rank(authority: 'ROLE_ADMIN').save(flush: true)
        def userRole = new Rank(authority: 'ROLE_USER').save(flush: true)

        def testAdmin = new Member(username: 'admin', password: 'admin', firstName: 'toto', lastName:'tata', mail:'toto@gmail.com', birthDate: new Date("1985/10/10"))
        testAdmin.save(flush: true, failOnError: true)

        def testUser = new Member(username: 'user', password: 'user', firstName: 'titi', lastName:'tutu', mail:'titi@gmail.com',birthDate: new Date("1988/10/10"))
        testUser.save(flush: true, failOnError: true)

        MemberRank.create testAdmin, adminRole, true
        MemberRank.create testUser, userRole, true
    }

    def destroy = {
    }
}

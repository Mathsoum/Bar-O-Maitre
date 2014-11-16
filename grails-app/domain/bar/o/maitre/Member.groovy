package bar.o.maitre

import grails.plugin.springsecurity.acl.AclClass
import grails.plugin.springsecurity.acl.AclEntry
import grails.plugin.springsecurity.acl.AclObjectIdentity
import grails.plugin.springsecurity.acl.AclSid
import groovy.time.TimeCategory
import org.hibernate.Transaction
import org.springframework.security.acls.domain.BasePermission

class Member {

	transient springSecurityService

	String username
	String password

    String firstName
    String lastName
    String mail
    Date birthDate

	boolean enabled = true
	boolean accountExpired
	boolean accountLocked
	boolean passwordExpired

    //Friendship relation
    static hasMany = [friends: Member, bars : Bar]

	static transients = ['springSecurityService']
	static constraints = {
		username blank: false, unique: true
		password blank: false
        firstName blank: false, nullable: false
        lastName blank: false, nullable: false
        mail email:true, unique: true

        use(TimeCategory) {
            birthDate max: (new Date() - 18.years)
        }
	}

	static mapping = {
		password column: '`password`'
	}

	Set<Rank> getAuthorities() {
		MemberRank.findAllByMember(this).collect { it.rank }
	}

    def beforeInsert() {
        encodePassword()
    }

    def afterInsert() {
        AclSid memberSid = new AclSid(principal: true, sid: username)
        memberSid.save()

        AclObjectIdentity aclObjectIdentity =
            new AclObjectIdentity(
                aclClass: AclClass.findByClassName(Member.class.getName()),
                objectId: id,
                owner: memberSid,
                entriesInheriting: false
            )
        aclObjectIdentity.save(failOnError: true)

        AclEntry aclEntry = new AclEntry(
            aclObjectIdentity: aclObjectIdentity,
            sid: memberSid,
            aceOrder: 0,
            mask: BasePermission.ADMINISTRATION.mask,
            granting: false,
            auditSuccess: false,
            auditFailure: false
        )
        aclEntry.save(failOnError: true)
    }

	def beforeUpdate() {
		if (isDirty('password')) {
			encodePassword()
		}
	}

	protected void encodePassword() {
		password = springSecurityService?.passwordEncoder ? springSecurityService.encodePassword(password) : password
	}

    boolean equals(o) {
        if (this.is(o)) return true
        if (getClass() != o.class) return false

        Member member = (Member) o

        if (mail != member.mail) return false
        if (username != member.username) return false

        return true
    }


    @Override
    public String toString() {
        return username;
    }
}

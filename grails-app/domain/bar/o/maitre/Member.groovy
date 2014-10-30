package bar.o.maitre

import groovy.time.TimeCategory

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
        return "$username ($mail)"
    }
}

package bar.o.maitre
import groovy.time.*


class User {
    String nickname
    String firstName
    String lastName
    String mail
    Date birthDate

    static constraints = {
        nickname blank: false, nullable: false
        firstName blank: false, nullable: false
        lastName blank: false, nullable: false
        mail email:true

        use(TimeCategory) {
            birthDate max: (new Date() - 18.years)
        }

        nickname validator: {
           User.findByNickname(it) == null
        }

        mail validator: {
            User.findByMail(it) == null
        }
    }
}

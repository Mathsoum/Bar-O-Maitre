package bar.o.maitre

class Bar {
    String barName
    String description
    String address
    String type
    String price
    static hasMany = [likers : Member]

    Member admin

    static constraints = {
        barName blank: false, nullable : false
        description blank: false, nullable : false
        address blank: false, nullable : false
        type blank: false, nullable : false
        price blank: false, nullable : false
        admin blank: false, nullable : false
    }

    String getNbLike(){
       likers.size()
    }
}

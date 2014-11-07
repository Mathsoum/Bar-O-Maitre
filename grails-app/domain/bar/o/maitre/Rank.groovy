package bar.o.maitre
import groovy.transform.EqualsAndHashCode

@EqualsAndHashCode
class Rank {

	String authority

	static mapping = {
		cache true
	}

	static constraints = {
		authority blank: false, unique: true
	}

    @Override
    public String toString() {
        return "$authority"
    }
}

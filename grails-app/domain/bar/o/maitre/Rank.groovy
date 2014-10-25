package bar.o.maitre

class Rank {

	String authority

	static mapping = {
		cache true
	}

	static constraints = {
		authority blank: false, unique: true
	}

    boolean equals(o) {
        if (this.is(o)) return true
        if (getClass() != o.class) return false

        Rank rank = (Rank) o

        if (authority != rank.authority) return false

        return true
    }

    int hashCode() {
        return authority.hashCode()
    }

    @Override
    public String toString() {
        return "Rank{" +
            "authority='" + authority + '\'' +
            '}';
    }
}

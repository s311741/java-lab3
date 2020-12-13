package scene;

public final class Flag extends Thing {
	public Flag (Environment e, String id) { super(e, id); }
	public Flag (Environment e) { this(e, "flag"); }

	@Override
	public String toString () {
		return (this.env.isWindy() ? "waving " : "") + "flag";
	}

	@Override
	public boolean equals (Object other) {
		return other instanceof Flag;
	}
}

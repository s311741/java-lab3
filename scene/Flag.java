package scene;

public final class Flag extends Thing {
	public Flag (IEnvironment e) {
		super(e);
	}

	@Override
	public String toString () {
		return (this.env.isWindy() ? "waving " : "") + "flag";
	}
}

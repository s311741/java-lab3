package scene;

public final class Jessamine extends Thing {
	public Jessamine (IEnvironment e) {
		super(e);
	}

	@Override
	public String toString () {
		IEnvironment.Season s = this.env.season();
		return (s == IEnvironment.Season.SPRING || s == IEnvironment.Season.SUMMER
				? "blossoming " : "")
			+ "jessamine";
	}
}

package scene;

public final class Jessamine extends Thing {
	public Jessamine (Environment e, String id) { super(e, id); }
	public Jessamine (Environment e) { this(e, "jessamine"); }

	@Override
	public String toString () {
		Environment.Season s = this.env.season();
		boolean isBlossoming = (s == Environment.Season.SPRING || s == Environment.Season.SUMMER);
		return (isBlossoming ? "blossoming " : "") + "jessamine";
	}

	@Override
	public boolean equals (Object other) {
		return other instanceof Jessamine;
	}
}

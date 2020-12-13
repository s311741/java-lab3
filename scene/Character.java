package scene;

public final class Character extends Thing {
	private Thing proximity;

	public Character (IEnvironment e, Thing initialProximity) {
		super(e);
		this.proximity = initialProximity;
	}

	public Character (IEnvironment e) {
		this(e, null);
	}

	public void walkTo (Thing t, float walkSpeed) {
		if (t.getEnvironment() != this.getEnvironment()) {
			throw new RuntimeException("Tried to walk towards somewhere in another environment");
		}
		if (t == this) {
			throw new RuntimeException("Tried to walk towards themselves");
		}
		if (t == null) {
			throw new RuntimeException("Tried to walk towards nowhere");
		}

		if (this.proximity == t) {
			// Already there
			return;
		}

		this.proximity = t;

		// Walking somewhere creates vibration over there
		this.env.createVibrationAt(this.proximity, (float) Math.pow(walkSpeed, 2.0));
	}

	public Thing nearWhat () {
		return this.proximity;
	}

	@Override
	public String toString () {
		String r = "Main character";
		if (this.proximity != null) {
			r += ", who is near " + this.proximity.toString();
		}
		return r;
	}

	@Override
	public int hashCode () {
		return super.hashCode() ^ this.proximity.hashCode();
	}

	@Override
	public boolean equals (Object other) {
		if (!(other instanceof Character) || other.hashCode() != this.hashCode()) {
			return false;
		}
		return ((Character) other).nearWhat().equals(this.nearWhat());
	}
}

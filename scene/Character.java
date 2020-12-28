package scene;

import java.util.HashMap;

public final class Character extends Thing {
	private Thing proximity;

	public Character (Environment e, String id, Thing initialProximity) {
		super(e, id);
		this.proximity = initialProximity;
	}

	public Character (Environment e, String id) { this(e, id, null); }
	public Character (Environment e, Thing ip) { this(e, "char", ip); }
	public Character (Environment e) { this(e, "char", null); }

	public void walkTo (Thing t, float walkSpeed) {
		if (t == null || t == this
		 || t.getEnvironment() != this.getEnvironment()
		 || t == this.proximity) {
			throw new IllegalArgumentException( "Character cannot walk towards " + t.toString());
		}

		this.proximity = t;

		// Walking somewhere creates vibration over there
		this.env.createVibrationAt(this.proximity, (float) Math.pow(walkSpeed, 2.0));
	}

	public Thing nearWhat () {
		return this.proximity;
	}

	@Override
	protected void initializeFromSettings (HashMap<String, String> settings) {
		if (settings.containsKey("initially-near")) {
			Thing near = this.getEnvironment()
				.getThingByID(settings.get("initially-near"));
			this.walkTo(near, 0.0f);
		}
	}

	@Override
	public String toString () {
		String result = "Main character";
		if (this.proximity != null) {
			result += ", who is near " + this.proximity.toString();
		}
		return result;
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

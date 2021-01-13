package scene;

import java.util.HashMap;

public final class Character extends Thing {
	private Thing proximity;

	public Character (Environment e, String id, Thing initialProximity) {
		super(e, id);
		this.proximity = initialProximity;

		this.addSetting("initially-near", (String s) -> {
			Thing near = this.getEnvironment().getThingByID(s);
			this.proximity = near;
		});
	}

	public Character (Environment e, String id) { this(e, id, null); }
	public Character (Environment e, Thing initialProximity) { this(e, "char", initialProximity); }
	public Character (Environment e) { this(e, "char", null); }

	public void walkTo (Thing target, float walkSpeed) {
		if (target == null || target == this
		 || target.getEnvironment() != this.getEnvironment()
		 || target == this.proximity) {
			throw new IllegalArgumentException("Character cannot walk towards " + target.toString());
		}
		this.proximity = target;

		// Walking somewhere creates vibration over there
		this.env.createVibrationAt(this.proximity, (float) Math.pow(walkSpeed, 2.0));

		Character policeman = (Character) this.getEnvironment().getThingByID("policeman");
		if (policeman != null && policeman != this && policeman.nearWhat() == target) {
			// A policeman is there; we are abducted
			throw new AbductedAmidstWalkingException(this, policeman, target);
		}
	}

	public Thing nearWhat () {
		return this.proximity;
	}

	@Override
	public String toString () {
		String result = "character \'" + this.getID() + '\'';
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

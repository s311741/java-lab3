package scene;

public final class House extends Thing {
	private Thing roofAttachment = null; // What's atop the roof

	public House (IEnvironment e) {
		super(e);
	}

	public void setRoofAttachment (Thing t) {
		this.roofAttachment = t;
	}

	public Thing getRoofAttachment () {
		return this.roofAttachment;
	}

	@Override
	public String toString () {
		String result = "house";
		if (roofAttachment != null)
			result += " with " + roofAttachment.toString() + " atop its roof";
		return result;
	}

	@Override
	public int hashCode () {
		return super.hashCode() ^ this.roofAttachment.hashCode();
	}

	@Override
	public boolean equals (Object other) {
		if (!(other instanceof House) || other.hashCode() != this.hashCode()) {
			return false;
		}
		return ((House) other).getRoofAttachment().equals(this.getRoofAttachment());
	}
}

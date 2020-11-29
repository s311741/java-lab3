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
	public int hashCode () {
		return this.getClass().hashCode() ^ this.roofAttachment.hashCode();
	}

	@Override
	public String toString () {
		String r = "house";
		if (roofAttachment != null)
			r += " with " + roofAttachment.toString() + " atop its roof";
		return r;
	}
}

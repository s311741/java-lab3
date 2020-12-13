package scene;

public final class Grass extends Thing implements IColored {

	public Grass (Environment e, String id) { super(e, id); }
	public Grass (Environment e) { this(e, "grass"); }

	public boolean isWithered () {
		Environment.Season s = this.env.season();
		return s == Environment.Season.SUMMER || s == Environment.Season.SPRING;
	}

	@Override
	public Color getColor () {
		return this.isWithered() ? Color.GREY : Color.GREEN;
	}

	@Override
	public void setColor (Color c) {
		throw new RuntimeException("Tried to set color of grass directly");
	}

	@Override
	public String toString () {
		return this.getColor().toString() + " grass";
	}

	@Override
	public int hashCode () {
		int hash = super.hashCode();
		if (this.isWithered()) {
			hash += 257;
		}
		return hash;
	}
}

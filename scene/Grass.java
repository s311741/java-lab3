package scene;

public final class Grass extends Thing implements IColored {
	public Grass (IEnvironment env) {
		super(env);
	}

	public boolean isWithered () {
		IEnvironment.Season s = this.env.season();
		return s == IEnvironment.Season.SUMMER || s == IEnvironment.Season.SPRING;
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
		int r = 271;
		if (this.isWithered()) {
			r += 257;
		}
		return this.getClass().hashCode() ^ r;
	}
}

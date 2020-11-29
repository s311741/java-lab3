package scene;

public final class Grass extends Thing implements IColored {
	boolean isWithered;

	public Grass (IEnvironment env, boolean isWithered) {
		super(env);
		this.isWithered = isWithered;
	}

	@Override
	public Color getColor () {
		return this.isWithered ? Color.GREY : Color.GREEN;
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
		if (this.isWithered) {
			r += 257;
		}
		return this.getClass().hashCode() ^ r;
	}
}

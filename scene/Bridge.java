package scene;

public final class Bridge extends Thing implements IColored {
	private Color color;

	public Bridge (IEnvironment e) {
		super(e);
	}

	@Override
	public String toString () {
		return this.getColor().toString() + " bridge";
	}

	@Override
	public void setColor (Color c) {
		this.color = c;
	}

	@Override
	public Color getColor () {
		return this.color;
	}

	@Override
	public int hashCode () {
		return super.hashCode() ^ this.color.hashCode();
	}
}

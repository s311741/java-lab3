package scene;

import java.util.HashMap;
import java.util.Map;

public final class Bridge extends Thing implements IColored {
	private Color color;

	public Bridge (Environment e, String id) {
		super(e, id);
		this.addSetting("color", (String s) -> { this.setColor(Color.valueOf(s)); });
	}
	public Bridge (Environment e) { this(e, "bridge"); }

	@Override
	public void setColor (Color c) {
		this.color = c;
	}

	@Override
	public Color getColor () {
		return this.color;
	}

	@Override
	public String toString () {
		return this.getColor().toString() + " bridge";
	}

	@Override
	public int hashCode () {
		return super.hashCode() ^ this.color.hashCode();
	}

	@Override
	public boolean equals (Object other) {
		if (!(other instanceof Bridge) || other.hashCode() != this.hashCode()) {
			return false;
		}
		return ((Bridge) other).getColor() == this.getColor();
	}
}

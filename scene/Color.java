package scene;

import java.io.Serializable;

public enum Color {
	BLACK,
	WHITE,
	GREEN,
	BLUE,
	GREY;

	@Override
	public String toString () {
		return this.name().toLowerCase();
	}
}

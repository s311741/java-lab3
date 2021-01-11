package scene;

public interface IColored {
	public static enum Color {
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

	Color getColor ();
	void setColor (Color c);
}

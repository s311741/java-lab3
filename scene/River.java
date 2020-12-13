package scene;

public final class River extends Thing {
	public River (Environment e, String id) { super(e, id); }
	public River (Environment e) { this(e, "river"); }

	@Override
	public String toString () {
		String s = "";
		if (this.env.vibrationSource() == this && this.env.vibrationAmplitude() > 50.0f) {
			s += "storming ";
		}
		return s + "river";
	}

	@Override
	public boolean equals (Object other) {
		return other instanceof River;
	}
}

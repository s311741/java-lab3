package scene;

public final class River extends Thing {
	public River (IEnvironment e) {
		super(e);
	}

	@Override
	public String toString () {
		String s = "";
		if (this.env.vibrationAmplitude() > 50.0f) {
			s += "storming ";
		}
		return s + "river";
	}
}

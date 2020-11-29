package scene;

public abstract class Thing {
	protected IEnvironment env = null;
	public boolean isRootElement;

	public Thing (IEnvironment env) {
		this.env = env;
		this.env.addThing(this);
		this.isRootElement = true;
	}

	public IEnvironment getEnvironment () {
		return this.env;
	}

	@Override
	public boolean equals (Object other) {
		return this.getClass().hashCode() == other.getClass().hashCode()
			&& this.hashCode() == other.hashCode();
	}

	@Override
	public int hashCode () {
		return this.getClass().hashCode() ^ (this.isRootElement ? 1 : 0);
	}
}

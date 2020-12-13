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
	public int hashCode () {
		return super.hashCode() ^ this.getClass().hashCode();
	}
}

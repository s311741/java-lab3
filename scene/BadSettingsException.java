package scene;

public class BadSettingsException extends Exception {
	public BadSettingsException (String noSuchClass) {
		super('\'' + noSuchClass + "\' is not a Thing subclass");
	}

	public BadSettingsException (String setting, Class<? extends Thing> thingType) {
		super('\'' + setting + "\' is not a setting in " + thingType.getName());
	}
}

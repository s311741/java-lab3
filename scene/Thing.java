package scene;

import java.io.BufferedReader;
import java.io.IOException;
import java.lang.ClassLoader;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;

public abstract class Thing {
	protected Environment env = null;
	public boolean isRootElement;
	protected String id;

	public Thing (Environment env, String id) {
		this.id = id;
		this.env = env;
		this.isRootElement = true;

		this.env.addThing(this);
	}

	public final Environment getEnvironment () {
		return this.env;
	}

	public final String getID () {
		return this.id;
	}

	static HashMap<String, Class> thingSubclasses;
	static {
		class ClassMapPopulator {
			private HashMap<String, Class> targetMap;
			public ClassMapPopulator (HashMap<String, Class> target) {
				this.targetMap = target;
			}
			public void add (String name) {
				String packageName = Thing.class.getPackage().toString();
				packageName = packageName.substring(8, packageName.length()); // remove 'package '
				final String realClassName = packageName + "." + name;
				try {
					final Class c = Thing.class.getClassLoader().loadClass(realClassName);
					if (c.getSuperclass() != Thing.class) {
						System.err.println("Class" + realClassName + " is not a subclass of Thing");
						System.exit(1);
					}
					targetMap.put(name, c);
				} catch (ClassNotFoundException e) {
					System.err.println("Class " + realClassName + " not found");
					System.exit(1);
				}
			}
		}

		ClassMapPopulator pop = new ClassMapPopulator(thingSubclasses = new HashMap<String, Class>());

		pop.add("Bridge");
		pop.add("Character");
		pop.add("Flag");
		pop.add("Grass");
		pop.add("House");
		pop.add("Jessamine");
		pop.add("River");
	}

	public static final Thing newFromFile (Environment env, BufferedReader br) throws BadSettingsException {
		try {
			String line = br.readLine();
			String[] tokens = line.split(" ");
			String classname = tokens[0];
			String id = tokens[1];

			Class c = thingSubclasses.get(classname);
			if (c == null) {
				throw new BadSettingsException(classname);
			}
			Constructor ctor = c.getConstructor(Environment.class, String.class);

			HashMap<String, String> settings = new HashMap<String, String>();
			while (true) {
				line = br.readLine();
				if (line == null || line.isEmpty()) {
					break;
				}
				tokens = line.split("=");

				if (tokens.length < 2) {
					continue;
				}

				settings.put(tokens[0], tokens[1]);
				if (!br.ready()) {
					break;
				}
			}

			Thing t = (Thing) ctor.newInstance(env, id);
			t.initializeFromSettings(settings);

			return t;
		}
		catch (NoSuchMethodException e) { e.printStackTrace(); }
		catch (InstantiationException e) { e.printStackTrace(); }
		catch (InvocationTargetException e) { e.printStackTrace(); }
		catch (IllegalAccessException e) { e.printStackTrace(); }
		catch (IOException e) { e.printStackTrace(); }

		System.exit(1);
		// Tech like __attribute__((noreturn)) isn't quite there yet I guess
		return null;
	}


	@FunctionalInterface protected interface ISettingApply { void apply (String value); }
	private HashMap<String, ISettingApply> validSettings = new HashMap<String, ISettingApply>();

	protected final void addSetting (String s, ISettingApply apply) {
		this.validSettings.put(s, apply);
	}

	private void initializeFromSettings (HashMap<String, String> settings) throws BadSettingsException {
		for (HashMap.Entry<String, String> setting: settings.entrySet()) {
			String key = setting.getKey();
			if (!this.validSettings.containsKey(key)) {
				throw new BadSettingsException(key, this.getClass());
			}
			this.validSettings.get(key).apply(setting.getValue());
		}
	}


	@Override
	public int hashCode () {
		return this.getClass().hashCode() ^ this.id.hashCode();
	}
}

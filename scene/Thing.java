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


	static HashMap<String, Class> derived;
	private static void registerThingClass (String name) {
		try {
			derived.put(name, Thing.class.getClassLoader().loadClass("scene." + name));
		} catch (ClassNotFoundException e) {
			System.err.println("Class " + name + " not found");
			System.exit(1);
		}
	}
	static {
		derived = new HashMap<String, Class>();
		registerThingClass("Bridge");
		registerThingClass("Character");
		registerThingClass("Flag");
		registerThingClass("Grass");
		registerThingClass("House");
		registerThingClass("Jessamine");
		registerThingClass("River");
	}

	public static final Thing newFromFile (Environment env, BufferedReader br) throws IOException {
		try {
			String line = br.readLine();
			String[] tokens = line.split(" ");
			String classname = tokens[0];
			String id = tokens[1];

			Class c = derived.get(classname);
			Constructor ctor = c.getConstructor(Environment.class, String.class);

			HashMap<String, String> settings = new HashMap<String, String>();
			while (true) {
				line = br.readLine();
				if (line == null || line.isEmpty()) {
					break;
				}
				tokens = line.split("=");
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
		System.exit(1);
		// Tech like __attribute__((noreturn)) isn't quite there yet I guess
		return null;
	}

	protected void initializeFromSettings (HashMap<String, String> settings) { }


	@Override
	public int hashCode () {
		return super.hashCode() ^ this.getClass().hashCode() ^ this.id.hashCode();
	}
}

package scene;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.HashMap;

public final class Environment implements IEnvironment {
	private HashMap<String, Thing> contents;

	private float vibrAmp;
	private Thing vibrSrc;

	public Environment () {
		this.contents = new HashMap<String, Thing>();
		this.vibrAmp = 0.0f;
		this.vibrSrc = null;
	}

	public Environment (BufferedReader br) {
		this();
		try {
			while (br.ready()) {
				Thing.newFromFile(this, br);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void addThing (Thing t) {
		IEnvironment e = t.getEnvironment();
		if (e != null && e != this) {
			throw new RuntimeException("Tried to add something to several environments at once");
		}

		if (this.contents.containsKey(t.getID())) {
			throw new RuntimeException("Tried to add two objects with same IDs");
		}
		this.contents.put(t.getID(), t);
	}

	public void print (PrintStream s) {
		s.println("Environment contains:");
		for (Thing t: this.contents.values()) {
			if (t.isRootElement) {
				s.println("- " + t.toString());
			}
		}
	}

	public Thing getThingByID (String id) {
		if (this.contents.containsKey(id)) {
			return this.contents.get(id);
		}
		return null;
	}

	@Override
	public boolean isWindy () {
		return true;
	}

	@Override
	public float daytime () {
		return 12.0f;
	}

	@Override
	public Season season () {
		return Season.SUMMER;
	}

	@Override
	public void createVibrationAt (Thing where, float amplitude) {
		this.vibrSrc = where;
		this.vibrAmp = amplitude;
	}

	@Override
	public float vibrationAmplitude () {
		return this.vibrAmp;
	}

	@Override
	public Thing vibrationSource () {
		return this.vibrSrc;
	}

	@Override
	public int hashCode () {
		int hash = this.getClass().hashCode() ^ this.contents.size();
		for (Thing t: this.contents.values()) {
			hash ^= t.hashCode() + 0x9e3779b9 + (hash << 6) + (hash >> 2);
		}
		return hash;
	}

	@Override
	public boolean equals (Object other) {
		if (!(other instanceof Environment) || other.hashCode() != this.hashCode()) {
			return false;
		}

		Environment otherEnv = (Environment) other;
		if (otherEnv.contents.size() != this.contents.size()) {
			return false;
		}

		for (int i = 0; i < this.contents.size(); i++) {
			if (!this.contents.get(i).equals(otherEnv.contents.get(i))) {
				return false;
			}
		}

		return this.vibrationAmplitude() == otherEnv.vibrationAmplitude()
			&& this.vibrationSource().equals(otherEnv.vibrationSource());
	}
}

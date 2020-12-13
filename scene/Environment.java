package scene;

import java.util.ArrayList;
import java.io.PrintStream;

public final class Environment implements IEnvironment {
	private ArrayList<Thing> contents;

	private float vibrAmp;
	private Thing vibrSrc;

	public Environment () {
		this.contents = new ArrayList<Thing>();
		this.vibrAmp = 0.0f;
		this.vibrSrc = null;
	}

	@Override
	public void addThing (Thing t) {
		IEnvironment e = t.getEnvironment();
		if (e != null && e != this) {
			throw new RuntimeException("Tried to add something to several environments at once");
		}
		this.contents.add(t);
	}

	public void print (PrintStream s) {
		s.println("Environment contains:");
		for (Thing t: this.contents) {
			if (t.isRootElement) {
				s.println("- " + t.toString());
			}
		}
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
		for (Thing t: this.contents) {
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

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
	public int hashCode () {
		int hash = this.getClass().hashCode() ^ this.contents.size();
		for (Thing t: this.contents) {
			hash ^= t.hashCode() + 0x9e3779b9 + (hash << 6) + (hash >> 2);
		}
		return hash;
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
}

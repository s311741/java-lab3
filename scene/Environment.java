package scene;

import java.util.ArrayList;
import java.io.PrintStream;

public final class Environment implements IEnvironment {
	private ArrayList<Thing> contents;
	private float amplitude;

	public Environment () {
		this.contents = new ArrayList<Thing>();
		this.amplitude = 0.0f;
	}

	@Override
	public void addThing (Thing t) {
		this.contents.add(t);
		if (t.getEnvironment() != this) {
			throw new RuntimeException("Tried to add a building to several environments at once");
		}
	}

	public void startWalkingFastHere () {
		this.amplitude += 80.0;
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
			hash ^= t.hashCode() + (hash << 6) + (hash >> 2);
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
	public float vibrationAmplitude () {
		return this.amplitude;
	}
}

package scene;

public interface IEnvironment {
	void addThing (Thing t);

	boolean isWindy ();
	float daytime ();
	float vibrationAmplitude ();

	public enum Season {
		WINTER,
		SPRING,
		SUMMER,
		AUTUMN
	}
	Season season ();
}

package scene;

public interface IEnvironment {
	void addThing (Thing t);

	boolean isWindy ();
	float daytime ();

	float vibrationAmplitude ();
	Thing vibrationSource ();
	void createVibrationAt (Thing where, float amplitude);

	public enum Season {
		WINTER,
		SPRING,
		SUMMER,
		AUTUMN
	}
	Season season ();
}

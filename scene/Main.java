package scene;

import java.io.PrintStream;

public class Main {
	public static void main (String[] args) {
		Environment env = new Environment();

		Flag flag = new Flag(env);
		flag.isRootElement = false;

		House house = new House(env);
		house.setRoofAttachment(flag);

		Grass grass = new Grass(env);
		Jessamine jess = new Jessamine(env);

		Bridge bridge = new Bridge(env);
		bridge.setColor(Color.BLUE);

		River river = new River(env);

		Character ch = new Character(env, grass);

		PrintStream s = System.out;
		s.println("Before our arrival:\n");
		env.print(s);

		ch.walkTo(river, 10.0f);

		s.println("\nAfter our arrival:\n");
		env.print(s);
	}
}

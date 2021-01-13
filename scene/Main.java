package scene;

import java.io.PrintStream;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileNotFoundException;

public class Main {
	public static void main (String[] args) {
		final Environment env;
		try {
			env = new Environment(new BufferedReader(new FileReader("env.ini")));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			System.exit(1);
			return;
		}

		System.out.println("Before our arrival:\n");
		env.print(System.out);

		final Character policeman = (Character) env.getThingByID("policeman");
		final Character mainCharacter = (Character) env.getThingByID("snufkin");

		try {
			mainCharacter.walkTo(env.getThingByID("river"), 10.0f);
		} catch (AbductedAmidstWalkingException e) {
			System.out.println();
			System.out.println(e.getMessage());
		} finally {
			System.out.println("\nAfter our arrival:\n");
			env.print(System.out);
		}
	}
}

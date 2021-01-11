package scene;

import java.io.PrintStream;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileNotFoundException;

public class Main {
	public static void main (String[] args) {
		Environment env;
		try {
			env = new Environment(new BufferedReader(new FileReader("env.ini")));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			System.exit(1);
			return;
		}

		PrintStream s = System.out;
		s.println("Before our arrival:\n");
		env.print(s);

		Character policeman = (Character) env.getThingByID("policeman");
		Character mainCharacter = (Character) env.getThingByID("snufkin");

		try {
			mainCharacter.walkTo(env.getThingByID("river"), 10.0f);
		} catch (AbductedAmidstWalkingException e) {
			s.println();
			s.println(e.getMessage());
		} finally {
			s.println("\nAfter our arrival:\n");
			env.print(s);
		}
	}
}

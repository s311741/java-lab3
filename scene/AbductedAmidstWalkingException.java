package scene;

public class AbductedAmidstWalkingException extends RuntimeException {
	public AbductedAmidstWalkingException (Character who, Character byWhom, Thing where) {
		super("Oh no! Character \'" + who.getID() +
		      "\' was abducted by \'" + byWhom.getID() +
		      "\' after arriving at " + where.toString() + '!');
	}
}

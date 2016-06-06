package eg.edu.guc.yugioh.exceptions;

@SuppressWarnings("serial")
public class MonsterMultipleAttackException extends RuntimeException {

	public MonsterMultipleAttackException()
	{
		super();
	}
	public MonsterMultipleAttackException(String m){
	super(m);
}
}

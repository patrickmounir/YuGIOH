package eg.edu.guc.yugioh.exceptions;

@SuppressWarnings("serial")
public class MultipleMonsterAdditionException extends RuntimeException {
public MultipleMonsterAdditionException(String m){
	super(m);
}
public MultipleMonsterAdditionException(){
	super();
}
}

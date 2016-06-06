package eg.edu.guc.yugioh.exceptions;

@SuppressWarnings("serial")
public class WrongPhaseException extends RuntimeException {
public WrongPhaseException(String m ){
	super(m);
}

public WrongPhaseException(){
	super();
}}

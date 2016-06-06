package eg.edu.guc.yugioh.exceptions;

@SuppressWarnings("serial")
public class NoSpaceException extends RuntimeException {
public NoSpaceException(String m){
	super(m);
}
public NoSpaceException(){
	super();
}
}

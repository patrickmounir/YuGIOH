package eg.edu.guc.yugioh.exceptions;

@SuppressWarnings("serial")
public class MissingFieldException  extends UnexpectedFormatException{
public MissingFieldException(String sourceFile, int sourceLine){
	super(sourceFile,sourceLine,"There is a missing field in "+sourceFile+" in line "+sourceLine);

}
}

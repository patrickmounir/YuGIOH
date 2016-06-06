package eg.edu.guc.yugioh.exceptions;

@SuppressWarnings("serial")
public class UnknownCardTypeException extends UnexpectedFormatException {
private String unknownType;
public UnknownCardTypeException(String sourceFile,int sourceLine,String  unknownType){
	super(sourceFile,sourceLine,"There is an unknown card type field in "+sourceFile+" in line "+sourceLine+" of type "+unknownType);
	this.unknownType=unknownType;
}
public String getUnknownType() {
	return unknownType;
}

public void setUnknownType(String unknownType) {
	this.unknownType = unknownType;
}

}

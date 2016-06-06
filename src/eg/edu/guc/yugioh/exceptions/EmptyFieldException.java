package eg.edu.guc.yugioh.exceptions;

@SuppressWarnings("serial")
public class EmptyFieldException extends UnexpectedFormatException {
private int sourceField;
public EmptyFieldException(String sourceFile,int sourceLine, int sourceIndex){
	super(sourceFile,sourceLine,"The field number "+sourceIndex+" in "+sourceFile+" in line "+sourceLine+" is empty");
	this.sourceField=sourceIndex;
}
public int getSourceField() {
	return sourceField;
}

public void setSourceField(int sourceIndex) {
	this.sourceField = sourceIndex;
}

}

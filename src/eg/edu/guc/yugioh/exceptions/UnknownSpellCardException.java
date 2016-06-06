package eg.edu.guc.yugioh.exceptions;

@SuppressWarnings("serial")
public class UnknownSpellCardException extends UnexpectedFormatException{
private String unknownSpell;

public UnknownSpellCardException(String sourceFile, int sourceLine, String unknownSpell){
	super(sourceFile,sourceLine,"There is an unknown Spell type in "+sourceFile+" in line "+sourceLine+" of type "+unknownSpell);
	this.setUnknownSpell(unknownSpell);
}

public String getUnknownSpell() {
	return unknownSpell;
}

public void setUnknownSpell(String unknownSpell) {
	this.unknownSpell = unknownSpell;
}
}

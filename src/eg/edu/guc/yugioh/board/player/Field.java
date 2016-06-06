package eg.edu.guc.yugioh.board.player;
import eg.edu.guc.yugioh.cards.*;
import eg.edu.guc.yugioh.cards.spells.SpellCard;
import eg.edu.guc.yugioh.exceptions.NoMonsterSpaceException;
import eg.edu.guc.yugioh.exceptions.NoSpellSpaceException;

import java.util.ArrayList;

public class Field {
private Phase phase;
private ArrayList <MonsterCard> monstersArea;
private ArrayList <SpellCard> spellArea;
private ArrayList <Card> hand;
private ArrayList <Card> graveyard;
private Deck deck;

public Field() throws Exception{
    monstersArea=new ArrayList <MonsterCard>();
    spellArea=new ArrayList <SpellCard> ();
	hand=new ArrayList <Card> ();
	graveyard=new ArrayList <Card> ();
	try {
		deck=new Deck();
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	phase=Phase.MAIN1;
}

public Deck getDeck() {
	return deck;
}

public Phase getPhase() {
	return phase;
}
public void setPhase(Phase phase) {
	this.phase = phase;
}
public ArrayList<MonsterCard> getMonstersArea() {
	return monstersArea;
}
public ArrayList<SpellCard> getSpellArea() {
	return spellArea;
}
public ArrayList<Card> getHand() {
	return hand;
}
public ArrayList<Card> getGraveyard() {
	return graveyard;
}
public void addMonsterToField(MonsterCard monster, Mode m, boolean isHidden)throws NoMonsterSpaceException{
	if(monstersArea.size()<5){
		monstersArea.add(monster);
		monster.setMode(m);
		monster.setHidden(isHidden);
		hand.remove(monster);
		monster.setLocation(Location.FIELD);
	}else {
		throw new NoMonsterSpaceException("There is no Space for a monster on the field");
	}
	
}
public void addMonsterToField(MonsterCard monster, Mode mode, ArrayList<MonsterCard> sacrifices)throws NoMonsterSpaceException{
	if(monstersArea.size()<5){	
	if((monster.getLevel()==5 || monster.getLevel()==6 )&& sacrifices.size()==1){
			removeMonsterToGraveyard(sacrifices);
			
			addMonsterToField(monster,mode,(mode==Mode.ATTACK)?false:true);
		}
		
			if((monster.getLevel()==7 || monster.getLevel()==8) && sacrifices.size()==2){
				removeMonsterToGraveyard(sacrifices);
				
					
				addMonsterToField(monster,mode,(mode==Mode.ATTACK)?false:true);}
			}
	else{
		throw new NoMonsterSpaceException("There is no Space for a monster on the field");
	}
	
		
	
}
public void removeMonsterToGraveyard(MonsterCard monster){
	if(	monstersArea.remove(monster)){
	monster.setLocation(Location.GRAVEYARD);
	graveyard.add(monster);
	monstersArea.remove(monster);}
	//graveyard.get(graveyard.size()-1).setLocation(Location.GRAVEYARD);
	
}
public void removeMonsterToGraveyard(ArrayList <MonsterCard> monsters){
	while(!monsters.isEmpty()){
		MonsterCard tmp=monsters.get(0);
		removeMonsterToGraveyard(tmp);
		monsters.remove(0);
	}
}
public void addSpellToField(SpellCard action,MonsterCard monster, boolean hidden){
	if(spellArea.size()<5){
		hand.remove(action);
		spellArea.add(action);
		spellArea.get(spellArea.size()-1).setLocation(Location.FIELD);
		action.setHidden(hidden);
		
		if(hidden==false){
	   activateSetSpell(action,monster);
		}
	}
	else {
		throw new NoSpellSpaceException("There is no Space for a spell on the field");
	}
}
public void activateSetSpell(SpellCard action, MonsterCard monster){
	if(action.isHidden()==false && spellArea.contains(action)){
	action.action(monster);
	removeSpellToGraveyard(action);
	
	}
	//spellArea.remove(action);
	//graveyard.add(action);
	//graveyard.get(graveyard.size()-1).setLocation(Location.GRAVEYARD);
}
public void removeSpellToGraveyard(SpellCard spell){
	if(spellArea.remove(spell)){
	graveyard.add(spell);
	graveyard.get(graveyard.size()-1).setLocation(Location.GRAVEYARD);
	}
}
public void removeSpellToGraveyard(ArrayList<SpellCard> spells){
	while(!spells.isEmpty()){
		removeSpellToGraveyard(spells.get(0));
		spells.remove(0);
	}
}
public void addCardToHand(){
	Card tmp=deck.drawOneCard();
	if(tmp!=null){
		hand.add(tmp);
	hand.get(hand.size()-1).setLocation(Location.HAND);
	}else
		Card.getBoard().setWinner(Card.getBoard().getOpponentPlayer());
	
	
}
public void addNCardsToHand(int n){
	for(int i=0;i<n;i++){
		Card tmp=deck.drawOneCard();
	if(tmp!=null){
		hand.add(tmp);
	tmp.setLocation(Location.HAND);}
	else
		Card.getBoard().setWinner(Card.getBoard().getOpponentPlayer());
	}
}
public void turnAll(){
for(int i=0;i<monstersArea.size();i++){
	monstersArea.get(i).setTurned(false);
	monstersArea.get(i).setAttacked(false);
	
}
}
}

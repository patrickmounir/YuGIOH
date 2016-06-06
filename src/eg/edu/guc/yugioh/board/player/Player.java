package eg.edu.guc.yugioh.board.player;

import java.util.ArrayList;

import eg.edu.guc.yugioh.cards.Card;
import eg.edu.guc.yugioh.cards.Mode;
import eg.edu.guc.yugioh.cards.MonsterCard;
import eg.edu.guc.yugioh.cards.spells.SpellCard;
import eg.edu.guc.yugioh.exceptions.DefenseMonsterAttackException;
import eg.edu.guc.yugioh.exceptions.MonsterMultipleAttackException;
import eg.edu.guc.yugioh.exceptions.MultipleMonsterAdditionException;
import eg.edu.guc.yugioh.exceptions.NoMonsterSpaceException;
import eg.edu.guc.yugioh.exceptions.NoSpellSpaceException;
import eg.edu.guc.yugioh.exceptions.WrongPhaseException;

public class Player implements Duelist  {
	private String name;
	private int lifePoints;
	private Field field;
	private int c;
	
public Player(String name) throws Exception{
		this.name=name;
		lifePoints=8000;
		c=0;
		try {
			field=new Field();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
public boolean isActive(){
	return this == Card.getBoard().getActivePlayer();
}
	public String getName() {
		return name;
	}

	public Field getField() {
		return field;
	}

	public int getLifePoints() {
		return lifePoints;
	}

	public void setLifePoints(int lifePoints) {
		if(lifePoints>0)
		this.lifePoints = lifePoints;
		else this.lifePoints=0;
	}

	@Override
	public boolean summonMonster(MonsterCard monster) throws MultipleMonsterAdditionException, WrongPhaseException ,NoMonsterSpaceException{
		// TODO Auto-generated method stub
		if(this==Card.getBoard().getActivePlayer()){
		if(field.getPhase()==Phase.MAIN1||field.getPhase()==Phase.MAIN2){
		if(c==0){
		if(field.getHand().contains(monster)&&Card.getBoard().getWinner()==null){
		field.addMonsterToField(monster, Mode.ATTACK, false);
		
		if(field.getMonstersArea().contains(monster)){c++;return true;}
		}}
		else{throw new MultipleMonsterAdditionException(this.name+": You can't add more than 1 monster this turn");}}
		else {
			throw new WrongPhaseException(this.name+": You can't summon a monster in "+field.getPhase()+" pahse");
		}}
		return false;}

	@Override
	public boolean summonMonster(MonsterCard monster,
			ArrayList<MonsterCard> sacrifices)  throws MultipleMonsterAdditionException,WrongPhaseException, NoMonsterSpaceException{
		// TODO Auto-generated method stub
		if(this==Card.getBoard().getActivePlayer()){
		if(field.getPhase()==Phase.MAIN1||field.getPhase()==Phase.MAIN2){
		if(c==0){
		if(this==Card.getBoard().getActivePlayer()&&field.getHand().contains(monster)&&Card.getBoard().getWinner()==null){
			field.addMonsterToField(monster,Mode.ATTACK,sacrifices);
			
			if(field.getMonstersArea().contains(monster)){c++;return true;}
			
		}}
		else {throw new MultipleMonsterAdditionException(this.name+": You can't add more than 1 monster this turn");}}
		else {
			throw new WrongPhaseException(this.name+": You can't summon a monster in "+field.getPhase()+" pahse");
		}}
		return false;
	}

	@Override
	public boolean setMonster(MonsterCard monster) throws MultipleMonsterAdditionException, WrongPhaseException {
		// TODO Auto-generated method stub
		if(this==Card.getBoard().getActivePlayer()){
		if(field.getPhase()==Phase.MAIN1||field.getPhase()==Phase.MAIN2){
		if(c==0){
		if(this==Card.getBoard().getActivePlayer()&&field.getHand().contains(monster)&&Card.getBoard().getWinner()==null){
			field.addMonsterToField(monster, Mode.DEFENSE, true);
			
			if(field.getMonstersArea().contains(monster)){c++;return true;}
			
		}}else{ throw new MultipleMonsterAdditionException(this.name+": You can't add more than 1 monster in a turn ");}}
		else {
			throw new WrongPhaseException(this.name+": You can't set a monster in "+field.getPhase()+" pahse");
		}}
		return false;
	}

	@Override
	public boolean setMonster(MonsterCard monster,
			ArrayList<MonsterCard> sacrifices) throws MultipleMonsterAdditionException , WrongPhaseException{
		// TODO Auto-generated method stub
		if(this==Card.getBoard().getActivePlayer()){
		if(c==0){
			if(field.getPhase()==Phase.MAIN1||field.getPhase()==Phase.MAIN2){
		if(this==Card.getBoard().getActivePlayer()&&Card.getBoard().getWinner()==null&&field.getHand().contains(monster)){
	
			field.addMonsterToField(monster, Mode.DEFENSE, sacrifices);
			
			if(field.getMonstersArea().contains(monster)){c++;return true;}
			}}
			else { throw new WrongPhaseException(this.name+": You can't set a monster in "+field.getPhase()+" pahse");
			}
				}else {throw new MultipleMonsterAdditionException(this.name+": You can't add more than 1 monster in a turn");}
		}
		return false;
	}

	@Override
	public boolean setSpell(SpellCard spell) throws WrongPhaseException {
		// TODO Auto-generated method stub
		if(this==Card.getBoard().getActivePlayer()){
		if(field.getPhase()==Phase.MAIN1||field.getPhase()==Phase.MAIN2){
			if(Card.getBoard().getActivePlayer().getField().getSpellArea().size()<6){
		if(this==Card.getBoard().getActivePlayer()&&Card.getBoard().getWinner()==null&&field.getHand().contains(spell)){
			field.addSpellToField(spell, null, true);
			if(field.getSpellArea().contains(spell))return true;
		}
		}
			else throw new NoSpellSpaceException(this.name+": There is no space for in the spell area");
		}
		else {
			throw new WrongPhaseException(this.name+": You can't set a spell card in "+field.getPhase()+" pahse");
		}}
		return false;
		
	}

	@Override
	public boolean activateSpell(SpellCard spell, MonsterCard monster) throws WrongPhaseException {
		// TODO Auto-generated method stub
		
		if(this==Card.getBoard().getActivePlayer()){
		if(field.getPhase()==Phase.MAIN1||field.getPhase()==Phase.MAIN2){
		if(this==Card.getBoard().getActivePlayer()&&Card.getBoard().getWinner()==null){
			spell.setHidden(false);
			if(field.getSpellArea().contains(spell)){
			field.activateSetSpell(spell, monster);
			}
			else{ if(field.getHand().contains(spell)){
				field.addSpellToField(spell, monster, false);
				}}
			if(field.getGraveyard().contains(spell)){return true;}
		}}
		else {
			throw new WrongPhaseException(this.name+": You can't activate this spell in "+field.getPhase()+" pahse");
		}}
		return false;
	}

	@Override
	public boolean declareAttack(MonsterCard activeMonster,
			MonsterCard opponentMonster) throws DefenseMonsterAttackException , MonsterMultipleAttackException, WrongPhaseException{
		if(this==Card.getBoard().getActivePlayer()){
		if(field.getPhase()==Phase.BATTLE){
		if(!activeMonster.isAttacked()){
		if(activeMonster.getMode()==Mode.ATTACK){
		if(this==Card.getBoard().getActivePlayer()&& Card.getBoard().getOpponentPlayer().getField().getMonstersArea().contains(opponentMonster)){

			activeMonster.action(opponentMonster);
			activeMonster.setAttacked(true);
			if(Card.getBoard().getOpponentPlayer().getLifePoints()==0){
					Card.getBoard().setWinner(Card.getBoard().getActivePlayer());
				}
			 if(Card.getBoard().getActivePlayer().getLifePoints()==0){
					Card.getBoard().setWinner(Card.getBoard().getOpponentPlayer());
				}
		
		return true;
		}}
		else {throw new DefenseMonsterAttackException(this.name+": You Can't Attack using a monster in defense");}
		}else {
			throw new MonsterMultipleAttackException(this.name+": You attack usong the same monster more than once ");
		}}
		else {
			throw new WrongPhaseException(this.name+": You can't attack monster in "+field.getPhase()+" pahse");
		}}
		return false;
	
	}

	@Override
	public boolean declareAttack(MonsterCard activeMonster) throws DefenseMonsterAttackException , MonsterMultipleAttackException,WrongPhaseException  {
		// TODO Auto-generated method stub
		if(this==Card.getBoard().getActivePlayer()){
		if(field.getPhase()==Phase.BATTLE){
		if(!activeMonster.isAttacked()){
		if(activeMonster.getMode()==Mode.ATTACK){
		if(this==Card.getBoard().getActivePlayer()&&Card.getBoard().getOpponentPlayer().getField().getMonstersArea().size()==0&&Card.getBoard().getWinner()==null){

			activeMonster.action();
			activeMonster.setAttacked(true);
			if(Card.getBoard().getOpponentPlayer().getLifePoints()==0){
					Card.getBoard().setWinner(Card.getBoard().getActivePlayer());
				}
			 if(Card.getBoard().getActivePlayer().getLifePoints()==0){
					Card.getBoard().setWinner(Card.getBoard().getOpponentPlayer());
				}
		
		return true;
		}}else {throw new DefenseMonsterAttackException(this.name+": You can't attack using a monster in defense");}
		}else {
			throw new MonsterMultipleAttackException(this.name+": You can't Attack more than once using the same monster");
		}}
		else throw new WrongPhaseException(this.name+": You can't attack in "+field.getPhase()+" pahse"); 
		}return false;
	}

	@Override
	public void addCardToHand() throws WrongPhaseException{
		// TODO Auto-generated method stub
		if(this==Card.getBoard().getActivePlayer()){
		if(this.field.getPhase()==Phase.MAIN1||this.field.getPhase()==Phase.MAIN2){
if(this == Card.getBoard().getActivePlayer()&&Card.getBoard().getWinner()==null){
		this.field.addCardToHand();	
		}}
		else {
			throw new WrongPhaseException(this.name+": You can't add cards to hand in "+field.getPhase()+" pahse");
		}
	}
	}
	@Override
	public void addNCardsToHand(int n) throws WrongPhaseException {
		// TODO Auto-generated method stub
		if(this==Card.getBoard().getActivePlayer()){
		if(this.field.getPhase()==Phase.MAIN1||this.field.getPhase()==Phase.MAIN2){
if(this == Card.getBoard().getActivePlayer()&&Card.getBoard().getWinner()==null){
		this.field.addNCardsToHand(n);	
		}
	}else {throw new WrongPhaseException(this.name+": You can't draw cards in "+field.getPhase()+" pahse");}
	}}

	@Override
	public void endPhase() {
		// TODO Auto-generated method stub
if(this == Card.getBoard().getActivePlayer()&&Card.getBoard().getWinner()==null){
			switch(this.field.getPhase()){
			case MAIN1:this.field.setPhase(Phase.BATTLE);
			               
				break;
			case BATTLE:this.field.setPhase(Phase.MAIN2);
			    
				break;
			case MAIN2: endTurn();
				break;
			}
		}
	}

	@Override
	public boolean endTurn() {
		// TODO Auto-generated method stub
		if(this==Card.getBoard().getActivePlayer()&&Card.getBoard().getWinner()==null){
			c=0;
			field.turnAll();
			this.field.setPhase(Phase.MAIN1);
			Card.getBoard().nextPlayer();
			return true;
		}
		return false;
	}

	@Override
	public boolean switchMonsterMode(MonsterCard monster) throws WrongPhaseException {
		if(this==Card.getBoard().getActivePlayer()){
		if(this.field.getPhase()==Phase.MAIN1||this.field.getPhase()==Phase.MAIN2){
		if(this == Card.getBoard().getActivePlayer()&&field.getMonstersArea().contains(monster) &&Card.getBoard().getWinner()==null){
			if(!monster.isTurned()){
				if(monster.getMode()==Mode.DEFENSE){
					monster.setMode(Mode.ATTACK);
					monster.setHidden(false);}
				else{monster.setMode(Mode.DEFENSE);
				monster.setHidden(true);
					
				}
				monster.setTurned(true);
			return true;
			}}
		}else {
			throw new WrongPhaseException(this.name+": You can't switch a monster in "+field.getPhase()+" pahse");
		}
			
		}
		return false;
	}
	public void setName(String pName) {
		// TODO Auto-generated method stub
		name = pName;
	}
	
}

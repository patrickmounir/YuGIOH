package eg.edu.guc.yugioh.cards;

public class MonsterCard extends Card {
	
private int level;
private int defensePoints;
private int attackPoints;
private Mode mode;
private boolean turned;
private boolean attacked;

public MonsterCard(String name, String description,int level,int attackPoints,int defensePoints){
	super(name,description);
	this.level=level;
	this.defensePoints=defensePoints;
	this.attackPoints=attackPoints;
	mode=Mode.DEFENSE;
	setTurned(false);
	attacked=false;
}
public void action(){
	Card.getBoard().getOpponentPlayer().setLifePoints(Card.getBoard().getOpponentPlayer().getLifePoints()-this.attackPoints);
	if(Card.getBoard().getOpponentPlayer().getLifePoints()==0){
		Card.getBoard().setWinner(Card.getBoard().getActivePlayer());
	}
}
public int getLevel() {
	return level;
}

public int getDefensePoints() {
	return defensePoints;
}

public void setDefensePoints(int defensePoints) {
	this.defensePoints = defensePoints;
}

public int getAttackPoints() {
	return attackPoints;
}

public void setAttackPoints(int attackPoints) {
	this.attackPoints = attackPoints;
}

public Mode getMode() {
	return mode;
}

public void setMode(Mode mode) {
	this.mode = mode;
}
@Override
public void action(MonsterCard monster) {
	// TODO Auto-generated method stub
	
	 if(monster.getMode()==Mode.ATTACK){
		int diff= Math.abs(monster.attackPoints-attackPoints);
		if(monster.attackPoints>attackPoints){
			Card.getBoard().getActivePlayer().setLifePoints(Card.getBoard().getActivePlayer().getLifePoints()-diff);
			Card.getBoard().getActivePlayer().getField().removeMonsterToGraveyard(this);
		}else{ if(monster.attackPoints<attackPoints){Card.getBoard().getOpponentPlayer().setLifePoints(Card.getBoard().getOpponentPlayer().getLifePoints()-diff);
			Card.getBoard().getOpponentPlayer().getField().removeMonsterToGraveyard(monster);
			
		}else{
			Card.getBoard().getOpponentPlayer().getField().removeMonsterToGraveyard(monster);
			Card.getBoard().getActivePlayer().getField().removeMonsterToGraveyard(this);
		}
		}
	}else{ if(monster.defensePoints<attackPoints){
		Card.getBoard().getOpponentPlayer().getField().removeMonsterToGraveyard(monster);
	}else{
		if(monster.defensePoints>attackPoints){
			Card.getBoard().getActivePlayer().setLifePoints(Card.getBoard().getActivePlayer().getLifePoints()-(monster.defensePoints-attackPoints));
		}
	}
		
	}
	
}
public boolean isTurned() {
	return turned;
}
public void setTurned(boolean turned) {
	this.turned = turned;
}
public void setAttacked(boolean b) {
	// TODO Auto-generated method stub
	attacked=b;
}
public boolean isAttacked() {
	// TODO Auto-generated method stub
	return attacked;
}
}

package eg.edu.guc.yugioh.cards.spells;

import eg.edu.guc.yugioh.cards.MonsterCard;

public class GracefulDice extends SpellCard {

	
	public GracefulDice(String name, String description) {
		super(name, description);
		// TODO Auto-generated constructor stub
	}

	public void action(MonsterCard monster){
		int rand=(int)(Math.random()*10)+1;
		int randNow=rand*100;
		int size=getBoard().getActivePlayer().getField().getMonstersArea().size();
		for(int i=0;i<size;i++){
			int n1=getBoard().getActivePlayer().getField().getMonstersArea().get(i).getAttackPoints();
			int n2=getBoard().getActivePlayer().getField().getMonstersArea().get(i).getDefensePoints();
			getBoard().getActivePlayer().getField().getMonstersArea().get(i).setAttackPoints(n1+randNow);
			getBoard().getActivePlayer().getField().getMonstersArea().get(i).setDefensePoints(n2+randNow);
		}
	}
}

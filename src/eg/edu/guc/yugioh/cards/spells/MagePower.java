package eg.edu.guc.yugioh.cards.spells;

import eg.edu.guc.yugioh.cards.MonsterCard;

public class MagePower extends SpellCard {

	public MagePower(String name, String description) {
		super(name, description);
	}
    public void action(MonsterCard monster){
    	if(( getBoard().getActivePlayer().getField()).getMonstersArea().contains(monster)){
    	int size=getBoard().getActivePlayer().getField().getSpellArea().size();
    	int n=getBoard().getActivePlayer().getField().getMonstersArea().indexOf(monster);
    	int old=getBoard().getActivePlayer().getField().getMonstersArea().get(n).getAttackPoints();
    	getBoard().getActivePlayer().getField().getMonstersArea().get(n).setAttackPoints(old+size*500);
    	int old1=getBoard().getActivePlayer().getField().getMonstersArea().get(n).getDefensePoints();
    	getBoard().getActivePlayer().getField().getMonstersArea().get(n).setDefensePoints(old1+size*500);
    }}
}

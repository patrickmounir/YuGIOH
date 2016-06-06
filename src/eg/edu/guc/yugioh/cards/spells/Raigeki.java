package eg.edu.guc.yugioh.cards.spells;

import eg.edu.guc.yugioh.cards.Card;
import eg.edu.guc.yugioh.cards.Location;
import eg.edu.guc.yugioh.cards.MonsterCard;

public class Raigeki extends SpellCard {

	public Raigeki(String name, String description) {
		super(name, description);
	}
    public void action(MonsterCard monster){
    	while(!getBoard().getOpponentPlayer().getField().getMonstersArea().isEmpty()){
        	Card tmp=getBoard().getOpponentPlayer().getField().getMonstersArea().get(0);
        	getBoard().getOpponentPlayer().getField().getGraveyard().add(tmp);
        	getBoard().getOpponentPlayer().getField().getMonstersArea().remove(0);
        	int n=getBoard().getOpponentPlayer().getField().getGraveyard().size();
        	getBoard().getOpponentPlayer().getField().getGraveyard().get(n-1).setLocation(Location.GRAVEYARD);
        	}
	}
}

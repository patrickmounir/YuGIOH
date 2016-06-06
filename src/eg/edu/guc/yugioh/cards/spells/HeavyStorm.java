package eg.edu.guc.yugioh.cards.spells;

import eg.edu.guc.yugioh.cards.Card;
import eg.edu.guc.yugioh.cards.Location;
import eg.edu.guc.yugioh.cards.MonsterCard;

public class HeavyStorm extends HarpieFeatherDuster {

	public HeavyStorm(String name, String description) {
		super(name, description);
	}
    public void action(MonsterCard monster){
		super.action(null);
		while(!getBoard().getActivePlayer().getField().getSpellArea().isEmpty()){
	    	Card tmp=getBoard().getActivePlayer().getField().getSpellArea().get(0);
	    	getBoard().getActivePlayer().getField().getGraveyard().add(tmp);
	    	getBoard().getActivePlayer().getField().getSpellArea().remove(0);
	    	int n=getBoard().getActivePlayer().getField().getGraveyard().size();
        	getBoard().getActivePlayer().getField().getGraveyard().get(n-1).setLocation(Location.GRAVEYARD);
	    	}
	}

}

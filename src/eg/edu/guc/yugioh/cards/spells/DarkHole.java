package eg.edu.guc.yugioh.cards.spells;

import eg.edu.guc.yugioh.cards.Card;
import eg.edu.guc.yugioh.cards.MonsterCard;

public class DarkHole extends Raigeki{

	public DarkHole(String name, String description) {
		super(name, description);
	}
public void action(MonsterCard monster){
		super.action(null);
		while(!getBoard().getActivePlayer().getField().getMonstersArea().isEmpty()){
        	Card tmp=getBoard().getActivePlayer().getField().getMonstersArea().get(0);
        	getBoard().getActivePlayer().getField().getGraveyard().add(tmp);
        	getBoard().getActivePlayer().getField().getMonstersArea().remove(0);
        	}
	}
}

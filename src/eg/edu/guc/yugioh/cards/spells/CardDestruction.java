package eg.edu.guc.yugioh.cards.spells;

import eg.edu.guc.yugioh.cards.Card;
import eg.edu.guc.yugioh.cards.Location;
import eg.edu.guc.yugioh.cards.MonsterCard;

public class CardDestruction extends SpellCard {

	public CardDestruction(String name,String description){
		super(name,description);
	}
	public void action(MonsterCard monster){
		int n=0;
		while(!getBoard().getOpponentPlayer().getField().getHand().isEmpty()){
			Card tmp=getBoard().getOpponentPlayer().getField().getHand().get(0);
			getBoard().getOpponentPlayer().getField().getHand().get(0).setLocation(Location.GRAVEYARD);
			getBoard().getOpponentPlayer().getField().getGraveyard().add(tmp);
			getBoard().getOpponentPlayer().getField().getHand().remove(0);
			n++;
		}
		getBoard().getOpponentPlayer().getField().addNCardsToHand(n);
		
		int n1=0;
		while(!getBoard().getActivePlayer().getField().getHand().isEmpty()){
			Card tmp=getBoard().getActivePlayer().getField().getHand().get(0);
			getBoard().getActivePlayer().getField().getHand().get(0).setLocation(Location.GRAVEYARD);
			getBoard().getActivePlayer().getField().getGraveyard().add(tmp);
			getBoard().getActivePlayer().getField().getHand().remove(0);
			n1++;
		}
		getBoard().getActivePlayer().getField().addNCardsToHand(n1);
	}
}

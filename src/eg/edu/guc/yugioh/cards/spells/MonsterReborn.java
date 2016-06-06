package eg.edu.guc.yugioh.cards.spells;

import eg.edu.guc.yugioh.cards.Card;
import eg.edu.guc.yugioh.cards.Location;
import eg.edu.guc.yugioh.cards.MonsterCard;

public class MonsterReborn extends SpellCard {

	public MonsterReborn(String name, String description) {
		super(name, description);
	}
    public void action(MonsterCard monster){
    	int size=getBoard().getActivePlayer().getField().getGraveyard().size();
    	int max=0; MonsterCard maxCard=null;int maxPos=-1;
    	for(int i=0;i<size;i++){
    		Card tmp=getBoard().getActivePlayer().getField().getGraveyard().get(i);
    	    if(tmp instanceof MonsterCard){
    	    	if(((MonsterCard) tmp).getAttackPoints()>max){
    	    	max=((MonsterCard) tmp).getAttackPoints();
    	    	maxCard=(MonsterCard) tmp;
    	    	maxPos=i;}
    	    }
    	}
    	
    	int size1=getBoard().getOpponentPlayer().getField().getGraveyard().size();
    	int max1=0; MonsterCard maxCard1=null;int maxPos1=-1;
    	for(int j=0;j<size1;j++){
    		Card tmp1=getBoard().getOpponentPlayer().getField().getGraveyard().get(j);
    	    if(tmp1 instanceof MonsterCard){
    	    	if(((MonsterCard) tmp1).getAttackPoints()>max1){
    	    	max1=((MonsterCard) tmp1).getAttackPoints();
    	    	maxCard1=(MonsterCard) tmp1;
    	    	maxPos1=j;}
    	    }
    	}
    	if(maxCard==null&&maxCard1==null){
    		return;
    	}else{
    		if(maxCard1!=null&&maxCard==null){
    			getBoard().getActivePlayer().getField().getMonstersArea().add(maxCard1);
        		getBoard().getOpponentPlayer().getField().getGraveyard().remove(maxPos1);
        		int n=getBoard().getActivePlayer().getField().getMonstersArea().size();
        		getBoard().getActivePlayer().getField().getMonstersArea().get(n-1).setLocation(Location.FIELD);
    		}else{
    			if(maxCard!=null&&maxCard1==null){
    				getBoard().getActivePlayer().getField().getMonstersArea().add(maxCard);
    	    		getBoard().getActivePlayer().getField().getGraveyard().remove(maxPos);
    	    		int n=getBoard().getActivePlayer().getField().getMonstersArea().size();
    	    		getBoard().getActivePlayer().getField().getMonstersArea().get(n-1).setLocation(Location.FIELD);
    			}else{
    	if(maxCard.getAttackPoints()>maxCard1.getAttackPoints()){
    		getBoard().getActivePlayer().getField().getMonstersArea().add(maxCard);
    		getBoard().getActivePlayer().getField().getGraveyard().remove(maxPos);
    		int n=getBoard().getActivePlayer().getField().getMonstersArea().size();
    		getBoard().getActivePlayer().getField().getMonstersArea().get(n-1).setLocation(Location.FIELD);
    	}
    	else{
    		getBoard().getActivePlayer().getField().getMonstersArea().add(maxCard1);
    		getBoard().getOpponentPlayer().getField().getGraveyard().remove(maxPos1);
    		int n=getBoard().getActivePlayer().getField().getMonstersArea().size();
    		getBoard().getActivePlayer().getField().getMonstersArea().get(n-1).setLocation(Location.FIELD);
    	}
    }}}}
}

package eg.edu.guc.yugioh.cards;

import eg.edu.guc.yugioh.board.Board;

public abstract class Card implements Cloneable{
	
@Override
	public Object clone() throws CloneNotSupportedException {
		// TODO Auto-generated method stub
		return super.clone();
	}
private String name;
private String description;
private boolean isHidden;
private Location location;
private static Board board;

public Card(String name,String description){
	this.name=name;
	this.description=description;
	this.isHidden=true;
	this.location=Location.DECK;
}
public abstract void action(MonsterCard monster);
public String getName(){
	return this.name;
}
public String getDescription(){
	return this.description;
}
public boolean isHidden(){
	return this.isHidden;
}
public Location getLocation(){
	return this.location;
}
public void setHidden(boolean isHidden){
	this.isHidden=isHidden;
}
public void setLocation(Location location){
	this.location=location;
}
public static Board getBoard() {
	return board;
}
public static void setBoard(Board board) {
	Card.board = board;
}

}

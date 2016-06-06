package eg.edu.guc.yugioh.board;

import eg.edu.guc.yugioh.board.player.Player;
import eg.edu.guc.yugioh.cards.Card;

public class Board {
private  Player activePlayer;
private  Player opponentPlayer;
private Player winner;

public Board(){
Card.setBoard(this);
winner=null;
}

public Player getActivePlayer() {
	return activePlayer;
}
public void setActivePlayer(Player activePlayer) {
	this.activePlayer = activePlayer;
}
public Player getOpponentPlayer() {
	return opponentPlayer;
}
public void setOpponentPlayer(Player opponentPlayer) {
	this.opponentPlayer = opponentPlayer;
}
public Player getWinner() {
	return winner;
}
public void setWinner(Player winner) {
	this.winner = winner;
}

public void whoStarts(Player p1, Player p2){
	int starts=(int)(Math.random()*2)+1;
	if(starts==1){
		setActivePlayer(p1);
		setOpponentPlayer(p2);
	}
		else{
			setActivePlayer(p2);
			setOpponentPlayer(p1);
		}
	}
public void startGame(Player p1, Player p2){
	whoStarts(p1,p2);
	getActivePlayer().getField().addNCardsToHand(5);
	getOpponentPlayer().getField().addNCardsToHand(5);
	getActivePlayer().getField().addCardToHand();
	
}
public void nextPlayer(){
	Player tmp=activePlayer;
	setActivePlayer(opponentPlayer);
	setOpponentPlayer(tmp);
	getActivePlayer().getField().addCardToHand();}
	public static void main(String [] args)
			throws Exception {

		Board board = new Board();
		Player p1 = new Player("Yugi");
		Player p2 = new Player("Kaiba");
		board.startGame(p1, p2);
}
}

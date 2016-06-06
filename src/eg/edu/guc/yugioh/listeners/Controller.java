package eg.edu.guc.yugioh.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import eg.edu.guc.yugioh.board.Board;
import eg.edu.guc.yugioh.board.player.Player;
import eg.edu.guc.yugioh.gui.GameBoard;
import eg.edu.guc.yugioh.gui.StartUpWindow;

public class Controller implements ActionListener{
private GameBoard gameBoard;
private StartUpWindow start;
String p1Name;
String p2Name;
public Controller() throws Exception{
	//String p1Name= JOptionPane.showInputDialog("First Player Name");
	//String p2Name= JOptionPane.showInputDialog("Second Player Name");
	start =  new StartUpWindow();
	
	 	start.getStart().addActionListener(this);
			

}
@SuppressWarnings("unused")
public void actionPerformed(ActionEvent e) {
		
		
		Player p1 = null;
	try {
		p1 = new Player (start.getP1Name().getText());
	} catch (Exception e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
		
	}
Player p2 = null;
try {
	p2 = new Player(start.getP2Name().getText());
} catch (Exception e1) {
	// TODO Auto-generated catch block
	
	e1.printStackTrace();
}

Board b = new Board();		
b.startGame(p1, p2);
	try {
	gameBoard= new GameBoard(p1,p2);
} catch (Exception e1) {
	// TODO Auto-generated catch block
	e1.printStackTrace();
	
}
	try {
		GameManager game = new GameManager(this);
	} catch (Exception e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
	

start.setVisible(false);
gameBoard.setVisible(true);}

	
public GameBoard getGameBoard() {
	return gameBoard;
}
public void setGameBoard(GameBoard gameBoard) {
	this.gameBoard = gameBoard;
}


public StartUpWindow getStart() {
	return start;
}


public void setStart(StartUpWindow start) {
	this.start = start;
}

}

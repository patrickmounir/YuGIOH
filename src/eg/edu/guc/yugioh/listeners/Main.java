package eg.edu.guc.yugioh.listeners;

public class Main{
private  Controller controll;
private GameManager game ;
public Main() throws Exception{
	
	setControll(new Controller ());
	//while(controll.getGameBoard()==null);
	//setGame(new GameManager(controll));
	
}
@SuppressWarnings("unused")
public static void main(String[] args) {
	
	try {
		Main m = new Main();
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}
public GameManager getGame() {
	return game;
}
public void setGame(GameManager game) {
	this.game = game;
}
public Controller getControll() {
	return controll;
}
public void setControll(Controller controll) {
	this.controll = controll;
}
}
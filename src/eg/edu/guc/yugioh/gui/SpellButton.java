package eg.edu.guc.yugioh.gui;



import javax.swing.JMenuItem;

import eg.edu.guc.yugioh.board.player.Player;
import eg.edu.guc.yugioh.cards.MonsterCard;
import eg.edu.guc.yugioh.cards.spells.SpellCard;

@SuppressWarnings("serial")
public class SpellButton extends CardButton {
	private JMenuItem activate;
	private JMenuItem set;
	private JMenuItem activate1;
	
public SpellButton(String file){
	super(file+".jpg");
	
	activate= new JMenuItem("Activate");
	activate1= new JMenuItem("Activate");
	set= new JMenuItem("Set");
	getFieldActions().add(activate1);
	getActions().add(activate);
	getActions().add(set);
	
}
public JMenuItem getActivate() {
	return activate;
}
public void setActivate(JMenuItem activate) {
	this.activate = activate;
}
public JMenuItem getSet() {
	return set;
}
public void setSet(JMenuItem set) {
	this.set = set;
}
public boolean actions(Player player,JMenuItem i, MonsterCard card){
	if(i ==set){
		return player.setSpell((SpellCard) getCard());
	}
	else{
		return player.activateSpell((SpellCard) getCard(), card);
	}
}
public JMenuItem getActivate1() {
	return activate1;
}
public void setActivate1(JMenuItem activate1) {
	this.activate1 = activate1;
}


}

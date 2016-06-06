package eg.edu.guc.yugioh.gui;

import javax.swing.JMenuItem;

@SuppressWarnings("serial")
public class MonsterButton extends CardButton{
	
	private JMenuItem attack;
	private JMenuItem set;
	private JMenuItem summon;
	private JMenuItem switchToDefense;
 public MonsterButton(String file){
	 super(file+".jpg");
	 attack= new JMenuItem("Attack");
	 summon= new JMenuItem("Summon");
	 set= new JMenuItem("Set");
	 switchToDefense = new JMenuItem("Switch");
	 getFieldActions().add(attack);
	 getFieldActions().add(switchToDefense);
	 getActions().add(summon);
	 getActions().add(set);
 }




public JMenuItem getAttack() {
	return attack;
}

public void setAttack(JMenuItem attack) {
	this.attack = attack;
}

public JMenuItem getSet() {
	return set;
}

public void setSet(JMenuItem set) {
	this.set = set;
}

public JMenuItem getSummon() {
	return summon;
}

public void setSummon(JMenuItem summon) {
	this.summon = summon;
}



public JMenuItem getSwitch() {
	// TODO Auto-generated method stub
	return switchToDefense;
}
}

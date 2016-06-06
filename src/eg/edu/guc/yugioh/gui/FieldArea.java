package eg.edu.guc.yugioh.gui;

import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JPanel;

import eg.edu.guc.yugioh.cards.MonsterCard;
import eg.edu.guc.yugioh.cards.spells.SpellCard;

@SuppressWarnings("serial")
public class FieldArea extends JPanel{
	private ArrayList <MonsterButton> monsterButtons;
	private ArrayList<SpellButton> spellButtons;
	private int monsterindex;
	private int spellindex;
public FieldArea(){
	super();
	monsterindex =0;
	spellindex=0;
	setLayout(new GridLayout(2,5));
	setOpaque(false);
	setBackground(null);
	monsterButtons = new ArrayList<MonsterButton>();
	spellButtons = new ArrayList<SpellButton>();
	for(int i =0;i<5;i++){
		MonsterButton b = new MonsterButton ("stone1");
		b.setCard(null);
		add(b);
		monsterButtons.add(b);
	}
	for(int i =0;i<5;i++){
		SpellButton b = new SpellButton ("stone1");
		b.setCard(null);
		add(b);
		spellButtons.add(b);
	}
	
	
	setVisible(true);
	validate();
}
public void addMonsterToField(MonsterCard card){
	monsterButtons.get(monsterindex).setFile(card.getName());
	monsterindex++;
}
public void addSpellToField(SpellCard card){
	spellButtons.get(spellindex).setFile(card.getName());
	spellindex++;
}

public ArrayList <MonsterButton> getMonsterButtons() {
	return monsterButtons;
}
public void setMonsterButtons(ArrayList <MonsterButton> monsterButtons) {
	this.monsterButtons = monsterButtons;
}
public ArrayList<SpellButton> getSpellButtons() {
	return spellButtons;
}
public void setSpellButtons(ArrayList<SpellButton> spellButtons) {
	this.spellButtons = spellButtons;
}
}

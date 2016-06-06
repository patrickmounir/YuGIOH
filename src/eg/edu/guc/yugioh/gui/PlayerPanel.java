package eg.edu.guc.yugioh.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.OverlayLayout;

import eg.edu.guc.yugioh.board.player.Player;
import eg.edu.guc.yugioh.cards.Card;
import eg.edu.guc.yugioh.cards.Mode;
import eg.edu.guc.yugioh.cards.MonsterCard;
import eg.edu.guc.yugioh.cards.spells.SpellCard;

@SuppressWarnings("serial")
public class PlayerPanel extends JPanel {
	private Player p;
	private JLabel playerName;
	private JTextArea pLife;
	private FieldArea fa;
	private JLabel graveyard;
	private JLabel deck;
	private JButton endTurn;
	private JButton endPhase;
	private JPanel hand;
	private JLabel deckSize;
	private ArrayList<CardButton> handList;
	public PlayerPanel(Player p){
		super();
		setBorder(null);
		setBackground(null);
		setOpaque(false);
		
		
		setLayout(new BorderLayout());
		setVisible(true);
		
		JPanel down  = new JPanel();
		down.setLayout(new FlowLayout());
		down.setOpaque(false);
		this.p = p;
		setFa(new FieldArea());
		 playerName= new JLabel();
		playerName.setBorder(null);
		playerName.setBackground(null);
		playerName.setOpaque(false);
		pLife = new JTextArea();
		pLife.setEditable(false);
		pLife.setBackground(null);
		pLife.setLineWrap(true);
		pLife.setWrapStyleWord(true);
		pLife.setOpaque(false);
		pLife.setSize(100, 50);
	
		addCard(p.getField().getHand().size());
		pLife.setText("\nLP\n\n"+p.getLifePoints()+"");
		CustomFont f = new CustomFont("LP\n"+p.getLifePoints()+"",Font.BOLD);
		
		pLife.setFont(f);
		pLife.setForeground(Color.yellow);
		graveyard= new JLabel();
		graveyard.setIcon(new ImageIcon("cards//grave.jpg"));
		deck= new JLabel();
		deckSize = new JLabel("<html><br>"+p.getField().getDeck().getDeck().size()+"</html>");
		deckSize.setFont(new Font("PerryGothic",Font.BOLD,22));
		deckSize.setForeground(new Color(225,120,45));
		
		
		deck.setIcon(new ImageIcon("cards//card.jpg"));
		JPanel deckl = new JPanel();
		deckl.setLayout(new OverlayLayout(deckl));
		
		deckl.add(deckSize);deckl.add(deck);
		
		playerName.setText("<html><br>"+p.getName()+"</html>");
		playerName.setFont(new Font("PerryGothic",Font.BOLD,24));
		endTurn  = new JButton("end turn");
		endPhase= new JButton("end Phase");
		if(p == Card.getBoard().getActivePlayer())
		{
		playerName.setForeground(Color.WHITE);
		}
		else{
			playerName.setForeground(Color.YELLOW);
		}
		hand.setBackground(null);
		hand.setOpaque(false);
		down.add(playerName);
		down.add(pLife);
		down.add(endTurn);
		down.add(endPhase);
		down.add(fa);
		JPanel right = new JPanel();
		right.setOpaque(false);
		right.setBackground(null);
		down.setBackground(null);
		right.setLayout(new BorderLayout());
		right.setVisible(true);
		right.add(deckl,BorderLayout.SOUTH);
		right.add(graveyard,BorderLayout.NORTH);
		//right.add(deckSize,BorderLayout.WEST);
		
		down.add(right);
		
		add(down,BorderLayout.SOUTH);
		add(hand , BorderLayout.NORTH);
		validate();
	}

public void refresh(){
	ArrayList<MonsterCard> monsterfield = p.getField().getMonstersArea();
	ArrayList<SpellCard> spellfield =p.getField().getSpellArea();
	for(int i =0;i<5;i++){
		if(i>= monsterfield.size()){
			fa.getMonsterButtons().get(i).setFile("stone1");
			fa.getMonsterButtons().get(i).setCard(null);
		}else { fa.getMonsterButtons().get(i).setCard(monsterfield.get(i));
			if(monsterfield.get(i).getMode()==Mode.DEFENSE){
			fa.getMonsterButtons().get(i).setFile("defense");
		}else{
			fa.getMonsterButtons().get(i).setFile(monsterfield.get(i).getName());
		}
			
		}
		if(i>=spellfield.size()){
			fa.getSpellButtons().get(i).setFile("stone1");
			fa.getSpellButtons().get(i).setCard(null);
		}else {fa.getSpellButtons().get(i).setFile("card");
		       
		        fa.getSpellButtons().get(i).setCard(spellfield.get(i));
			fa.getSpellButtons().get(i).getActions().remove(fa.getSpellButtons().get(i).getSet());
		}
		
	}
	ArrayList<Card> pHand = p.getField().getHand();
	if(pHand.size()!=0){
int i=0;
	for(i =0;i<pHand.size();i++){
		Card c = pHand.get(i);
		CardButton b= null;
		if(i<handList.size()){
		b = handList.get(i);
		if(pHand.contains(b.getCard()))
		{
			if(p!=Card.getBoard().getActivePlayer()){
			b.setFile("card");
			b.setCard(c);
		}
	else{ b.setFile(c.getName());
	b.setCard(c);
	}
			}
		else{
			handList.remove(i);
			hand.remove(i);
		}
		}else{
			if(!p.isActive()){
				if(c instanceof MonsterCard){
			    	 b = new MonsterButton("card");
			    	
			    }else{b = new SpellButton("card");
			}b.setFile("card");
			}
		else{
		    
		   
		    if(c instanceof MonsterCard){
		    	 b = new MonsterButton(c.getName());
		    	
		    }else{b = new SpellButton(c.getName());
		} b.setFile(c.getName());
		}
		hand.add(b);
		b.setCard(c);
		handList.add(b);
		}
	
	}
	for(;i<handList.size();){
		hand.remove(i);
		handList.remove(i);
	}

	
	}
	
	if(p.getField().getGraveyard().size()!=0){
		graveyard.setIcon(new ImageIcon("cards//"+p.getField().getGraveyard().get(p.getField().getGraveyard().size()-1).getName()+".jpg"));
	}else{
		graveyard.setIcon(new ImageIcon("cards//grave.jpg"));
	}
	
	
	pLife.setText("\nLP\n\n"+p.getLifePoints());
	if(p == Card.getBoard().getActivePlayer())
	{
	playerName.setForeground(Color.WHITE);
	}else{
		playerName.setForeground(Color.YELLOW);
	}
	deckSize.setText("<html><br>"+p.getField().getDeck().getDeck().size()+"</html>");
	
	validate();
}
public void addCard(int size){
	hand = new JPanel();
	hand.setLayout(new FlowLayout());
	handList = new ArrayList<CardButton>();
	
	for(int i =0;i<size;i++)
	{ Card c = p.getField().getHand().get(i);
	 CardButton b=null;
		if(!p.isActive()){
			if(c instanceof MonsterCard){
		    	 b = new MonsterButton("card");
		    	
		    }else{b = new SpellButton("card");
		}b.setFile("card");
		}
	else{
	    
	   
	    if(c instanceof MonsterCard){
	    	 b = new MonsterButton(c.getName());
	    	
	    }else{b = new SpellButton(c.getName());
	} b.setFile(c.getName());
	}
	hand.add(b);
	b.setCard(c);
	handList.add(b);
	}
}

	public JTextArea getpLife() {
	return pLife;
}
public void setpLife(String pLife) {
	this.pLife.setText(pLife);
}
public JButton getEndTurn() {
	return endTurn;
}
public void setEndTurn(JButton endTurn) {
	this.endTurn = endTurn;
}
public JButton getEndPhase() {
	return endPhase;
}
public void setEndPhase(JButton endPhase) {
	this.endPhase = endPhase;
}
public ArrayList<CardButton> getHandList() {
	return handList;
}
public void setHandList(ArrayList<CardButton> handList) {
	this.handList = handList;
}
	public Player getP() {
		return p;
	}

	public void setP(Player p) {
		this.p = p;
	}

	public FieldArea getFa() {
		return fa;
	}

	public void setFa(FieldArea fa) {
		this.fa = fa;
	}

	public JLabel getGraveyard() {
		return graveyard;
	}

	public void setGraveyard(JLabel graveyard) {
		this.graveyard = graveyard;
	}

	public JLabel getDeck() {
		return deck;
	}

	public void setDeck(JLabel deck) {
		this.deck = deck;
	}

	public JPanel getHand() {
		return hand;
	}

	public void setHand(JPanel hand) {
		this.hand = hand;
	}
	
	
	

}

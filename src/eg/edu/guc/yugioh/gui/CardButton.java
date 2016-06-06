package eg.edu.guc.yugioh.gui;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPopupMenu;

import eg.edu.guc.yugioh.cards.Card;

@SuppressWarnings("serial")
public class  CardButton extends JButton implements ActionListener {
	private Card card;
	private String file="stone1.jpg";
	private JPopupMenu actions;
	private JPopupMenu fieldActions;
	
	public String getFile() {
		return file;
	}

	public void setFile(String file) {
		this.file = file;
		setIcon(new ImageIcon("cards//"+file+".jpg"));
	}

	public CardButton(String file){
		 super();
		 setPreferredSize(new Dimension(104, 152));
		 if(file!=null)
		 this.file = file;
		 setContentAreaFilled(false);
			setOpaque(false);
			
		
		actions = new JPopupMenu("Actions");
		fieldActions= new JPopupMenu();
		setIcon(new ImageIcon("cards//"+file));
		
		
		
		 
       
		 setVisible(true);
		 validate();
	 }
	public void show (String cardName){
setFile(cardName);		
	}
	public void hide(){
		setFile("card");
	}
	
	
		public JPopupMenu getActions() {
		return actions;
	}

	public void setActions(JPopupMenu actions) {
		this.actions = actions;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		
	}

	public Card getCard() {
		return card;
	}

	public void setCard(Card card) {
		this.card = card;
	}

	public JPopupMenu getFieldActions() {
		return fieldActions;
	}

	public void setFieldActions(JPopupMenu fieldActions) {
		this.fieldActions = fieldActions;
	}

	
	
}

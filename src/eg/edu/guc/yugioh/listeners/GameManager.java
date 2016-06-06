package eg.edu.guc.yugioh.listeners;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JComponent;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import eg.edu.guc.yugioh.board.player.Phase;
import eg.edu.guc.yugioh.board.player.Player;
import eg.edu.guc.yugioh.cards.Card;
import eg.edu.guc.yugioh.cards.Location;
import eg.edu.guc.yugioh.cards.MonsterCard;
import eg.edu.guc.yugioh.cards.spells.SpellCard;
import eg.edu.guc.yugioh.exceptions.DefenseMonsterAttackException;
import eg.edu.guc.yugioh.exceptions.MonsterMultipleAttackException;
import eg.edu.guc.yugioh.exceptions.MultipleMonsterAdditionException;
import eg.edu.guc.yugioh.exceptions.NoSpaceException;
import eg.edu.guc.yugioh.exceptions.WrongPhaseException;
import eg.edu.guc.yugioh.gui.CardButton;
import eg.edu.guc.yugioh.gui.GameEnder;
import eg.edu.guc.yugioh.gui.MonsterButton;
import eg.edu.guc.yugioh.gui.PlayerPanel;
import eg.edu.guc.yugioh.gui.SpellButton;

public class GameManager implements ActionListener{
private Controller controller;
private JComponent firstClick;
private JComponent secondClick;
private JComponent thirdClick;
private JComponent fourthClick;
private GameEnder endGame;

public GameManager(Controller con) throws Exception{
	
	
		
		controller=con;
		
	
	
	PlayerPanel player1 =controller.getGameBoard().getPp1();
	PlayerPanel player2 =controller.getGameBoard().getPp2();
	endGame = new GameEnder();
	endGame.getYes().addActionListener(this);
    endGame.getNo().addActionListener(this);
	addAllActionListenersToButtons(player1);
	addAllActionListenersToButtons(player2);
	
	
}
public void addAllActionListenersToButtons(PlayerPanel player) {
	
	ArrayList<CardButton> playerhand = player.getHandList();	
	for (CardButton button : playerhand) {
		button.addActionListener(this);
		if(button instanceof MonsterButton)
		{//((MonsterButton) button).getAttack().addActionListener(this);
		 ((MonsterButton) button).getSet().addActionListener(this);
			((MonsterButton) button).getSummon().addActionListener(this);
		}else {
			((SpellButton) button).getSet().addActionListener(this);
			((SpellButton) button).getActivate1().addActionListener(this);

			((SpellButton) button).getActivate().addActionListener(this);
		}
	}
	ArrayList<MonsterButton> monsterArea = player.getFa().getMonsterButtons();
     for(MonsterButton button: monsterArea){
    	 button.addActionListener(this);
    	 button.getAttack().addActionListener(this);
	     button.getSwitch().addActionListener(this);

     }
     ArrayList<SpellButton> spellArea = player.getFa().getSpellButtons();
     for(SpellButton button: spellArea){
    	 button.addActionListener(this);
    	  button.getSet().addActionListener(this);
			button.getActivate().addActionListener(this);
     }
     player.getEndPhase().addActionListener(this);
     player.getEndTurn().addActionListener(this);
     
    }

public Controller getController() {
	return controller;
}

public void setController(Controller controller) {
	this.controller = controller;
}

@SuppressWarnings("unused")
@Override
public void actionPerformed(ActionEvent e) {
	// TODO Auto-generated method stub
	try{
	 //Player p1 =controller.getGameBoard().getPp1().getP();
	 PlayerPanel pp1=controller.getGameBoard().getPp1();
	 PlayerPanel pp2=controller.getGameBoard().getPp2();
	 //Player p2 =controller.getGameBoard().getPp2().getP();
	if(e.getSource() instanceof MonsterButton && ((MonsterButton) e.getSource()).getCard()!=null ){
		monsterButtonsActions(e,Card.getBoard().getActivePlayer());
		//monsterButtonsActions(e,p2);
		
		}
	if(e.getSource()instanceof JMenuItem){
	 itemsActions(e,Card.getBoard().getActivePlayer());
	
	}
	if(e.getSource() instanceof SpellButton&& ((SpellButton) e.getSource()).getCard()!=null && !Card.getBoard().getOpponentPlayer().getField().getSpellArea().contains(((SpellButton) e.getSource()).getCard()) ){
		spellButtonsActions(e,Card.getBoard().getActivePlayer());
		//spellButtonsActions(e,p2);
	}
	if(e.getSource()==controller.getGameBoard().getPp1().getEndPhase())
	{endPhase(controller.getGameBoard().getPp1());}
	if(e.getSource()==controller.getGameBoard().getPp1().getEndTurn())
	{endturn(controller.getGameBoard().getPp1()); }
	if(e.getSource()==controller.getGameBoard().getPp2().getEndPhase())
		{endPhase(controller.getGameBoard().getPp2());}
    if(e.getSource()==controller.getGameBoard().getPp2().getEndTurn())
	{endturn(controller.getGameBoard().getPp2());
	  
	}
    
    if(e.getSource()==endGame.getYes()){
    	
    	controller.getGameBoard().dispose();
    	controller.getStart().dispose();
    	endGame.dispose();
  	  
  	Main main = new Main();
           return;
    }
    if(e.getSource()==endGame.getNo()){
    	System.exit(0);
    }
     refresh(pp1);
	refresh(pp2);
	
	
	if(Card.getBoard().getWinner()!=null){
		JOptionPane.showMessageDialog(controller.getGameBoard(), "Winner is "+Card.getBoard().getWinner().getName());
		endGame.setVisible(true);
		

		
	}
	
	}catch(WrongPhaseException | DefenseMonsterAttackException | MonsterMultipleAttackException | NoSpaceException|MultipleMonsterAdditionException e1){
		
			JOptionPane.showMessageDialog(controller.getGameBoard(), e1.getMessage());
			secondClick= null;
			firstClick =null;
			thirdClick=null;
			fourthClick=null;
	} catch (Exception e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	} 
		
	
}
public void refresh(PlayerPanel pp) {
	// TODO Auto-generated method stub
    for(int i = pp.getP().getField().getHand().size();i>=0;i--){
	pp.refresh();}
	ArrayList<CardButton> playerhand = pp.getHandList();
	
	for (CardButton button : playerhand) {
		if(button.getActionListeners().length==0){
		button.addActionListener(this);
		if(button instanceof MonsterButton)
		{((MonsterButton) button).getAttack().addActionListener(this);
		 ((MonsterButton) button).getSet().addActionListener(this);
			((MonsterButton) button).getSummon().addActionListener(this);
			 ((MonsterButton) button).getSwitch().addActionListener(this);
		}else {
			((SpellButton) button).getSet().addActionListener(this);
			((SpellButton) button).getActivate().addActionListener(this);
			((SpellButton) button).getActivate1().addActionListener(this);
		}}}
	
	if(Card.getBoard().getActivePlayer().getField().getPhase()==Phase.MAIN1){
		controller.getGameBoard().getMain1().setForeground(Color.WHITE);
		controller.getGameBoard().getMain2().setForeground(Color.BLACK);
		controller.getGameBoard().getBattle().setForeground(Color.BLACK);
	}else{	if(Card.getBoard().getActivePlayer().getField().getPhase()==Phase.MAIN2){
		controller.getGameBoard().getMain1().setForeground(Color.BLACK);
		controller.getGameBoard().getMain2().setForeground(Color.WHITE);
		controller.getGameBoard().getBattle().setForeground(Color.BLACK);

	}else{
		controller.getGameBoard().getMain1().setForeground(Color.BLACK);
		controller.getGameBoard().getMain2().setForeground(Color.BLACK);
		controller.getGameBoard().getBattle().setForeground(Color.WHITE);
	}
		
	}
	
}
public void spellButtonsActions(ActionEvent e, Player p1) {
	// TODO Auto-generated method stub
	if(secondClick == null){
	SpellButton b = (SpellButton)e.getSource();
	if(b.getCard()!=null&&!Card.getBoard().getOpponentPlayer().getField().getHand().contains(b.getCard())){
	controller.getGameBoard().setPic(b.getCard().getName()+" big");
	controller.getGameBoard().setText(b.getCard().getDescription());	
	b.getActions().show(b,104/2,152/2);
		
	firstClick= b;
	}
	}
}
public void endPhase(PlayerPanel p){
	if(Card.getBoard().getActivePlayer().getField().getPhase()==Phase.MAIN2){
		endturn(p);		   
	}else{p.getP().endPhase();
	JOptionPane.showMessageDialog(controller.getGameBoard(), Card.getBoard().getActivePlayer().getField().getPhase()+" is active");
	}
}
public void endturn(PlayerPanel p) {
	// TODO Auto-generated method stub
	p.getP().endTurn();
	JOptionPane.showMessageDialog(controller.getGameBoard(), Card.getBoard().getActivePlayer().getName()+"'s Turn");
}
public void monsterButtonsActions(ActionEvent e,Player p){
	MonsterButton b = (MonsterButton)e.getSource();
	if(!(secondClick instanceof JMenuItem)){
	if(b.getCard()!=null&&b instanceof MonsterButton&&!Card.getBoard().getOpponentPlayer().getField().getHand().contains(b.getCard())){	
	controller.getGameBoard().setPic(b.getCard().getName()+" big");
	controller.getGameBoard().setText(((MonsterCard) b.getCard()).getAttackPoints()+"/ATK "+((MonsterCard) b.getCard()).getDefensePoints()+"/DEF \n Level "+((MonsterCard) b.getCard()).getLevel()+"\n"+b.getCard().getDescription());
	if(b.getCard().getLocation()==Location.HAND){
		b.getActions().show(b,104/2,152/2);}
			else{if(Card.getBoard().getActivePlayer().getField().getMonstersArea().contains(b.getCard()))
				b.getFieldActions().show(b,104/2,152/2);
			else{
				nullify();
			}
			}
	firstClick = b;
	
	}else{
		nullify();
	}
	}else{if(firstClick instanceof MonsterButton){
		if(secondClick==((MonsterButton) firstClick).getAttack()){
			if(e.getSource() instanceof MonsterButton){
			thirdClick = (MonsterButton) e.getSource();
			
			if(p.declareAttack((MonsterCard)((MonsterButton) firstClick).getCard(),(MonsterCard)((MonsterButton) thirdClick).getCard())){
				secondClick= null;
				firstClick =null;
				thirdClick=null;
			}else{
				nullify();
			}
			}
		}else{
			if(secondClick==((MonsterButton) firstClick).getSummon()){
				if(e.getSource() instanceof MonsterButton&& ((CardButton) e.getSource()).getCard().getLocation()==Location.FIELD){
					if(thirdClick==null){
				thirdClick = (MonsterButton) e.getSource();
				if(((MonsterCard) ((CardButton) firstClick).getCard()).getLevel()==5||((MonsterCard) ((CardButton) firstClick).getCard()).getLevel()==6){
					ArrayList<MonsterCard> sc= new ArrayList<MonsterCard>();
					sc.add((MonsterCard) ((MonsterButton) thirdClick).getCard());
					
					if(p.summonMonster((MonsterCard) ((CardButton) firstClick).getCard(), sc)){
						secondClick= null;
						firstClick =null;
						thirdClick=null;
					
					}
				}}else{				if(e.getSource() instanceof MonsterButton&& ((CardButton) e.getSource()).getCard().getLocation()==Location.FIELD){

					fourthClick = (MonsterButton) e.getSource();
					if(((MonsterCard) ((CardButton) firstClick).getCard()).getLevel()==7||((MonsterCard) ((CardButton) firstClick).getCard()).getLevel()==8){
						ArrayList<MonsterCard> sc= new ArrayList<MonsterCard>();
						sc.add((MonsterCard) ((MonsterButton) thirdClick).getCard());
						sc.add((MonsterCard) ((MonsterButton) fourthClick).getCard());
						
						if(p.summonMonster((MonsterCard) ((CardButton) firstClick).getCard(), sc)){
							secondClick= null;
							firstClick =null;
							thirdClick=null;
							fourthClick=null;
						}
					}
				}else{
					nullify();
				}
				}}else{
					nullify();
				}
			}else{
				if(secondClick==((MonsterButton) firstClick).getSet()){
					if(e.getSource() instanceof MonsterButton&& ((CardButton) e.getSource()).getCard().getLocation()==Location.FIELD){
						if(thirdClick==null){
					thirdClick = (MonsterButton) e.getSource();
					if(((MonsterCard) ((CardButton) firstClick).getCard()).getLevel()==5||((MonsterCard) ((CardButton) firstClick).getCard()).getLevel()==6){
						ArrayList<MonsterCard> sc= new ArrayList<MonsterCard>();
						sc.add((MonsterCard) ((MonsterButton) thirdClick).getCard());
						if(p.setMonster((MonsterCard) ((CardButton) firstClick).getCard(), sc)){
							secondClick= null;
							firstClick =null;
							thirdClick=null;
						
						}
					}}else{if(e.getSource() instanceof MonsterButton&& ((CardButton) e.getSource()).getCard().getLocation()==Location.FIELD&& e.getSource()!=thirdClick){

						fourthClick = (MonsterButton) e.getSource();
						if(((MonsterCard) ((CardButton) firstClick).getCard()).getLevel()==7||((MonsterCard) ((CardButton) firstClick).getCard()).getLevel()==8){
							ArrayList<MonsterCard> sc= new ArrayList<MonsterCard>();
							sc.add((MonsterCard) ((MonsterButton) thirdClick).getCard());
							sc.add((MonsterCard) ((MonsterButton) fourthClick).getCard());
							if(p.setMonster((MonsterCard) ((CardButton) firstClick).getCard(), sc)){
								secondClick= null;
								firstClick =null;
								thirdClick=null;
								fourthClick=null;
							}
						}
					}else{
						nullify();
					}
					}
					}else{
						nullify();
					}
				}
			}
		}
	}else{ if(firstClick instanceof SpellButton){
		    if(secondClick == ((SpellButton) firstClick).getActivate()){
		    	if(e.getSource() instanceof MonsterButton){
		    		MonsterCard m = (MonsterCard) ((CardButton) e.getSource()).getCard();
		    		if(p.activateSpell((SpellCard) ((CardButton) firstClick).getCard(), m)){
		    			
		    			secondClick= null;
		    			firstClick = null;
		    			
		    		}
		    	}
		    }
	}
		
	}
	
	}
}
public void itemsActions(ActionEvent e , Player p){
	JMenuItem item = (JMenuItem) e.getSource();
	if(firstClick instanceof MonsterButton){
	if(((MonsterButton) firstClick).getSummon()==item){
		if(((MonsterCard) ((CardButton) firstClick).getCard()).getLevel()<=4){
		if(p.summonMonster((MonsterCard) ((CardButton) firstClick).getCard())){
			firstClick = null;
		}else{
			nullify();}
		}
		else{ secondClick=((MonsterButton) firstClick).getSummon();
		String str=(((MonsterCard) ((CardButton) firstClick).getCard()).getLevel()<=6)? "1":"2" ;
			JOptionPane.showMessageDialog(controller.getGameBoard(), "This Monster Needs "+str+" Tribute(s) to Summon");
		}
		}else{
			
		if(((MonsterButton) firstClick).getSet()==item){
			if(((MonsterCard) ((CardButton) firstClick).getCard()).getLevel()<=4){
			if(p.setMonster((MonsterCard) ((CardButton) firstClick).getCard())){
				firstClick = null;
			}else{nullify();}}else{
			
				secondClick = ((MonsterButton) firstClick).getSet();
				String str=(((MonsterCard) ((CardButton) firstClick).getCard()).getLevel()<=6)? "1":"2" ;
				JOptionPane.showMessageDialog(controller.getGameBoard(), "This Monster Needs "+str+"Tribute(s) to Set");
			
		}}else{
			if(((MonsterButton) firstClick).getAttack()==item){
				if(Card.getBoard().getOpponentPlayer().getField().getMonstersArea().size()==0){
				if(p.declareAttack((MonsterCard) ((CardButton) firstClick).getCard())){
					firstClick = null;
				}else{ nullify();
					}}else{
				
					secondClick = item;
					JOptionPane.showMessageDialog(controller.getGameBoard(), "Please choose the monster you want to attack");
					}	
		}else{
			if(((MonsterButton) firstClick).getSwitch()==item){
				if(p.switchMonsterMode((MonsterCard) ((CardButton) firstClick).getCard())){
					firstClick =null;
				}else{
					nullify();
				}
			}
			}
	}
}}
	else{
		if(firstClick instanceof SpellButton){
			if(((SpellButton) firstClick).getSet()==item){
				if(p.setSpell((SpellCard) ((SpellButton) firstClick).getCard())){
				firstClick =null;	
				}else{
					nullify();
				}
			}
		else{ if(((SpellButton) firstClick).getActivate()==item){
			
			switch(((SpellButton) firstClick).getCard().getName()){
			case"Change Of Heart":
			case "Mage Power":
			case"Graceful Dice":JOptionPane.showMessageDialog(controller.getGameBoard(), "Please choose the monster you want to attack");
				secondClick=item;break;
			case"Card Destruction":
			case "Harpie's Feather Duster":
			case"Pot of Greed": 
			case "Monster Reborn":
			case "Dark Hole":
			case"Heavy Storm":
			case"Raigeki":if(p.activateSpell((SpellCard) ((CardButton) firstClick).getCard(), null)){
				firstClick=null;
			}else{nullify();}break;
			default: nullify();
			}
		}else{
			nullify();
		}
			
		}
	}}

}


public void nullify() {
	// TODO Auto-generated method stub
	firstClick = null;
	secondClick = null;
	thirdClick = null;
	fourthClick = null;
}

public GameEnder getEndGame() {
	return endGame;
}
public void setEndGame(GameEnder endGame) {
	this.endGame = endGame;
}
}

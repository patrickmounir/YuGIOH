package eg.edu.guc.yugioh.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Color;
import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import eg.edu.guc.yugioh.board.player.Player;

@SuppressWarnings("serial")
public class GameBoard extends JFrame{
	private PlayerPanel pp1;
	private PlayerPanel pp2;
	private JLabel main1;
	private JLabel  battle;
	private JLabel main2;
	
	private JLabel pic;
	private JTextArea text;
	
	public GameBoard(Player p1, Player p2) throws Exception{
		super("Yu-Gi-OH!");
		setUndecorated(true);
        setContentPane(new JLabel(new ImageIcon("cards//back.jpg")));
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setLayout(new FlowLayout());
		//setSize(1400,1200);
		
		JPanel middle = new JPanel();
		middle.setSize(new Dimension(1160,1200));
		JPanel left = new JPanel ();
		left.setSize(new Dimension(333,1200));
		left.setLayout(new BorderLayout());
		middle.setLayout(new BorderLayout());
		pic = new JLabel();
		pic.setIcon(new ImageIcon("cards//card big.jpg"));
		pic.setPreferredSize(new Dimension(333,548));
		left.add(pic, BorderLayout.NORTH);
		
		text= new JTextArea(" ");
		text.setLineWrap(true);
		text.setEditable(false);
        text.setWrapStyleWord(true);
        text.setForeground(new Color(0,0,102));
		JScrollPane scroller = new JScrollPane(text);
        scroller.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scroller.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS); 
        text.setBackground(null);
         text.setOpaque(false);
         scroller.setBackground(null);
         scroller.setAutoscrolls(true);
         scroller.setOpaque(false);
         text.setFont(new Font("PerryGothic",Font.BOLD,25));
		text.setPreferredSize(new Dimension(333,254));left.add(scroller,BorderLayout.WEST);
         main1 = new JLabel("<html><br>Main 1</html>");
         main1.setOpaque(false);
         main1.setBackground(null);
         
         main1.setFont(new Font("PerryGothic",Font.BOLD,55));
         main2 = new JLabel("<html><br>Main 2</html>");
         main2.setFont(new Font("PerryGothic",Font.BOLD,55));
         main2.setOpaque(false);
         main2.setBackground(null);
         
         battle = new JLabel("<html><br>Battle</html>");
         main1.setForeground(new Color(255,255,255));
         main2.setForeground(Color.BLACK);
         battle.setForeground(Color.BLACK);
        battle.setFont(new Font("PerryGothic",Font.BOLD,55));
         battle.setOpaque(false);
         battle.setBackground(null);
         JPanel phase = new JPanel();
         phase.setOpaque(false);
         phase.setBackground(null);
         phase.setLayout(new BorderLayout());
         phase.add(main1,BorderLayout.NORTH);
         phase.add(battle,BorderLayout.CENTER);
         phase.add(main2,BorderLayout.SOUTH);
         JPanel allRight = new JPanel();
         allRight.setLayout(new FlowLayout());
         allRight.add(phase);
         allRight.add(left);
         allRight.setOpaque(false);
         allRight.setBackground(null);
		pp1 = new PlayerPanel(p1);
		pp2 = new PlayerPanel(p2);
	
		
		middle.add(pp1,BorderLayout.NORTH);
		middle.add(pp2,BorderLayout.SOUTH);
		
		add(middle);
		add(allRight);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		left.setOpaque(false);
		middle.setOpaque(false);
		setVisible(false);
		revalidate();
		
	}
	
	
	public PlayerPanel getPp1() {
		return pp1;
	}
	public void setPp1(PlayerPanel pp1) {
		this.pp1 = pp1;
	}
	public PlayerPanel getPp2() {
		return pp2;
	}
	public void setPp2(PlayerPanel pp2) {
		this.pp2 = pp2;
	}
	
	public JLabel getPic() {
		return pic;
	}
	public void setPic(String pic) {
		this.pic.setIcon(new ImageIcon("cards//"+pic+".jpg"));
		revalidate();
	}
	public JTextArea getText() {
		return text;
	}
	
	public void setText(String text) {
		this.text.setText("\n"+text); 	
revalidate();}


	public JLabel getMain1() {
		return main1;
	}


	public void setMain1(JLabel main1) {
		this.main1 = main1;
	}


	public JLabel getBattle() {
		return battle;
	}


	public void setBattle(JLabel battle) {
		this.battle = battle;
	}


	public JLabel getMain2() {
		return main2;
	}


	public void setMain2(JLabel main2) {
		this.main2 = main2;
	}
}

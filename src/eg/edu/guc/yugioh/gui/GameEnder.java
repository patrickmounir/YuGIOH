package eg.edu.guc.yugioh.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class GameEnder extends JFrame {
private JButton yes;
private JButton no;
	public GameEnder(){
	super();
	setUndecorated(true);
	setSize(new Dimension(250,150));
    setLayout(new BorderLayout());
	add(new JLabel("Do you want to start a new game?"),BorderLayout.NORTH);
	no = new JButton("NO");
	yes= new JButton("YES");
	JPanel p = new JPanel();
	p.setLayout(new FlowLayout());
	p.add(no);
	p.add(yes);
	add(p, BorderLayout.SOUTH);
    
    validate();
}
	public JButton getYes() {
		return yes;
	}
	public void setYes(JButton yes) {
		this.yes = yes;
	}
	public JButton getNo() {
		return no;
	}
	public void setNo(JButton no) {
		this.no = no;
	}
	
}

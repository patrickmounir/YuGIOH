package eg.edu.guc.yugioh.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class StartUpWindow extends JFrame{
	private CustomButton start;
	private JTextField p1Name;
	private JTextField p2Name;
	
	
	
	public StartUpWindow(){
		super("Yu-Gi-Oh!");
		setUndecorated(true);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setBackground(new Color(168,121,48));
		setContentPane(new JLabel(new ImageIcon("cards//start.jpg")));
		setLayout(new BorderLayout());
		start = new CustomButton("Play");
		start.setFont(new Font("PerryGothic",Font.CENTER_BASELINE,30));
		JPanel middle = new JPanel();
		JPanel panel = new JPanel();
		panel.setLayout(new FlowLayout());
		panel.setBackground(null);
		panel.setSize(new Dimension(400,400));
		panel.setOpaque(false);
		middle.setBackground(null);
		middle.setLayout(new FlowLayout());
		middle.setOpaque(false);
		JLabel first = new JLabel ("<html><br>Please enter <br>the first Player name</html>");
		first.setForeground(new Color(0,0,102));
		first.setFont(new Font("PerryGothic",Font.CENTER_BASELINE,20));
		JLabel second = new JLabel ("<html><br>Please enter <br>the second Player name</html>");
		second.setForeground(new Color(0,0,102));
		second.setFont(new Font("PerryGothic",Font.CENTER_BASELINE,20));
		p1Name = new JTextField();
		p1Name.setPreferredSize(new Dimension(200,30));
		p1Name.setFont(new Font("PerryGothic", Font.ROMAN_BASELINE, 20));
		start.setForeground(new Color(0,0,102));
		CustomButton exit = new CustomButton("EXIT");
		exit.setFont(new Font("PerryGothic",Font.CENTER_BASELINE,30));
		exit.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				System.exit(0);
			}
			
		});
		exit.setForeground(new Color(0,0,102));
		p2Name= new JTextField();
		p2Name.setPreferredSize(new Dimension(200,30));
		p2Name.setFont(new Font("PerryGothic", Font.ROMAN_BASELINE, 20));
		middle.add(first);
		middle.add(p1Name);
		middle.add(second);
		middle.add(p2Name);
		
		panel.add(start);
		panel.add(exit);
		add(middle,BorderLayout.NORTH);
		add(panel,BorderLayout.CENTER);
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
		validate();
		
	}
public CustomButton getStart() {
		return start;
	}
	public void setStart(CustomButton start) {
		this.start = start;
	}

public JTextField getP1Name() {
	return p1Name;
}
public void setP1Name(JTextField p1Name) {
	this.p1Name = p1Name;
}
public JTextField getP2Name() {
	return p2Name;
}
public void setP2Name(JTextField p2Name) {
	this.p2Name = p2Name;
}
}

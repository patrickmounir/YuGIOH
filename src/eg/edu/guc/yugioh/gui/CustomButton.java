package eg.edu.guc.yugioh.gui;

import java.awt.Dimension;
import java.awt.Font;
import javax.swing.JButton;

@SuppressWarnings("serial")
public class CustomButton extends JButton{
public CustomButton(String message){
	super();
	setPreferredSize(new Dimension(200,50));
	setBorder(null);
    setBorderPainted(false);
    setContentAreaFilled(false);
	setOpaque(false);
	
	
	setText(message);
	CustomFont f=new CustomFont(message,Font.BOLD);
	
	setFont(f);
	setVisible(true);
}
}

package eg.edu.guc.yugioh.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.GraphicsEnvironment;
import java.io.File;
import java.io.IOException;

import javax.swing.JComponent;

public class CustomFont extends Font {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String string;
    @SuppressWarnings("unused")
	private int FontStyle;

    public CustomFont(String text, int style) {
        super("PerryGothic", style, 15);
        GraphicsEnvironment ge = 
		         GraphicsEnvironment.getLocalGraphicsEnvironment();
		     try {
				ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("PERRYGOT.ttf")));
			} catch (FontFormatException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        FontStyle = style;
        string = text;  
        
        Drawing draw = new Drawing();
        draw.repaint();
    }

    private class Drawing extends JComponent {
        /**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		protected void paintComponent(Graphics g) {
            super.paintComponent(g);

            Font font = new Font("PerryGothic", Font.BOLD, 15);
            g.setFont(font);
            g.setColor(Color.YELLOW);
            g.drawString(string, 0, 0);
      }
    }
}
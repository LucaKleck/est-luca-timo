package frame;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JScrollPane;

public class JScrollPaneBg extends JScrollPane {
	private static final long serialVersionUID = -3730877990751762965L;

	public JScrollPaneBg() {
		setBackground(new Color(0,0,0,0));
		setOpaque(false);
	}
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
	}
}

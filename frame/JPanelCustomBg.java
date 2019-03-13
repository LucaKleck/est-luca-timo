package frame;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

public class JPanelCustomBg extends JPanel {
	private static final long serialVersionUID = 8407021853349418385L;
	private BufferedImage background;
	
	public JPanelCustomBg(BufferedImage background) {
		this.background = background;
		setBackground(new Color(0, 0, 0, 0));
		setOpaque(false);
	}


	@Override
	public void paint(Graphics g) {
		g.drawImage(background, 0, 0, getWidth(), getHeight(), null);
		super.paint(g);
	}
}

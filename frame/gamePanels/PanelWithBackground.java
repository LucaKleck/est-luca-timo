package frame.gamePanels;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

import frame.graphics.ResourceManager;

/**
 * @author Luca Kleck
 * 
 */
public class PanelWithBackground extends JPanel {
	private static final long serialVersionUID = 1L;

	public PanelWithBackground() {
		setForeground(Color.yellow);
		setOpaque(false);
		setBackground(new Color(0, 0, 0, 0));
	}
	
	@Override
	public void paint(Graphics g) {
		g.drawImage(ResourceManager.getBackground_02(), 0, 0, getWidth(), getHeight(), null);
		super.paint(g);
	}
}

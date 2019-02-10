package frame.gamePanels;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

import core.ResourceManager;

/**
 * @author Luca Kleck
 * 
 */
public class PanelWithVerticalBackground extends JPanel {
	private static final long serialVersionUID = 1L;

	public PanelWithVerticalBackground() {
		setBackground(new Color(0, 0, 0, 0));
		setOpaque(false);
	}
	
	@Override
	public void paint(Graphics g) {
		g.drawImage(ResourceManager.getBackgroundWoodVertical(), 0, 0, getWidth(), getHeight(), null);
		super.paint(g);
	}
}

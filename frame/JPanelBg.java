package frame;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

import core.ResourceManager;

public class JPanelBg extends JPanel {
	private static final long serialVersionUID = -823599225488240219L;
	
	public JPanelBg() {
		setBackground(new Color(0, 0, 0, 0));
		setOpaque(false);
	}

	@Override
	public void paint(Graphics g) {
		g.drawImage(ResourceManager.getButton_04_bg(), 0, 0, getWidth(), getHeight(), null);
		g.drawImage(ResourceManager.getButton_04_frame(), 0, 0, getWidth(), getHeight(), null);
		super.paint(g);
	}
}

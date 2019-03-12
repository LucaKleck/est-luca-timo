package frame;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;

import javax.swing.JButton;

import core.ResourceManager;

public class JButton_01 extends JButton {
	private static final long serialVersionUID = 8270095812722406884L;

	public JButton_01(String string) {
		super(string);
		setBackground(new Color(0, 0, 0, 0));
		setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		setOpaque(false);
		setForeground(Color.YELLOW);
		addMouseListener(new MouseHoverListener(this));
	}

	@Override
	public void paint(Graphics g) {
		g.drawImage(ResourceManager.getButton_01(), 0, 0, getWidth(), getHeight(), null);
		super.paint(g);
	}
	
	
}

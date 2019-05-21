package frame.customPresets;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;

import javax.swing.JButton;

import frame.graphics.ResourceManager;

public class JButton_02 extends JButton {
	private static final long serialVersionUID = -7928816224518625719L;
	public static final int UP = 1;
	public static final int RIGHT = 3;
	public static final int DOWN = 5;
	public static final int LEFT = 7;
	
	private int direction;
	
	public JButton_02(int direction) {
		this.direction = direction;
		setBackground(new Color(0, 0, 0, 0));
		setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		setOpaque(false);
		setForeground(Color.YELLOW);
		addMouseListener(new MouseHoverListener(this));
	}

	@Override
	public void paint(Graphics g) {
		g.drawImage(ResourceManager.getButton_04_bg(), 0, 0, getWidth(), getHeight(), null);
		switch (direction) {
		case DOWN:
			g.drawImage(ResourceManager.getSlider_02_04(), 0, 0, getWidth(), getHeight(), null);
			break;
		case LEFT:
			g.drawImage(ResourceManager.getSlider_02_04(), 0, 0, getWidth(), getHeight(), null);
			break;
		case UP:
			g.drawImage(ResourceManager.getSlider_02_03(), 0, 0, getWidth(), getHeight(), null);
			break;
		case RIGHT:
			g.drawImage(ResourceManager.getSlider_02_04(), 0, 0, getWidth(), getHeight(), null);
			break;
		}
		super.paint(g);
	}
}

package frame.customPresets;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.JButton;

import frame.graphics.ResourceManager;

public class JButtonCustomBg extends JButton {
	private static final long serialVersionUID = 8300127069468952763L;
	private BufferedImage image;
	private boolean hasFrame;
	public JButtonCustomBg(BufferedImage image, boolean hasFrame, String text) {
		this.hasFrame  = hasFrame;
		this.image = image;
		setText(text);
		setBackground(new Color(0, 0, 0, 0));
		setForeground(Color.YELLOW);
		setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		setOpaque(false);
		addMouseListener(new MouseHoverListener(this));
	}

	@Override
	public void paint(Graphics g) {
		g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
		if(hasFrame) {
			g.drawImage(ResourceManager.getFrame(), 0, 0, getWidth(), getHeight(), null);
		}
		super.paint(g);
	}
}

package frame;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.JViewport;

import core.ResourceManager;

public class JViewportCustomBg extends JViewport {
	private static final long serialVersionUID = 9101930974609155487L;
	private BufferedImage background;
	private boolean hasFrame;
	
	public JViewportCustomBg(BufferedImage background, boolean hasFrame) {
		this.background = background;
		this.hasFrame = hasFrame;
		setBackground(new Color(138, 115, 91));
		setOpaque(true);
	}
	
	@Override
	public void paint(Graphics g) {
		g.drawImage(background, 0, 0, getWidth(), getHeight(), null);
		super.paint(g);
		if(hasFrame) {
			g.drawImage(ResourceManager.getFrame_10_01(), 0, 0, 10, getHeight(), null);
			g.drawImage(ResourceManager.getFrame_10_02(), getWidth()-10, 0, 10, getHeight(), null);
		}
		
	}

}

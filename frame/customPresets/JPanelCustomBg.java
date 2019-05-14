package frame.customPresets;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

import core.ResourceManager;

public class JPanelCustomBg extends JPanel {
	private static final long serialVersionUID = 8407021853349418385L;
	private BufferedImage background;
	private boolean hasLeftRightFrame;
	private boolean hasTopBottomFrame;
	
	public JPanelCustomBg(BufferedImage background) {
		hasLeftRightFrame = false;
		this.background = background;
		setBackground(new Color(0, 0, 0, 0));
		setOpaque(false);
	}
	
	public JPanelCustomBg(BufferedImage background, boolean lr) {
		hasLeftRightFrame = lr;
		this.background = background;
		setBackground(new Color(0, 0, 0, 0));
		setOpaque(false);
	}
	
	public JPanelCustomBg(BufferedImage background, boolean lr, boolean tb) {
		hasLeftRightFrame = lr;
		hasTopBottomFrame = tb;
		this.background = background;
		setBackground(new Color(0, 0, 0, 0));
		setOpaque(false);
	}


	@Override
	public void paint(Graphics g) {
		g.drawImage(background, 0, 0, getWidth(), getHeight(), null);
		super.paint(g);
		if(hasTopBottomFrame) {
			g.drawImage(ResourceManager.getFrame_10_03(), 0, getHeight()-10, getWidth(), 10, null);
			g.drawImage(ResourceManager.getFrame_10_04(), 0, 0, getWidth(), 10, null);
		}
		if(hasLeftRightFrame) {
			g.drawImage(ResourceManager.getFrame_10_01(), 0, 0, 10, getHeight(), null);
			g.drawImage(ResourceManager.getFrame_10_02(), getWidth()-10, 0, 10, getHeight(), null);
		}
	}
}

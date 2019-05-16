package frame.customPresets;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import core.ResourceManager;

public class StatisticLabel extends JLabel {

	private static final long serialVersionUID = 6270195112324466784L;
	private boolean hasBg = false;
	
	public StatisticLabel(String name) {
		super(name, SwingConstants.CENTER);
		setBackground(new Color(0, 0, 0, 0));
		setOpaque(false);
		setForeground(Color.YELLOW);
	}
	
	public StatisticLabel(String name, boolean hasBg) {
		super(name, SwingConstants.CENTER);
		this.hasBg = hasBg;
		setHorizontalAlignment(SwingConstants.LEFT);
		setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		setBackground(new Color(0, 0, 0, 0));
		setOpaque(false);
		setForeground(Color.YELLOW);
	}
	
	@Override
	public void paint(Graphics g) {
		if(hasBg) {
			g.drawImage(ResourceManager.getText_bg_02(), 0, 0, getWidth(), getHeight(), null);
		}
		super.paint(g);
	}
}

package frame.customPresets;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.BorderFactory;
import javax.swing.JLabel;

import core.ResourceManager;

public class CustomLable extends JLabel {

	private static final long serialVersionUID = 6270195112324466784L;
	private boolean hasBg = false;
	
	public CustomLable(String name) {
		super(name);
		setBackground(new Color(0, 0, 0, 0));
		setOpaque(false);
		setForeground(Color.YELLOW);
	}
	
	public CustomLable(String name, boolean hasBg) {
		this(name);
		this.hasBg = hasBg;
		setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
	}
	
	public CustomLable(String name, boolean hasBg, int hAlignment) {
		this(name, hasBg);
		setHorizontalAlignment(hAlignment);
	}
	
	@Override
	public void paint(Graphics g) {
		if(hasBg) {
			g.drawImage(ResourceManager.getText_bg_02(), 0, 0, getWidth(), getHeight(), null);
		}
		super.paint(g);
	}
}

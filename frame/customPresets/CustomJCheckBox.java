package frame.customPresets;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JCheckBox;

import frame.graphics.ResourceManager;

public class CustomJCheckBox extends JCheckBox {
	private static final long serialVersionUID = 3954271410930209173L;
	private boolean hasBg = false;

	public CustomJCheckBox(String text, boolean hasBg) {
		super(text); // new ImageIcon(ResourceManager.getButton_04_frame().getScaledInstance(16, 16, Image.SCALE_SMOOTH))
		this.hasBg = hasBg;
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

package frame.customPresets;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JProgressBar;
import javax.swing.plaf.basic.BasicProgressBarUI;

import core.ResourceManager;
import entity.Entity;

public class CustomProgressBar extends JProgressBar {
	private static final long serialVersionUID = 6329206797198034182L;

	public CustomProgressBar() {
		setUI(new CustomBarUI());
		setOpaque(false);
		setBackground(new Color(0, 0, 0, 0));
		setForeground(new Color(10, 150, 30));
		setBorder(BorderFactory.createEmptyBorder(2, 2, 2, 2));
		setMinimum(0);
		setValue(0);
		setStringPainted(true);
	}

	class CustomBarUI extends BasicProgressBarUI {
		@Override
		protected void paintDeterminate(Graphics g, JComponent c) {
			  g.drawImage(ResourceManager.getText_bg_02(), 0, 0, progressBar.getWidth(), progressBar.getHeight(), null);
			  super.paintDeterminate(g, c);
		}
	}
	
	public static CustomProgressBar createFromEntity(Entity entity) {
		CustomProgressBar p = new CustomProgressBar();
		p.setMaximum(entity.getMaxHealth());
		p.setValue(entity.getCurrentHealth());
		p.setStringPainted(true);
		p.setString(entity.getCurrentHealth() + "/" + entity.getMaxHealth());
		
		if(entity.isControllable() == false) {
			p.setForeground(new Color(220, 20, 40));
		}
		
		return p;
	}
	
}

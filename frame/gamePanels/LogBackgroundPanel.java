package frame.gamePanels;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import core.Core;

public class LogBackgroundPanel extends JPanel {
	private static final long serialVersionUID = 2527363637826501962L;

	public LogBackgroundPanel() {
		setBackground(new Color(0, 0, 0, 120));
		setOpaque(false);
		setVisible(new Boolean(Core.getSetting(Core.SETTING_SHOW_LOG)));
	}

	@Override
	protected void paintComponent(Graphics g) {
		g.setColor(getBackground());
		GradientPaint dartToDarker = new GradientPaint(0, getHeight(), new Color(0,0,0,200), 0, 0, getBackground());
		((Graphics2D) g).setPaint(dartToDarker);
		((Graphics2D) g).fillRect(0, 0, getWidth(), getHeight());
		super.paintComponent(g);
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
	}
	
}

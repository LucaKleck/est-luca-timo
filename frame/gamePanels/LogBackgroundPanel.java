package frame.gamePanels;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

public class LogBackgroundPanel extends JPanel {
	private static final long serialVersionUID = 2527363637826501962L;
	public LogBackgroundPanel() {
		setBackground(new Color(0, 0, 0, 120));
		setOpaque(false);
	}
	protected void paintComponent(Graphics g) {
        g.setColor( getBackground() );
        g.fillRect(0, 0, getWidth(), getHeight());
        super.paintComponent(g);
    }
	
	@Override
    public void paint(Graphics g) {
        super.paint(g);
    }
	
}

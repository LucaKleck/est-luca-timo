package frame.gamePanels;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JTextArea;

public class JTextAreaLog extends JTextArea {
	private static final long serialVersionUID = 8081537743411532128L;

	public JTextAreaLog() {
		setOpaque(false);
		setBackground(new Color(0,0,0,120));
		setSelectionColor(Color.DARK_GRAY);
		setSelectedTextColor(Color.WHITE);
		setForeground(Color.WHITE);
		setEditable(false);
		setFont(new Font("MS PGothic", Font.PLAIN, 16));
		setText("This is the log, keeping track of all important events");
	}
	
	@Override
    public void paint(Graphics g) {
        super.paint(g);
    }
}

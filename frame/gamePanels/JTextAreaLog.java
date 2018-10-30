package frame.gamePanels;

import java.awt.AWTEvent;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JTextArea;

public class JTextAreaLog extends JTextArea {
	private static final long serialVersionUID = 8081537743411532128L;

	public JTextAreaLog() {
		setWrapStyleWord(true);
		setLineWrap(true);
		setOpaque(false);
		setAutoscrolls(true);
		setFont(new Font("MS PGothic", Font.BOLD, 13));
		setSelectionColor(Color.DARK_GRAY);
		setSelectedTextColor(Color.WHITE);
		setForeground(Color.WHITE);
		setEditable(false);
		setText("This is the log, keeping track of all important events");
		disableEvents(AWTEvent.KEY_EVENT_MASK | AWTEvent.INPUT_METHOD_EVENT_MASK);
		
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
	}
	
}

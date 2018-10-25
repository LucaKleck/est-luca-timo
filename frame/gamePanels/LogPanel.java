package frame.gamePanels;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;

import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;

public class LogPanel extends JScrollPane {
	private static final long serialVersionUID = 123L;
	private static JTextArea log = new JTextArea();
	
	public LogPanel() {
		
		setDoubleBuffered(true);
		setFocusTraversalKeysEnabled(false);
		setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		setCursor(Cursor.getPredefinedCursor(Cursor.TEXT_CURSOR));
		setAutoscrolls(true);
		setBackground(new Color(0,0,0,0));
		log.setSelectionColor(Color.DARK_GRAY);
		log.setSelectedTextColor(Color.WHITE);
		log.setForeground(Color.WHITE);
		
		log.setBackground(new Color(0,0,0,120));
		log.setEditable(false);
		log.setText("This is the log, keeping track of all important events");
		log.setFont(new Font("MS PGothic", Font.PLAIN, 16));
		setViewportView(log);
		
	}
	public static JTextArea getLog() {
		return log;
	}
}

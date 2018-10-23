package frame.gamePanels;

import java.awt.Font;

import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import java.awt.Cursor;
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
		
		log.setEditable(false);
		log.setText("This is the log, keeping track of all important events");
		log.setFont(new Font("MS PGothic", Font.PLAIN, 16));
		setViewportView(log);
		
	}
	public static JTextArea getLog() {
		return log;
	}
}

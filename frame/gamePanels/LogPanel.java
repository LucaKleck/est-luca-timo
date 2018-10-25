package frame.gamePanels;

import java.awt.Cursor;
import java.awt.Graphics;

import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;

public class LogPanel extends JScrollPane {
	private static final long serialVersionUID = 123L;
	private static JTextArea log = new JTextAreaLog();
	
	public LogPanel() {
		getVerticalScrollBar().setUnitIncrement(8);
		
		setDoubleBuffered(true);
		setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		setCursor(Cursor.getPredefinedCursor(Cursor.TEXT_CURSOR));
		setViewportView(log);
		
		setOpaque(false);
		
		this.getViewport().setOpaque(false);
		
	}
 	@Override
    public void paint(Graphics g) {
        super.paint(g);
    }
	public static JTextArea getLog() {
		return log;
	}
	public static void appendNewLine(String line) {
		try {
			LogPanel.getLog().append(System.lineSeparator()+" "+line);
		} catch (NullPointerException nl) {
		}
	}
	
}

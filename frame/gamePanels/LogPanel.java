package frame.gamePanels;

import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;

import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;

import frame.MainJFrame;

public class LogPanel extends JScrollPane {
	private static final long serialVersionUID = 123L;
	private static JTextArea log = new JTextAreaLog();
	
	public LogPanel() {
		getVerticalScrollBar().setUnitIncrement(8);
		
		setDoubleBuffered(true);
		setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		setCursor(Cursor.getPredefinedCursor(Cursor.TEXT_CURSOR));
		setAutoscrolls(true);
		
		setViewportView(log);
		
		setOpaque(false);
		
		this.getViewport().setOpaque(false);
		
		this.getVerticalScrollBar().addAdjustmentListener(new AdjustmentListener() {
			@Override
		    public void adjustmentValueChanged(final AdjustmentEvent e) {
				MainJFrame.staticRepaint();
		    }
		});
		
	    this.getHorizontalScrollBar().addAdjustmentListener(new AdjustmentListener() {
	        @Override
	        public void adjustmentValueChanged(final AdjustmentEvent e) {
	        	MainJFrame.staticRepaint();
	        }
	    });
	}
 	@Override
    public void paint(Graphics g) {
        super.paint(g);
    }
	public static JTextArea getLog() {
		return log;
	}
	public static void appendNewLine(String line) {
		LogPanel.getLog().append(System.lineSeparator()+line); 
	}
	
}

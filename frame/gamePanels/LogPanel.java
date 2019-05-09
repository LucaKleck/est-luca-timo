package frame.gamePanels;

import java.awt.AWTEvent;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.util.Calendar;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.LookAndFeel;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.MatteBorder;
import javax.swing.plaf.basic.BasicArrowButton;
import javax.swing.plaf.basic.BasicScrollBarUI;

import core.Core;

/**
 * @author Luca Kleck
 */
public class LogPanel extends JScrollPane {
	private static final long serialVersionUID = 123L;
	private static JTextArea log = new JTextAreaLog();
	
	private static boolean loaded = false;	
	private static String loadText = "";
	
	public LogPanel() {
		setBorder(new MatteBorder(2, 0, 0, 0, new Color(0, 0, 0, 100)));
		setName("GameLog");
		getVerticalScrollBar().setUnitIncrement(8);

		setDoubleBuffered(true);
		setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		log.setEnabled(new Boolean(Core.getSetting(Core.SETTING_ENABLE_LOG)));
		log.setAutoscrolls(true);
		setViewportView(log);
		
		setOpaque(false);
		
		Component horizontalStrut = Box.createHorizontalStrut(20);
		horizontalStrut.setPreferredSize(new Dimension(5, 0));
		horizontalStrut.setEnabled(false);
		setRowHeaderView(horizontalStrut);
		this.getVerticalScrollBar().setUI(new LogScrollBarUI());
		this.getVerticalScrollBar().setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
		
		this.getVerticalScrollBar().setOpaque(false);
		this.getViewport().setOpaque(false);
		this.getRowHeader().setOpaque(false);

	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
	}

	public static JTextArea getLog() {
		return log;
	}

	public static void appendNewLine(String line) {
		String time = ""+Calendar.getInstance().get(Calendar.HOUR_OF_DAY)+":"+Calendar.getInstance().get(Calendar.MINUTE)+" - ";
		if(Core.getMainJFrame().getCurrentComponent() instanceof MainGamePanel) {
			LogPanel.getLog().append(System.lineSeparator() + time + line);
			LogPanel.getLog().setCaretPosition(LogPanel.getLog().getDocument().getLength());
		}
	}
	
	public static void setLoad(String textAfterReset) {
		loaded = true;
		LogPanel.loadText = textAfterReset;
	}
	
	class LogScrollBarUI extends BasicScrollBarUI {
		@Override
		protected void configureScrollBarColors() {
	        LookAndFeel.installColors(scrollbar, "ScrollBar.background",
	                                  "ScrollBar.foreground");
	        thumbHighlightColor = new Color(255, 255, 255, 120);
	        thumbLightShadowColor = new Color(255, 255, 255, 200);
	        thumbDarkShadowColor = new Color(1, 1, 1, 120);
	        thumbColor = new Color(0, 0, 0, 120);
	        trackColor = new Color(255, 255, 255, 40);
	        trackHighlightColor = new Color(255, 255, 255, 50);
	    }
		@Override
		protected JButton createDecreaseButton(int orientation) {
			JButton btn = new BasicArrowButton(orientation, new Color(0, 0, 0, 50), new Color(255, 255, 255, 120),
					new Color(255, 255, 255, 120), new Color(255, 255, 255, 120));
			btn.setOpaque(false);
			return btn;
		}

		@Override
		protected JButton createIncreaseButton(int orientation) {
			JButton btn = new BasicArrowButton(orientation, new Color(0, 0, 0, 50), new Color(255, 255, 255, 120),
					new Color(255, 255, 255, 120), new Color(255, 255, 255, 120));
			btn.setOpaque(false);
			return btn;
		}
		
	}
	
	private static class JTextAreaLog extends JTextArea {
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
			if(loaded == true) {
				setText(loadText);
				loaded = false;
			}
		}
		
	}
}

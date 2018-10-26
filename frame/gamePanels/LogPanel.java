package frame.gamePanels;

import static sun.swing.SwingUtilities2.drawHLine;
import static sun.swing.SwingUtilities2.drawRect;
import static sun.swing.SwingUtilities2.drawVLine;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Rectangle;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.LookAndFeel;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.MatteBorder;
import javax.swing.plaf.basic.BasicArrowButton;
import javax.swing.plaf.basic.BasicScrollBarUI;
import java.awt.Component;
import javax.swing.Box;
import java.awt.Dimension;

public class LogPanel extends JScrollPane {
	private static final long serialVersionUID = 123L;
	private static JTextArea log = new JTextAreaLog();

	public LogPanel() {
		setBorder(new MatteBorder(2, 0, 0, 0, new Color(0, 0, 0, 100)));
		setName("GameLog");
		getVerticalScrollBar().setUnitIncrement(8);

		setDoubleBuffered(true);
		setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		setCursor(Cursor.getPredefinedCursor(Cursor.TEXT_CURSOR));
		setViewportView(log);

		setOpaque(false);
		
		Component horizontalStrut = Box.createHorizontalStrut(20);
		horizontalStrut.setPreferredSize(new Dimension(5, 0));
		horizontalStrut.setVisible(false);
		horizontalStrut.setEnabled(false);
		horizontalStrut.setFocusTraversalKeysEnabled(false);
		horizontalStrut.setFocusable(false);
		setRowHeaderView(horizontalStrut);
		this.getVerticalScrollBar().setUI(new LogScrollBarUI());
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
		try {
			LogPanel.getLog().append(System.lineSeparator() + line);
		} catch (NullPointerException nl) {
		}
	}

	class LogScrollBarUI extends BasicScrollBarUI {
		@Override
		protected void configureScrollBarColors() {
	        LookAndFeel.installColors(scrollbar, "ScrollBar.background",
	                                  "ScrollBar.foreground");
	        thumbHighlightColor = new Color(255, 255, 255, 120);
	        thumbLightShadowColor = new Color(255, 255, 255, 120);
	        thumbDarkShadowColor = new Color(255, 255, 255, 120);
	        thumbColor = new Color(0, 0, 0, 120);
	        trackColor = new Color(255, 255, 255, 120);
	        trackHighlightColor = new Color(255, 255, 255, 120);
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
		
		@Override
		protected void paintTrack(Graphics g, JComponent c, Rectangle trackBounds) {
	        g.setColor(trackColor);
	        g.drawRect(trackBounds.x, trackBounds.y, trackBounds.width, trackBounds.height);

	        if(trackHighlight == DECREASE_HIGHLIGHT)        {
	            paintDecreaseHighlight(g);
	        }
	        else if(trackHighlight == INCREASE_HIGHLIGHT)           {
	            paintIncreaseHighlight(g);
	        }
	    }

		@Override
		protected void paintThumb(Graphics g, JComponent c, Rectangle thumbBounds) {
			if (thumbBounds.isEmpty() || !scrollbar.isEnabled()) {
				return;
			}

			int w = thumbBounds.width;
			int h = thumbBounds.height;

			g.translate(thumbBounds.x, thumbBounds.y);

			g.setColor(thumbDarkShadowColor);
			drawRect(g, 0, 0, w - 1, h - 1);
			g.setColor(thumbColor);
			g.fillRect(0, 0, w - 1, h - 1);

			g.setColor(thumbHighlightColor);
			drawVLine(g, 1, 1, h - 2);
			drawHLine(g, 2, w - 3, 1);

			g.setColor(thumbLightShadowColor);
			drawHLine(g, 2, w - 2, h - 2);
			drawVLine(g, w - 2, 1, h - 3);

			g.translate(-thumbBounds.x, -thumbBounds.y);
		}

	}
}

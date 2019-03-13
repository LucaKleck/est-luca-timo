package frame;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.BorderFactory;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

public class JScrollPaneBg extends JScrollPane {
	private static final long serialVersionUID = -3730877990751762965L;
	
	public JScrollPaneBg(BufferedImage image) {
		setViewport(new JViewportCustomBg(image, false));
		DragAndDropJPanelMouseAdapter ma = new DragAndDropJPanelMouseAdapter(getViewport());
		addMouseListener(ma);
		addMouseMotionListener(ma);
		setBackground(new Color(0,0,0,0));
		setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
		setOpaque(false);
		
		setBorder(null);
		setAutoscrolls(true);
		
		getViewport().setOpaque(false);
		getViewport().setBackground(new Color(0,0,0,0));
		
		setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		getVerticalScrollBar().setUnitIncrement(8);
		getVerticalScrollBar().setUI(new CustomScrollBarUI());
		getVerticalScrollBar().setOpaque(false);

		getHorizontalScrollBar().setUnitIncrement(8);
		getHorizontalScrollBar().setUI(new CustomScrollBarUI());
		getHorizontalScrollBar().setOpaque(false);
		
	}
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
	}
	
}


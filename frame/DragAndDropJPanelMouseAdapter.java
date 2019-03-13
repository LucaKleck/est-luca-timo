package frame;

import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JViewport;

public class DragAndDropJPanelMouseAdapter extends MouseAdapter {
	private Point origin;
	private JViewport viewPort;
	
	public DragAndDropJPanelMouseAdapter(JViewport viewPort) {
		this.viewPort = viewPort;
	}
	
	@Override
	public void mousePressed(MouseEvent e) {
		origin = new Point(e.getPoint());
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		if (origin != null) {
			
			if (viewPort != null) {
				int deltaY = origin.y - e.getY();
				Point p = new Point();
				p.setLocation(viewPort.getViewPosition().getX(), viewPort.getViewPosition().getY() + deltaY);
				if (p.getY() < 0) {
					p.setLocation(p.getX(), 0);
				}
				viewPort.setViewPosition(p);
				origin = new Point(e.getPoint());
			}
		}
	}
}

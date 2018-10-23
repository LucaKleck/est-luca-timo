package frame.gamePanels;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.ImageObserver;

import javax.swing.JPanel;
import javax.swing.Timer;

import map.MapImage;
import map.ObjectMap;

/**  
 * Contains and draws the MapImage, takes care of the displacement
 * @author Luca Kleck 
 * @see MainGamePanel
 */
public class MapPanel extends JPanel implements ImageObserver {
	private static final long serialVersionUID = 121L;
	
	private static MapImage mapImage;
	private static MapPanel self;
	private static double displacementMultiplier = 0.8;
	private static int displacementX;
	private static int displacementY;
	private Refresh refresh = new Refresh();
	
	public MapPanel() {
		this.setName("MapPanel");
		self = this;
		mapImage = new MapImage(735, 735);
		setBackground(new Color(0, 0, 0, 0));
		refresh.run();
		this.addMouseListener(new MouseEventHandler());
	}
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		
		// Draw background
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, this.getWidth(), this.getHeight());

		// draw map image with displacement & multiplier
		g.drawImage(mapImage,
				(int) (displacementX),
				(int) (displacementY),
				(int) (this.getWidth() * displacementMultiplier),
				(int) (this.getWidth() * displacementMultiplier), this);
	}
	
	public static void addDisplacementX(int displacementX) {
		if( (MapPanel.displacementX+displacementX) < (self.getWidth()/2/displacementMultiplier) && (MapPanel.displacementX+displacementX) > -(self.getWidth()/2*displacementMultiplier*1.25) ) {			
			MapPanel.displacementX += displacementX;
		} else {
			if(MapPanel.displacementX > 0) {
				MapPanel.displacementX = (int) (self.getWidth()/2/displacementMultiplier);
			} else {
				MapPanel.displacementX = (int) -(self.getWidth()/2*displacementMultiplier*1.25);
			}
		}
	}
	
	public static void addDisplacementY(int displacementY) {
		if( (MapPanel.displacementY+displacementY) < (self.getWidth()/5.25*displacementMultiplier) && (MapPanel.displacementY+displacementY) > -(self.getWidth()/2*displacementMultiplier*1.5) ) {
			MapPanel.displacementY += displacementY;
		} else {
			if(MapPanel.displacementY > 0) {
				MapPanel.displacementY = (int) (self.getWidth()/5.25*displacementMultiplier);
			} else {
				MapPanel.displacementY = (int) -(self.getWidth()/2*displacementMultiplier*1.5);
			}
		}
	}
	
	public static void addDisplacementMultiplier(double displacementMultiplier) {
		if(MapPanel.displacementMultiplier+displacementMultiplier >= 0.8 && MapPanel.displacementMultiplier+displacementMultiplier < 2) {
			MapPanel.displacementMultiplier += displacementMultiplier;		
		}
	}

	/**
	 * Inner class that periodically repaints the map so displacement changes feel responsive
	 * @author Luca Kleck
	 */
	private class Refresh implements Runnable {
		
		@Override
		public void run() {
			Timer timer = new Timer(100, new RefreshTask());
			timer.setRepeats(true);
			timer.start();
		}
		
		private class RefreshTask implements ActionListener {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					self.repaint();
				} catch (NullPointerException s) {
				}
			}

		}
	}
	
	public static void reset() {
		MapPanel.displacementMultiplier = 1;
		MapPanel.displacementX = 0;
		MapPanel.displacementY = 0;
	}

	private void mouseEventHandler(MouseEvent e) {
		// X 
		double factorX = (double) self.getWidth() / (double) MapImage.getImageWidth();
		int x = (int) ( ((e.getX()-displacementX) / displacementMultiplier) / factorX / mapImage.getMapTileSize() );
		// Y
		double factorY = (double) self.getWidth() / (double) MapImage.getImageHeight();
		int y = (int) ( ((e.getY()-displacementY) / displacementMultiplier) / factorY / mapImage.getMapTileSize() );
		// 
		ObjectMap.getSelected().resetSelected(x, y);
		
		mapImage.redraw();
	}
	
	private class MouseEventHandler implements MouseListener {

		@Override
		public void mouseClicked(MouseEvent e) {
		}

		@Override
		public void mousePressed(MouseEvent e) {
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			mouseEventHandler(e);
		}

		@Override
		public void mouseEntered(MouseEvent e) {
		}

		@Override
		public void mouseExited(MouseEvent e) {
		}
		
	}
}

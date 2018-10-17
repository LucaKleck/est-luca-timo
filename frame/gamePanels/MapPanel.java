package frame.gamePanels;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.ImageObserver;

import javax.swing.JPanel;
import javax.swing.Timer;

import map.MapImage;

/**  
 * Contains and draws the MapImage, takes care of the displacement
 * @author Luca Kleck 
 * @see MainGamePanel
 */
public class MapPanel extends JPanel implements ImageObserver {
	private static final long serialVersionUID = 121L;
	
	private static MapImage mapImage;
	private static MapPanel self;
	private static double displacementMultiplier = 1;
	private static int displacementX;
	private static int displacementY;
	Refresh refresh = new Refresh();
	
	public MapPanel() {
		this.setName("MapPanel");
		self = this;
		mapImage = new MapImage(500, 500);
		setBackground(new Color(0, 0, 0, 0));
		refresh.run();
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
		g.setColor(Color.RED);
		g.fillOval((int) (displacementX-4+(this.getWidth()/2)), (int) (displacementY-4+(this.getWidth()/2)), 8, 8);
	}
	
	public static void addDisplacementX(int displacementX) {
		if( (MapPanel.displacementX+displacementX) < (self.getWidth()/(2+(displacementMultiplier/2-0.5))) && (MapPanel.displacementX+displacementX) > -(self.getWidth()/(2+(displacementMultiplier/2-0.5))) ) {			
			MapPanel.displacementX += displacementX;
		}
		System.out.println("-------------------");
		System.out.println(self.getWidth()/2);
		System.out.println("&");
		System.out.println(MapPanel.displacementX);
	}
	
	public static void addDisplacementY(int displacementY) {
		if( (MapPanel.displacementY+displacementY) < (self.getWidth()/5.25*displacementMultiplier) && (MapPanel.displacementY+displacementY) > -(self.getWidth()/(2+(displacementMultiplier/2-0.5))) ) {
			MapPanel.displacementY += displacementY;
		}
		System.out.println("-------------------");
		System.out.println(self.getWidth()/5.25*displacementMultiplier);
		System.out.println("&");
		System.out.println(MapPanel.displacementY);
	}
	
	public static void addDisplacementMultiplier(double displacementMultiplier) {
		if(MapPanel.displacementMultiplier+displacementMultiplier >= 1 && MapPanel.displacementMultiplier+displacementMultiplier < 2) {
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
}

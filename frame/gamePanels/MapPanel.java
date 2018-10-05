/**  
* MapPanel.java
* @author Luca Kleck
* @version 0.01 
* @since 0.01
* @see MainGamePanel
*/
package frame.gamePanels;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.ImageObserver;

import javax.swing.JPanel;
import javax.swing.Timer;

import map.MapImage;

public class MapPanel extends JPanel implements ImageObserver {
	private static final long serialVersionUID = 121L;
	
	private static MapImage mapImage;
	private static MapPanel self;
	private static double displacementMultiplier = 1;
	private static int displacementX;
	private static int displacementY;
	private boolean startDisplacement = false;
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

		// displacement
		if (startDisplacement == false) {
			displacementX = -(this.getWidth() / 2);
			displacementY = -(this.getHeight() / 2);
			startDisplacement = true;
		}

		// draw map image with displacement & multiplier
		g.drawImage(mapImage,
				(int) ((this.getWidth() / 2) + displacementX),
				(int) ((this.getHeight() / 2) + displacementY),
				(int) (this.getWidth() * displacementMultiplier),
				(int) (this.getWidth() * displacementMultiplier), this);
	}
	
	public static void addDisplacementX(int displacementX) {
		MapPanel.displacementX += displacementX;
	}
	
	public static void addDisplacementY(int displacementY) {
		MapPanel.displacementY += displacementY;
	}
	
	public static void addDisplacementMultiplier(double displacementMultiplier) {
		if(MapPanel.displacementMultiplier+displacementMultiplier >= 1 && MapPanel.displacementMultiplier+displacementMultiplier < 2) {
			MapPanel.displacementMultiplier += displacementMultiplier;		
		}
	}

	/**
	 * Refresh - Inner class that periodically repaints the map so displacement changes feel responsive
	 * @author Luca Kleck
	 *
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
	
}

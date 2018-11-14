package frame.gamePanels;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.ImageObserver;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.swing.JPanel;
import javax.swing.Timer;

import map.MapImage;
import map.ObjectMap;

/**
 * Contains and draws the MapImage, takes care of the displacement
 * 
 * @author Luca Kleck
 * @see MainGamePanel
 */
public class MapPanel extends JPanel implements ImageObserver {
	private static final long serialVersionUID = 121L;
	
	private static final int IMAGE_SIZE = 3136;
	private static final int DEFAULT_DISPLACEMENT = 4;
	private static final ExecutorService EXS = Executors.newFixedThreadPool(1);

	private static MapImage mapImage;
	private static MapPanel self;
	private static double displacementMultiplier = DEFAULT_DISPLACEMENT;
	private static int displacementX;
	private static int displacementY;
	public static Refresh refresh;
	

	public MapPanel() {
		this.setName("MapPanel");
		this.setDoubleBuffered(true);
		this.setOpaque(false);
		self = this;

		mapImage = new MapImage(IMAGE_SIZE, IMAGE_SIZE);
		setBackground(new Color(0, 0, 0, 0));

		refresh = new Refresh();
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
		g.drawImage(mapImage, (int) (displacementX), (int) (displacementY),
				(int) (this.getWidth() * displacementMultiplier), (int) (this.getWidth() * displacementMultiplier),
				this);
//		g.drawImage(mapImage, (int) (displacementX), (int) (displacementY),
//				(int) (this.getWidth() * displacementMultiplier), (int) (this.getWidth() * displacementMultiplier),
//				this);
	}

	public static void addDisplacementX(int displacementX) {
		if ((MapPanel.displacementX + displacementX) < (self.getWidth() / (displacementMultiplier / 2 ) )
				&& (MapPanel.displacementX + displacementX) > -(self.getWidth() / 2 * displacementMultiplier * 1.75)) {
			MapPanel.displacementX += displacementX;
		} else {
			if (MapPanel.displacementX > 0) {
				MapPanel.displacementX = (int) (self.getWidth() / (displacementMultiplier / 2 ) );
			} else {
				MapPanel.displacementX = (int) -(self.getWidth() / 2 * displacementMultiplier * 1.75);
			}
		}
		MapPanel.refresh.run();
	}

	public static void addDisplacementY(int displacementY) {
		if ((MapPanel.displacementY + displacementY) < (self.getWidth() / 5.25 * displacementMultiplier)
				&& (MapPanel.displacementY + displacementY) > -(self.getWidth() / 2 * displacementMultiplier * 1.75)) {
			MapPanel.displacementY += displacementY;
		} else {
			if (MapPanel.displacementY > 0) {
				MapPanel.displacementY = (int) (self.getWidth() / 5.25 * displacementMultiplier);
			} else {
				MapPanel.displacementY = (int) -(self.getWidth() / 2 * displacementMultiplier * 1.75);
			}
		}
		MapPanel.refresh.run();
	}

	public static void addDisplacementMultiplier(double displacementMultiplier) {
		if (MapPanel.displacementMultiplier + displacementMultiplier >= 3
				&& MapPanel.displacementMultiplier + displacementMultiplier < 5) {
			MapPanel.displacementMultiplier += displacementMultiplier;
			MapPanel.refresh.run();
		}
	}

	/**
	 * Inner class that repaints the map so displacement changes feel responsive
	 * 
	 * @author Luca Kleck
	 */
	public class Refresh implements Runnable {
		private Timer repaintTimer = new Timer(1, new RefreshTask());

		@Override
		public void run() {
			if (!repaintTimer.isRunning()) {
				repaintTimer.setRepeats(false);
				repaintTimer.start();
			} else {
				repaintTimer.restart();
			}
		}

		private class RefreshTask implements ActionListener {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					self.repaint();
//					System.out.println("Height["+getHeight()+"] Width["+getWidth()+"]");
				} catch (NullPointerException s) {
				}
			}

		}

	}

	public static void reset() {
		MapPanel.displacementMultiplier = DEFAULT_DISPLACEMENT;
		MapPanel.displacementX = 0;
		MapPanel.displacementY = 0;
		self.repaint();
	}

	private void mouseEventHandler(MouseEvent e) {
		int x = -1;
		double factorX = (double) this.getWidth() / (double) MapImage.getImageWidth();
		int y = -1;
		double factorY = (double) this.getWidth() / (double) MapImage.getImageHeight();
		// X
		if((((e.getX() - displacementX) / displacementMultiplier) / factorX / mapImage.getMapTileSize()) >= 0) {
			x = (int) (((e.getX() - displacementX) / displacementMultiplier) / factorX / mapImage.getMapTileSize());
		}
		// Y
		if((((e.getY() - displacementY) / displacementMultiplier) / factorY / mapImage.getMapTileSize()) >= 0) {
			y = (int) (((e.getY() - displacementY) / displacementMultiplier) / factorY / mapImage.getMapTileSize());
		}
		// 
		boolean isLeftClick = false;
		if(e.getButton() == 1) {
			isLeftClick = true;
		} else {
			isLeftClick = false;
		}
		ClickOnTileHandler clickOnTileHandler = new ClickOnTileHandler(x,y,isLeftClick);
		EXS.execute(clickOnTileHandler);
		System.gc();
	}
	private class ClickOnTileHandler implements Runnable {
		private int x;
		private int y;
		private boolean isLeftClick;
		
		public ClickOnTileHandler(int x, int y, boolean isLeftClick) {
			this.x = x;
			this.y = y;
			this.isLeftClick = isLeftClick;
		}
		
		@Override
		public void run() {
			ObjectMap.getSelected().clickedOnTile(x, y, isLeftClick);
		}
		
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

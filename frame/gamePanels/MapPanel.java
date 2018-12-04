package frame.gamePanels;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.swing.JPanel;

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

	private static double displacementMultiplier = DEFAULT_DISPLACEMENT;
	private static MapImage mapImage;
	private static MapPanel mapPanelSelf;
	private static int displacementX;
	private static int displacementY;
	
	private BufferedImage mapImageLocal;

	public MapPanel() {
		this.setName("MapPanel");
		this.setDoubleBuffered(true);
		this.setOpaque(false);
		mapPanelSelf = this;

		mapImage = new MapImage(IMAGE_SIZE, IMAGE_SIZE);
		setBackground(new Color(0, 0, 0, 0));

		EXS.submit(new RepaintMapPanel());

		this.addMouseListener(new MouseEventHandler());
	}
	@Override
	public void paint(Graphics g) {
		super.paint(g);

		// Draw background
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
		
		if(mapImageLocal == null) {
			 mapImageLocal = mapImage.getCombinedImage();
		}
		// draw map image with displacement & multiplier
		g.drawImage(mapImageLocal, (int) (displacementX), (int) (displacementY),
				(int) (this.getWidth() * displacementMultiplier), (int) (this.getWidth() * displacementMultiplier),
				this);
	}

	public static void addDisplacementX(int displacementX) {
		if ((MapPanel.displacementX + displacementX) < (mapPanelSelf.getWidth() / (displacementMultiplier / 2 ) )
				&& (MapPanel.displacementX + displacementX) > -(mapPanelSelf.getWidth() / 2 * displacementMultiplier * 1.75)) {
			MapPanel.displacementX += displacementX;
		} else {
			if (MapPanel.displacementX > 0) {
				MapPanel.displacementX = (int) (mapPanelSelf.getWidth() / (displacementMultiplier / 2 ) );
			} else {
				MapPanel.displacementX = (int) -(mapPanelSelf.getWidth() / 2 * displacementMultiplier * 1.75);
			}
		}
	}

	public static void addDisplacementY(int displacementY) {
		if ((MapPanel.displacementY + displacementY) < (mapPanelSelf.getWidth() / 5.25 * displacementMultiplier)
				&& (MapPanel.displacementY + displacementY) > -(mapPanelSelf.getWidth() / 2 * displacementMultiplier * 1.75)) {
			MapPanel.displacementY += displacementY;
		} else {
			if (MapPanel.displacementY > 0) {
				MapPanel.displacementY = (int) (mapPanelSelf.getWidth() / 5.25 * displacementMultiplier);
			} else {
				MapPanel.displacementY = (int) -(mapPanelSelf.getWidth() / 2 * displacementMultiplier * 1.75);
			}
		}
	}

	public static void addDisplacementMultiplier(double displacementMultiplier) {
		if (MapPanel.displacementMultiplier + displacementMultiplier >= 3
				&& MapPanel.displacementMultiplier + displacementMultiplier < 5) {
			MapPanel.displacementMultiplier += displacementMultiplier;
			
		}
	}

	public static void reset() {
		MapPanel.displacementMultiplier = DEFAULT_DISPLACEMENT;
		MapPanel.displacementX = 0;
		MapPanel.displacementY = 0;
	}
	
	public static MapImage getMapImage() {
		return mapImage;
	}

	public static MapPanel getMapPanel() {
		return mapPanelSelf;
	}
	
	public static ExecutorService getMapPanelExecutor() {
		return EXS;
	}
	
	public static class RepaintMapPanel implements Runnable {
		
		@Override
		public void run() {
			mapPanelSelf.repaint();
		}
		
	}
	
	private void mouseEventHandler(MouseEvent e) {
		float x = -1;
		double factorX = (double) this.getWidth() / (double) MapImage.getImageWidth();
		float  y = -1;
		double factorY = (double) this.getWidth() / (double) MapImage.getImageHeight();
		// X
		if((((e.getX() - displacementX) / displacementMultiplier) / factorX / mapImage.getMapTileSize()) >= 0) {
			x = (float) (((e.getX() - displacementX) / displacementMultiplier) / factorX / mapImage.getMapTileSize());
		}
		// Y
		if((((e.getY() - displacementY) / displacementMultiplier) / factorY / mapImage.getMapTileSize()) >= 0) {
			y = (float) (((e.getY() - displacementY) / displacementMultiplier) / factorY / mapImage.getMapTileSize());
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
		private float x;
		private float y;
		private boolean isLeftClick;
		
		public ClickOnTileHandler(float x, float y, boolean isLeftClick) {
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

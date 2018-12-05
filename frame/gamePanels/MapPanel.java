package frame.gamePanels;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.WritableRaster;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.swing.JPanel;

import core.Core;
import core.GameInfo;
import map.MapImage;

/**
 * Contains and draws the MapImage, takes care of the displacement
 * 
 * @author Luca Kleck
 * @see MainGamePanel
 * @version 3
 */
public class MapPanel extends JPanel {
	private static final long serialVersionUID = 121L;
	
	private static final int IMAGE_SIZE = 3136;
	private static final int DEFAULT_DISPLACEMENT = 4;
	private static final float MAX_ZOOM = 5;
	private static final float MIN_ZOOM = 3;
	private static final ExecutorService MAP_REFRESH_THREAD = Executors.newFixedThreadPool(1);
	private static final ExecutorService CLICK_THREAD = Executors.newFixedThreadPool(1);

	private static double displacementMultiplier = DEFAULT_DISPLACEMENT;
	private MapImage mapImage;
	private static int displacementX;
	private static int displacementY;
	
	private BufferedImage mapTileLocal;
	private BufferedImage upperLayerLocal;

	public MapPanel() {
		this.setName("MapPanel");
		this.setDoubleBuffered(true);
		this.setOpaque(false);

		mapImage = new MapImage(IMAGE_SIZE, IMAGE_SIZE);
		setBackground(new Color(0, 0, 0, 0));
		mapTileLocal = mapImage.getMapTileLayer();
		upperLayerLocal = mapImage.getCombinedImage();
		MAP_REFRESH_THREAD.execute(new MapRefresh(this));
		
		this.addMouseListener(new MouseEventHandler());
	}
	@Override
	public void paint(Graphics g) {
		super.paint(g);

		// Draw background
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
		
		
		// backdrop
		g.drawImage(mapTileLocal, (int) (displacementX), (int) (displacementY),
				(int) (this.getWidth() * displacementMultiplier), (int) (this.getWidth() * displacementMultiplier),
				this);
		
		// draw map image with displacement & multiplier
		g.drawImage(upperLayerLocal, (int) (displacementX), (int) (displacementY),
				(int) (this.getWidth() * displacementMultiplier), (int) (this.getWidth() * displacementMultiplier),
				this);
	}

	public static void addDisplacementX(int displacementX) {
		MapPanel mp = ((MainGamePanel) Core.getMainJFrame().getCurrentComponent()).getMapPanel();
		if ((MapPanel.displacementX + displacementX) < (mp.getWidth() / (displacementMultiplier / 2 ) )
				&& (MapPanel.displacementX + displacementX) > -(mp.getWidth() / 2 * displacementMultiplier * 1.75)) {
			MapPanel.displacementX += displacementX;
		} else {
			if (MapPanel.displacementX > 0) {
				MapPanel.displacementX = (int) (mp.getWidth() / (displacementMultiplier / 2 ) );
			} else {
				MapPanel.displacementX = (int) -(mp.getWidth() / 2 * displacementMultiplier * 1.75);
			}
		}
	}

	public static void addDisplacementY(int displacementY) {
		MapPanel mp = ((MainGamePanel) Core.getMainJFrame().getCurrentComponent()).getMapPanel();
		if ((MapPanel.displacementY + displacementY) < (mp.getWidth() / 5.25 * displacementMultiplier)
				&& (MapPanel.displacementY + displacementY) > -(mp.getWidth() / 2 * displacementMultiplier * 1.75)) {
			MapPanel.displacementY += displacementY;
		} else {
			if (MapPanel.displacementY > 0) {
				MapPanel.displacementY = (int) (mp.getWidth() / 5.25 * displacementMultiplier);
			} else {
				MapPanel.displacementY = (int) -(mp.getWidth() / 2 * displacementMultiplier * 1.75);
			}
		}
	}

	public static void addDisplacementMultiplier(double displacementMultiplier) {
		if (MapPanel.displacementMultiplier + displacementMultiplier >= MIN_ZOOM
				&& MapPanel.displacementMultiplier + displacementMultiplier < MAX_ZOOM) {
			MapPanel.displacementMultiplier += displacementMultiplier;
			addDisplacementX(0);
			addDisplacementY(0);
		}
	}

	public static void reset() {
		MapPanel.displacementMultiplier = DEFAULT_DISPLACEMENT;
		MapPanel.displacementX = 0;
		MapPanel.displacementY = 0;
	}
	
	public MapImage getMapImage() {
		return mapImage;
	}

	static BufferedImage deepCopy(BufferedImage bi) {
		 ColorModel cm = bi.getColorModel();
		 boolean isAlphaPremultiplied = cm.isAlphaPremultiplied();
		 WritableRaster raster = bi.copyData(null);
		 return new BufferedImage(cm, raster, isAlphaPremultiplied, null);
	}
	
	private class MapRefresh implements Runnable {
		
		private boolean run = true;
		private MapPanel mapPanel;
		
		public MapRefresh(MapPanel mapPanel) {
			this.mapPanel = mapPanel;
		}
		
		@Override
		public void run() {
			while(run) {
				if(Core.getMainJFrame().getCurrentComponent() instanceof MainGamePanel) {
					((MainGamePanel) Core.getMainJFrame().getCurrentComponent()).getMapPanel().repaint();
				}
				if(Core.getMainJFrame().getCurrentComponent() instanceof MainGamePanel) {
					MainGamePanel mgf = (MainGamePanel) Core.getMainJFrame().getCurrentComponent();
					if(!mgf.getMapPanel().equals(mapPanel)) {
						mapPanel=null;
						mapImage=null;
						run=false;
					}
				}
				try {
					Thread.sleep(15);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
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
		CLICK_THREAD.execute(clickOnTileHandler);
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
			GameInfo.getObjectMap().getSelected().clickedOnTile(x, y, isLeftClick);
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

package frame.gamePanels;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.WritableRaster;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.swing.JPanel;
import javax.swing.Timer;

import core.Core;
import core.GameInfo;
import core.MapImage;
import core.ObjectMap;

/**
 * Contains and draws the MapImage, takes care of the displacement
 * 
 * @author Luca Kleck
 * @see MainGamePanel
 * @version 4
 */
public class MapPanel extends JPanel implements MouseMotionListener, ActionListener {
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
	
	private Timer mouseHoverUpdateTimer = new Timer(500, this);
	
	private BufferedImage mapTileLocal;
	private BufferedImage upperLayerLocal;
	private Point lastMousePoint;
	private boolean mouseMoved = false;

	public MapPanel() {
		setLayout(null);
		setName("MapPanel");
		setDoubleBuffered(true);
		setOpaque(false);

		mapImage = new MapImage(IMAGE_SIZE, IMAGE_SIZE);
		setBackground(new Color(0, 0, 0, 0));
		mapTileLocal = mapImage.getMapTileLayer();
		upperLayerLocal = mapImage.getCombinedImage();
		MAP_REFRESH_THREAD.execute(new MapRefresh(this));
		
		addMouseMotionListener(this);
		addMouseMotionListener(ma);
		addMouseListener(ma);
		
		mouseHoverUpdateTimer.setRepeats(true);
		mouseHoverUpdateTimer.start();
	}
	
	@Override
	public void update(Graphics g) {
		paint(g);
	}
	@Override
	public void paint(Graphics g) {
		// Draw background
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
		
		// world
		g.drawImage(mapTileLocal, (int) (displacementX), (int) (displacementY),
				(int) (this.getWidth() * displacementMultiplier), (int) (this.getWidth() * displacementMultiplier),
				this);
		
		// Entities and other
		g.drawImage(upperLayerLocal, (int) (displacementX), (int) (displacementY),
				(int) (this.getWidth() * displacementMultiplier), (int) (this.getWidth() * displacementMultiplier),
				this);
		
		super.paint(g);
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

	public void setPosition(int xPos, int yPos) {
		MapPanel mp = ((MainGamePanel) Core.getMainJFrame().getCurrentComponent()).getMapPanel();
		int tileSize = (int) (mp.getWidth() * displacementMultiplier) / -49;
		MapPanel.displacementX = (xPos-5)*tileSize;
		MapPanel.displacementY = (yPos-3)*tileSize;
		
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
					while(mapImage.getMapImageLock().isLocked());
					mapImage.getMapImageLock().lock();
					((MainGamePanel) Core.getMainJFrame().getCurrentComponent()).getMapPanel().repaint();
					mapImage.getMapImageLock().unlock();
				}
				// Check if game ended
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
		if(((MainGamePanel) Core.getMainJFrame().getCurrentComponent()).getBtnNextRound().isEnabled()) {
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

	@Override
	public void mouseDragged(MouseEvent e) {
		removeAll();
		System.gc();
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		if(lastMousePoint == null) {
			lastMousePoint = e.getPoint();
		}
		if(e.getPoint().x - lastMousePoint.x >= 5 || e.getPoint().x - lastMousePoint.x <= -5 && e.getPoint().y - lastMousePoint.y >= 5 || e.getPoint().y - lastMousePoint.y <= -5 && mouseMoved==false) {
			removeAll();
			System.gc();
			lastMousePoint = e.getPoint();
			mouseMoved  = true;
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(mouseMoved) {
			float x = -1;
			double factorX = (double) this.getWidth() / (double) MapImage.getImageWidth();
			float  y = -1;
			double factorY = (double) this.getWidth() / (double) MapImage.getImageHeight();
			// X
			if((((lastMousePoint.getX() - displacementX) / displacementMultiplier) / factorX / mapImage.getMapTileSize()) >= 0) {
				x = (float) (((lastMousePoint.getX() - displacementX) / displacementMultiplier) / factorX / mapImage.getMapTileSize());
			}
			// Y
			if((((lastMousePoint.getY() - displacementY) / displacementMultiplier) / factorY / mapImage.getMapTileSize()) >= 0) {
				y = (float) (((lastMousePoint.getY() - displacementY) / displacementMultiplier) / factorY / mapImage.getMapTileSize());
			}
			if(ObjectMap.inBounds((int) x,(int) y)) {
				JPanel mapTileInfoPanel = new MapTileInfoPanel(GameInfo.getObjectMap().getMap()[(int) x][(int) y]);
				mapTileInfoPanel.setBounds(lastMousePoint.x, lastMousePoint.y, mapTileInfoPanel.getPreferredSize().width, mapTileInfoPanel.getPreferredSize().height);
				add(mapTileInfoPanel);
			}
			mouseMoved=false;
		}
	}
	
	private MouseAdapter ma = new MouseAdapter() {

		private Point origin;
		private boolean moved = false;
		
		@Override
		public void mousePressed(MouseEvent e) {
			moved = false;
			origin = new Point(e.getPoint());
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			if(!moved) {
				mouseEventHandler(e);
			}
			
		}

		@Override
		public void mouseDragged(MouseEvent e) {
			if (origin != null) {
				int deltaX = origin.x - e.getX();
				int deltaY = origin.y - e.getY();
				if(deltaX >= 3 || deltaX <= 3) {
					if(deltaY >= 3 || deltaY <= -3) {
						moved = true;
						origin = new Point(e.getPoint());
						addDisplacementX(-deltaX);
						addDisplacementY(-deltaY);
					}
				}
				
			}
		}

	};
}

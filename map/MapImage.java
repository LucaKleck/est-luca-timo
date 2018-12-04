package map;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import javax.imageio.ImageIO;

import core.Boot;
import core.GameInfo;
import entity.Entity;
import entity.building.Building;
import entity.unit.Unit;
import entity.unit.Warrior;

public class MapImage {

	private static final int IMAGE_TYPE = BufferedImage.TYPE_INT_ARGB;
	
	private static final int[] TOP_LEFT = {-1, -1};
	private static final int[] TOP = {0, -1};
	private static final int[] TOP_RIGHT = {1, -1};
	private static final int[] LEFT = {-1, 0};
	private static final int[] RIGHT = {1, 0};
	private static final int[] BOTTOM_LEFT = {-1, 1};
	private static final int[] BOTTOM = {0, 1};
	private static final int[] BOTTOM_RIGHT = {1, 1};
	
	private BufferedImage selectionLayer; // selection is drawn here
	private BufferedImage effectLayer; // draw unit movement arrows and so on
	private BufferedImage unitBuildingLayer; // draw Units and buildings
	private BufferedImage decalLayer; // draw map tile overlays
	private BufferedImage mapTileLayer; // draw map tiles

	
	private static final Lock mapImageLock = new ReentrantLock();
	private static int mapTileSize;
	private static int imageWidth;
	private static int imageHeight;
	
	// external resources
	// Map Tiles
	// Forest
	private static BufferedImage plainImage;
	private static BufferedImage forestImage;
	private static BufferedImage forestImage192;

	private static BufferedImage forestImageLeftRight;
	private static BufferedImage forestImageTopBottom;

	private static BufferedImage forestImageEndBottom;
	private static BufferedImage forestImageEndLeft;
	private static BufferedImage forestImageEndTop;
	private static BufferedImage forestImageEndRight;

	
	//Buildings
	private static BufferedImage buildingImage;
	
	//Units
	private static BufferedImage warriorImage;
	
	private BufferedImage combinedImage;

	private boolean updateFlag = true;

	
	public MapImage(int width, int height) {
		if(plainImage == null) {
			try {
				plainImage = ImageIO.read( Boot.class.getResource("/resources/plain.png") );
				
				// Forest
				forestImage = ImageIO.read( Boot.class.getResource("/resources/forest.png") );
				forestImage192 = ImageIO.read( Boot.class.getResource("/resources/forest192.png") );
				
				forestImageLeftRight = ImageIO.read( Boot.class.getResource("/resources/forestLeftRight.png") );
				forestImageTopBottom = ImageIO.read( Boot.class.getResource("/resources/forestTopBottom.png") );
				
				forestImageEndBottom = ImageIO.read( Boot.class.getResource("/resources/forestEndBottom.png") );
				forestImageEndLeft = ImageIO.read( Boot.class.getResource("/resources/forestEndLeft.png") );
				forestImageEndTop = ImageIO.read( Boot.class.getResource("/resources/forestEndTop.png") );
				forestImageEndRight = ImageIO.read( Boot.class.getResource("/resources/forestEndRight.png") );
				
				// Buildings
				buildingImage = ImageIO.read( Boot.class.getResource("/resources/building.png") );
				
				//Units
				warriorImage = ImageIO.read( Boot.class.getResource("/resources/warrior.png") );
				
			} catch (IOException e) {
			}
		}

		imageWidth = width;
		imageHeight = height;
		mapTileSize = (int) (width / ObjectMap.getMap().length);

		mapTileLayer = new BufferedImage(imageWidth, imageHeight, IMAGE_TYPE);
		decalLayer = new BufferedImage(imageWidth, imageHeight, IMAGE_TYPE);
		drawMapTileLayer();
		drawDecalLayer();
		
		unitBuildingLayer = new BufferedImage(imageWidth, imageHeight, IMAGE_TYPE);
		effectLayer = new BufferedImage(imageWidth, imageHeight, IMAGE_TYPE);
		selectionLayer = new BufferedImage(imageWidth, imageHeight, IMAGE_TYPE);
		
		combinedImage = new BufferedImage(imageWidth, imageHeight, IMAGE_TYPE);
		
		Timer graphicsTimer = new Timer("GraphicsThread");
		graphicsTimer.scheduleAtFixedRate(new GraphicsTask(), 10, 15);
		

	}
	
	private void drawDecalLayer() {
		Graphics2D g = getDecalLayer().createGraphics();
		for (int x = 0; x < ObjectMap.getMap().length; x++) {
			for (int y = 0; y < ObjectMap.getMap()[0].length; y++) {
				if(ObjectMap.getMap()[x][y].isRoad()) {
					g.fillRect(x*mapTileSize, y*mapTileSize+27, mapTileSize, 10);
				}
			}
		}
	}
	
	private void drawMapTileLayer() {
		Graphics2D g = getMapTileLayer().createGraphics();
		for (int xRow = 0; xRow < ObjectMap.getMap().length; xRow++) {
			for (int yColumn = 0; yColumn < ObjectMap.getMap()[0].length; yColumn++) {
				try {
					g.drawImage(getImageForTile(xRow, yColumn), xRow * mapTileSize, yColumn * mapTileSize, mapTileSize, mapTileSize, null);
				} catch (NullPointerException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	
	private void drawUnitBuildingLayer() {
		Graphics2D g = getUnitBuildingLayer().createGraphics();
		g.setComposite(AlphaComposite.Clear);
		g.fillRect(0, 0, imageWidth, imageHeight); 
		g.setComposite(AlphaComposite.SrcOver);
		ArrayList<Entity> e = ObjectMap.getEntityMap();
		for (Iterator<Entity> iterator = e.iterator(); iterator.hasNext();) {
			Entity s = iterator.next();
			if(s instanceof Unit) {
				g.setColor(Color.BLUE);
				if(ObjectMap.getSelected().getSelectedEntity() != null) {
					if(ObjectMap.getSelected().getSelectedEntity().equals(s)) {
						g.setColor(Color.WHITE);
					}
				}
				g.fillRoundRect((int) (s.getPoint().x*mapTileSize)-5, (int) (s.getPoint().y*mapTileSize)-5, 10, 10, 10, 10);
				if(s instanceof Warrior) {
					g.drawImage(warriorImage, (int) (s.getPoint().x*mapTileSize)-8, (int) (s.getPoint().y*mapTileSize)-8, null);
				}
			} else if(s instanceof Building) {
				// if instance of other building
				g.drawImage(buildingImage, s.getXPos()*mapTileSize, s.getYPos()*mapTileSize, null);
			}
		}
	}

	private void drawEffectLayer() {
		Graphics2D g = getEffectLayer().createGraphics();
		g.setComposite(AlphaComposite.Clear);
		g.fillRect(0, 0, imageWidth, imageHeight); 
		g.setComposite(AlphaComposite.SrcOver);
		for (int i = 0; i < GameInfo.getRoundInfo().getEventList().size(); i++) {
			// draw each effect
			if(GameInfo.getRoundInfo().getEventList().get(i).getEffect() != null) {
				g.drawImage(GameInfo.getRoundInfo().getEventList().get(i).getEffect(), 0, 0, imageWidth, imageHeight, null);
			}
		}
	}
	
	private void drawSelecionLayer() {
		Graphics2D g = getSelectionLayer().createGraphics();
		g.setComposite(AlphaComposite.Clear);
		g.fillRect(0, 0, imageWidth, imageHeight); 
		g.setComposite(AlphaComposite.SrcOver);
		g.setColor(new Color(255, 0, 0, 120));
		if(ObjectMap.getSelected().getSelectedMapTile() != null) {
			g.fillRect(ObjectMap.getSelected().getSelectedMapTile().getXPos() * mapTileSize,
					ObjectMap.getSelected().getSelectedMapTile().getYPos() * mapTileSize, mapTileSize, mapTileSize);
		}
		if(ObjectMap.getSelected().getSelectedEntity() != null) {
			g.setColor(new Color(0,0,255,120));
			g.fillRect(ObjectMap.getSelected().getSelectedEntity().getXPos() * mapTileSize,
					ObjectMap.getSelected().getSelectedEntity().getYPos() * mapTileSize, mapTileSize, mapTileSize);
		}
	}
	
	private BufferedImage getImageForTile(int x, int y) {
		
		boolean top = checkTile(x, y, ObjectMap.getMap()[x][y].getType(), TOP);
		boolean right = checkTile(x, y, ObjectMap.getMap()[x][y].getType(), RIGHT);
		boolean bottom = checkTile(x, y, ObjectMap.getMap()[x][y].getType(), BOTTOM);
		boolean left = checkTile(x, y, ObjectMap.getMap()[x][y].getType(), LEFT);
		boolean topLeft = checkTile(x, y, ObjectMap.getMap()[x][y].getType(), TOP_LEFT);
		boolean topRight = checkTile(x, y, ObjectMap.getMap()[x][y].getType(), TOP_RIGHT);
		boolean bottomLeft = checkTile(x, y, ObjectMap.getMap()[x][y].getType(), BOTTOM_LEFT);
		boolean bottomRight = checkTile(x, y, ObjectMap.getMap()[x][y].getType(), BOTTOM_RIGHT);
		
		if (ObjectMap.getMap()[x][y].getName().matches("Plain")) {
			return plainImage;
		} else if (ObjectMap.getMap()[x][y].getName().matches("Forest") ) {
			//MIDDLE
			if(top && right && bottom && left) {
				return forestImage192.getSubimage(64, 64, 64, 64);
			}
			
			//CORNERS
			if(!top && right && bottom && !left) {
				return forestImage192.getSubimage(0, 0, 64, 64);
			}
			if(!top && !right && bottom && left) {
				return forestImage192.getSubimage(128, 0, 64, 64);
			}
			if(top && !right && !bottom && left) {
				return forestImage192.getSubimage(128, 128, 64, 64);
			}
			if(top && right && !bottom && !left) {
				return forestImage192.getSubimage(0, 128, 64, 64);
			}
			
			//RIGHT, LEFT, TOP, BOTTOM
			if(top && right && bottom && !left) {
				return forestImage192.getSubimage(0, 64, 64, 64);
			}
			if(!top && right && bottom && left) {
				return forestImage192.getSubimage(64, 0, 64, 64);
			}
			if(top && !right && bottom && left) {
				return forestImage192.getSubimage(128, 64, 64, 64);
			}
			if(top && right && !bottom && left) {
				return forestImage192.getSubimage(64, 128, 64, 64);
			}
			
			//LEFT TO RIGHT
			if(!top && right && !bottom && !left) {
				return forestImageEndLeft;
			}
			if(!top && !right && !bottom && left) {
				return forestImageEndRight;
			}
			if(!top && right && !bottom && left) {
				return forestImageLeftRight;
			}
			
			//TOP TO BOTTOM
			if(!top && !right && bottom && !left) {
				return forestImageEndTop;
			}
			if(top && !right && !bottom && !left) {
				return forestImageEndBottom;
			}
			if(top && !right && bottom && !left) {
				return forestImageTopBottom;
			}
			
			//DEFAULT
			if(!top && !right && !bottom && !left && !topLeft && !topRight && !bottomLeft && !bottomRight) {
				return forestImage;
			}
			return forestImage;
		} else if (ObjectMap.getMap()[x][y].getName().matches("Mountain") ) {
			return plainImage;
		} else if (ObjectMap.getMap()[x][y].getName().matches("Water") ) {
			return plainImage;
		} else {
			return plainImage;
		}
	}
	
	private boolean checkTile(int x, int y, int type, int[] pos) {
		boolean flag = false;
		try {
			if(ObjectMap.getMap()[(x + pos[0])][y + pos[1]].getType() == type) {
				flag = true;
			}
		} catch (IndexOutOfBoundsException iex) {
		}
		return flag;
	}

	public void redrawArea(int xStart, int xEnd, int yStart, int yEnd) {
		int xDiff = 0;
		int yDiff = 0;
		//
		if(xStart > xEnd) {
			xDiff = xStart - xEnd;
		} else if(xStart < xEnd) {
			xDiff = xEnd - xStart;
		} else {
			xDiff = 1;
		}
		//
		if(yStart > yEnd) {
			yDiff = yStart - yEnd;
		} else if(yStart < yEnd) {
			yDiff = yEnd - yStart;
		} else {
			yDiff = 1;
		}
		//
		for(int xCount = 0; xCount < xDiff; xCount++) {
			for(int yCount = 0; yCount < yDiff; yCount++) {
				// Make versions of drawXY that are with x/y coords
			}
		}
	}
	
	private void drawCombinedImage() {
		Graphics2D g = getCombinedImage().createGraphics();
		g.setComposite(AlphaComposite.Clear);
		g.fillRect(0, 0, imageWidth, imageHeight); 
		g.setComposite(AlphaComposite.SrcOver);
		g.drawImage(getDecalLayer(), 0, 0, null);
		g.drawImage(getSelectionLayer(), 0, 0, null);
		g.drawImage(getUnitBuildingLayer(), 0, 0, null);
		g.drawImage(getEffectLayer(), 0, 0, null);
	}
	
	public synchronized BufferedImage getSelectionLayer() {
		return selectionLayer;
	}

	public synchronized BufferedImage getEffectLayer() {
		return effectLayer;
	}

	public synchronized BufferedImage getUnitBuildingLayer() {
		return unitBuildingLayer;
	}

	public synchronized BufferedImage getDecalLayer() {
		return decalLayer;
	}

	public synchronized BufferedImage getMapTileLayer() {
		return mapTileLayer;
	}

	public synchronized BufferedImage getCombinedImage() {
		return combinedImage;
	}
	
	public static int getImageHeight() {
		return imageHeight;
	}

	public static int getImageWidth() {
		return imageWidth;
	}

	public double getMapTileSize() {
		return mapTileSize;
	}
	
	public synchronized void update() {
		this.updateFlag = true;
	}
	
	public ReentrantLock getMapImageLock() {
		return (ReentrantLock) mapImageLock;
	}
	
	private class GraphicsTask extends TimerTask {

		@Override
		public void run() {
			if(updateFlag) {
				mapImageLock.lock();
				drawDecalLayer();
				drawSelecionLayer();
				drawUnitBuildingLayer();
				drawEffectLayer();
				drawCombinedImage();
				updateFlag = false;
				mapImageLock.unlock();
			}
		}
		
	}
	
}

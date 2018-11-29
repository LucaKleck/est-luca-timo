package map;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

import javax.imageio.ImageIO;

import core.Boot;
import effects.MapImageEffect;
import entity.Entity;
import entity.building.Building;
import entity.unit.Unit;

public class MapImage implements ImageObserver {

	private static final int IMAGE_TYPE = BufferedImage.TYPE_INT_ARGB;
	
	private static ArrayList<MapImageEffect> effectList = new ArrayList<MapImageEffect>();
	
	private static BufferedImage selectionLayer; // selection is drawn here
	private static BufferedImage effectLayer; // draw unit movement arrows and so on
	private static BufferedImage unitBuildingLayer; // draw Units and buildings
	private static BufferedImage decalLayer; // draw map tile overlays
	private static BufferedImage mapTileLayer; // draw map tiles

	private static int mapTileSize;
	private static int imageWidth;
	private static int imageHeight;
	
	// external resources
	private static BufferedImage plainImage;
	private static BufferedImage forestImage;
	private static BufferedImage forestImage192;
	private static BufferedImage forestImageLeftRight;
	private static BufferedImage forestImageTopBottom;

	public MapImage(int width, int height) {
		try {
			plainImage = ImageIO.read( Boot.class.getResource("/resources/plain.png") );
			forestImage = ImageIO.read( Boot.class.getResource("/resources/forest.png") );
			forestImage192 = ImageIO.read( Boot.class.getResource("/resources/forest192.png") );
			forestImageLeftRight = ImageIO.read( Boot.class.getResource("/resources/forestLeftRight.png") );
			forestImageTopBottom = ImageIO.read( Boot.class.getResource("/resources/forestTopBottom.png") );
		} catch (IOException e) {
		}

		imageWidth = width;
		imageHeight = height;
		mapTileSize = (int) (width / ObjectMap.getMap().length);

		drawMapTileLayer();
		drawDecalLayer();
		drawUnitBuildingLayer();
		drawSelecionLayer();
		drawEffectLayer();


	}

	private void drawSelecionLayer() {
		selectionLayer = new BufferedImage(imageWidth, imageHeight, IMAGE_TYPE);
		Graphics2D g = selectionLayer.createGraphics();
		g.setColor(new Color(255, 0, 0, 120));
		try {
			g.fillRect(ObjectMap.getSelected().getSelectedMapTile().getXPos() * mapTileSize,
					ObjectMap.getSelected().getSelectedMapTile().getYPos() * mapTileSize, mapTileSize, mapTileSize);
		} catch (NullPointerException e) {
		}
		try {
			g.setColor(new Color(0,0,255,120));
			g.fillRect(ObjectMap.getSelected().getSelectedEntity().getXPos() * mapTileSize,
					ObjectMap.getSelected().getSelectedEntity().getYPos() * mapTileSize, mapTileSize, mapTileSize);
		} catch(NullPointerException nl) {
		}
	}
	
	private void drawMapTileLayer() {
		mapTileLayer = new BufferedImage(imageWidth, imageHeight, IMAGE_TYPE);
		Graphics2D g = mapTileLayer.createGraphics();
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

	private void drawDecalLayer() {
		decalLayer = new BufferedImage(imageWidth, imageHeight, IMAGE_TYPE);
		Graphics2D g = decalLayer.createGraphics();
		g.setColor(new Color(100,100,30));
		for (int x = 0; x < ObjectMap.getMap().length; x++) {
			for (int y = 0; y < ObjectMap.getMap()[0].length; y++) {
				if(ObjectMap.getMap()[x][y].isRoad()) {
					g.fillRect(x*mapTileSize, y*mapTileSize+27, mapTileSize, 10);
				}
			}
		}
	}

	private void drawUnitBuildingLayer() {
		unitBuildingLayer = new BufferedImage(imageWidth, imageHeight, IMAGE_TYPE);
		Graphics2D g = unitBuildingLayer.createGraphics();
		ArrayList<Entity> e = ObjectMap.getEntityMap();
		Random r = new Random();
		for (Iterator<Entity> iterator = e.iterator(); iterator.hasNext();) {
			Entity s = iterator.next();
			if(s instanceof Unit) {
				g.setColor(Color.BLUE);
			} else if (s instanceof Building) {
				g.setColor(new Color(100,100,40));
			}
			g.fillRoundRect(s.getXPos()*mapTileSize+r.nextInt(54), s.getYPos()*mapTileSize+r.nextInt(54), 10, 10, 10, 10);
		}
	}

	private void drawEffectLayer() {
		effectLayer = new BufferedImage(imageWidth, imageHeight, IMAGE_TYPE);
		@SuppressWarnings("unused")
		Graphics2D g = effectLayer.createGraphics();
		for (int i = 0; i < effectList.size(); i++) {
			// draw each effect
			// effectList needs to be cleared after the round
		}
	}
	
	private BufferedImage getImageForTile(int x, int y) {
		boolean top = checkTop(x, y, ObjectMap.getMap()[x][y].getType());
		boolean right = checkRight(x, y, ObjectMap.getMap()[x][y].getType());
		boolean bottom = checkBottom(x, y, ObjectMap.getMap()[x][y].getType());
		boolean left = checkLeft(x, y, ObjectMap.getMap()[x][y].getType());
		boolean topLeft = checkTopLeft(x, y, ObjectMap.getMap()[x][y].getType());
		boolean topRight = checkTopRight(x, y, ObjectMap.getMap()[x][y].getType());
		boolean bottomLeft = checkBottomLeft(x, y, ObjectMap.getMap()[x][y].getType());
		boolean bottomRight = checkBottomRight(x, y, ObjectMap.getMap()[x][y].getType());
		
		if (ObjectMap.getMap()[x][y].getName().matches("Plain")) {
			return plainImage;
		} else if (ObjectMap.getMap()[x][y].getName().matches("Forest") ) {
			if(top && right && bottom && left && topLeft && topRight && bottomLeft && bottomRight) {
				return forestImage192.getSubimage(64, 64, 64, 64);
			}
			if(!top && right && !bottom && left && !topLeft && !topRight && !bottomLeft && !bottomRight) {
				return forestImageLeftRight;
			}
			if(!top && !right && bottom && !left && !topLeft && !topRight && !bottomLeft && !bottomRight) {
				return forestImageTopBottom;
			}
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

	private boolean checkTopRight(int x, int y, int type) {
		boolean flag = false;
		try {
			if(ObjectMap.getMap()[(x+1)][y-1].getType() == type) {
				flag = true;
			}
		} catch (IndexOutOfBoundsException iex) {
		}
		return flag;
	}

	private boolean checkBottomLeft(int x, int y, int type) {
		boolean flag = false;
		try {
			if(ObjectMap.getMap()[(x-1)][y+1].getType() == type) {
				flag = true;
			}
		} catch (IndexOutOfBoundsException iex) {
		}
		return flag;
	}

	private boolean checkBottomRight(int x, int y, int type) {
		boolean flag = false;
		try {
			if(ObjectMap.getMap()[(x+1)][y+1].getType() == type) {
				flag = true;
			}
		} catch (IndexOutOfBoundsException iex) {
		}
		return flag;
	}

	private boolean checkTopLeft(int x, int y, int type) {
		boolean flag = false;
		try {
			if(ObjectMap.getMap()[(x-1)][y-1].getType() == type) {
				flag = true;
			}
		} catch (IndexOutOfBoundsException iex) {
		}
		return flag;
	}

	private boolean checkLeft(int x, int y, int type) {
		boolean flag = false;
		try {
			if(ObjectMap.getMap()[(x-1)][y].getType() == type) {
				flag = true;
			}
		} catch (IndexOutOfBoundsException iex) {
		}
		return flag;
	}

	private boolean checkBottom(int x, int y, int type) {
		boolean flag = false;
		try {
			if(ObjectMap.getMap()[(x)][y+1].getType() == type) {
				flag = true;
			}
		} catch (IndexOutOfBoundsException iex) {
		}
		return flag;
	}

	private boolean checkRight(int x, int y, int type) {
		boolean flag = false;
		try {
			if(ObjectMap.getMap()[(x+1)][y].getType() == type) {
				flag = true;
			}
		} catch (IndexOutOfBoundsException iex) {
		}
		return flag;
	}

	private boolean checkTop(int x, int y, int type) {
		boolean flag = false;
		try {
			if(ObjectMap.getMap()[(x)][y-1].getType() == type) {
				flag = true;
			}
		} catch (IndexOutOfBoundsException iex) {
		}
		return flag;
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

	public void redraw() {
		drawMapTileLayer();
		drawDecalLayer();
		drawUnitBuildingLayer();
		drawSelecionLayer();
		drawEffectLayer();
	}
	
	public void redrawSelection() {
		drawSelecionLayer();
	}
	
	public void redrawUnitBuildingLayer() {
		drawUnitBuildingLayer();
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
	
	public static BufferedImage getSelectionLayer() {
		return selectionLayer;
	}

	public static BufferedImage getEffectLayer() {
		return effectLayer;
	}

	public static BufferedImage getUnitBuildingLayer() {
		return unitBuildingLayer;
	}

	public static BufferedImage getDecalLayer() {
		return decalLayer;
	}

	public static BufferedImage getMapTileLayer() {
		return mapTileLayer;
	}

	@Override
	public boolean imageUpdate(Image arg0, int arg1, int arg2, int arg3, int arg4, int arg5) {
		return false;
	}
	
}

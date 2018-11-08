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
import entity.Entity;
import entity.building.Building;
import entity.unit.Unit;

public class MapImage extends BufferedImage implements ImageObserver {

	private static MapImage self;
	private static final int IMAGE_TYPE = TYPE_INT_ARGB;
	private static Graphics2D g2d;
	private static ArrayList<MapImageEffect> effectList = new ArrayList<MapImageEffect>();
	private static BufferedImage effectLayer; // draw unit movement arrows and so on
	private static BufferedImage topLayer; // draw Units and buildings
	private static BufferedImage middleLayer; // draw map tile overlays
	private static BufferedImage bottomLayer; // draw map tiles
	private static int mapTileSize;
	private static int imageWidth;
	private static int imageHeight;
	private static BufferedImage plainImage;
	private static BufferedImage forestImage;

	public MapImage(int width, int height) {
		super(width, height, IMAGE_TYPE);
		self = this;
		try {
			plainImage = ImageIO.read( Boot.class.getResource("/resources/plain.png") );
			forestImage = ImageIO.read( Boot.class.getResource("/resources/forest.png") );
		} catch (IOException e) {
		}
		MapImage.g2d = createGraphics();
		imageWidth = width;
		imageHeight = height;
		mapTileSize = (int) (width / ObjectMap.getMap().length);

		drawTopLayer();
		drawMiddleLayer();
		drawBottomLayer();
		drawEffectLayer();

		combineLayers();

	}

	private void combineLayers() {
		g2d.drawImage(bottomLayer, 0, 0, imageWidth, imageHeight, this);
		g2d.drawImage(middleLayer, 0, 0, imageWidth, imageHeight, this);
		g2d.drawImage(topLayer, 0, 0, imageWidth, imageHeight, this);
		g2d.drawImage(effectLayer, 0, 0, imageWidth, imageHeight, this);
	}

	private void drawBottomLayer() {
		// this will only happen once, after that we need something to check for changes
		// then only change those
		// may be subject to change
		bottomLayer = new BufferedImage(imageWidth, imageHeight, IMAGE_TYPE);
		Graphics2D g = bottomLayer.createGraphics();
		for (int xRow = 0; xRow < ObjectMap.getMap().length; xRow++) {
			for (int yColumn = 0; yColumn < ObjectMap.getMap()[0].length; yColumn++) {
//				g.setColor(getColorForTile(xRow, yColumn));
//				g.fillRect(xRow * mapTileSize, yColumn * mapTileSize, mapTileSize, mapTileSize);
				
				try {
					g.drawImage(getImageForTile(xRow, yColumn), xRow * mapTileSize, yColumn * mapTileSize, mapTileSize, mapTileSize, null);
				} catch (NullPointerException e) {
					e.printStackTrace();
				}
			}
		}
	}

	private void drawMiddleLayer() {
		middleLayer = new BufferedImage(imageWidth, imageHeight, IMAGE_TYPE);
		Graphics2D g = middleLayer.createGraphics();
		g.setColor(new Color(100,100,30));
		for (int x = 0; x < ObjectMap.getMap().length; x++) {
			for (int y = 0; y < ObjectMap.getMap()[0].length; y++) {
				if(ObjectMap.getMap()[x][y].isRoad()) {
					g.fillRect(x*mapTileSize, y*mapTileSize+27, mapTileSize, 10);
				}
			}
		}
	}

	private void drawTopLayer() {
		topLayer = new BufferedImage(imageWidth, imageHeight, IMAGE_TYPE);
		drawTopInside();
		/*Graphics2D g = topLayer.createGraphics();
		g.setColor(new Color(255, 0, 0, 120));
		try {
			g.fillRect(ObjectMap.getSelected().getSelectedMapTile().getXPos() * mapTileSize,
					ObjectMap.getSelected().getSelectedMapTile().getYPos() * mapTileSize, mapTileSize, mapTileSize);
		} catch (NullPointerException e) {
		}*/ /* Old Map Squares
			 * for (int xRow = 0; xRow < ObjectMap.getMap().length; xRow++) { for (int
			 * yColumn = 0; yColumn < ObjectMap.getMap()[0].length; yColumn++) {
			 * g.setColor(new Color(0, 0, 0)); g.drawRect(xRow * mapTileSize, yColumn *
			 * mapTileSize, mapTileSize, mapTileSize); } }
			 */
	}

	private void drawEffectLayer() {
		effectLayer = new BufferedImage(imageWidth, imageHeight, IMAGE_TYPE);
		Graphics2D g = effectLayer.createGraphics();
		for (int i = 0; i < effectList.size(); i++) {
			// draw each effect
			// effectList needs to be cleared after the round
		}
		
		// draw selected tile (may be put into another layer)
		g.setColor(new Color(255, 0, 0, 120));
		try {
			g.fillRect(ObjectMap.getSelected().getSelectedMapTile().getXPos() * mapTileSize,
					ObjectMap.getSelected().getSelectedMapTile().getYPos() * mapTileSize, mapTileSize, mapTileSize);
		} catch (NullPointerException e) {
		}
	}
	
	private void drawTopInside() {
		Graphics2D g = topLayer.createGraphics();
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
	
	private void drawBottomInside(int x, int y) {
		Graphics2D g = bottomLayer.createGraphics(); //TODO make it so the images are created on boot then stored for refreshes
		try {
			g.drawImage(getImageForTile(x, y), x * mapTileSize, y * mapTileSize, mapTileSize, mapTileSize, null);
		} catch (NullPointerException e) {
		}
	}
	
	@SuppressWarnings("unused")
	private Color getColorForTile(int x, int y) {
		Color color = Color.BLACK;
		if (ObjectMap.getMap()[x][y].getName() == "Plain") {
			color = new Color(0, 240, 13);
		} else if (ObjectMap.getMap()[x][y].getName() == "Forest") {
			color = new Color(0, 100, 20);
		} else if (ObjectMap.getMap()[x][y].getName() == "Mountain") {
			color = new Color(40, 20, 5);
		} else if (ObjectMap.getMap()[x][y].getName() == "Water") {
			color = new Color(10, 30, 255);
		}
		return color;
	}
	
	private BufferedImage getImageForTile(int x, int y) {
		if (ObjectMap.getMap()[x][y].getName().matches("Plain")) {
			return plainImage;
		} else if (ObjectMap.getMap()[x][y].getName().matches("Forest") ) {
			return forestImage;
		} else if (ObjectMap.getMap()[x][y].getName().matches("Mountain") ) {
			return plainImage;
		} else if (ObjectMap.getMap()[x][y].getName().matches("Water") ) {
			return plainImage;
		} else {
			return plainImage;
		}
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

	@Override
	public boolean imageUpdate(Image arg0, int arg1, int arg2, int arg3, int arg4, int arg5) {
		return false;
	}

	public void redraw() {
		drawTopLayer();
		drawMiddleLayer();
		drawBottomLayer();
		
		combineLayers();
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
				drawEffectLayer();
				drawTopLayer();
				drawBottomInside(xCount, yCount);
			}
		}
		combineLayers();
	}
	
	public static void staticRepaint() {
		self.redraw();
	}
	
	public static MapImage getMapImage() {
		return self;
	}

}

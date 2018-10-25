package map;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.util.ArrayList;

public class MapImage extends BufferedImage implements ImageObserver {

	private static MapImage self;
	private static final int IMAGE_TYPE = TYPE_INT_ARGB;
	private static Graphics2D g2d;
	private static ArrayList<MapImageEffect> effectList = new ArrayList<MapImageEffect>();
	private static BufferedImage effectLayer; // draw unit movement arrows and so on
	private static BufferedImage topLayer; // Draw the grid
	private static BufferedImage middleLayer; // draw Units and Buildings
	private static BufferedImage bottomLayer; // draw map tiles
	private static int mapTileSize;
	private static int imageWidth;
	private static int imageHeight;

	public MapImage(int width, int height) {
		super(width, height, IMAGE_TYPE);
		self = this;
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
				g.setColor(getColorForTile(xRow, yColumn));
				g.fillRect(xRow * mapTileSize, yColumn * mapTileSize, mapTileSize, mapTileSize);
			}
		}
	}
	
	private void drawMiddleLayer() {
		middleLayer = new BufferedImage(imageWidth, imageHeight, IMAGE_TYPE);
		@SuppressWarnings("unused")
		Graphics2D g = middleLayer.createGraphics();
		for (int xRow = 0; xRow < ObjectMap.getMap().length; xRow++) {
			for (int yColumn = 0; yColumn < ObjectMap.getMap()[0].length; yColumn++) {
			}
		}
	}

	private void drawTopLayer() {
		topLayer = new BufferedImage(imageWidth, imageHeight, IMAGE_TYPE);
		Graphics2D g = topLayer.createGraphics();
		g.setColor(new Color(255,0,0,120));
		try {
			g.fillRect(ObjectMap.getSelected().getSelectedMapTile().getXPos() * mapTileSize, ObjectMap.getSelected().getSelectedMapTile().getYPos() * mapTileSize, mapTileSize, mapTileSize);
		} catch (NullPointerException e) {
		}
		for (int xRow = 0; xRow < ObjectMap.getMap().length; xRow++) {
			for (int yColumn = 0; yColumn < ObjectMap.getMap()[0].length; yColumn++) {
				g.setColor(new Color(0, 0, 0));
				g.drawRect(xRow * mapTileSize, yColumn * mapTileSize, mapTileSize, mapTileSize);
			}
		}
	}

	private void drawEffectLayer() {
		effectLayer = new BufferedImage(imageWidth, imageHeight, IMAGE_TYPE);
		@SuppressWarnings("unused")
		Graphics2D g = effectLayer.createGraphics();
		for(int i = 0; i < effectList.size(); i++) {
			// draw each effect
			// effectList needs to be cleared after the round
		}
	}
	
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

	public static void staticRepaint() {
		self.redraw();
	}

}

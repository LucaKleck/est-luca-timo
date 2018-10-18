package map;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;

public class MapImage extends BufferedImage implements ImageObserver {

	private static final int IMAGE_TYPE = TYPE_INT_ARGB;
	private static Graphics2D g2d;
	@SuppressWarnings("unused")
	private static BufferedImage effectLayer; // draw click event and other stuff
	private static BufferedImage topLayer; // Draw the grid
	private static BufferedImage middleLayer; // draw Units and Buildings
	private static BufferedImage bottomLayer; // draw map tiles
	private static int mapTileSize;
	private int imageWidth;
	private int imageHeight;

	public MapImage(int width, int height) {
		super(width, height, IMAGE_TYPE);
		effectLayer = new BufferedImage(width, height, IMAGE_TYPE);

		MapImage.g2d = createGraphics();
		this.imageWidth = width;
		this.imageHeight = height;
		mapTileSize = (int) (width / ObjectMap.getMap().length);

		drawTopLayer();
		drawMiddleLayer();
		drawBottomLayer();

		combineLayers();
		
		
	}

	private void combineLayers() {
		g2d.drawImage(bottomLayer, 0, 0, imageWidth, imageHeight, this);
		g2d.drawImage(middleLayer, 0, 0, imageWidth, imageHeight, this);
		g2d.drawImage(topLayer, 0, 0, imageWidth, imageHeight, this);
	}

	private void drawTopLayer() {
		topLayer = new BufferedImage(imageWidth, imageHeight, IMAGE_TYPE);
		Graphics2D g = topLayer.createGraphics();
		g.setColor(new Color(100,0,0,30));
		g.fillRect(ObjectMap.getSelectedMapTile()[0] * mapTileSize, ObjectMap.getSelectedMapTile()[1] * mapTileSize, mapTileSize, mapTileSize);
		for (int xRow = 0; xRow < ObjectMap.getMap().length; xRow++) {
			for (int yColumn = 0; yColumn < ObjectMap.getMap()[0].length; yColumn++) {
				g.setColor(new Color(0, 0, 0, 120));
				g.drawRect(xRow * mapTileSize, yColumn * mapTileSize, mapTileSize, mapTileSize);
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

	public void redraw() {
		drawTopLayer();
		drawMiddleLayer();
		drawBottomLayer();

		combineLayers();
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

	public double getMapTileSize() {
		return mapTileSize;
	}
	
	public int getImageWidth() {
		return imageWidth;
	}

	public int getImageHeight() {
		return imageHeight;
	}
	
	@Override
	public boolean imageUpdate(Image arg0, int arg1, int arg2, int arg3, int arg4, int arg5) {
		return false;
	}

}

package map;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;

import javax.swing.Timer;

public class MapImage extends BufferedImage implements ImageObserver{
	private class RedrawMap implements Runnable {

		@Override
		public void run() {
			RedrawEvent event = new RedrawEvent();
			Timer t = new Timer(1000, event);
			t.setRepeats(true);
			t.start();
		}
		
		private class RedrawEvent implements ActionListener {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				
			}	
			
		}
		
	}
	
	private static final int IMAGE_TYPE = TYPE_INT_ARGB;
	private static Graphics2D g2d;
	@SuppressWarnings("unused")
	private static BufferedImage effectLayer; // draw click event and other stuff
	private static BufferedImage topLayer; // Draw the grid
	private static BufferedImage middleLayer; // draw Units and Buildings
	private static BufferedImage bottomLayer; // draw map tiles
	private static int width;
	private static int height;
	private static int mapTileSize;
	
	private Thread thread = new Thread(new RedrawMap());
	
	public MapImage(int width, int height) {
		super(width, height, IMAGE_TYPE);
		effectLayer = new BufferedImage(width, height, IMAGE_TYPE);
		topLayer = new BufferedImage(width, height, IMAGE_TYPE);
		middleLayer = new BufferedImage(width, height, IMAGE_TYPE);
		bottomLayer = new BufferedImage(width, height, IMAGE_TYPE);
		
		MapImage.g2d = createGraphics();
		MapImage.width = width;
		MapImage.height = height;
		mapTileSize = (int) (width/ObjectMap.getMap().length);
		
		drawTopLayer();
		drawMiddleLayer();
		drawBottomLayer();
		
		combineLayers();
		
		thread.run();
		thread.start();
	}
	private void combineLayers() {
		g2d.drawImage(bottomLayer, 0, 0, width, height, this);
		g2d.drawImage(middleLayer, 0, 0, width, height, this);
		g2d.drawImage(topLayer, 0, 0, width, height, this);
	}
	private void drawTopLayer() {
		Graphics g = topLayer.getGraphics();
		for( int xRow = 0; xRow < ObjectMap.getMap().length; xRow++) {
			for(int yColumn = 0; yColumn < ObjectMap.getMap()[0].length; yColumn++) {
				g.setColor(new Color(0, 0, 0, 120));
				g.drawRect(xRow*mapTileSize, yColumn*mapTileSize, mapTileSize, mapTileSize);
			}
		}
	}
	private void drawMiddleLayer() {
		@SuppressWarnings("unused")
		Graphics g = middleLayer.getGraphics();
		for( int xRow = 0; xRow < ObjectMap.getMap().length; xRow++) {
			for(int yColumn = 0; yColumn < ObjectMap.getMap()[0].length; yColumn++) {
//				if(ObjectMap.getMap()[xRow][yColumn].getBuilding != null) {
			}
		}
	}
	private void drawBottomLayer() {
		//this will only happen once, after that we need something to check for changes then only change those
		//may be subject to change
		Graphics g = bottomLayer.getGraphics();
		for( int xRow = 0; xRow < ObjectMap.getMap().length; xRow++) {
			for(int yColumn = 0; yColumn < ObjectMap.getMap()[0].length; yColumn++) {
				g.setColor(getColorForTile(xRow,yColumn));
				g.fillRect(xRow*mapTileSize, yColumn*mapTileSize, mapTileSize, mapTileSize);
			}
		}
	}
	private Color getColorForTile(int x, int y) {
		Color color = Color.BLACK;
		if(ObjectMap.getMap()[x][y].getName() == "Plain") {
			color = new Color(0,240,13);
		} else if(ObjectMap.getMap()[x][y].getName() == "Forest") {
			color = new Color(0,100,20);
		} else if(ObjectMap.getMap()[x][y].getName() == "Mountain") {
			color = new Color(40,20,5);
		} else if(ObjectMap.getMap()[x][y].getName() == "Water") {
			color = new Color(10,30,255);
		}
		return color;
	}
	@Override
	public boolean imageUpdate(Image arg0, int arg1, int arg2, int arg3, int arg4, int arg5) {
		return false;
	}
}

package effects;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import abilities.Ability;
import entity.Entity;
import map.MapImage;

public class MapImageEffect extends BufferedImage {

	public MapImageEffect(Entity source, Entity target, Ability ability) {
		super(MapImage.getImageWidth(), MapImage.getImageHeight(), TYPE_INT_ARGB);
	}
	
	public MapImageEffect(Entity source, int x, int y) {
		super(MapImage.getImageWidth(), MapImage.getImageHeight(), TYPE_INT_ARGB);
		paintArrow(this.createGraphics(), source.getXPos(), source.getYPos(), x, y);
	}
	
	private void paintArrow(Graphics2D g, int x1, int y1, int x2, int y2) {
		g.drawLine(x1, y1, x2, y2);
	}
}

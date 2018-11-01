package map;

import java.awt.image.BufferedImage;

public class MapImageEffect extends BufferedImage {

	public MapImageEffect(int x1, int x2, int y1, int y2, boolean attackMove) {
		super(MapImage.getImageWidth(), MapImage.getImageHeight(), TYPE_INT_ARGB);
	}

}

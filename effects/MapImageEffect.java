package effects;

import java.awt.image.BufferedImage;

import abilities.Ability;
import entity.Entity;
import map.MapImage;

public abstract class MapImageEffect extends BufferedImage {

	public MapImageEffect(Entity source, Entity target, Ability ability) {
		super(MapImage.getImageWidth(), MapImage.getImageHeight(), TYPE_INT_ARGB);
	}
}

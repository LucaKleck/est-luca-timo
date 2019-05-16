package effects;

import java.awt.image.BufferedImage;

import abilities.Ability;
import core.MapImage;
import entity.Entity;

public abstract class MapImageEffect extends BufferedImage {

	public MapImageEffect(Entity source, Entity target, Ability ability) {
		super(MapImage.getImageWidth(), MapImage.getImageHeight(), TYPE_INT_ARGB);
	}

	@Override
	public String toString() {
		return "MapImageEffect";
	}
	
}

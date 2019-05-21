package frame.graphics.effects;

import java.awt.image.BufferedImage;

import entity.Entity;
import events.abilities.Ability;
import frame.graphics.MapImage;

public abstract class MapImageEffect extends BufferedImage {

	public MapImageEffect(Entity source, Entity target, Ability ability) {
		super(MapImage.getImageWidth(), MapImage.getImageHeight(), TYPE_INT_ARGB);
	}

	@Override
	public String toString() {
		return "MapImageEffect";
	}
	
}

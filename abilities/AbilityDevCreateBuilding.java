package abilities;

import com.sun.javafx.geom.Point2D;

import entity.Entity;
import entity.building.Building;
import map.ObjectMap;

public class AbilityDevCreateBuilding extends Ability {

	public AbilityDevCreateBuilding() {
		super(Ability.ABILITY_DEV_CREATE_BUILDING);
	}

	@Override
	public void applyAbility(Entity source, Entity target) {
		int level = 1;
		int hp = 10;
		Point2D p = new Point2D((float) ObjectMap.getSelected().getSelectedMapTile().getXPos(), (float) ObjectMap.getSelected().getSelectedMapTile().getyPos());
		ObjectMap.getEntityMap().add(new Building(p, "Building", hp, hp, level, true, null));
	}

}

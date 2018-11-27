package abilities;

import entity.Entity;
import entity.building.Building;
import map.ObjectMap;

public class AbilityDevCreateBuilding extends Ability {

	public AbilityDevCreateBuilding() {
		super(Ability.ABILITY_DEV_CREATE_BUILDING);
	}

	@Override
	public void applyAbility(Entity source, Entity target) {
		ObjectMap.getEntityMap().add(new Building(ObjectMap.getSelected().getSelectedMapTile().getXYPoint(), "Building", 4, null));
	}

}

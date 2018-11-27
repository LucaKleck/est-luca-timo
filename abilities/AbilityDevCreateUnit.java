package abilities;

import entity.Entity;
import entity.unit.Warrior;
import map.ObjectMap;

public class AbilityDevCreateUnit extends Ability {

	public AbilityDevCreateUnit() {
		super(Ability.ABILITY_DEV_CREATE_UNIT);
	}

	@Override
	public void applyAbility(Entity source, Entity target) {
		ObjectMap.getEntityMap().add(new Warrior(ObjectMap.getSelected().getSelectedMapTile().getXYPoint(), "Warrior", 2, 10));
	}

}

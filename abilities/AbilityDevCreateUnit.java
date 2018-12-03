package abilities;

import com.sun.javafx.geom.Point2D;

import entity.Entity;
import entity.unit.Warrior;
import map.ObjectMap;

public class AbilityDevCreateUnit extends Ability {

	public AbilityDevCreateUnit() {
		super(Ability.ABILITY_DEV_CREATE_UNIT);
	}

	@Override
	public void applyAbility(Entity source, Entity target) {
		Point2D p = new Point2D((float) ObjectMap.getSelected().getSelectedMapTile().getXPos(), (float) ObjectMap.getSelected().getSelectedMapTile().getYPos());
		ObjectMap.getEntityMap().add(new Warrior(p, "Warrior", 3, 1, true));
	}

}

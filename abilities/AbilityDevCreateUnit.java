package abilities;

import com.sun.javafx.geom.Point2D;

import core.GameInfo;
import entity.Entity;
import entity.unit.Warrior;

public class AbilityDevCreateUnit extends Ability {

	public AbilityDevCreateUnit() {
		super(Ability.ABILITY_DEV_CREATE_UNIT);
	}

	@Override
	public void applyAbility(Entity source, Entity target) {
		Point2D p = new Point2D((float) GameInfo.getObjectMap().getSelected().getSelectedMapTile().getXPos(), (float) GameInfo.getObjectMap().getSelected().getSelectedMapTile().getYPos());
		GameInfo.getObjectMap().getEntityMap().add(new Warrior(p, "Warrior", 3, 1, true));
	}

}

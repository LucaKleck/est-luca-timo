package abilities;

import java.util.ArrayList;

import com.sun.javafx.geom.Point2D;

import core.GameInfo;
import entity.Entity;
import entity.unit.Warrior;

public class AbilityDevCreateEnemyUnit extends Ability {

	public AbilityDevCreateEnemyUnit() {
		super(Ability.ABILITY_DEV_CREATE_UNIT, Ability.ABILITY_DESC_DEV_CREATE_UNIT);
	}

	@Override
	public void applyAbility(Entity source, Entity target) {
		Point2D p = new Point2D((float) GameInfo.getObjectMap().getSelected().getSelectedMapTile().getXPos(), (float) GameInfo.getObjectMap().getSelected().getSelectedMapTile().getYPos());
		GameInfo.getObjectMap().getEntityMap().add(new Warrior(p, "DEV",  3, 1, false, new ArrayList<>()));
	}

}

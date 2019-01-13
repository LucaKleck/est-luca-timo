package abilities;

import java.util.ArrayList;

import com.sun.javafx.geom.Point2D;

import core.GameInfo;
import entity.Entity;
import entity.unit.Builder;

public class AbilityDevCreateBuilder extends Ability {

	public AbilityDevCreateBuilder() {
		super(Ability.ABILITY_DEV_CREATE_BUILDER, Ability.ABILITY_DESC_DEV_CREATE_BUILDER);
	}

	@Override
	public void applyAbility(Entity source, Entity target) {
		Point2D p = new Point2D((float) GameInfo.getObjectMap().getSelected().getSelectedMapTile().getXPos(), (float) GameInfo.getObjectMap().getSelected().getSelectedMapTile().getYPos());
		GameInfo.getObjectMap().getEntityMap().add(new Builder(p, "DEV",  3, 1, true, new ArrayList<>()));
	}

}

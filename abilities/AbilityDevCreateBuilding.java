package abilities;

import java.util.ArrayList;

import com.sun.javafx.geom.Point2D;

import core.GameInfo;
import entity.Entity;
import entity.building.ProductionBuilding;

public class AbilityDevCreateBuilding extends Ability {

	public AbilityDevCreateBuilding() {
		super(Ability.ABILITY_DEV_CREATE_BUILDING, Ability.ABILITY_DESC_DEV_CREATE_BUILDING, Ability.ABILITY_TYPE_PRODUCE);
	}

	@Override
	public void applyAbility(Entity source, Entity target) {
		int level = 1;
		int hp = 10;
		Point2D p = new Point2D((float) GameInfo.getObjectMap().getSelected().getSelectedMapTile().getXPos(), (float) GameInfo.getObjectMap().getSelected().getSelectedMapTile().getYPos());
		GameInfo.getObjectMap().getEntityMap().add(new ProductionBuilding(p, ProductionBuilding.TOWN_CENTER, hp, hp, level, true, new ArrayList<>()));
	}

}

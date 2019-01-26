package entity.building;

import java.util.ArrayList;

import com.sun.javafx.geom.Point2D;

import abilities.Ability;
import abilities.CreateUnit;
import entity.unit.Unit;

public class TownCenter extends EconomyBuilding {

	public TownCenter(Point2D pointXY, String name, int maxHealth, int currentHealth, int level, boolean controlable, ArrayList<Ability> abilities) {
		super(pointXY, name, maxHealth, currentHealth, level, controlable, abilities);
		abilities.add(new CreateUnit(pointXY, Unit.UNIT_BUILDER, Ability.ABILITY_CREATE_BUILDER, Ability.ABILITY_DESC_CREATE_BUILDER));
		abilities.add(new CreateUnit(pointXY, Unit.UNIT_WARRIOR, Ability.ABILITY_CREATE_WARRIOR, Ability.ABILITY_DESC_CREATE_WARRIOR));
		abilities.add(new CreateUnit(pointXY, Unit.UNIT_MAGE, Ability.ABILITY_CREATE_MAGE, Ability.ABILITY_DESC_CREATE_MAGE));
	}

}

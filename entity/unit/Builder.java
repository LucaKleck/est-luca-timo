package entity.unit;

import java.util.ArrayList;

import com.sun.javafx.geom.Point2D;

import abilities.Ability;
import abilities.Build;
import entity.building.Building;

public class Builder extends Unit {

	private static final int BASE_MAX_HEALTH = 3;
	private static final int BASE_DAMAGE = 2;
	private static final int MOVEMENT_RANGE = 2;

	private Point2D buildPoint;
	
	public Builder(Point2D pointXY, String name, int currentHealth, int level, boolean controlable, ArrayList<Ability> abilities) {
		super(pointXY, name, BASE_MAX_HEALTH, currentHealth, level, controlable, BASE_DAMAGE, MOVEMENT_RANGE, abilities);
		//Production Buildings
		abilities.add(new Build(Building.BARRACKS, Ability.ABILITY_BUILD_BARRACKS, Ability.ABILITY_DESC_BUILD_BARRACKS, this, controlable));
		abilities.add(new Build(Building.TOWN_CENTER, Ability.ABILITY_BUILD_TOWN_CENTER, Ability.ABILITY_DESC_BUILD_TOWN_CENTER, this, controlable));
		abilities.add(new Build(Building.SIEGE_WORKSHOP, Ability.ABILITY_BUILD_SIEGE_WORKSHOP, Ability.ABILITY_DESC_BUILD_SIEGE_WORKSHOP, this, controlable));
		//Resource Buildings
		abilities.add(new Build(Building.WOOD_GETTER, Ability.ABILITY_BUILD_WOOD_GETTER, Ability.ABILITY_DESC_BUILD_WOOD_GETTER, this, controlable));
		abilities.add(new Build(Building.STONE_GETTER, Ability.ABILITY_BUILD_STONE_GETTER, Ability.ABILITY_DESC_BUILD_STONE_GETTER, this, controlable));
		abilities.add(new Build(Building.FOOD_GETTER, Ability.ABILITY_BUILD_FOOD_GETTER, Ability.ABILITY_DESC_BUILD_FOOD_GETTER, this, controlable));
		abilities.add(new Build(Building.METAL_GETTER, Ability.ABILITY_BUILD_METAL_GETTER, Ability.ABILITY_DESC_BUILD_METAL_GETTER, this, controlable));
		abilities.add(new Build(Building.GOLD_GETTER, Ability.ABILITY_BUILD_GOLD_GETTER, Ability.ABILITY_DESC_BUILD_GOLD_GETTER, this, controlable));
		abilities.add(new Build(Building.MANA_GETTER, Ability.ABILITY_BUILD_MANA_GETTER, Ability.ABILITY_DESC_BUILD_MANA_GETTER, this, controlable));
		//Defense Buildings
		abilities.add(new Build(Building.WALL, Ability.ABILITY_BUILD_WALL, Ability.ABILITY_DESC_BUILD_WALL, this, controlable));
		abilities.add(new Build(Building.ARCHER_TOWER, Ability.ABILITY_BUILD_ARCHER_TOWER, Ability.ABILITY_DESC_BUILD_ARCHER_TOWER, this, controlable));
		
	}
	
	public void setBuildPoint(Point2D buildPoint) {
		this.buildPoint = buildPoint;
	}

	public Point2D getBuildPoint() {
		return buildPoint;
	}
	
}

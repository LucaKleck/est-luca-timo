package entity.unit;

import java.util.ArrayList;

import abilities.Ability;
import abilities.Build;
import core.Point2DNoFxReq;
import entity.building.Building;

public class Builder extends Unit {

	private static final int BASE_MAX_HEALTH = 3;
	private static final int BASE_DAMAGE = 2;
	private static final int MOVEMENT_RANGE = 2;
	private static final int MAX_RANGE = 5;
	private Point2DNoFxReq buildPoint;
	
	public Builder(Point2DNoFxReq pointXY, String name, int currentHealth, int level, boolean controlable, ArrayList<Ability> abilities) {
		super(pointXY, name, BASE_MAX_HEALTH, currentHealth, level, controlable, BASE_DAMAGE, MOVEMENT_RANGE, abilities);
		addAbilities(abilities);
	}
	
	public Builder(Point2DNoFxReq pointXY, String name, int level, boolean controlable, ArrayList<Ability> abilities) {
		super(pointXY, name, BASE_MAX_HEALTH, BASE_MAX_HEALTH, level, controlable, BASE_DAMAGE, MOVEMENT_RANGE, abilities);
		addAbilities(abilities);
	}
	
	public void addAbilities(ArrayList<Ability> abilities) {
		
		boolean controlable = this.isControlable();
		
		if(controlable) {
			//Production Buildings
			abilities.add(new Build(Building.BARRACKS, Ability.ABILITY_BUILD_BARRACKS, Ability.ABILITY_DESC_BUILD_BARRACKS, this, controlable));
			abilities.add(new Build(Building.SIEGE_WORKSHOP, Ability.ABILITY_BUILD_SIEGE_WORKSHOP, Ability.ABILITY_DESC_BUILD_SIEGE_WORKSHOP, this, controlable));
			abilities.add(new Build(Building.STABLE, Ability.ABILITY_BUILD_STABLE, Ability.ABILITY_DESC_BUILD_STABLE, this, controlable));
			abilities.add(new Build(Building.CHURCH, Ability.ABILITY_BUILD_CHURCH, Ability.ABILITY_DESC_BUILD_CHURCH, this, controlable));
			//Resource Buildings
			abilities.add(new Build(Building.WOOD_GETTER, Ability.ABILITY_BUILD_WOOD_GETTER, Ability.ABILITY_DESC_BUILD_WOOD_GETTER, this, controlable));
			abilities.add(new Build(Building.STONE_GETTER, Ability.ABILITY_BUILD_STONE_GETTER, Ability.ABILITY_DESC_BUILD_STONE_GETTER, this, controlable));
			abilities.add(new Build(Building.FOOD_GETTER, Ability.ABILITY_BUILD_FOOD_GETTER, Ability.ABILITY_DESC_BUILD_FOOD_GETTER, this, controlable));
			abilities.add(new Build(Building.METAL_GETTER, Ability.ABILITY_BUILD_METAL_GETTER, Ability.ABILITY_DESC_BUILD_METAL_GETTER, this, controlable));
			abilities.add(new Build(Building.GOLD_GETTER, Ability.ABILITY_BUILD_GOLD_GETTER, Ability.ABILITY_DESC_BUILD_GOLD_GETTER, this, controlable));
			abilities.add(new Build(Building.MANA_GETTER, Ability.ABILITY_BUILD_MANA_GETTER, Ability.ABILITY_DESC_BUILD_MANA_GETTER, this, controlable));
		}
		//Defense Buildings
		abilities.add(new Build(Building.MAGE_TOWER, Ability.ABILITY_BUILD_MAGE_TOWER, Ability.ABILITY_DESC_BUILD_MAGE_TOWER, this, controlable));
		abilities.add(new Build(Building.ARCHER_TOWER, Ability.ABILITY_BUILD_ARCHER_TOWER, Ability.ABILITY_DESC_BUILD_ARCHER_TOWER, this, controlable));		
	}
	
	public void setBuildPoint(Point2DNoFxReq buildPoint) {
		this.buildPoint = buildPoint;
	}

	public int getMaxRange() {
		return MAX_RANGE;
	}
	
	public Point2DNoFxReq getBuildPoint() {
		return buildPoint;
	}
	
}

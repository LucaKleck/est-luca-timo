package entity.building;

import java.util.ArrayList;

import abilities.Ability;
import core.Point2DNoFxReq;
import entity.Entity;

public abstract class Building extends Entity {
	
	private int efficiency;
	//Resource Buildings
	public static final String WOOD_GETTER = "Lumberjack";
	public static final String FOOD_GETTER = "Farm";
	public static final String GOLD_GETTER = "Goldmine";
	public static final String METAL_GETTER = "Metalforge";
	public static final String STONE_GETTER = "Quarry";
	public static final String MANA_GETTER = "Manastonecollector";
	//Production Buildings	
	public static final String TOWN_CENTER = "Town Center";
	public static final String BARRACKS = "Barracks";
	public static final String PORTAL = "Portal";
	public static final String SIEGE_WORKSHOP = "Siege-Workshop";
	//Defense Buildings
	public static final String WALL = "Wall";
	public static final String ARCHER_TOWER = "Archer Tower";
	
	public Building(Point2DNoFxReq pointXY, String name, int maxHealth, int currentHealth, int level, boolean controlable, ArrayList<Ability> abilities) {
		super(pointXY, name, maxHealth, currentHealth, level, controlable, abilities);
		efficiency = level*12;
	}
	
	@Override
	public String toString() {
		return "Building [efficiency=" + efficiency + ", getName()=" + getName() + ", getMaxHealth()=" + getMaxHealth()
				+ ", getCurrentHealth()=" + getCurrentHealth() + ", getMaxRange()=" + getMaxRange()
				+ ", getAbilities()=" + getAbilities() + ", getEvent()=" + getEvent() + ", getId()=" + getId() + "]";
	}

	public int getEfficiency() {
		return efficiency;
	}
	
}

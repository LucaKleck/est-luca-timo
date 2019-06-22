package entity.building;

import java.util.ArrayList;

import core.Point2DNoFxReq;
import entity.Entity;
import events.abilities.Ability;

public abstract class Building extends Entity {

	private int efficiency;
	// Resource Buildings
	private static final int BASE_EFFICIENCY = 12;
	
	public static final String WOOD_GETTER = "Lumberjack";
	public static final String FOOD_GETTER = "Farm";
	public static final String GOLD_GETTER = "Goldmine";
	public static final String METAL_GETTER = "Metalforge";
	public static final String STONE_GETTER = "Quarry";
	public static final String MANA_GETTER = "Manastonecollector";
	// Production Buildings
	public static final String TOWN_CENTER = "Town Center";
	public static final String BARRACKS = "Barracks";
	public static final String PORTAL = "Portal";
	public static final String SIEGE_WORKSHOP = "Siege-Workshop";
	public static final String STABLE = "Stable";
	public static final String CHURCH = "Church";
	// Defense Buildings
	public static final String MAGE_TOWER = "Mage Tower";
	public static final String ARCHER_TOWER = "Archer Tower";

	public Building(Point2DNoFxReq pointXY, String name, int maxHealth, int currentHealth, int level,
			boolean controlable, ArrayList<Ability> abilities) {
		super(pointXY, name, maxHealth, currentHealth, level, controlable, abilities, false);
		switch (name) {
		case WOOD_GETTER:
			efficiency = (level + 1) * 6;
			break;
		case FOOD_GETTER:
			efficiency = (level + 1) * 6;
			break;
		default:
			efficiency = (level + 1) * BASE_EFFICIENCY;
			break;
		}
	}

	public int getEfficiency() {
		return efficiency;
	}
	
	public void updateEfficiency() {
		efficiency = (this.getLevel() + 1) * 12;
	}

}

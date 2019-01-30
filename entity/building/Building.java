package entity.building;

import java.util.ArrayList;

import com.sun.javafx.geom.Point2D;

import abilities.Ability;
import entity.Entity;

public abstract class Building extends Entity {
	
	private int efficiency;
	
	public static final String WOOD_GETTER = "Lumberjack";
	public static final String FOOD_GETTER = "Farmer";
	public static final String GOLD_GETTER = "Goldminer";
	public static final String METAL_GETTER = "Metalforge";
	public static final String STONE_GETTER = "Stonemason";
	public static final String MANA_GETTER = "Manastonecollector";
	
	public static final String TOWN_CENTER = "Town Center";
	public static final String BARRACKS = "Barracks";
	
	public Building(Point2D pointXY, String name, int maxHealth, int currentHealth, int level, boolean controlable, ArrayList<Ability> abilities) {
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

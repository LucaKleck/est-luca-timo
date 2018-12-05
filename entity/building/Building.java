package entity.building;

import java.util.ArrayList;

import com.sun.javafx.geom.Point2D;

import abilities.Ability;
import entity.Entity;

public class Building extends Entity {
	
	private BuildingRessources ressources;
	
	public static final String WOOD_GETTER = "Lumberjack";
	public static final String FOOD_GETTER = "Farmer";
	public static final String GOLD_GETTER = "Goldminer";
	public static final String METAL_GETTER = "Metalforge";
	public static final String STONE_GETTER = "Stonemason";
	public static final String MANA_GETTER = "Manastonecollector";

	private int efficiency;
	
	public Building(Point2D pointXY, String name, int maxHealth, int currentHealth, int level, boolean controlable, ArrayList<Ability> abilities) {
		super(pointXY, maxHealth, currentHealth, level, controlable, abilities);
		ressources = new BuildingRessources (getXPos(), getYPos(), name);
	}
	
	
	public int getEfficiency(int xPos, int yPos) {
		return efficiency;
	}
	public BuildingRessources getRessources() {
		return ressources;
	}
	
}

package entity.building;

import entity.Entity;

public class Building extends Entity {
	
	private BuildingRessources ressources;
	
	public static final String woodgetter = "Lumberjack";
	public static final String foodgetter = "Farmer";
	public static final String goldgetter = "Goldminer";
	public static final String metalgetter = "Metalforge";
	public static final String stonegetter = "Stonemason";
	public static final String manastonegetter = "Manastonecollector";
	
	public Building(int xPos, int yPos, String name, int health) {
		super(xPos, yPos, name, health);
		BuildingRessources ressources = new BuildingRessources (xPos, yPos, name);
	}
	
}

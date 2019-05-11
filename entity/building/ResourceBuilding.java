package entity.building;

import java.util.ArrayList;

import abilities.Ability;
import abilities.CollectResources;
import core.Point2DNoFxReq;

public class ResourceBuilding extends Building {
	
	public static final String EXPLAINED = "Buildings get their resources in a radius of 1 (9 fields total). Every Map-tile has a % of Resources, that gets multiplied by the efficiency of your Building";
	private BuildingRessources ressources;
	
	public ResourceBuilding(Point2DNoFxReq pointXY, String name, int maxHealth, int currentHealth, int level, boolean controlable, ArrayList<Ability> abilities) {
		super(pointXY, name, maxHealth, currentHealth, level, controlable, abilities);
		abilities.add(new CollectResources(this));
	}
	
	public BuildingRessources getRessources() {
		if(ressources == null) {
			ressources = new BuildingRessources (getXPos(), getYPos(), getName());
		}
		return ressources;
	}
	
}
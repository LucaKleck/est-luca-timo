package entity.building;

import java.util.ArrayList;

import abilities.Ability;
import abilities.CollectResources;
import core.Point2DNoFxReq;

public class ResourceBuilding extends Building {
	
	private BuildingRessources ressources;
	
	public ResourceBuilding(Point2DNoFxReq pointXY, String name, int maxHealth, int currentHealth, int level, boolean controlable, ArrayList<Ability> abilities) {
		super(pointXY, name, maxHealth, currentHealth, level, controlable, abilities);
		ressources = new BuildingRessources (getXPos(), getYPos(), name);
		abilities.add(new CollectResources(this));
	}
	
	public BuildingRessources getRessources() {
		return ressources;
	}
	
}
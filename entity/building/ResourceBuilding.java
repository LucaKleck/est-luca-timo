package entity.building;

import java.util.ArrayList;

import core.Point2DNoFxReq;
import events.abilities.Ability;
import events.abilities.CollectResources;

public class ResourceBuilding extends Building {
	
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
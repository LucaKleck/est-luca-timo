package entity.building;

import java.util.ArrayList;

import com.sun.javafx.geom.Point2D;

import abilities.Ability;
import abilities.CollectResources;

public class RessourceBuilding extends Building {
	
	private BuildingRessources ressources;
	
	public RessourceBuilding(Point2D pointXY, String name, int maxHealth, int currentHealth, int level, boolean controlable, ArrayList<Ability> abilities) {
		super(pointXY, name, maxHealth, currentHealth, level, controlable, abilities);
		ressources = new BuildingRessources (getXPos(), getYPos(), name);
		abilities.add(new CollectResources(this));
	}
	
	public BuildingRessources getRessources() {
		return ressources;
	}
	
}
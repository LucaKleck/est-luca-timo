package entity.building;

import java.util.ArrayList;

import com.sun.javafx.geom.Point2D;

import abilities.Ability;

public class EconomyBuilding extends Building {

	public static final String TOWN_CENTER = "Town Center";
	
	public EconomyBuilding(Point2D pointXY, String name, int maxHealth, int currentHealth, int level, boolean controlable, ArrayList<Ability> abilities) {
		super(pointXY, name, maxHealth, currentHealth, level, controlable, abilities);
	}
	
}

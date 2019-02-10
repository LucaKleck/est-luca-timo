package entity.building;

import java.util.ArrayList;

import com.sun.javafx.geom.Point2D;

import abilities.Ability;
import abilities.RangedAttack;

public class DefenseBuilding extends Building {
	
	public DefenseBuilding(Point2D pointXY, String name, int maxHealth, int currentHealth, int level, boolean controlable, ArrayList<Ability> abilities) {
		super(pointXY, name, maxHealth, currentHealth, level, controlable, abilities);
		if(name.equals(Building.ARCHER_TOWER)) {
			abilities.add(new RangedAttack());
		}
	}
	
}

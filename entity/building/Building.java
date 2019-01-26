package entity.building;

import java.util.ArrayList;

import com.sun.javafx.geom.Point2D;

import abilities.Ability;
import entity.Entity;

public abstract class Building extends Entity {
	
	private int efficiency;
	
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

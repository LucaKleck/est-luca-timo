package entity.unit;

import java.awt.Point;
import java.util.ArrayList;

import abilities.Ability;
import entity.Entity;

public class Unit extends Entity {

	private int baseDamage;
	private int movementRange;

	public Unit(int xPos, int yPos, String name, int maxHealth, int currentHealth, int level,  int baseDamage,  int movementRange, ArrayList<Ability> abilities) {
		super(xPos, yPos, name, maxHealth, currentHealth, level, abilities);
		this.baseDamage = baseDamage;
		this.movementRange = movementRange;
	}
	
	public Unit(Point pointXY, String name, int maxHealth, int currentHealth, int level,  int baseDamage,  int movementRange, ArrayList<Ability> abilities) {
		super(pointXY, name, maxHealth, currentHealth, level, abilities);
		this.baseDamage = baseDamage;
		this.movementRange = movementRange;
	}

	public int getMovementRange() {
		return movementRange;
	}

	public int getBaseDamage() {
		return baseDamage;
	}

	@Override
	public String toString() {
		return "Unit [ id="+super.getId()+", name=" + getName() + ", damage=" + baseDamage + ", health=" + getMaxHealth() + ", movementRange=" + movementRange + "]";
	}

}
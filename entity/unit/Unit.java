package entity.unit;

import java.util.ArrayList;

import abilities.Ability;
import entity.Entity;

public class Unit extends Entity {

	private int damage;
	private int movementRange;

	public Unit(int xPos, int yPos, String name, int health,  int damage,  int movementRange, ArrayList<Ability> abilities) {
		super(xPos, yPos, name, health, abilities);
		this.damage = damage;
		this.movementRange = movementRange;
	}

	public int getMovementRange() {
		return movementRange;
	}

	public int getDamage() {
		return damage;
	}

	@Override
	public String toString() {
		return "Unit [name=" + getName() + ", damage=" + damage + ", health=" + getMaxHealth() + ", movementRange=" + movementRange + "]";
	}

}
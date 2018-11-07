package entity.unit;

import entity.Entity;

public class Unit extends Entity {

	private int damage;
	private int movementRange;

	public Unit(int xPos, int yPos, int zPos, String name, int health,  int damage,  int movementRange) {
		super(xPos, yPos, zPos, name, health);
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
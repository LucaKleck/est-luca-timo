package entity.unit;

import entity.Entity;

public class Unit extends Entity {

	private int damage;
	private int priorityPoints;
	private int movementRange;

	public Unit(String name, int damage, int health, int movementRange, int xPos, int yPos) {
		super(name,health,xPos,yPos);
		this.damage = damage;
		this.movementRange = movementRange;
	}

	public int getDamage() {
		return damage;
	}

	public void setDamage(int damage) {
		this.damage = damage;
	}

	public int getPriorityPoints() {
		return priorityPoints;
	}

	public void setPriorityPoints(int priorityPoints) {
		this.priorityPoints = priorityPoints;
	}

	public int getRange() {
		return movementRange;
	}

	public void setRange(int range) {
		this.movementRange = range;
	}

	@Override
	public String toString() {
		return "Unit [name=" + getName() + ", damage=" + damage + ", health=" + getHealth() + ", priorityPoints=" + priorityPoints
				+ ", movementRange=" + movementRange + "]";
	}
	

}

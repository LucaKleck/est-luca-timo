package entity.unit;

public class Unit {

	private String name;
	private int damage;
	private int health;
	private int priorityPoints;
	private int movementRange;

	public Unit(String name, int damage, int health, int movementRange) {
		this.name = name;
		this.damage = damage;
		this.health = health;
		this.movementRange = movementRange;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
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
		return "Unit [name=" + name + ", damage=" + damage + ", health=" + health + ", priorityPoints=" + priorityPoints
				+ ", movementRange=" + movementRange + "]";
	}
	

}

package unit;

public class Unit {

	private String name;
	private int damage;
	private int health;
	private int priorityPoints;
	private int range;

	public Unit(String name, int damage, int health, int range) {
		this.name = name;
		this.damage = damage;
		this.health = health;
		this.range = range;
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
		return range;
	}

	public void setRange(int range) {
		this.range = range;
	}

	@Override
	public String toString() {
		return "Unit [name=" + name + ", damage=" + damage + ", health=" + health + ", priorityPoints=" + priorityPoints
				+ ", range=" + range + "]";
	}
	

}

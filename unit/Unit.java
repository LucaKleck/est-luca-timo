package unit;

public class Unit {

	private String name;
	private int damage;
	private int health;
	private int priorityPoints;

	public Unit(String name, int damage, int health) {
		this.name = name;
		this.damage = damage;
		this.health = health;
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

}

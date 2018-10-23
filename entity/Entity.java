package entity;

public class Entity {

	private String name;
	private int health;
	private int xPos;
	private int yPos;
	
	public Entity(String name, int health, int xPos, int yPos) {
		this.name = name;
		this.health = health;
		this.xPos = xPos;
		this.yPos = yPos;
	}
	
	public int getxPos() {
		return xPos;
	}

	public int getyPos() {
		return yPos;
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
	
}
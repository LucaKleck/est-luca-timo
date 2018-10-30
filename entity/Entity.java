package entity;

import java.util.ArrayList;

public class Entity {

	private String name;
	private int maxRange = 5; // Will be calculated via the abilities in the future
	private int health;
	private int xPos;
	private int yPos;
	private ArrayList<Ability> abilities = new ArrayList<>();

	public Entity( int xPos, int yPos, String name, int health) {
		this.name = name;
		this.health = health;
		this.xPos = xPos;
		this.yPos = yPos;
	}

	public int getXPos() {
		return xPos;
	}

	public int getYPos() {
		return yPos;
	}

	public String getName() {
		return name;
	}

	public int getHealth() {
		return health;
	}

	public int getMaxRange() {
		return maxRange;
	}
	
	public boolean hasAbility() {
		boolean has = false;
		try {
			for(int i = 0; i < abilities.size(); i++) {
				if(abilities.get(i) != null) {
					has = true;
				}
			}
		} catch (NullPointerException nl) {
		}
		return has;
	}
}

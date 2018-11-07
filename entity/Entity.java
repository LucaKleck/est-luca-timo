package entity;

import java.util.ArrayList;

public class Entity {

	private String name;
	private int maxRange = 5; // Will be calculated via the abilities in the future
	private int maxHealth;
	private int currentHealth;
	private int xPos;
	private int yPos;
	private int zPos;
	private ArrayList<Ability> abilities = new ArrayList<>();

	public Entity( int xPos, int yPos, int zPos, String name, int maxHealth) {
		this.name = name;
		this.maxHealth = maxHealth;
		this.xPos = xPos;
		this.yPos = yPos;
		this.zPos = zPos;
	}

	public int getXPos() {
		return xPos;
	}

	public int getYPos() {
		return yPos;
	}
	
	public int getZPos() {
		return zPos;
	}

	public String getName() {
		return name;
	}

	public int getMaxHealth() {
		return maxHealth;
	}

	public int getCurrentHealth() {
		return currentHealth;
	}
	
	public int getMaxRange() {
		return maxRange;
	}
	
	@Override
	public String toString() {
		return "Entity [name=" + name + ", maxRange=" + maxRange + ", maxHealth=" + maxHealth + ", currentHealth="
				+ currentHealth + ", xPos=" + xPos + ", yPos=" + yPos + ", zPos=" + zPos + ", abilities=" + abilities
				+ "]";
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

package entity;

import java.util.ArrayList;

import abilities.Ability;
import map.ObjectMap;

public class Entity {

	private String name;
	private int maxRange = 5; // Will be calculated via the abilities in the future
	private int maxHealth;
	private int currentHealth;
	private int xPos;
	private int yPos;
	private ArrayList<Ability> abilities = new ArrayList<>();

	public Entity(int xPos, int yPos, String name, int maxHealth) {
		this.name = name;
		this.maxHealth = maxHealth;
		this.currentHealth = maxHealth;
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

	public int getMaxHealth() {
		return maxHealth;
	}

	public int getCurrentHealth() {
		return currentHealth;
	}

	public void setCurrentHealth(int currentHealth) {
		this.currentHealth = currentHealth;

		if (currentHealth <= 0) {
			destroy();
		}

	}

	public int getMaxRange() {
		return maxRange;
	}

	public ArrayList<Ability> getAbilities() {
		return abilities;
	}

	private void destroy() {

		int i = 0;

		while (ObjectMap.getEntityMap().get(i) != this) {
			i++;
		}

		ObjectMap.getEntityMap().remove(i);

	}

	@Override
	public String toString() {
		return "Entity [name=" + name + ", maxRange=" + maxRange + ", maxHealth=" + maxHealth + ", currentHealth="
				+ currentHealth + ", xPos=" + xPos + ", yPos=" + yPos + ", abilities=" + abilities + "]";
	}

	public boolean hasAbility() {
		boolean has = false;
		try {
			for (int i = 0; i < abilities.size(); i++) {
				if (abilities.get(i) != null) {
					has = true;
					return has;
				}
			}
		} catch (NullPointerException nl) {
		}
		return has;
	}

}

package entity;

import java.awt.Point;
import java.util.ArrayList;

import abilities.Ability;
import core.Event;
import map.ObjectMap;

public class Entity {

	private int xPos;
	private int yPos;
	private String name;
	private int maxHealth;
	private int currentHealth;
	private ArrayList<Ability> abilities;
	private int level = 1;
	private int maxRange = 5; // Will be calculated via the abilities in the future
	private Event event = null;
	
	public Entity(int xPos, int yPos, String name, int maxHealth, ArrayList<Ability> abilities) {
		this.name = name;
		this.maxHealth = maxHealth;
		this.currentHealth = maxHealth;
		this.xPos = xPos;
		this.yPos = yPos;
		this.abilities = abilities;
	}
	
	public Entity(Point pointXY, String name, int maxHealth, ArrayList<Ability> abilities) {
		this.name = name;
		this.maxHealth = maxHealth;
		this.currentHealth = maxHealth;
		this.xPos = (int) pointXY.getX();
		this.yPos = (int) pointXY.getY();
		this.abilities = abilities;
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

	public int getLevel() {
		return level;
	}
	
	public void addLevel() {
		level ++;
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

	public Event getEvent() {
		return event;
	}

	public void setEvent(Event event) {
		this.event = event;
	}

}

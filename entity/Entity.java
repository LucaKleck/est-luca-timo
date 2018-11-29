package entity;

import java.awt.Point;
import java.util.ArrayList;

import abilities.Ability;
import core.Event;
import map.ObjectMap;

public class Entity extends Target {
	private static int entityCount;
	
	private int id;
	private String name;
	private int maxHealth;
	private int currentHealth;
	private ArrayList<Ability> abilities;
	private int level = 1;
	private int maxRange = 5; // Will be calculated via the abilities in the future
	private Event event = null;
	private boolean controlable = false;
	
	public Entity(int xPos, int yPos, String name, int maxHealth, int currentHealth, int level, boolean controlable, ArrayList<Ability> abilities) {
		entityCount++;
		this.id = entityCount;
		this.name = name;
		this.maxHealth = maxHealth;
		this.currentHealth = currentHealth;
		super.xPos = xPos;
		super.yPos = yPos;
		this.level = level;
		this.controlable = controlable;
		this.abilities = abilities;
	}
	
	public Entity(Point pointXY, String name, int maxHealth, int currentHealth, int level, boolean controlable, ArrayList<Ability> abilities) {
		entityCount++;
		this.id = entityCount;
		this.name = name;
		this.maxHealth = maxHealth;
		this.currentHealth = currentHealth;
		this.xPos = (int) pointXY.getX();
		this.yPos = (int) pointXY.getY();
		this.level = level;
		this.controlable = controlable;
		this.abilities = abilities;
	}

	public int getXPos() {
		return xPos;
	}

	public int getYPos() {
		return yPos;
	}

	public void setxPos(int xPos) {
		this.xPos = xPos;
	}

	public void setyPos(int yPos) {
		this.yPos = yPos;
	}

	public void setPoint(Point pointXY) {
		this.xPos = (int) pointXY.getX();
		this.yPos = (int) pointXY.getY();
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
	
	public boolean getControlable() {
		return controlable;
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
		return "Entity [id=" + id + ", xPos=" + xPos + ", yPos=" + yPos + ", name=" + name + ", maxHealth=" + maxHealth
				+ ", currentHealth=" + currentHealth + ", abilities=" + abilities + ", level=" + level + ", maxRange="
				+ maxRange + ", event=" + event + "]";
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
		if(this.event != null) {
			this.event.cancelEvent();
			this.event=null;
			System.gc();
		}
		this.event = event;
		//System.out.println(event.toString());
		//System.out.println(GameInfo.getEventQueue());
	}

	public int getId() {
		return id;
	}

}

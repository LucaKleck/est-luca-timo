package entity;

import java.util.ArrayList;

import abilities.Ability;
import core.Event;
import core.GameInfo;
import core.Point2DNoFxReq;
import statusEffects.StatusEffect;

public class Entity {
	private static int entityCount;
	
	private int id;
	private Point2DNoFxReq pointXY = new Point2DNoFxReq();
	private String name;
	private int maxHealth;
	private int currentHealth;
	private ArrayList<Ability> abilities;
	private ArrayList<StatusEffect> statusEffects = new ArrayList<>(); // maybe add to constructor for permanent effects
	private int level = 1;
	private int maxRange = 5; // Will be calculated via the abilities in the future
	private Event event = null;
	private boolean controlable = false;
	
	public Entity(Point2DNoFxReq pointXY, String name, int maxHealth, int currentHealth, int level, boolean controlable, ArrayList<Ability> abilities) {
		entityCount++;
		this.id = entityCount;
		this.pointXY.setLocation(pointXY);
		this.name = name;
		this.maxHealth = maxHealth;
		this.currentHealth = currentHealth;
		this.level = level;
		this.controlable = controlable;
		this.abilities = abilities;
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

	public ArrayList<Ability> getAbilities() {
		return abilities;
	}

	public int getLevel() {
		return level;
	}
	
	public boolean isControlable() {
		return controlable;
	}
	
	public int getId() {
		return id;
	}

	public int getXPos() {
		return (int) pointXY.x;
	}
	
	public int getYPos() {
		return (int) pointXY.y;
	}
	
	public Point2DNoFxReq getPoint() {
		return pointXY;
	}
	
	public void setPoint(Point2DNoFxReq moveToPoint) {
		this.pointXY.setLocation(moveToPoint);
	}
	
	public void setCurrentHealth(int currentHealth) {
		this.currentHealth = currentHealth;

		if (currentHealth <= 0) {
			destroy();
		}

	}
	
	public void setName(String name) {
		this.name = name;
	}

	public void addLevel() {
		level ++;
	}
	
	private void destroy() {

		int i = 0;
		
		while (GameInfo.getObjectMap().getEntityMap().get(i) != this && i < GameInfo.getObjectMap().getEntityMap().size() - 1) {
			i++;
		}

		if(this.event != null) {
			GameInfo.getRoundInfo().getEventList().remove(this.event);
			this.event = null;
		}
		if(GameInfo.getObjectMap().getEntityMap().get(i) == this) {
			GameInfo.getObjectMap().getEntityMap().remove(i);
		}
		

	}

	@Override
	public String toString() {
		return "Entity [id=" + id + ", pointXY=" + pointXY + ", name=" + name + ", maxHealth=" + maxHealth
				+ ", currentHealth=" + currentHealth + ", abilities=" + abilities + ", level=" + level + ", maxRange="
				+ maxRange + ", event=" + event + ", controlable=" + controlable + "]";
	}

	public boolean hasAbility() {
		boolean has = false;
		for (int i = 0; i < abilities.size(); i++) {
			if (abilities.get(i) != null) {
				has = true;
				return has;
			}
		}
		return has;
	}

	public void setEvent(Event event) {
		if(this.event != null) {
			GameInfo.getRoundInfo().getEventList().remove(this.event);
			this.event=null;
			System.gc();
		}
		this.event = event;
	}
	
	public Event getEvent() {
		return this.event;
	}

	/**
	 * This is needed for when the round end iterates through all the Events
	 */
	public void removeEventWithoutRemovingFromList() {
		this.event = null;
	}
	
	public ArrayList<StatusEffect> getStatusEffects() {
		return statusEffects;
	}
	
	public void addStatusEffect(StatusEffect statusEffect) {
		statusEffects.add(statusEffect);
	}
	
	public void removeStatusEffect(StatusEffect statusEffect) {
				
		statusEffects.remove(statusEffect);
		
	}

}

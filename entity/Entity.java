package entity;

import java.util.ArrayList;

import abilities.Ability;
import abilities.Destroy;
import core.Core;
import core.Event;
import core.GameInfo;
import core.PlayerStats;
import core.PlayerStats.PlayerResources;
import core.Point2DNoFxReq;
import cost.AvailableResources;
import cost.Cost;
import entity.building.Building;
import entity.building.ProductionBuilding;
import entity.unit.Unit;
import frame.menuPanels.EndScreenPanel;
import statusEffects.StatusEffect;

public class Entity {
	private static int entityCount;

	public static final int MAX_LEVEL = 3; // The MaxLevel of every entity

	private int id;
	private Point2DNoFxReq pointXY = new Point2DNoFxReq();
	private String name;
	private int maxHealth;
	private int currentHealth;
	private ArrayList<Ability> abilities = new ArrayList<Ability>();
	private ArrayList<StatusEffect> statusEffects = new ArrayList<>(); // maybe add to constructor for permanent effects
	private int level = 0;
	private Event event = null;
	private boolean controllable = false;
	private PlayerResources playerResources;
	private PlayerStats playerStats;
	private boolean autoIdle = false;

	public Entity(Point2DNoFxReq pointXY, String name, int maxHealth, int currentHealth, int level, boolean controlable,
			ArrayList<Ability> abilities, boolean autoIdle) {
		entityCount++;
		this.id = entityCount;
		this.pointXY.setLocation(pointXY);
		this.name = name;
		this.maxHealth = maxHealth;
		this.currentHealth = currentHealth;
		this.level = level;
		this.controllable = controlable;
		this.abilities = abilities;
		this.autoIdle = autoIdle;
		
		if(controlable == true) {
			this.abilities.add(new Destroy());
		}
		
	}

	public boolean canBeLeveled() {
		if(playerResources == null) {
			playerResources = GameInfo.getPlayerStats().getPlayerResources();
		}
		if(playerStats == null) {
			playerStats = GameInfo.getPlayerStats();
		}
		
		if (this.getLevel() < Entity.MAX_LEVEL) {
			Cost levelUpCost = playerStats.getCostManager().getLevelUpCostManager().getLevelUpCost(this);
			
			AvailableResources availableResources = playerStats.getCostManager().getAvailableResources();
			
			int availableFood = availableResources.getAvailableFood();
			int availableWood =  availableResources.getAvailableWood();
			int availableStone = availableResources.getAvailableStone();
			int availableMetal = availableResources.getAvailableMetal();
			int availableGold = availableResources.getAvailableGold();
			int availableManaStone = availableResources.getAvailableManaStone();
			
			if (levelUpCost.getFoodCost() > availableFood) {
				return false;
			}
			if (levelUpCost.getWoodCost() > availableWood) {
				return false;
			}
			if (levelUpCost.getStoneCost() > availableStone) {
				return false;
			}
			if (levelUpCost.getMetalCost() > availableMetal) {
				return false;
			}
			if (levelUpCost.getGoldCost() > availableGold) {
				return false;
			}
			if (levelUpCost.getManaStoneCost() > availableManaStone) {
				return false;
			}
			return true;
		} else {
			return false;
		}
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
	
	public ArrayList<Ability> getAbilities() {
		return abilities;
	}

	public int getLevel() {
		return level;
	}

	public boolean isControllable() {
		return controllable;
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
		level++;
	}

	public void destroy() {

		int i = 0;

		while (GameInfo.getObjectMap().getEntityMap().get(i) != this
				&& i < GameInfo.getObjectMap().getEntityMap().size() - 1) {
			i++;
		}

		if (this.event != null) {
			this.event = null;
		}
		if (GameInfo.getObjectMap().getEntityMap().get(i) == this) {
			GameInfo.getObjectMap().getEntityMap().remove(i);
		}
		
		if(this.isControllable() == false) {
			if(this instanceof Building) {
				GameInfo.getPlayerStats().addBuildingsDestroyed(1);
			}
			if(this instanceof Unit) {
				GameInfo.getPlayerStats().addUnitsKilled(1);
			}
		}
		if(this instanceof ProductionBuilding) {
			if(this.getName().equals(Building.TOWN_CENTER)) {
				Core.getMainJFrame().setCurrentComponent(new EndScreenPanel(false));
			} else if(this.getName().equals(Building.PORTAL)) {
				Core.getMainJFrame().setCurrentComponent(new EndScreenPanel(true));
			}
		}

	}

	@Override
	public String toString() {
		return name+ "(id="+id+") hp("+ currentHealth + "/" + maxHealth+")";
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
		if (this.event != null) {
			GameInfo.getRoundInfo().getEventList().remove(this.event);
			this.event = null;
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

	public void addStatusEffect(StatusEffect statusEffect, Entity source, Entity target) {
		statusEffect.setTarget(target);
		statusEffect.setSource(source);
		for (StatusEffect se : statusEffects) {
			if (se.getClass().getName().equals(statusEffect.getClass().getName())) {
				se.setDuration(statusEffect.getDuration());
				return;
			}
		}
		statusEffects.add(statusEffect);
	}

	public void removeStatusEffect(StatusEffect statusEffect) {

		statusEffects.remove(statusEffect);

	}

	public boolean isAutoIdle() {
		return autoIdle;
	}

	public void setAutoIdle(boolean autoIdle) {
		this.autoIdle = autoIdle;
	}

}

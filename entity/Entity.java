package entity;

import java.util.ArrayList;

import abilities.Ability;
import abilities.Build;
import abilities.CreateUnit;
import abilities.LevelUp;
import core.Event;
import core.GameInfo;
import core.PlayerStats.PlayerResources;
import cost.BuildingCostManager;
import cost.Cost;
import cost.LevelUpCostManager;
import cost.ProductionCostManager;
import core.Point2DNoFxReq;
import statusEffects.StatusEffect;

public class Entity {
	private static int entityCount;

	public static final int MAX_LEVEL = 3; // The MaxLevel of every entity

	private int id;
	private Point2DNoFxReq pointXY = new Point2DNoFxReq();
	private String name;
	private int maxHealth;
	private int currentHealth;
	private ArrayList<Ability> abilities;
	private ArrayList<StatusEffect> statusEffects = new ArrayList<>(); // maybe add to constructor for permanent effects
	private int level = 0;
	private int maxRange = 5; // Will be calculated via the abilities in the future
	private Event event = null;
	private boolean controlable = false;
	private BuildingCostManager buildingCostManager;
	private ProductionCostManager productionCostManager;
	private LevelUpCostManager levelUpCostManager;
	private PlayerResources playerResources;

	public Entity(Point2DNoFxReq pointXY, String name, int maxHealth, int currentHealth, int level, boolean controlable,
			ArrayList<Ability> abilities) {
		entityCount++;
		this.id = entityCount;
		this.pointXY.setLocation(pointXY);
		this.name = name;
		this.maxHealth = maxHealth;
		this.currentHealth = currentHealth;
		this.level = level;
		this.controlable = controlable;
		this.abilities = abilities;
		buildingCostManager = new BuildingCostManager();
		levelUpCostManager = new LevelUpCostManager();
		productionCostManager = new ProductionCostManager();
	}

	public boolean canBeLeveled() {
		if(playerResources == null) {
			playerResources = GameInfo.getPlayerStats().getPlayerResources();
		}
		
		if (this.getLevel() < Entity.MAX_LEVEL) {
			Cost levelUpCost = levelUpCostManager.getLevelUpCost(this);
			
			int availableFood = playerResources.getFood();
			int availableWood =  playerResources.getWood();
			int availableStone = playerResources.getStone();
			int availableMetal = playerResources.getMetal();
			int availableGold = playerResources.getGold();
			int availableManaStone = playerResources.getManaStone();
			
			for(Event event: GameInfo.getRoundInfo().getEventList()) {
				if(event.getAbility() instanceof LevelUp) {
					Cost entityLevelUpCost;
					entityLevelUpCost = levelUpCostManager.getLevelUpCost(event.getTarget());
					availableFood -= entityLevelUpCost.getFoodCost();
					availableWood -= entityLevelUpCost.getWoodCost();
					availableStone -= entityLevelUpCost.getStoneCost();
					availableMetal -= entityLevelUpCost.getMetalCost();
					availableGold -= entityLevelUpCost.getGoldCost();
					availableManaStone -= entityLevelUpCost.getManaStoneCost();
				}
				if(event.getAbility() instanceof Build) {
					Cost entityBuildingCost;
					entityBuildingCost = buildingCostManager.getBuildingCost(((Build)event.getAbility()).getBuildingType());
					availableFood -= entityBuildingCost.getFoodCost();
					availableWood -= entityBuildingCost.getWoodCost();
					availableStone -= entityBuildingCost.getStoneCost();
					availableMetal -= entityBuildingCost.getMetalCost();
					availableGold -= entityBuildingCost.getGoldCost();
					availableManaStone -= entityBuildingCost.getManaStoneCost();
				}
				if(event.getAbility() instanceof CreateUnit) {
					Cost productionCost;
					productionCost = productionCostManager.getProductionCost(((CreateUnit)event.getAbility()).getType());
					availableFood -= productionCost.getFoodCost();
					availableWood -= productionCost.getWoodCost();
					availableStone -= productionCost.getStoneCost();
					availableMetal -= productionCost.getMetalCost();
					availableGold -= productionCost.getGoldCost();
					availableManaStone -= productionCost.getManaStoneCost();
				}
			}
			
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
		level++;
	}

	private void destroy() {

		int i = 0;

		while (GameInfo.getObjectMap().getEntityMap().get(i) != this
				&& i < GameInfo.getObjectMap().getEntityMap().size() - 1) {
			i++;
		}

		if (this.event != null) {
			GameInfo.getRoundInfo().getEventList().remove(this.event);
			this.event = null;
		}
		if (GameInfo.getObjectMap().getEntityMap().get(i) == this) {
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

}

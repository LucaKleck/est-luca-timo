package core;

import cost.CostManager;
import frame.MainJFrame;

/**
 * @author Luca Kleck
 */
public class PlayerStats {
	
	private int unitsKilled = 0;
	private int buildingsDestroyed = 0;
	private int unitsCreated = 0;
	private int buildingsBuilt = 0;
	
	private int totalWoodCollected = 0;
	private int totalFoodCollected = 0;
	private int totalStoneCollected = 0;
	private int totalMetalCollected = 0;
	private int totalGoldCollected = 0;
	private int totalManaStoneCollected = 0;
	
	private PlayerResources playerResources;
	private CostManager costManager;
	
	private static final int INITAL_FOOD = 500;
	private static final int INITAL_WOOD = 250;
	private static final int INITAL_STONE = 200;
	private static final int INITAL_METAL = 50;
	private static final int INITAL_GOLD = 50;
	private  static final int INITAL_MANA_STONE = 0;

	public PlayerStats() {
		playerResources = new PlayerResources(INITAL_FOOD, INITAL_WOOD, INITAL_STONE, INITAL_METAL, INITAL_GOLD, INITAL_MANA_STONE);
		costManager = new CostManager();
	}
	
	public PlayerStats(int unitsKilled, int buildingsDestroyed, int unitsCreated, int buildingsBuilt, PlayerResources playerResources, int totalFoodCollected, int totalWoodCollected, int totalStoneCollected, int totalMetalCollected, int totalGoldCollected, int totalManaStoneCollected) {
		this.unitsKilled = unitsKilled;
		this.buildingsDestroyed = buildingsDestroyed;
		this.unitsCreated = unitsCreated;
		this.buildingsBuilt = buildingsBuilt;
		
		this.totalFoodCollected = totalFoodCollected;
		this.totalWoodCollected = totalWoodCollected;
		this.totalGoldCollected = totalGoldCollected;
		this.totalStoneCollected = totalStoneCollected;
		this.totalMetalCollected = totalMetalCollected;
		this.totalManaStoneCollected = totalManaStoneCollected;
		
		this.playerResources = playerResources;
		costManager = new CostManager();
	}

	public PlayerResources getPlayerResources() {
		return playerResources;
	}
	
	public CostManager getCostManager() {
		return costManager;
	}

	public synchronized int getUnitsKilled() {
		return unitsKilled;
	}

	public synchronized void setUnitsKilled(int unitsKilled) {
		this.unitsKilled = unitsKilled;
	}
	
	public synchronized void addUnitsKilled(int unitsKilled) {
		this.unitsKilled += unitsKilled;
	}

	public synchronized int getBuildingsDestroyed() {
		return buildingsDestroyed;
	}

	public synchronized void setBuildingsDestroyed(int buildingsDestroyed) {
		this.buildingsDestroyed = buildingsDestroyed;
	}
	
	public synchronized void addBuildingsDestroyed(int buildingsDestroyed) {
		this.buildingsDestroyed += buildingsDestroyed;
	}

	public synchronized int getUnitsCreated() {
		return unitsCreated;
	}

	public synchronized void setUnitsCreated(int unitsCreated) {
		this.unitsCreated = unitsCreated;
	}
	
	public synchronized void addUnitsCreated(int unitsCreated) {
		this.unitsCreated += unitsCreated;
	}

	public synchronized int getBuildingsBuilt() {
		return buildingsBuilt;
	}

	public synchronized void setBuildingsBuilt(int buildingsBuilt) {
		this.buildingsBuilt = buildingsBuilt;
	}
	
	public synchronized void addBuildingsBuilt(int buildingsBuilt) {
		this.buildingsBuilt += buildingsBuilt;
	}
	
	public synchronized int getTotalGoldCollected() {
		return totalGoldCollected;
	}

	public synchronized void setTotalGoldCollected(int totalGoldCollected) {
		this.totalGoldCollected = totalGoldCollected;
	}
	
	public synchronized void addTotalGoldCollected(int totalGoldCollected) {
		this.totalGoldCollected += totalGoldCollected;
	}
	
	public synchronized int getTotalFoodCollected() {
		return totalFoodCollected;
	}

	public synchronized void setTotalFoodCollected(int totalFoodCollected) {
		this.totalFoodCollected = totalFoodCollected;
	}
	
	public synchronized void addTotalFoodCollected(int totalFoodCollected) {
		this.totalFoodCollected += totalFoodCollected;
	}
	
	public synchronized int getTotalWoodCollected() {
		return totalWoodCollected;
	}

	public synchronized void setTotalWoodCollected(int totalWoodCollected) {
		this.totalWoodCollected = totalWoodCollected;
	}
	
	public synchronized void addTotalWoodCollected(int totalWoodCollected) {
		this.totalWoodCollected += totalWoodCollected;
	}
	
	public synchronized int getTotalStoneCollected() {
		return totalStoneCollected;
	}

	public synchronized void setTotalStoneCollected(int totalStoneCollected) {
		this.totalStoneCollected = totalStoneCollected;
	}
	
	public synchronized void addTotalStoneCollected(int totalStoneCollected) {
		this.totalStoneCollected += totalStoneCollected;
	}
	
	public synchronized int getTotalMetalCollected() {
		return totalMetalCollected;
	}

	public synchronized void setTotalMetalCollected(int totalMetalCollected) {
		this.totalMetalCollected = totalGoldCollected;
	}
	
	public synchronized void addTotalMetalCollected(int totalMetalCollected) {
		this.totalMetalCollected += totalGoldCollected;
	}
	
	public synchronized int getTotalManaStoneCollected() {
		return totalManaStoneCollected;
	}

	public synchronized void setTotalManaStoneCollected(int totalManaStoneCollected) {
		this.totalManaStoneCollected = totalManaStoneCollected;
	}
	
	public synchronized void addTotalManaStoneCollected(int totalManaStoneCollected) {
		this.totalManaStoneCollected += totalManaStoneCollected;
	}

	public synchronized void setPlayerResources(PlayerResources playerResources) {
		this.playerResources = playerResources;
	}
	
	public class PlayerResources {
		private int gold = 0;
		private int food = 0;
		private int wood = 0;
		private int stone = 0;
		private int metal = 0;
		private int manaStone = 0;

		public PlayerResources() {
		}

		public PlayerResources(int food, int wood, int stone, int metal, int gold, int manaStone) {
			this.food = food;
			this.wood = wood;
			this.stone = stone;
			this.metal = metal;
			this.gold = gold;
			this.manaStone = manaStone;
		}
		
		public int getGold() {
			return gold;
		}

		public int getFood() {
			return food;
		}

		public int getWood() {
			return wood;
		}

		public int getStone() {
			return stone;
		}

		public int getMetal() {
			return metal;
		}

		public int getManaStone() {
			return manaStone;
		}

		public synchronized void addGold(int gold) {
			this.gold += gold;
		}

		public synchronized void addFood(int food) {
			this.food += food;
		}

		public synchronized void addWood(int wood) {
			this.wood += wood;
		}

		public synchronized void addStone(int stone) {
			this.stone += stone;
		}

		public synchronized void addMetal(int metal) {
			this.metal += metal;
		}

		public synchronized void addManaStone(int manaStone) {
			this.manaStone += manaStone;
		}
		
		public synchronized void reduceGoldBy(int gold) {
			this.gold -= gold;
		}

		public synchronized void reduceFoodBy(int food) {
			this.food -= food;
		}

		public synchronized void reduceWoodBy(int wood) {
			this.wood -= wood;
		}

		public synchronized void reduceStoneBy(int stone) {
			this.stone -= stone;
		}

		public synchronized void reduceMetalBy(int metal) {
			this.metal -= metal;
		}

		public synchronized void reduceManaStoneBy(int manaStone) {
			this.manaStone -= manaStone;
		}

		@Override
		public String toString() {
			return MainJFrame.makeCssStyle("padding:3px; background-color: #FFFFFF; color: #121212")+"PlayerResources [gold=" + gold + ", food=" + food + ", wood=" + wood + ", stone=" + stone
					+ ", metal=" + metal + ", manaStone=" + manaStone + "]";
		}
		
	}
}

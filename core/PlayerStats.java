package core;


public class PlayerStats {
	
	private int clicks = 0;
	
	private int unitsKilled = 0;
	private int buildingsDestroyed = 0;
	private int damageDealt = 0;

	private int unitsCreated = 0;
	private int buildingsBuilt = 0;
	
	private int timePlayedMins = 0;

	private PlayerResources playerResources;

	public PlayerStats() {
		playerResources = new PlayerResources();
	}
	
	public PlayerStats(int clicks,int unitsKilled, int buildingsDestroyed, int damageDealt, int unitsCreated, int buildingsBuilt, int timePlayedMins, PlayerResources playerResources) {
		this.clicks = clicks;
		this.unitsKilled = unitsKilled;
		this.buildingsDestroyed = buildingsDestroyed;
		this.damageDealt = damageDealt;
		this.unitsCreated = unitsCreated;
		this.buildingsBuilt = buildingsBuilt;
		this.timePlayedMins = timePlayedMins;
		this.playerResources = playerResources;
	}

	public PlayerResources getPlayerResources() {
		return playerResources;
	}

	public synchronized int getClicks() {
		return clicks;
	}

	public synchronized void setClicks(int clicks) {
		this.clicks = clicks;
	}

	public synchronized int getUnitsKilled() {
		return unitsKilled;
	}

	public synchronized void setUnitsKilled(int unitsKilled) {
		this.unitsKilled = unitsKilled;
	}

	public synchronized int getBuildingsDestroyed() {
		return buildingsDestroyed;
	}

	public synchronized void setBuildingsDestroyed(int buildingsDestroyed) {
		this.buildingsDestroyed = buildingsDestroyed;
	}

	public synchronized int getDamageDealt() {
		return damageDealt;
	}

	public synchronized void setDamageDealt(int damageDealt) {
		this.damageDealt = damageDealt;
	}

	public synchronized int getUnitsCreated() {
		return unitsCreated;
	}

	public synchronized void setUnitsCreated(int unitsCreated) {
		this.unitsCreated = unitsCreated;
	}

	public synchronized int getBuildingsBuilt() {
		return buildingsBuilt;
	}

	public synchronized void setBuildingsBuilt(int buildingsBuilt) {
		this.buildingsBuilt = buildingsBuilt;
	}

	public synchronized int getTimePlayedMins() {
		return timePlayedMins;
	}

	public synchronized void setTimePlayedMins(int timePlayedMins) {
		this.timePlayedMins = timePlayedMins;
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

		public PlayerResources(int gold, int food, int wood, int stone, int metal, int manaStone) {
			this.gold = gold;
			this.food = food;
			this.wood = wood;
			this.stone = stone;
			this.metal = metal;
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
		
	}
}

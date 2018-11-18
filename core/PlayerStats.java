package core;


public class PlayerStats {
	private int unitsKilled;
	private int buildingsDestroyed;

	private int unitsCreated;
	private int buildingsBuilt;

	private PlayerResources playerResources;

	public PlayerStats() {

	}
	
	public PlayerStats(int unitsKilled, int buildingsDestroyed, int unitsCreated, int buildingsBuilt, PlayerResources playerResources) {
		this.unitsKilled = unitsKilled;
		this.buildingsDestroyed = buildingsDestroyed;
		this.unitsCreated = unitsCreated;
		this.buildingsBuilt = buildingsBuilt;
		this.playerResources = playerResources;
	}

	public PlayerResources getPlayerResources() {
		return playerResources;
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
		
	}
}

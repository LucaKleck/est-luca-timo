package cost;

public class Cost {

	private int foodCost;
	private int woodCost;
	private int stoneCost;
	private int metalCost;
	private int goldCost;
	private int manaStoneCost;

	public Cost(int foodCost, int woodCost, int stoneCost, int metalCost, int goldCost, int manaStoneCost) {
		this.foodCost = foodCost;
		this.woodCost = woodCost;
		this.stoneCost = stoneCost;
		this.metalCost = metalCost;
		this.goldCost = goldCost;
		this.manaStoneCost = manaStoneCost;
	}
	
	public int getManaStoneCost() {
		return manaStoneCost;
	}

	public int getFoodCost() {
		return foodCost;
	}

	public int getWoodCost() {
		return woodCost;
	}

	public int getStoneCost() {
		return stoneCost;
	}

	public int getMetalCost() {
		return metalCost;
	}

	public int getGoldCost() {
		return goldCost;
	}
	
}

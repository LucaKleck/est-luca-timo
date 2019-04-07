package cost;

public class AvailableResources {

	private int availableFood;
	private int availableWood; 
	private int availableStone;
	private int availableMetal;
	private int availableGold;
	private int availableManaStone;
	
	public AvailableResources() {
		this.availableFood = 0;
		this.availableWood = 0;
		this.availableStone = 0;
		this.availableMetal = 0;
		this.availableGold = 0;
		this.availableManaStone = 0;
	}
	
	public int getAvailableFood() {
		return availableFood;
	}
	public void setAvailableFood(int availableFood) {
		this.availableFood = availableFood;
	}
	public void reduceAvailableFoodBy(int availableFood) {
		this.availableFood -= availableFood;
	}
	
	public int getAvailableWood() {
		return availableWood;
	}
	public void setAvailableWood(int availableWood) {
		this.availableWood = availableWood;
	}
	public void reduceAvailableWoodBy(int availableWood) {
		this.availableWood -= availableWood;
	}
	
	public int getAvailableStone() {
		return availableStone;
	}
	public void setAvailableStone(int availableStone) {
		this.availableStone = availableStone;
	}
	public void reduceAvailableStoneBy(int availableStone) {
		this.availableStone -= availableStone;
	}
	
	public int getAvailableMetal() {
		return availableMetal;
	}
	public void setAvailableMetal(int availableMetal) {
		this.availableMetal = availableMetal;
	}
	public void reduceAvailableMetalBy(int availableMetal) {
		this.availableMetal -= availableMetal;
	}
	
	public int getAvailableGold() {
		return availableGold;
	}
	public void setAvailableGold(int availableGold) {
		this.availableGold = availableGold;
	}
	public void reduceAvailableGoldBy(int availableGold) {
		this.availableGold -= availableGold;
	}
	
	public int getAvailableManaStone() {
		return availableManaStone;
	}
	public void setAvailableManaStone(int availableManaStone) {
		this.availableManaStone = availableManaStone;
	}
	public void reduceAvailableManaStoneBy(int availableManaStone) {
		this.availableManaStone -= availableManaStone;
	}
	
}

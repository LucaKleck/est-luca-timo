package cost;

import entity.unit.Unit;

public class ProductionCostManager {

	private Cost archerProductionCost;
	private Cost builderProductionCost;
	private Cost mageProductionCost;
	private Cost trebuchetProductionCost;
	private Cost warriorProductionCost;
	private Cost batteringRamProductionCost;
	private Cost cavalryProductionCost;
	private Cost cavalryArcherProductionCost;
	private Cost dragonProductionCost;
	private Cost mangonelProductionCost;
	private Cost knightProductionCost;
	private Cost priestProductionCost;
	private Cost heroProductionCost;

	//Cost-Constructor: Food, Wood, Stone, Metal, Gold, ManaStone
	
	public Cost getProductionCost(String type) {
		
		if(type.equals(Unit.UNIT_ARCHER)) {
			if(archerProductionCost == null) {
				archerProductionCost = new Cost(250, 0, 0, 50, 100, 0);
			}
			return archerProductionCost;
		}
		if(type.equals(Unit.UNIT_BUILDER)) {
			if(builderProductionCost == null) {
				builderProductionCost = new Cost(300, 0, 0, 50, 0, 0);
			}
			return builderProductionCost;
		}
		if(type.equals(Unit.UNIT_MAGE)) {
			if(mageProductionCost == null) {
				mageProductionCost = new Cost(200, 0, 0, 0, 50, 150);
			}
			return mageProductionCost;
		}
		if(type.equals(Unit.UNIT_TREBUCHET)) {
			if(trebuchetProductionCost == null) {
				trebuchetProductionCost = new Cost(0, 300, 100, 100, 100, 0);
			}
			return trebuchetProductionCost;
		}
		if(type.equals(Unit.UNIT_WARRIOR)) {
			if(warriorProductionCost == null) {
				warriorProductionCost = new Cost(200, 100, 0, 100, 0, 0);
			}
			return warriorProductionCost;
		}
		if(type.equals(Unit.UNIT_BATTERING_RAM)) {
			if(batteringRamProductionCost == null) {
				batteringRamProductionCost = new Cost(0, 300, 100, 100, 100, 0);
			}
			return batteringRamProductionCost;
		}
		if(type.equals(Unit.UNIT_CAVALRY)) {
			if(cavalryProductionCost == null) {
				cavalryProductionCost = new Cost(250, 0, 0, 150, 0, 0);
			}
			return cavalryProductionCost;
		}
		if(type.equals(Unit.UNIT_CAVALRY_ARCHER)) {
			if(cavalryArcherProductionCost == null) {
				cavalryArcherProductionCost = new Cost(200, 0, 0, 200, 50, 0);
			}
			return cavalryArcherProductionCost;
		}
		if(type.equals(Unit.UNIT_DRAGON)) {
			if(dragonProductionCost == null) {
				dragonProductionCost = new Cost(400, 0, 0, 300, 300, 100);
			}
			return dragonProductionCost;
		}
		if(type.equals(Unit.UNIT_HERO)) {
			if(heroProductionCost == null) {
				heroProductionCost = new Cost(1000, 200, 200, 200, 500, 200);
			}
			return heroProductionCost;
		}
		if(type.equals(Unit.UNIT_KNIGHT)) {
			if(knightProductionCost == null) {
				knightProductionCost = new Cost(200, 0, 0, 200, 50, 0);
			}
			return knightProductionCost;
		}
		if(type.equals(Unit.UNIT_MANGONEL)) {
			if(mangonelProductionCost == null) {
				mangonelProductionCost = new Cost(0, 300, 100, 100, 100, 0);
			}
			return mangonelProductionCost;
		}
		if(type.equals(Unit.UNIT_PRIEST)) {
			if(priestProductionCost == null) {
				priestProductionCost = new Cost(200, 0, 0, 100, 50, 100);
			}
			return priestProductionCost;
		}
		return null;
	}
	
}

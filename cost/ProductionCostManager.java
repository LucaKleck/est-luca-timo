package cost;

import entity.unit.Unit;

public class ProductionCostManager {

	private Cost archerProductionCost;
	private Cost builderProductionCost;
	private Cost mageProductionCost;
	private Cost trebuchetProductionCost;
	private Cost warriorProductionCost;

	//Cost-Constructor: Food, Wood, Stone, Metal, Gold, ManaStone
	
	public Cost getProductionCost(String type) {
		
		if(type.equals(Unit.UNIT_ARCHER)) {
			if(archerProductionCost == null) {
				archerProductionCost = new Cost(250, 0, 0, 50, 150, 0);
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
				warriorProductionCost = new Cost(200, 0, 0, 200, 50, 0);
			}
			return warriorProductionCost;
		}
		
		return null;
	}
	
}

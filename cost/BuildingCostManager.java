package cost;

import entity.building.Building;

public class BuildingCostManager {

	// DefenseBuildings
	private Cost archerTowerBuildingCost;
	private Cost mageTowerBuildingCost;
	// ProductionBuildings
	private Cost siegeWorkshopBuildingCost;
	private Cost barracksBuildingCost;
	private Cost churchBuildingCost;
	private Cost stableBuildingCost;
	// ResourceBuildings
	private Cost foodGetterBuildingCost;
	private Cost manaStoneGetterBuildingCost;
	private Cost stoneGetterBuildingCost;
	private Cost metalGetterBuildingCost;
	private Cost goldGetterBuildingCost;
	private Cost woodGetterBuildingCost;
	
	//Cost-Constructor: Food, Wood, Stone, Metal, Gold, ManaStone
	
	public Cost getBuildingCost(String buildingType) {
			if (buildingType.equals(Building.ARCHER_TOWER)) {
				if (archerTowerBuildingCost == null) {
					archerTowerBuildingCost = new Cost(100, 300, 150, 0, 0, 0);
				}
				return archerTowerBuildingCost;
			}
			if (buildingType.equals(Building.MAGE_TOWER)) {
				if (mageTowerBuildingCost == null) {
					mageTowerBuildingCost = new Cost(100, 0, 300, 0, 0, 150);
				}
				return mageTowerBuildingCost;
			}
			
			if (buildingType.equals(Building.BARRACKS)) {
				if (barracksBuildingCost == null) {
					barracksBuildingCost = new Cost(150, 300, 0, 0, 50, 0);
				}
				return barracksBuildingCost;
			}
			if (buildingType.equals(Building.SIEGE_WORKSHOP)) {
				if (siegeWorkshopBuildingCost == null) {
					siegeWorkshopBuildingCost = new Cost(0, 300, 150, 150, 50, 0);
				}
				return siegeWorkshopBuildingCost;
			}
			if (buildingType.equals(Building.CHURCH)) {
				if (churchBuildingCost == null) {
					churchBuildingCost = new Cost(0, 300, 300, 0, 50, 50);
				}
				return churchBuildingCost;
			}
			if (buildingType.equals(Building.STABLE)) {
				if (stableBuildingCost == null) {
					stableBuildingCost = new Cost(300, 150, 150, 0, 0, 0);
				}
				return stableBuildingCost;
			}
			
			if (buildingType.equals(Building.FOOD_GETTER)) {
				if (foodGetterBuildingCost == null) {
					foodGetterBuildingCost = new Cost(0, 250, 0, 0, 0, 0);
				}
				return foodGetterBuildingCost;
			}
			if (buildingType.equals(Building.WOOD_GETTER)) {
				if (woodGetterBuildingCost == null) {
					woodGetterBuildingCost = new Cost(300, 0, 0, 0, 0, 0);
				}
				return woodGetterBuildingCost;
			}
			if (buildingType.equals(Building.STONE_GETTER)) {
				if (stoneGetterBuildingCost == null) {
					stoneGetterBuildingCost = new Cost(100, 300, 0, 0, 0, 0);
				}
				return stoneGetterBuildingCost;
			}
			if (buildingType.equals(Building.METAL_GETTER)) {
				if (metalGetterBuildingCost == null) {
					metalGetterBuildingCost = new Cost(100, 0, 300, 0, 0, 0);
				}
				return metalGetterBuildingCost;
			}
			if (buildingType.equals(Building.MANA_GETTER)) {
				if (manaStoneGetterBuildingCost == null) {
					manaStoneGetterBuildingCost = new Cost(100, 0, 0, 0, 300, 0);
				}
				return manaStoneGetterBuildingCost;
			}
			if (buildingType.equals(Building.GOLD_GETTER)) {
				if (goldGetterBuildingCost == null) {
					goldGetterBuildingCost = new Cost(100, 0, 0, 300, 0, 0);
				}
				return goldGetterBuildingCost;
			}
		return null;
	}
	
}

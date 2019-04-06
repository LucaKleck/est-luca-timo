package cost;

import java.util.ArrayList;

import entity.Entity;
import entity.building.Building;
import entity.building.DefenseBuilding;
import entity.building.ProductionBuilding;
import entity.building.ResourceBuilding;
import entity.unit.Archer;
import entity.unit.Builder;
import entity.unit.Mage;
import entity.unit.Trebuchet;
import entity.unit.Warrior;

public class LevelUpCostManager {
	// Units
	private ArrayList<Cost> archerLevelUpCost;
	private ArrayList<Cost> builderLevelUpCost;
	private ArrayList<Cost> mageLevelUpCost;
	private ArrayList<Cost> warriorLevelUpCost;
	private ArrayList<Cost> trebuchetLevelUpCost;
	// Buildings
	// DefenseBuildings
	private ArrayList<Cost> archerTowerLevelUpCost;
	private ArrayList<Cost> mageTowerLevelUpCost;
	// ProductionBuildings
	private ArrayList<Cost> siegeWorkshopLevelUpCost;
	private ArrayList<Cost> townCenterLevelUpCost;
	private ArrayList<Cost> barracksLevelUpCost;
	// ResourceBuildings
	private ArrayList<Cost> foodGetterLevelUpCost;
	private ArrayList<Cost> manaStoneGetterLevelUpCost;
	private ArrayList<Cost> stoneGetterLevelUpCost;
	private ArrayList<Cost> metalGetterLevelUpCost;
	private ArrayList<Cost> goldGetterLevelUpCost;
	private ArrayList<Cost> woodGetterLevelUpCost;

	//Cost-Constructor: Food, Wood, Stone, Metal, Gold, ManaStone
	
	public Cost getLevelUpCost(Entity entity) {
		
		if(entity.getLevel() < Entity.MAX_LEVEL == false) {
			return null;
		}
		
		if (entity instanceof Archer) {
			if (archerLevelUpCost == null) {
				archerLevelUpCost = new ArrayList<Cost>();
				archerLevelUpCost.add(new Cost(200, 0, 0, 0, 0, 0));
				archerLevelUpCost.add(new Cost(400, 0, 0, 0, 100, 0));
				archerLevelUpCost.add(new Cost(800, 0, 0, 0, 200, 0));
			}
			return archerLevelUpCost.get(entity.getLevel());
		}
		if (entity instanceof Builder) {
			if (builderLevelUpCost == null) {
				builderLevelUpCost = new ArrayList<Cost>();
				builderLevelUpCost.add(new Cost(200, 0, 0, 0, 0, 0));
				builderLevelUpCost.add(new Cost(300, 100, 0, 0, 0, 0));
				builderLevelUpCost.add(new Cost(400, 200, 200, 0, 0, 0));
			}
			return builderLevelUpCost.get(entity.getLevel());
		}
		if (entity instanceof Mage) {
			if (mageLevelUpCost == null) {
				mageLevelUpCost = new ArrayList<Cost>();
				mageLevelUpCost.add(new Cost(200, 0, 0, 0, 0, 0));
				mageLevelUpCost.add(new Cost(300, 0, 0, 0, 0, 100));
				mageLevelUpCost.add(new Cost(400, 0, 0, 0, 0, 300));
			}
			return mageLevelUpCost.get(entity.getLevel());
		}
		if (entity instanceof Warrior) {
			if (warriorLevelUpCost == null) {
				warriorLevelUpCost = new ArrayList<Cost>();
				warriorLevelUpCost.add(new Cost(200, 0, 0, 0, 0, 0));
				warriorLevelUpCost.add(new Cost(300, 0, 0, 0, 100, 0));
				warriorLevelUpCost.add(new Cost(400, 0, 0, 0, 200, 0));
			}
			return warriorLevelUpCost.get(entity.getLevel());
		}
		if (entity instanceof Trebuchet) {
			if (trebuchetLevelUpCost == null) {
				trebuchetLevelUpCost = new ArrayList<Cost>();
				trebuchetLevelUpCost.add(new Cost(0, 200, 0, 0, 0, 0));
				trebuchetLevelUpCost.add(new Cost(0, 300, 0, 100, 0, 0));
				trebuchetLevelUpCost.add(new Cost(0, 400, 10, 200, 0, 0));
			}
			return trebuchetLevelUpCost.get(entity.getLevel());
		}
		if (entity instanceof DefenseBuilding) {
			if (entity.getName().equals(Building.ARCHER_TOWER)) {
				if (archerTowerLevelUpCost == null) {
					archerTowerLevelUpCost = new ArrayList<Cost>();
					archerTowerLevelUpCost.add(new Cost(0, 200, 0, 0, 0, 0));
					archerTowerLevelUpCost.add(new Cost(100, 300, 0, 0, 0, 0));
					archerTowerLevelUpCost.add(new Cost(200, 400, 0, 0, 100, 0));
				}
				return archerTowerLevelUpCost.get(entity.getLevel());
			}
			if (entity.getName().equals(Building.MAGE_TOWER)) {
				if (mageTowerLevelUpCost == null) {
					mageTowerLevelUpCost = new ArrayList<Cost>();
					mageTowerLevelUpCost.add(new Cost(0, 0, 200, 0, 0, 0));
					mageTowerLevelUpCost.add(new Cost(0, 0, 300, 0, 0, 100));
					mageTowerLevelUpCost.add(new Cost(0, 0, 400, 0, 100, 200));
				}
				return mageTowerLevelUpCost.get(entity.getLevel());
			}
		}
		if (entity instanceof ProductionBuilding) {
			if (entity.getName().equals(Building.TOWN_CENTER)) {
				if (townCenterLevelUpCost == null) {
					townCenterLevelUpCost = new ArrayList<Cost>();
					townCenterLevelUpCost.add(new Cost(0, 200, 100, 100, 0, 0));
					townCenterLevelUpCost.add(new Cost(100, 400, 400, 200, 0, 0));
					townCenterLevelUpCost.add(new Cost(200, 800, 800, 400, 200, 0));
				}
				return townCenterLevelUpCost.get(entity.getLevel());
			}
			if (entity.getName().equals(Building.BARRACKS)) {
				if (barracksLevelUpCost == null) {
					barracksLevelUpCost = new ArrayList<Cost>();
					barracksLevelUpCost.add(new Cost(0, 200, 100, 0, 0, 0));
					barracksLevelUpCost.add(new Cost(0, 300, 300, 300, 300, 0));
					barracksLevelUpCost.add(new Cost(100, 200, 400, 400, 400, 100));
				}
				return barracksLevelUpCost.get(entity.getLevel());
			}
			if (entity.getName().equals(Building.SIEGE_WORKSHOP)) {
				if (siegeWorkshopLevelUpCost == null) {
					siegeWorkshopLevelUpCost = new ArrayList<Cost>();
					siegeWorkshopLevelUpCost.add(new Cost(0, 200, 100, 0, 0, 0));
					siegeWorkshopLevelUpCost.add(new Cost(0, 300, 300, 300, 300, 0));
					siegeWorkshopLevelUpCost.add(new Cost(100, 200, 400, 400, 400, 0));
				}
				return siegeWorkshopLevelUpCost.get(entity.getLevel());
			}
		}
		if (entity instanceof ResourceBuilding) {
			if (entity.getName().equals(Building.FOOD_GETTER)) {
				if (foodGetterLevelUpCost == null) {
					foodGetterLevelUpCost = new ArrayList<Cost>();
					foodGetterLevelUpCost.add(new Cost(100, 0, 0, 0, 0, 0));
					foodGetterLevelUpCost.add(new Cost(200, 0, 0, 0, 100, 0));
					foodGetterLevelUpCost.add(new Cost(400, 0, 0, 0, 200, 0));
				}
				return foodGetterLevelUpCost.get(entity.getLevel());
			}
			if (entity.getName().equals(Building.WOOD_GETTER)) {
				if (woodGetterLevelUpCost == null) {
					woodGetterLevelUpCost = new ArrayList<Cost>();
					woodGetterLevelUpCost.add(new Cost(0, 100, 0, 0, 0, 0));
					woodGetterLevelUpCost.add(new Cost(0, 200, 0, 100, 0, 0));
					woodGetterLevelUpCost.add(new Cost(0, 400, 0, 200, 0, 0));
				}
				return woodGetterLevelUpCost.get(entity.getLevel());
			}
			if (entity.getName().equals(Building.STONE_GETTER)) {
				if (stoneGetterLevelUpCost == null) {
					stoneGetterLevelUpCost = new ArrayList<Cost>();
					stoneGetterLevelUpCost.add(new Cost(0, 0, 100, 0, 0, 0));
					stoneGetterLevelUpCost.add(new Cost(0, 0, 200, 100, 0, 0));
					stoneGetterLevelUpCost.add(new Cost(0, 0, 400, 200, 0, 0));
				}
				return stoneGetterLevelUpCost.get(entity.getLevel());
			}
			if (entity.getName().equals(Building.METAL_GETTER)) {
				if (metalGetterLevelUpCost == null) {
					metalGetterLevelUpCost = new ArrayList<Cost>();
					metalGetterLevelUpCost.add(new Cost(0, 0, 100, 100, 0, 0));
					metalGetterLevelUpCost.add(new Cost(0, 0, 200, 200, 0, 0));
					metalGetterLevelUpCost.add(new Cost(0, 0, 300, 400, 0, 0));
				}
				return metalGetterLevelUpCost.get(entity.getLevel());
			}
			if (entity.getName().equals(Building.MANA_GETTER)) {
				if (manaStoneGetterLevelUpCost == null) {
					manaStoneGetterLevelUpCost = new ArrayList<Cost>();
					manaStoneGetterLevelUpCost.add(new Cost(0, 0, 0, 0, 0, 100));
					manaStoneGetterLevelUpCost.add(new Cost(0, 0, 0, 0, 100, 200));
					manaStoneGetterLevelUpCost.add(new Cost(0, 0, 0, 0, 200, 400));
				}
				return manaStoneGetterLevelUpCost.get(entity.getLevel());
			}
			if (entity.getName().equals(Building.GOLD_GETTER)) {
				if (goldGetterLevelUpCost == null) {
					goldGetterLevelUpCost = new ArrayList<Cost>();
					goldGetterLevelUpCost.add(new Cost(0, 0, 0, 0, 100, 0));
					goldGetterLevelUpCost.add(new Cost(0, 0, 0, 100, 200, 0));
					goldGetterLevelUpCost.add(new Cost(0, 0, 0, 200, 400, 0));
				}
				return goldGetterLevelUpCost.get(entity.getLevel());
			}
		}
		return null;
	}
	
}

package entity;

import java.util.ArrayList;

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
	private ArrayList<LevelUpCost> archerLevelUpCost;
	private ArrayList<LevelUpCost> builderLevelUpCost;
	private ArrayList<LevelUpCost> mageLevelUpCost;
	private ArrayList<LevelUpCost> warriorLevelUpCost;
	private ArrayList<LevelUpCost> trebuchetLevelUpCost;
	// Buildings
	// DefenseBuildings
	private ArrayList<LevelUpCost> archerTowerLevelUpCost;
	private ArrayList<LevelUpCost> mageTowerLevelUpCost;
	// ProductionBuildings
	private ArrayList<LevelUpCost> siegeWorkshopLevelUpCost;
	private ArrayList<LevelUpCost> townCenterLevelUpCost;
	private ArrayList<LevelUpCost> barracksLevelUpCost;
	// ResourceBuildings
	private ArrayList<LevelUpCost> foodGetterLevelUpCost;
	private ArrayList<LevelUpCost> manaStoneGetterLevelUpCost;
	private ArrayList<LevelUpCost> stoneGetterLevelUpCost;
	private ArrayList<LevelUpCost> metalGetterLevelUpCost;
	private ArrayList<LevelUpCost> goldGetterLevelUpCost;
	private ArrayList<LevelUpCost> woodGetterLevelUpCost;

	public LevelUpCostManager() {

	}

	//LevelUpCost Constructor: Food, Wood, Stone, Metal, Gold, ManaStone
	
	public LevelUpCost getLevelUpCost(Entity entity) {
		
		if(entity.getLevel() < Entity.MAX_LEVEL == false) {
			return null;
		}
		
		if (entity instanceof Archer) {
			if (archerLevelUpCost == null) {
				archerLevelUpCost = new ArrayList<LevelUpCost>();
				archerLevelUpCost.add(new LevelUpCost(200, 0, 0, 0, 0, 0));
				archerLevelUpCost.add(new LevelUpCost(400, 0, 0, 0, 100, 0));
				archerLevelUpCost.add(new LevelUpCost(800, 0, 0, 0, 200, 0));
			}
			return archerLevelUpCost.get(entity.getLevel() - 1);
		}
		if (entity instanceof Builder) {
			if (builderLevelUpCost == null) {
				builderLevelUpCost = new ArrayList<LevelUpCost>();
				builderLevelUpCost.add(new LevelUpCost(200, 0, 0, 0, 0, 0));
				builderLevelUpCost.add(new LevelUpCost(300, 100, 0, 0, 0, 0));
				builderLevelUpCost.add(new LevelUpCost(400, 200, 200, 0, 0, 0));
			}
			return builderLevelUpCost.get(entity.getLevel() - 1);
		}
		if (entity instanceof Mage) {
			if (mageLevelUpCost == null) {
				mageLevelUpCost = new ArrayList<LevelUpCost>();
				mageLevelUpCost.add(new LevelUpCost(200, 0, 0, 0, 0, 0));
				mageLevelUpCost.add(new LevelUpCost(300, 0, 0, 0, 0, 100));
				mageLevelUpCost.add(new LevelUpCost(400, 0, 0, 0, 0, 300));
			}
			return mageLevelUpCost.get(entity.getLevel() - 1);
		}
		if (entity instanceof Warrior) {
			if (warriorLevelUpCost == null) {
				warriorLevelUpCost = new ArrayList<LevelUpCost>();
				warriorLevelUpCost.add(new LevelUpCost(200, 0, 0, 0, 0, 0));
				warriorLevelUpCost.add(new LevelUpCost(300, 0, 0, 0, 100, 0));
				warriorLevelUpCost.add(new LevelUpCost(400, 0, 0, 0, 200, 0));
			}
			return warriorLevelUpCost.get(entity.getLevel() - 1);
		}
		if (entity instanceof Trebuchet) {
			if (trebuchetLevelUpCost == null) {
				trebuchetLevelUpCost = new ArrayList<LevelUpCost>();
				trebuchetLevelUpCost.add(new LevelUpCost(0, 200, 0, 0, 0, 0));
				trebuchetLevelUpCost.add(new LevelUpCost(0, 300, 0, 100, 0, 0));
				trebuchetLevelUpCost.add(new LevelUpCost(0, 400, 10, 200, 0, 0));
			}
			return trebuchetLevelUpCost.get(entity.getLevel() - 1);
		}
		if (entity instanceof DefenseBuilding) {
			if (entity.getName().equals(Building.ARCHER_TOWER)) {
				if (archerTowerLevelUpCost == null) {
					archerTowerLevelUpCost = new ArrayList<LevelUpCost>();
					archerTowerLevelUpCost.add(new LevelUpCost(0, 200, 0, 0, 0, 0));
					archerTowerLevelUpCost.add(new LevelUpCost(100, 300, 0, 0, 0, 0));
					archerTowerLevelUpCost.add(new LevelUpCost(200, 400, 0, 0, 100, 0));
				}
				return archerTowerLevelUpCost.get(entity.getLevel() - 1);
			}
			if (entity.getName().equals(Building.MAGE_TOWER)) {
				if (mageTowerLevelUpCost == null) {
					mageTowerLevelUpCost = new ArrayList<LevelUpCost>();
					mageTowerLevelUpCost.add(new LevelUpCost(0, 0, 200, 0, 0, 0));
					mageTowerLevelUpCost.add(new LevelUpCost(0, 0, 300, 0, 0, 100));
					mageTowerLevelUpCost.add(new LevelUpCost(0, 0, 400, 0, 100, 200));
				}
				return mageTowerLevelUpCost.get(entity.getLevel() - 1);
			}
		}
		if (entity instanceof ProductionBuilding) {
			if (entity.getName().equals(Building.TOWN_CENTER)) {
				if (townCenterLevelUpCost == null) {
					townCenterLevelUpCost = new ArrayList<LevelUpCost>();
					townCenterLevelUpCost.add(new LevelUpCost(0, 200, 100, 100, 0, 0));
					townCenterLevelUpCost.add(new LevelUpCost(100, 400, 400, 200, 0, 0));
					townCenterLevelUpCost.add(new LevelUpCost(200, 800, 800, 400, 200, 0));
				}
				return townCenterLevelUpCost.get(entity.getLevel() - 1);
			}
			if (entity.getName().equals(Building.BARRACKS)) {
				if (barracksLevelUpCost == null) {
					barracksLevelUpCost = new ArrayList<LevelUpCost>();
					barracksLevelUpCost.add(new LevelUpCost(0, 200, 100, 0, 0, 0));
					barracksLevelUpCost.add(new LevelUpCost(0, 300, 300, 300, 300, 0));
					barracksLevelUpCost.add(new LevelUpCost(100, 200, 400, 400, 400, 100));
				}
				return barracksLevelUpCost.get(entity.getLevel() - 1);
			}
			if (entity.getName().equals(Building.SIEGE_WORKSHOP)) {
				if (siegeWorkshopLevelUpCost == null) {
					siegeWorkshopLevelUpCost = new ArrayList<LevelUpCost>();
					siegeWorkshopLevelUpCost.add(new LevelUpCost(0, 200, 100, 0, 0, 0));
					siegeWorkshopLevelUpCost.add(new LevelUpCost(0, 300, 300, 300, 300, 0));
					siegeWorkshopLevelUpCost.add(new LevelUpCost(100, 200, 400, 400, 400, 0));
				}
				return siegeWorkshopLevelUpCost.get(entity.getLevel() - 1);
			}
		}
		if (entity instanceof ResourceBuilding) {
			if (entity.getName().equals(Building.FOOD_GETTER)) {
				if (foodGetterLevelUpCost == null) {
					foodGetterLevelUpCost = new ArrayList<LevelUpCost>();
					foodGetterLevelUpCost.add(new LevelUpCost(100, 0, 0, 0, 0, 0));
					foodGetterLevelUpCost.add(new LevelUpCost(200, 0, 0, 0, 100, 0));
					foodGetterLevelUpCost.add(new LevelUpCost(400, 0, 0, 0, 200, 0));
				}
				return foodGetterLevelUpCost.get(entity.getLevel() - 1);
			}
			if (entity.getName().equals(Building.WOOD_GETTER)) {
				if (woodGetterLevelUpCost == null) {
					woodGetterLevelUpCost = new ArrayList<LevelUpCost>();
					woodGetterLevelUpCost.add(new LevelUpCost(0, 100, 0, 0, 0, 0));
					woodGetterLevelUpCost.add(new LevelUpCost(0, 200, 0, 100, 0, 0));
					woodGetterLevelUpCost.add(new LevelUpCost(0, 400, 0, 200, 0, 0));
				}
				return woodGetterLevelUpCost.get(entity.getLevel() - 1);
			}
			if (entity.getName().equals(Building.STONE_GETTER)) {
				if (stoneGetterLevelUpCost == null) {
					stoneGetterLevelUpCost = new ArrayList<LevelUpCost>();
					stoneGetterLevelUpCost.add(new LevelUpCost(0, 0, 100, 0, 0, 0));
					stoneGetterLevelUpCost.add(new LevelUpCost(0, 0, 200, 100, 0, 0));
					stoneGetterLevelUpCost.add(new LevelUpCost(0, 0, 400, 200, 0, 0));
				}
				return stoneGetterLevelUpCost.get(entity.getLevel() - 1);
			}
			if (entity.getName().equals(Building.METAL_GETTER)) {
				if (metalGetterLevelUpCost == null) {
					metalGetterLevelUpCost = new ArrayList<LevelUpCost>();
					metalGetterLevelUpCost.add(new LevelUpCost(0, 0, 100, 100, 0, 0));
					metalGetterLevelUpCost.add(new LevelUpCost(0, 0, 200, 200, 0, 0));
					metalGetterLevelUpCost.add(new LevelUpCost(0, 0, 300, 400, 0, 0));
				}
				return metalGetterLevelUpCost.get(entity.getLevel() - 1);
			}
			if (entity.getName().equals(Building.MANA_GETTER)) {
				if (manaStoneGetterLevelUpCost == null) {
					manaStoneGetterLevelUpCost = new ArrayList<LevelUpCost>();
					manaStoneGetterLevelUpCost.add(new LevelUpCost(0, 0, 0, 0, 0, 100));
					manaStoneGetterLevelUpCost.add(new LevelUpCost(0, 0, 0, 0, 100, 200));
					manaStoneGetterLevelUpCost.add(new LevelUpCost(0, 0, 0, 0, 200, 400));
				}
				return manaStoneGetterLevelUpCost.get(entity.getLevel() - 1);
			}
			if (entity.getName().equals(Building.GOLD_GETTER)) {
				if (goldGetterLevelUpCost == null) {
					goldGetterLevelUpCost = new ArrayList<LevelUpCost>();
					goldGetterLevelUpCost.add(new LevelUpCost(0, 0, 0, 0, 100, 0));
					goldGetterLevelUpCost.add(new LevelUpCost(0, 0, 0, 100, 200, 0));
					goldGetterLevelUpCost.add(new LevelUpCost(0, 0, 0, 200, 400, 0));
				}
				return goldGetterLevelUpCost.get(entity.getLevel() - 1);
			}
		}
		return null;
	}
	
}

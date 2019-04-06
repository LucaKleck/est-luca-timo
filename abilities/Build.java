package abilities;

import java.util.ArrayList;

import core.Event;
import core.GameInfo;
import core.Point2DNoFxReq;
import core.PlayerStats.PlayerResources;
import cost.BuildingCostManager;
import cost.Cost;
import cost.LevelUpCostManager;
import cost.ProductionCostManager;
import entity.Entity;
import entity.building.Building;
import entity.building.DefenseBuilding;
import entity.building.ProductionBuilding;
import entity.building.ResourceBuilding;
import entity.unit.Builder;

public class Build extends Ability {

	private String buildingType;
	private Builder builder;
	private static final int DEFAULT_MAX_RANGE = 2;
	private boolean controlable;

	private BuildingCostManager buildingCostManager;
	private LevelUpCostManager levelUpCostManager;
	private ProductionCostManager productionCostManager;
	private PlayerResources playerResources;
	
	public Build(String buildingType, String name, String description, Builder builder, boolean controlable) {
		super(name, description, Ability.ABILITY_TYPE_BUILD);
		this.buildingType = buildingType;
		this.builder = builder;
		this.controlable = controlable;
		super.maxRange = DEFAULT_MAX_RANGE;
		buildingCostManager = new BuildingCostManager();
		levelUpCostManager = new LevelUpCostManager();
		productionCostManager = new ProductionCostManager();
		playerResources = GameInfo.getPlayerStats().getPlayerResources();
	}

	public String getBuildingType() {
		return buildingType;
	}

	public boolean positionIsBuildable(int xPos, int yPos) {
		for (Entity entity : GameInfo.getObjectMap().getEntityMap()) {
			if (entity instanceof Building) {
				if (entity.getXPos() == xPos && entity.getYPos() == yPos) {
					return false;
				}
			}
		}
		for (Event event : GameInfo.getRoundInfo().getEventList()) {

			if (event.getAbility() instanceof Build) {

				Builder builder = (Builder) event.getSource();

				if ((int) builder.getBuildPoint().x == xPos && (int) builder.getBuildPoint().y == yPos) {
					return false;
				}
			}
		}
		return true;
	}

	@Override
	public void applyAbility(Entity source, Entity target) {
		Point2DNoFxReq buildPoint = builder.getBuildPoint();
		Building building = null;

		// Production Building
		if (buildingType.equals(Building.BARRACKS)) {
			building = new ProductionBuilding(buildPoint, Building.BARRACKS, 10, 10, 0, controlable, new ArrayList<>());
		}
		if (buildingType.equals(Building.PORTAL)) {
			building = new ProductionBuilding(buildPoint, Building.BARRACKS, 10, 10, 0, controlable, new ArrayList<>());
		}
		if (buildingType.equals(Building.SIEGE_WORKSHOP)) {
			building = new ProductionBuilding(buildPoint, Building.SIEGE_WORKSHOP, 10, 10, 0, controlable,
					new ArrayList<>());
		}
		// Resource Building
		if (buildingType.equals(Building.WOOD_GETTER)) {
			building = new ResourceBuilding(buildPoint, Building.WOOD_GETTER, 10, 10, 0, controlable,
					new ArrayList<>());
		}
		if (buildingType.equals(Building.STONE_GETTER)) {
			building = new ResourceBuilding(buildPoint, Building.STONE_GETTER, 10, 10, 0, controlable,
					new ArrayList<>());
		}
		if (buildingType.equals(Building.FOOD_GETTER)) {
			building = new ResourceBuilding(buildPoint, Building.FOOD_GETTER, 10, 10, 0, controlable,
					new ArrayList<>());
		}
		if (buildingType.equals(Building.GOLD_GETTER)) {
			building = new ResourceBuilding(buildPoint, Building.GOLD_GETTER, 10, 10, 0, controlable,
					new ArrayList<>());
		}
		if (buildingType.equals(Building.METAL_GETTER)) {
			building = new ResourceBuilding(buildPoint, Building.METAL_GETTER, 10, 10, 0, controlable,
					new ArrayList<>());
		}
		if (buildingType.equals(Building.MANA_GETTER)) {
			building = new ResourceBuilding(buildPoint, Building.MANA_GETTER, 10, 10, 0, controlable,
					new ArrayList<>());
		}
		// Defense Building
		if (buildingType.equals(Building.MAGE_TOWER)) {
			building = new DefenseBuilding(buildPoint, Building.MAGE_TOWER, 7, 7, 0, controlable, new ArrayList<>());
		}
		if (buildingType.equals(Building.ARCHER_TOWER)) {
			building = new DefenseBuilding(buildPoint, Building.ARCHER_TOWER, 10, 10, 0, controlable,
					new ArrayList<>());
		}
		GameInfo.getRoundInfo().getNewBuildings().add(building);
		GameInfo.getObjectMap().getEntityMap().add(building);
	}
	
	public boolean buildingCanBeBuild() {
		
		Cost buildingCost = buildingCostManager.getBuildingCost(this.getBuildingType());
		
		int availableFood = playerResources.getFood();
		int availableWood =  playerResources.getWood();
		int availableStone = playerResources.getStone();
		int availableMetal = playerResources.getMetal();
		int availableGold = playerResources.getGold();
		int availableManaStone = playerResources.getManaStone();
		
		for(Event event: GameInfo.getRoundInfo().getEventList()) {
			if(event.getAbility() instanceof LevelUp) {
				Cost entityLevelUpCost;
				entityLevelUpCost = levelUpCostManager.getLevelUpCost(event.getTarget());
				availableFood -= entityLevelUpCost.getFoodCost();
				availableWood -= entityLevelUpCost.getWoodCost();
				availableStone -= entityLevelUpCost.getStoneCost();
				availableMetal -= entityLevelUpCost.getMetalCost();
				availableGold -= entityLevelUpCost.getGoldCost();
				availableManaStone -= entityLevelUpCost.getManaStoneCost();
			}
			if(event.getAbility() instanceof Build) {
				Cost entityBuildingCost;
				entityBuildingCost = buildingCostManager.getBuildingCost(((Build)event.getAbility()).getBuildingType());
				availableFood -= entityBuildingCost.getFoodCost();
				availableWood -= entityBuildingCost.getWoodCost();
				availableStone -= entityBuildingCost.getStoneCost();
				availableMetal -= entityBuildingCost.getMetalCost();
				availableGold -= entityBuildingCost.getGoldCost();
				availableManaStone -= entityBuildingCost.getManaStoneCost();
			}
			if(event.getAbility() instanceof CreateUnit) {
				Cost productionCost;
				productionCost = productionCostManager.getProductionCost(((CreateUnit)event.getAbility()).getType());
				availableFood -= productionCost.getFoodCost();
				availableWood -= productionCost.getWoodCost();
				availableStone -= productionCost.getStoneCost();
				availableMetal -= productionCost.getMetalCost();
				availableGold -= productionCost.getGoldCost();
				availableManaStone -= productionCost.getManaStoneCost();
			}
		}
		
		if (buildingCost.getFoodCost() > availableFood) {
			return false;
		}
		if (buildingCost.getWoodCost() > availableWood) {
			return false;
		}
		if (buildingCost.getStoneCost() > availableStone) {
			return false;
		}
		if (buildingCost.getMetalCost() > availableMetal) {
			return false;
		}
		if (buildingCost.getGoldCost() > availableGold) {
			return false;
		}
		if (buildingCost.getManaStoneCost() > availableManaStone) {
			return false;
		}
		return true;
	}

}
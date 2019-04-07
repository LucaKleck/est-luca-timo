package abilities;

import java.util.ArrayList;

import core.Event;
import core.GameInfo;
import core.PlayerStats;
import core.PlayerStats.PlayerResources;
import core.Point2DNoFxReq;
import cost.AvailableResources;
import cost.Cost;
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

	private PlayerResources playerResources;
	private PlayerStats playerStats;
	
	public Build(String buildingType, String name, String description, Builder builder, boolean controlable) {
		super(name, description, Ability.ABILITY_TYPE_BUILD);
		this.buildingType = buildingType;
		this.builder = builder;
		this.controlable = controlable;
		super.maxRange = DEFAULT_MAX_RANGE;
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

		if(source.isControlable()) {
			if(playerResources == null) {
				playerResources = GameInfo.getPlayerStats().getPlayerResources();
			}
			
			Cost buildingCost = GameInfo.getPlayerStats().getCostManager().getBuildingCostManager().getBuildingCost(buildingType);
			
			playerResources.reduceFoodBy(buildingCost.getFoodCost());
			playerResources.reduceWoodBy(buildingCost.getWoodCost());
			playerResources.reduceStoneBy(buildingCost.getStoneCost());
			playerResources.reduceManaStoneBy(buildingCost.getManaStoneCost());
			playerResources.reduceGoldBy(buildingCost.getGoldCost());
			playerResources.reduceMetalBy(buildingCost.getMetalCost());
		}
		
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
		
		if(playerResources == null) {
			playerResources = GameInfo.getPlayerStats().getPlayerResources();
		}
		if(playerStats == null) {
			playerStats = GameInfo.getPlayerStats();
		}
		
		Cost levelUpCost = playerStats.getCostManager().getBuildingCostManager().getBuildingCost(buildingType);
		
		AvailableResources availableResources = playerStats.getCostManager().getAvailableResources();
		
		int availableFood = availableResources.getAvailableFood();
		int availableWood =  availableResources.getAvailableWood();
		int availableStone = availableResources.getAvailableStone();
		int availableMetal = availableResources.getAvailableMetal();
		int availableGold = availableResources.getAvailableGold();
		int availableManaStone = availableResources.getAvailableManaStone();
		
		if (levelUpCost.getFoodCost() > availableFood) {
			return false;
		}
		if (levelUpCost.getWoodCost() > availableWood) {
			return false;
		}
		if (levelUpCost.getStoneCost() > availableStone) {
			return false;
		}
		if (levelUpCost.getMetalCost() > availableMetal) {
			return false;
		}
		if (levelUpCost.getGoldCost() > availableGold) {
			return false;
		}
		if (levelUpCost.getManaStoneCost() > availableManaStone) {
			return false;
		}
		return true;
	}

}
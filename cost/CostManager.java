package cost;

import core.GameInfo;
import core.PlayerStats;
import core.PlayerStats.PlayerResources;
import events.Event;
import events.abilities.Build;
import events.abilities.CreateUnit;
import events.abilities.LevelUp;

public class CostManager {

	private ProductionCostManager productionCostManager;
	private LevelUpCostManager levelUpCostManager;
	private BuildingCostManager buildingCostManager;
	
	public CostManager(ProductionCostManager productionCostManager, LevelUpCostManager levelUpCostManager, BuildingCostManager buildingCostManager) {
		this.productionCostManager = productionCostManager;
		this.levelUpCostManager = levelUpCostManager;
		this.buildingCostManager = buildingCostManager;
	}
	
	public CostManager() {
		this.productionCostManager = new ProductionCostManager();
		this.levelUpCostManager = new LevelUpCostManager();
		this.buildingCostManager = new BuildingCostManager();
	}

	public ProductionCostManager getProductionCostManager() {
		return productionCostManager;
	}

	public LevelUpCostManager getLevelUpCostManager() {
		return levelUpCostManager;
	}

	public BuildingCostManager getBuildingCostManager() {
		return buildingCostManager;
	}
	
	public AvailableResources getAvailableResources() {
		
		AvailableResources availableResources = new AvailableResources();
		PlayerStats playerStats = GameInfo.getPlayerStats();
		PlayerResources playerResources = playerStats.getPlayerResources();
		
		
		availableResources.setAvailableFood(playerResources.getFood());
		availableResources.setAvailableWood(playerResources.getWood());
		availableResources.setAvailableStone(playerResources.getStone());
		availableResources.setAvailableMetal(playerResources.getMetal());
		availableResources.setAvailableGold(playerResources.getGold());
		availableResources.setAvailableManaStone(playerResources.getManaStone());
		
		for(Event event: GameInfo.getRoundInfo().getEventHandler().getEventList()) {
			if(event.getAbility() instanceof LevelUp) {
				Cost entityLevelUpCost;
				entityLevelUpCost = playerStats.getCostManager().getLevelUpCostManager().getLevelUpCost(event.getTarget());
				
				availableResources.reduceAvailableFoodBy(entityLevelUpCost.getFoodCost());
				availableResources.reduceAvailableWoodBy(entityLevelUpCost.getWoodCost());
				availableResources.reduceAvailableStoneBy(entityLevelUpCost.getStoneCost());
				availableResources.reduceAvailableMetalBy(entityLevelUpCost.getMetalCost());
				availableResources.reduceAvailableGoldBy(entityLevelUpCost.getGoldCost());
				availableResources.reduceAvailableManaStoneBy(entityLevelUpCost.getManaStoneCost());
			}
			if(event.getAbility() instanceof Build) {
				Cost entityBuildingCost;
				entityBuildingCost = playerStats.getCostManager().getBuildingCostManager().getBuildingCost(((Build)event.getAbility()).getBuildingType());
				
				availableResources.reduceAvailableFoodBy(entityBuildingCost.getFoodCost());
				availableResources.reduceAvailableWoodBy(entityBuildingCost.getWoodCost());
				availableResources.reduceAvailableStoneBy(entityBuildingCost.getStoneCost());
				availableResources.reduceAvailableMetalBy(entityBuildingCost.getMetalCost());
				availableResources.reduceAvailableGoldBy(entityBuildingCost.getGoldCost());
				availableResources.reduceAvailableManaStoneBy(entityBuildingCost.getManaStoneCost());
				
			}
			if(event.getAbility() instanceof CreateUnit) {
				Cost productionCost;
				productionCost = playerStats.getCostManager().getProductionCostManager().getProductionCost(((CreateUnit)event.getAbility()).getUnitType());
				
				availableResources.reduceAvailableFoodBy(productionCost.getFoodCost());
				availableResources.reduceAvailableWoodBy(productionCost.getWoodCost());
				availableResources.reduceAvailableStoneBy(productionCost.getStoneCost());
				availableResources.reduceAvailableMetalBy(productionCost.getMetalCost());
				availableResources.reduceAvailableGoldBy(productionCost.getGoldCost());
				availableResources.reduceAvailableManaStoneBy(productionCost.getManaStoneCost());

			}
		}
		
		return availableResources;
		
	}
	
}

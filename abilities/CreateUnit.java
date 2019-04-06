package abilities;

import java.util.ArrayList;
import java.util.Random;

import core.Event;
import core.GameInfo;
import core.PlayerStats.PlayerResources;
import core.Point2DNoFxReq;
import cost.BuildingCostManager;
import cost.Cost;
import cost.LevelUpCostManager;
import cost.ProductionCostManager;
import entity.Entity;
import entity.unit.Archer;
import entity.unit.Builder;
import entity.unit.Mage;
import entity.unit.Trebuchet;
import entity.unit.Unit;
import entity.unit.Warrior;

public class CreateUnit extends Ability {

	private Point2DNoFxReq point;
	private String unitType;
	private Random random;
	private boolean controlable;
	
	private BuildingCostManager buildingCostManager;
	private ProductionCostManager productionCostManager;
	private LevelUpCostManager levelUpCostManager;
	private PlayerResources playerResources;

	public CreateUnit(Point2DNoFxReq point, String unitType, String name, String description, boolean controlable) {
		super(name, description, Ability.ABILITY_TYPE_PRODUCE);
		super.maxRange = 0;
		this.random = new Random();
		this.controlable = controlable;
		this.unitType = unitType;
		this.point = point;
		buildingCostManager = new BuildingCostManager();
		levelUpCostManager = new LevelUpCostManager();
		productionCostManager = new ProductionCostManager();
	}

	@Override
	public void applyAbility(Entity source, Entity target) {
		
		if(playerResources == null) {
			playerResources = GameInfo.getPlayerStats().getPlayerResources();
		}
		
		Cost productionCost = productionCostManager.getProductionCost(unitType);
		
		playerResources.reduceFoodBy(productionCost.getFoodCost());
		playerResources.reduceWoodBy(productionCost.getWoodCost());
		playerResources.reduceStoneBy(productionCost.getStoneCost());
		playerResources.reduceManaStoneBy(productionCost.getManaStoneCost());
		playerResources.reduceGoldBy(productionCost.getGoldCost());
		playerResources.reduceMetalBy(productionCost.getMetalCost());
		
		Point2DNoFxReq pointXY = new Point2DNoFxReq(point.x + random.nextFloat(), point.y + random.nextFloat());
		
		if(unitType.equals(Unit.UNIT_BUILDER)) {
			GameInfo.getObjectMap().getEntityMap().add(new Builder(pointXY, "Builder", 3, 0, controlable, new ArrayList<>()));
		}
		if(unitType.equals(Unit.UNIT_WARRIOR)) {
			GameInfo.getObjectMap().getEntityMap().add(new Warrior(pointXY, "Warrior", 3, 0, controlable, new ArrayList<>()));
		}
		if(unitType.equals(Unit.UNIT_MAGE)) {
			GameInfo.getObjectMap().getEntityMap().add(new Mage(pointXY, "Mage", 3, 0, controlable, new ArrayList<>()));
		}
		if(unitType.equals(Unit.UNIT_ARCHER)) {
			GameInfo.getObjectMap().getEntityMap().add(new Archer(pointXY, "Archer", 3, 0, controlable, new ArrayList<>()));
		}
		if(unitType.equals(Unit.UNIT_TREBUCHET)) {
			GameInfo.getObjectMap().getEntityMap().add(new Trebuchet(pointXY, "Trebuchet", 5, 0, controlable, new ArrayList<>()));
		}
	}
	
	public boolean unitCanBeCreated() {
		PlayerResources playerResources = GameInfo.getPlayerStats().getPlayerResources();
		
		Cost creationCost = productionCostManager.getProductionCost(unitType);
		
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
		
		if (creationCost.getFoodCost() > availableFood) {
			return false;
		}
		if (creationCost.getWoodCost() > availableWood) {
			return false;
		}
		if (creationCost.getStoneCost() > availableStone) {
			return false;
		}
		if (creationCost.getMetalCost() > availableMetal) {
			return false;
		}
		if (creationCost.getGoldCost() > availableGold) {
			return false;
		}
		if (creationCost.getManaStoneCost() > availableManaStone) {
			return false;
		}
		return true;
	}

}

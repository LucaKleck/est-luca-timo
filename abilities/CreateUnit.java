package abilities;

import java.util.ArrayList;
import java.util.Random;

import core.GameInfo;
import core.PlayerStats;
import core.PlayerStats.PlayerResources;
import core.Point2DNoFxReq;
import cost.AvailableResources;
import cost.Cost;
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

	private PlayerResources playerResources;
	private PlayerStats playerStats;
	
	
	public CreateUnit(Point2DNoFxReq point, String unitType, String name, String description, boolean controlable) {
		super(name, description, Ability.ABILITY_TYPE_PRODUCE);
		super.maxRange = 0;
		this.random = new Random();
		this.controlable = controlable;
		this.unitType = unitType;
		this.point = point;
	}

	@Override
	public void applyAbility(Entity source, Entity target) {
		
		if(source.isControlable()) {
			if(playerResources == null) {
				playerResources = GameInfo.getPlayerStats().getPlayerResources();
			}
			
			Cost productionCost = GameInfo.getPlayerStats().getCostManager().getProductionCostManager().getProductionCost(unitType);
			
			playerResources.reduceFoodBy(productionCost.getFoodCost());
			playerResources.reduceWoodBy(productionCost.getWoodCost());
			playerResources.reduceStoneBy(productionCost.getStoneCost());
			playerResources.reduceManaStoneBy(productionCost.getManaStoneCost());
			playerResources.reduceGoldBy(productionCost.getGoldCost());
			playerResources.reduceMetalBy(productionCost.getMetalCost());
		}
		
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
		
		if(playerResources == null) {
			playerResources = GameInfo.getPlayerStats().getPlayerResources();
		}
		if(playerStats == null) {
			playerStats = GameInfo.getPlayerStats();
		}
		
		Cost levelUpCost = playerStats.getCostManager().getProductionCostManager().getProductionCost(unitType);
		
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

	public String getUnitType() {
		return unitType;
	}
	
}

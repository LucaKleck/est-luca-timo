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
import entity.unit.BatteringRam;
import entity.unit.Builder;
import entity.unit.Cavalry;
import entity.unit.CavalryArcher;
import entity.unit.Dragon;
import entity.unit.Hero;
import entity.unit.Knight;
import entity.unit.Mage;
import entity.unit.Mangonel;
import entity.unit.Priest;
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
		
		if(source.isControllable()) {
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
			GameInfo.getObjectMap().getEntityMap().add(new Builder(pointXY, "Builder", 0, controlable, new ArrayList<>(), false));
		}
		if(unitType.equals(Unit.UNIT_WARRIOR)) {
			GameInfo.getObjectMap().getEntityMap().add(new Warrior(pointXY, "Warrior", 0, controlable, new ArrayList<>(), false));
		}
		if(unitType.equals(Unit.UNIT_MAGE)) {
			GameInfo.getObjectMap().getEntityMap().add(new Mage(pointXY, "Mage", 0, controlable, new ArrayList<>(), false));
		}
		if(unitType.equals(Unit.UNIT_ARCHER)) {
			GameInfo.getObjectMap().getEntityMap().add(new Archer(pointXY, "Archer", 0, controlable, new ArrayList<>(), false));
		}
		if(unitType.equals(Unit.UNIT_TREBUCHET)) {
			GameInfo.getObjectMap().getEntityMap().add(new Trebuchet(pointXY, "Trebuchet", 0, controlable, new ArrayList<>(), false));
		}
		if(unitType.equals(Unit.UNIT_BATTERING_RAM)) {
			GameInfo.getObjectMap().getEntityMap().add(new BatteringRam(pointXY, "Battering Ram", 0, controlable, new ArrayList<>(), false));
		}
		if(unitType.equals(Unit.UNIT_CAVALRY)) {
			GameInfo.getObjectMap().getEntityMap().add(new Cavalry(pointXY, "Cavalry", 0, controlable, new ArrayList<>(), false));
		}
		if(unitType.equals(Unit.UNIT_CAVALRY_ARCHER)) {
			GameInfo.getObjectMap().getEntityMap().add(new CavalryArcher(pointXY, "Cavalry Archer", 0, controlable, new ArrayList<>(), false));
		}
		if(unitType.equals(Unit.UNIT_DRAGON)) {
			GameInfo.getObjectMap().getEntityMap().add(new Dragon(pointXY, "Dragon", 0, controlable, new ArrayList<>(), false));
		}
		if(unitType.equals(Unit.UNIT_HERO)) {
			GameInfo.getObjectMap().getEntityMap().add(new Hero(pointXY, "Hero", 0, controlable, new ArrayList<>(), false));
		}
		if(unitType.equals(Unit.UNIT_KNIGHT)) {
			GameInfo.getObjectMap().getEntityMap().add(new Knight(pointXY, "Knight", 0, controlable, new ArrayList<>(), false));
		}
		if(unitType.equals(Unit.UNIT_MANGONEL)) {
			GameInfo.getObjectMap().getEntityMap().add(new Mangonel(pointXY, "Mangonel", 0, controlable, new ArrayList<>(), false));
		}
		if(unitType.equals(Unit.UNIT_PRIEST)) {
			GameInfo.getObjectMap().getEntityMap().add(new Priest(pointXY, "Priest", 0, controlable, new ArrayList<>(), false));
		}
		
		GameInfo.getPlayerStats().addUnitsCreated(1);
		
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

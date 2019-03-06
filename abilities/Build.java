package abilities;

import java.util.ArrayList;

import com.sun.javafx.geom.Point2D;

import core.GameInfo;
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
	
	public Build(String buildingType, String name, String description, Builder builder, boolean controlable) {
		super(name, description, Ability.ABILITY_TYPE_BUILD);
		this.buildingType = buildingType;
		this.builder = builder;
		this.controlable = controlable;
		super.maxRange = DEFAULT_MAX_RANGE;
	}

	@Override
	public void applyAbility(Entity source, Entity target) {
		Point2D buildPoint = builder.getBuildPoint();
		Building building = null;
		//Production Building
		if(buildingType.equals(Building.BARRACKS)) {
			building = new ProductionBuilding(buildPoint, Building.BARRACKS, 10, 10, 1, controlable, new ArrayList<>());
		}
		if(buildingType.equals(Building.PORTAL)) {
			building = new ProductionBuilding(buildPoint, Building.BARRACKS, 10, 10, 1, controlable, new ArrayList<>());
		}
		if(buildingType.equals(Building.TOWN_CENTER)) {
			building = new ProductionBuilding(buildPoint, Building.TOWN_CENTER, 15, 15, 1, controlable, new ArrayList<>());		
		}
		if(buildingType.equals(Building.SIEGE_WORKSHOP)) {
			building = new ProductionBuilding(buildPoint, Building.SIEGE_WORKSHOP, 10, 10, 1, controlable, new ArrayList<>());		
		}
		//Resource Building
		if(buildingType.equals(Building.WOOD_GETTER)) {
			building = new ResourceBuilding(buildPoint, Building.WOOD_GETTER, 10, 10, 1, controlable, new ArrayList<>());		
		}
		if(buildingType.equals(Building.STONE_GETTER)) {
			building = new ResourceBuilding(buildPoint, Building.STONE_GETTER, 10, 10, 1, controlable, new ArrayList<>());		
		}
		if(buildingType.equals(Building.FOOD_GETTER)) {
			building = new ResourceBuilding(buildPoint, Building.FOOD_GETTER, 10, 10, 1, controlable, new ArrayList<>());		
		}
		if(buildingType.equals(Building.GOLD_GETTER)) {
			building = new ResourceBuilding(buildPoint, Building.GOLD_GETTER, 10, 10, 1, controlable, new ArrayList<>());		
		}
		if(buildingType.equals(Building.METAL_GETTER)) {
			building = new ResourceBuilding(buildPoint, Building.METAL_GETTER, 10, 10, 1, controlable, new ArrayList<>());		
		}
		if(buildingType.equals(Building.MANA_GETTER)) {
			building = new ResourceBuilding(buildPoint, Building.MANA_GETTER, 10, 10, 1, controlable, new ArrayList<>());		
		}
		//Defense Building
		if(buildingType.equals(Building.WALL)) {
			building = new DefenseBuilding(buildPoint, Building.WALL, 20, 20, 1, controlable, new ArrayList<>());		
		}
		if(buildingType.equals(Building.ARCHER_TOWER)) {
			building = new DefenseBuilding(buildPoint, Building.ARCHER_TOWER, 10, 10, 1, controlable, new ArrayList<>());		
		}
		GameInfo.getRoundInfo().getNewBuildings().add(building);
		GameInfo.getObjectMap().getEntityMap().add(building);
	}

}


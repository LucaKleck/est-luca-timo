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
	
	public Build(String buildingType, String name, String description, Builder builder) {
		super(name, description, Ability.ABILITY_TYPE_BUILD);
		this.buildingType = buildingType;
		this.builder = builder;
		super.maxRange = DEFAULT_MAX_RANGE;
	}

	@Override
	public void applyAbility(Entity source, Entity target) {
		Point2D buildPoint = builder.getBuildPoint();
		//Production Building
		if(buildingType.equals(Building.BARRACKS)) {
			GameInfo.getObjectMap().getEntityMap().add(new ProductionBuilding(buildPoint, Building.BARRACKS, 10, 10, 1, true, new ArrayList<>()));		
		}
		if(buildingType.equals(Building.TOWN_CENTER)) {
			GameInfo.getObjectMap().getEntityMap().add(new ProductionBuilding(buildPoint, Building.TOWN_CENTER, 15, 15, 1, true, new ArrayList<>()));		
		}
		//Resource Building
		if(buildingType.equals(Building.WOOD_GETTER)) {
			GameInfo.getObjectMap().getEntityMap().add(new ResourceBuilding(buildPoint, Building.WOOD_GETTER, 10, 10, 1, true, new ArrayList<>()));		
		}
		if(buildingType.equals(Building.STONE_GETTER)) {
			GameInfo.getObjectMap().getEntityMap().add(new ResourceBuilding(buildPoint, Building.STONE_GETTER, 10, 10, 1, true, new ArrayList<>()));		
		}
		if(buildingType.equals(Building.FOOD_GETTER)) {
			GameInfo.getObjectMap().getEntityMap().add(new ResourceBuilding(buildPoint, Building.FOOD_GETTER, 10, 10, 1, true, new ArrayList<>()));		
		}
		if(buildingType.equals(Building.GOLD_GETTER)) {
			GameInfo.getObjectMap().getEntityMap().add(new ResourceBuilding(buildPoint, Building.GOLD_GETTER, 10, 10, 1, true, new ArrayList<>()));		
		}
		if(buildingType.equals(Building.METAL_GETTER)) {
			GameInfo.getObjectMap().getEntityMap().add(new ResourceBuilding(buildPoint, Building.METAL_GETTER, 10, 10, 1, true, new ArrayList<>()));		
		}
		if(buildingType.equals(Building.MANA_GETTER)) {
			GameInfo.getObjectMap().getEntityMap().add(new ResourceBuilding(buildPoint, Building.MANA_GETTER, 10, 10, 1, true, new ArrayList<>()));		
		}
		//Defense Building
		if(buildingType.equals(Building.WALL)) {
			GameInfo.getObjectMap().getEntityMap().add(new DefenseBuilding(buildPoint, Building.WALL, 20, 20, 1, true, new ArrayList<>()));		
		}
		if(buildingType.equals(Building.ARCHER_TOWER)) {
			GameInfo.getObjectMap().getEntityMap().add(new DefenseBuilding(buildPoint, Building.ARCHER_TOWER, 10, 10, 1, true, new ArrayList<>()));		
		}
	}

}


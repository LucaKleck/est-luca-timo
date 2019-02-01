package abilities;

import java.util.ArrayList;

import com.sun.javafx.geom.Point2D;

import core.GameInfo;
import entity.Entity;
import entity.building.Building;
import entity.building.ProductionBuilding;
import entity.building.RessourceBuilding;
import entity.unit.Builder;

public class Build extends Ability {
	
	private String type;
	private Builder builder;
	private static final int DEFAULT_MAX_RANGE = 2;
	
	public Build(String type, String name, String description, Builder builder) {
		super(name, description);
		this.type = type;
		this.builder = builder;
		super.maxRange = DEFAULT_MAX_RANGE;
	}

	@Override
	public void applyAbility(Entity source, Entity target) {
		Point2D buildPoint = builder.getBuildPoint();
		if(type.matches(Building.BARRACKS)) {
			GameInfo.getObjectMap().getEntityMap().add(new ProductionBuilding(buildPoint, Building.BARRACKS, 10, 10, 1, true, new ArrayList<>()));		
		}
		if(type.matches(Building.TOWN_CENTER)) {
			GameInfo.getObjectMap().getEntityMap().add(new ProductionBuilding(buildPoint, Building.TOWN_CENTER, 15, 15, 1, true, new ArrayList<>()));		
		}
		if(type.matches(Building.WOOD_GETTER)) {
			GameInfo.getObjectMap().getEntityMap().add(new RessourceBuilding(buildPoint, Building.WOOD_GETTER, 10, 10, 1, true, new ArrayList<>()));		
		}
		if(type.matches(Building.STONE_GETTER)) {
			GameInfo.getObjectMap().getEntityMap().add(new RessourceBuilding(buildPoint, Building.STONE_GETTER, 10, 10, 1, true, new ArrayList<>()));		
		}
	}

}


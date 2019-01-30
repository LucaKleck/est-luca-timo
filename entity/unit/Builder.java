package entity.unit;

import java.util.ArrayList;

import com.sun.javafx.geom.Point2D;

import abilities.Ability;
import abilities.Build;
import entity.building.Building;

public class Builder extends Unit {

	private static final int BASE_MAX_HEALTH = 3;
	private static final int BASE_DAMAGE = 2;
	private static final int MOVEMENT_RANGE = 2;

	private Point2D buildPoint;
	
	public Builder(Point2D pointXY, String name, int currentHealth, int level, boolean controlable, ArrayList<Ability> abilities) {
		super(pointXY, name, BASE_MAX_HEALTH, currentHealth, level, controlable, BASE_DAMAGE, MOVEMENT_RANGE, abilities);
		abilities.add(new Build(Building.BARRACKS, Ability.ABILITY_BUILD_BARRACKS, Ability.ABILITY_DESC_BUILD_BARRACKS, this));
		abilities.add(new Build(Building.TOWN_CENTER, Ability.ABILITY_BUILD_TOWN_CENTER, Ability.ABILITY_DESC_BUILD_TOWN_CENTER, this));
		abilities.add(new Build(Building.WOOD_GETTER, Ability.ABILITY_BUILD_LUMBERJACK, Ability.ABILITY_DESC_BUILD_LUMBERJACK, this));
	}
	
	public void setBuildPoint(Point2D buildPoint) {
		this.buildPoint = buildPoint;
	}

	public Point2D getBuildPoint() {
		return buildPoint;
	}
	
}

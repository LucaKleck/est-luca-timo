package abilities;

import java.util.ArrayList;

import com.sun.javafx.geom.Point2D;

import core.GameInfo;
import entity.Entity;
import entity.building.Building;

public class Build extends Ability {
	
	private Point2D buildPoint = new Point2D();
	
	public Build() {
		super(Ability.ABILITY_BUILD, Ability.ABILITY_DESC_BUILD);
	}

	@Override
	public void applyAbility(Entity source, Entity target) {
		int level = 1;
		int hp = 10;
		GameInfo.getObjectMap().getEntityMap().add(new Building(buildPoint, Building.WOOD_GETTER, hp, hp, level, true, new ArrayList<>()));
	}
	
	public void setBuildPoint(Point2D moveToPoint) {
		this.buildPoint.setLocation(moveToPoint);
	}
	
	public Point2D getBuildPoint() {
		return buildPoint;
	}

}


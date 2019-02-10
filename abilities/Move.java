package abilities;

import com.sun.javafx.geom.Point2D;

import entity.Entity;

public class Move extends Ability {
	
	private Point2D moveToPoint = new Point2D();
	private static final int DEFAULT_MAX_RANGE = 2;
	
	public Move() {
		super(Ability.ABILITY_MOVE, Ability.ABILITY_DESC_MOVE, Ability.ABILITY_TYPE_MOVEMENT);
		super.maxRange = DEFAULT_MAX_RANGE;
	}
	
	public Move(int maxRange) {
		super(Ability.ABILITY_MOVE, Ability.ABILITY_DESC_MOVE, Ability.ABILITY_TYPE_MOVEMENT);
		super.maxRange = maxRange;
	}

	@Override
	public void applyAbility(Entity source, Entity target) {
		if(source.equals(target)) {
			source.setPoint(moveToPoint);
		}
	}
	
	public void setMoveToPoint(Point2D moveToPoint) {
		this.moveToPoint.setLocation(moveToPoint);
	}
	
	public Point2D getMoveToPoint() {
		return moveToPoint;
	}
	
	@Override
	public String toString() {
		return "Move [moveToPoint=" + moveToPoint + "]";
	}

	
}


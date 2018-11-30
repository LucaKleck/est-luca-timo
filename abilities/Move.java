package abilities;

import java.awt.Point;

import entity.Entity;

public class Move extends Ability {
	private Point moveToPoint;
	
	public Move() {
		super(Ability.ABILITY_MOVE);
	}

	@Override
	public void applyAbility(Entity source, Entity target) {
		if(source.equals(target)) {
			source.setPoint(moveToPoint);
		}
	}
	
	public void setMoveToPoint(Point moveToPoint) {
		this.moveToPoint = moveToPoint;
	}
	
	public Point getMoveToPoint() {
		return moveToPoint;
	}
	
}


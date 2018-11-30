package abilities;

import java.awt.Point;

import entity.Entity;

public class Move extends Ability {
	private Point moveToPoint;
	
	public Move(Point moveToPoint) {
		super(Ability.ABILITY_MOVE);
		this.moveToPoint = moveToPoint;
	}

	@Override
	public void applyAbility(Entity source, Entity target) {
		if(source.equals(target)) {
			source.setPoint(moveToPoint);
		}
	}
}


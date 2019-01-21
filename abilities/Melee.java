package abilities;

import com.sun.javafx.geom.Point2D;

import entity.Entity;

public class Melee extends Ability {

	private Point2D attackPoint = new Point2D();
	private static final int BASE_DAMAGE = 2;
	
	public Melee() {
		super(Ability.ABILITY_MELEE, Ability.ABILITY_DESC_MELEE);
	}

	@Override
	public void applyAbility(Entity source, Entity target) {
		target.setCurrentHealth(target.getCurrentHealth() - BASE_DAMAGE);
	}
	
	public void setAttackPoint(Point2D moveToPoint) {
		this.attackPoint.setLocation(moveToPoint);
	}
	
	public Point2D getAttackPoint() {
		return attackPoint;
	}

}


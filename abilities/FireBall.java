package abilities;

import entity.Entity;
import entity.unit.Mage;
import entity.unit.Unit;

public class FireBall extends Ability {

	private static final int BASE_DAMAGE = 2;
	
	public FireBall() {
		super(Ability.ABILITY_FIRE_BALL, Ability.ABILITY_DESC_FIRE_BALL, Ability.ABILITY_TYPE_DAMAGE);
	}
	
	public FireBall(int maxRange) {
		super(Ability.ABILITY_FIRE_BALL, Ability.ABILITY_DESC_FIRE_BALL, Ability.ABILITY_TYPE_DAMAGE);
		super.maxRange = maxRange;
	}
	
	@Override
	public void applyAbility(Entity source, Entity target) {
		if(source instanceof Mage) {
			target.setCurrentHealth(target.getCurrentHealth() - (BASE_DAMAGE  + ((Unit) source).getBaseDamage()));
		} else {
			target.setCurrentHealth(target.getCurrentHealth() - BASE_DAMAGE);
		}
	}

}


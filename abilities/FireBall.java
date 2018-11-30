package abilities;

import entity.Entity;
import entity.unit.Unit;

public class FireBall extends Ability {

	private static final int BASE_DAMAGE = 1;
	
	public FireBall(String name) {
		super(name);
	}
	
	@Override
	public void applyAbility(Entity source, Entity target) {
		if(source instanceof Unit) {
			target.setCurrentHealth(target.getCurrentHealth() - BASE_DAMAGE  + ((Unit) source).getBaseDamage() );
		} else {
			target.setCurrentHealth(target.getCurrentHealth() - BASE_DAMAGE);
		}
	}

}


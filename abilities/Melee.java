package abilities;

import entity.Entity;

public class Melee extends Ability {

	private static final int BASE_DAMAGE = 3;
	private static final int DEFAULT_MAX_RANGE = 1;
	
	public Melee() {
		super(Ability.ABILITY_MELEE, Ability.ABILITY_DESC_MELEE);
		super.maxRange = DEFAULT_MAX_RANGE;
	}
	
	public Melee(int maxRange) {
		super(Ability.ABILITY_MELEE, Ability.ABILITY_DESC_MELEE);
		super.maxRange = maxRange;
	}

	@Override
	public void applyAbility(Entity source, Entity target) {
		target.setCurrentHealth(target.getCurrentHealth() - BASE_DAMAGE);
	}	

}


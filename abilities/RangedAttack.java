package abilities;

import entity.Entity;

public class RangedAttack extends Ability {

	private static final int BASE_DAMAGE = 2;
	private static final int DEFAULT_MAX_RANGE = 3;
	
	public RangedAttack() {
		super(Ability.ABILITY_RANGED_ATTACK, Ability.ABILITY_DESC_RANGED_ATTACK, Ability.ABILITY_TYPE_DAMAGE);
		super.maxRange = DEFAULT_MAX_RANGE;
	}
	
	public RangedAttack(int maxRange) {
		super(Ability.ABILITY_RANGED_ATTACK, Ability.ABILITY_DESC_RANGED_ATTACK, Ability.ABILITY_TYPE_DAMAGE);
		super.maxRange = maxRange;
	}

	@Override
	public void applyAbility(Entity source, Entity target) {
		target.setCurrentHealth(target.getCurrentHealth() - BASE_DAMAGE);
	}	

}


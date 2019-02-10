package abilities;

import entity.Entity;

public class MeleeAttack extends Ability {

	private static final int BASE_DAMAGE = 3;
	private static final int DEFAULT_MAX_RANGE = 1;
	
	public MeleeAttack() {
		super(Ability.ABILITY_MELEE_ATTACK, Ability.ABILITY_DESC_MELEE_ATTACK, Ability.ABILITY_TYPE_DAMAGE);
		super.maxRange = DEFAULT_MAX_RANGE;
	}
	
	public MeleeAttack(int maxRange) {
		super(Ability.ABILITY_MELEE_ATTACK, Ability.ABILITY_DESC_MELEE_ATTACK, Ability.ABILITY_TYPE_DAMAGE);
		super.maxRange = maxRange;
	}

	@Override
	public void applyAbility(Entity source, Entity target) {
		target.setCurrentHealth(target.getCurrentHealth() - BASE_DAMAGE);
	}	

}


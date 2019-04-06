package abilities;

import entity.Entity;

public class RangedAttack extends Ability {

	private static final int BASE_DAMAGE = 1;
	private static final int DEFAULT_MAX_RANGE = 3;
	
	public RangedAttack() {
		super(Ability.ABILITY_RANGED_ATTACK, Ability.ABILITY_DESC_RANGED_ATTACK, Ability.ABILITY_TYPE_DAMAGE);
		super.maxRange = DEFAULT_MAX_RANGE;
		this.setDamage(BASE_DAMAGE);
	}
	
	public RangedAttack(int damage) {
		super(Ability.ABILITY_RANGED_ATTACK, Ability.ABILITY_DESC_RANGED_ATTACK, Ability.ABILITY_TYPE_DAMAGE);
		super.maxRange = DEFAULT_MAX_RANGE;
		this.setDamage(damage);
	}

	@Override
	public void applyAbility(Entity source, Entity target) {
		target.setCurrentHealth(target.getCurrentHealth() - this.getDamage());
	}	

}


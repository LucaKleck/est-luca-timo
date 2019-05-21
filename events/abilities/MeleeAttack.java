package events.abilities;

import entity.Entity;

public class MeleeAttack extends Ability {

	private static final int BASE_DAMAGE = 4;
	private static final int DEFAULT_MAX_RANGE = 1;
	
	public MeleeAttack() {
		super(Ability.ABILITY_MELEE_ATTACK, Ability.ABILITY_DESC_MELEE_ATTACK, Ability.ABILITY_TYPE_DAMAGE);
		super.maxRange = DEFAULT_MAX_RANGE;
		this.setDamage(BASE_DAMAGE);
	}
	
	public MeleeAttack(int maxRange) {
		super(Ability.ABILITY_MELEE_ATTACK, Ability.ABILITY_DESC_MELEE_ATTACK, Ability.ABILITY_TYPE_DAMAGE);
		super.maxRange = maxRange;
		this.setDamage(BASE_DAMAGE);
	}
	
	public MeleeAttack(int maxRange, int damage) {
		super(Ability.ABILITY_MELEE_ATTACK, Ability.ABILITY_DESC_MELEE_ATTACK, Ability.ABILITY_TYPE_DAMAGE);
		super.maxRange = maxRange;
		this.setDamage(damage);
	}

	@Override
	public void applyAbility(Entity source, Entity target) {
		target.setCurrentHealth(target.getCurrentHealth() - this.getDamage());
	}	

}


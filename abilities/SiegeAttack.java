package abilities;

import entity.Entity;
import entity.building.Building;

public class SiegeAttack extends Ability {

	private static final int BASE_DAMAGE = 1;
	
	public SiegeAttack() {
		super(Ability.ABILITY_SIEGE_ATTACK, Ability.ABILITY_DESC_SIEGE_ATTACK, Ability.ABILITY_TYPE_DAMAGE);
		this.setDamage(BASE_DAMAGE);
	}
	
	public SiegeAttack(int maxRange) {
		super(Ability.ABILITY_SIEGE_ATTACK, Ability.ABILITY_DESC_SIEGE_ATTACK, Ability.ABILITY_TYPE_DAMAGE);
		super.maxRange = maxRange;
		this.setDamage(BASE_DAMAGE);
	}
	
	@Override
	public void applyAbility(Entity source, Entity target) {
		if(target instanceof Building) {
			target.setCurrentHealth(target.getCurrentHealth() - (BASE_DAMAGE  + 3));
		} else {
			target.setCurrentHealth(target.getCurrentHealth() - BASE_DAMAGE);
		}
	}

}


package abilities;

import entity.Entity;
import entity.building.Building;
import entity.unit.Unit;

public class SiegeAttack extends Ability {

	private static final int BASE_DAMAGE = 1;
	
	public SiegeAttack() {
		super(Ability.ABILITY_SIEGE_ATTACK, Ability.ABILITY_DESC_SIEGE_ATTACK, Ability.ABILITY_TYPE_DAMAGE);
	}
	
	public SiegeAttack(int maxRange) {
		super(Ability.ABILITY_SIEGE_ATTACK, Ability.ABILITY_DESC_SIEGE_ATTACK, Ability.ABILITY_TYPE_DAMAGE);
		super.maxRange = maxRange;
	}
	
	@Override
	public void applyAbility(Entity source, Entity target) {
		if(target instanceof Building) {
			target.setCurrentHealth(target.getCurrentHealth() - (BASE_DAMAGE  + ((Unit) source).getBaseDamage()));
		} else {
			target.setCurrentHealth(target.getCurrentHealth() - BASE_DAMAGE);
		}
	}

}


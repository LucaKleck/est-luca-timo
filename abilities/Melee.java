package abilities;

import entity.Entity;

public class Melee extends Ability {

	private static final int BASE_DAMAGE = 2;
	
	public Melee() {
		super(Ability.ABILITY_MELEE, Ability.ABILITY_DESC_MELEE);
	}

	@Override
	public void applyAbility(Entity source, Entity target) {
		target.setCurrentHealth(target.getCurrentHealth() - BASE_DAMAGE);
	}

}


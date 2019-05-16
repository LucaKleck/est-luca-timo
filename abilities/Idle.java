package abilities;

import entity.Entity;

public class Idle extends Ability {

	public Idle() {
		super(ABILITY_IDLE, ABILITY_DESC_IDLE, ABILITY_TYPE_IDLE);
		this.maxRange = 0;
	}

	@Override
	public void applyAbility(Entity source, Entity target) {
	}

}

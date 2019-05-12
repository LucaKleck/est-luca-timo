package abilities;

import entity.Entity;

public class Destroy extends Ability {

	public Destroy() {
		super(Ability.ABILITY_DESTROY_ENTITY, Ability.ABILITY_DESC_DESTROY_ENTITY, Ability.ABILITY_TYPE_DESTROY);
		this.maxRange = 0;
	}

	@Override
	public void applyAbility(Entity source, Entity target) {
		target.destroy();
	}

}

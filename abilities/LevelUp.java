package abilities;

import entity.Entity;

public class LevelUp extends Ability {
	
	public LevelUp() {
		super(Ability.ABILITY_LEVEL_UP, Ability.ABILITY_DESC_LEVEL_UP, Ability.ABILITY_TYPE_LEVEL);
	}
	
	@Override
	public void applyAbility(Entity source, Entity target) {
		
		target.addLevel();
		
	}

}


package abilities;

import entity.Entity;
import statusEffects.Heal;
import statusEffects.Poison;
import statusEffects.StatusEffect;

public class AddStatusEffect extends Ability {
	
	private StatusEffect statusEffect;
	
	public AddStatusEffect(String name, String description) {
		super(name, description, Ability.ABILITY_TYPE_STATUS_EFFECT);
		if(name.equals(StatusEffect.STATUS_EFFECT_NAME_HEAL)) {
			statusEffect = new Heal(StatusEffect.STATUS_EFFECT_NAME_HEAL, StatusEffect.DURATION_HEAL);
		}
		if(name.equals(StatusEffect.STATUS_EFFECT_NAME_POISON)) {
			statusEffect = new Poison(StatusEffect.STATUS_EFFECT_NAME_POISON, StatusEffect.DURATION_POISON);
		}
	}
	
	@Override
	public void applyAbility(Entity source, Entity target) {
		target.addStatusEffect(statusEffect, source, target);
	}
	
}

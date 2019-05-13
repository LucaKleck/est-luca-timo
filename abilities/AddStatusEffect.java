package abilities;

import entity.Entity;
import statusEffects.Heal;
import statusEffects.Poison;
import statusEffects.StatusEffect;

public class AddStatusEffect extends Ability {
	
	public static final String TYPE_HEAL = "Heal";
	public static final String TYPE_POISON = "Poison";
	
	private String statusEffectType;
	private StatusEffect statusEffect;
	
	public AddStatusEffect(String name, String description, String statusEffectType) {
		super(name, description, Ability.ABILITY_TYPE_STATUS_EFFECT);
		
		this.statusEffectType = statusEffectType;
		
		if(statusEffectType.equals(TYPE_HEAL)) {
			statusEffect = new Heal(StatusEffect.STATUS_EFFECT_NAME_HEAL, StatusEffect.DURATION_HEAL);
		} else if(statusEffectType.equals(TYPE_POISON)) {
			statusEffect = new Poison(StatusEffect.STATUS_EFFECT_NAME_POISON, StatusEffect.DURATION_POISON);
		}
	}
	
	public String getStatusEffectType() {
		return this.statusEffectType;
	}
	
	public StatusEffect getStatusEffect() {
		return statusEffect;
	}
	
	@Override
	public void applyAbility(Entity source, Entity target) {
				
		target.addStatusEffect(statusEffect, source, target);
		
	}
	
}

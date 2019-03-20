package abilities;

import entity.Entity;
import statusEffects.Heal;
import statusEffects.Poison;
import statusEffects.StatusEffect;

public class AddStatusEffect extends Ability {
	
	public static final String TYPE_HEAL = "Heal";
	public static final String TYPE_POISON = "Poison";
	
	private String statusEffectType;
	
	public AddStatusEffect(String name, String description, String statusEffectType) {
		super(name, description, Ability.ABILITY_TYPE_STATUS_EFFECT);
		this.statusEffectType = statusEffectType;
	}
	
	public String getStatusEffectType() {
		return this.statusEffectType;
	}
	
	@Override
	public void applyAbility(Entity source, Entity target) {
				
		if(statusEffectType.equals(TYPE_HEAL)) {
			target.addStatusEffect(new Heal(StatusEffect.STATUS_EFFECT_NAME_HEAL, StatusEffect.DURATION_HEAL), source, target);
		} else if(statusEffectType.equals(TYPE_POISON)) {
			target.addStatusEffect(new Poison(StatusEffect.STATUS_EFFECT_NAME_POISON, StatusEffect.DURATION_POISON), source, target);
		}
		
	}
	
}

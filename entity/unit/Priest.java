package entity.unit;

import java.util.ArrayList;

import core.Point2DNoFxReq;
import events.abilities.Ability;
import events.abilities.AddStatusEffect;
import events.abilities.MeleeAttack;
import events.statusEffects.StatusEffect;

public class Priest extends Unit {

	private static final int BASE_MAX_HEALTH = 3;
	private static final int BASE_DAMAGE = 1;
	private static final int MOVEMENT_RANGE = 3;
	
	public Priest(Point2DNoFxReq pointXY, String name, int currentHealth, int level, boolean controlable, ArrayList<Ability> abilities, boolean autoIdle) {
		super(pointXY, name, BASE_MAX_HEALTH, currentHealth, level, controlable, BASE_DAMAGE, MOVEMENT_RANGE, abilities, autoIdle);
		//Basic attack for self-defense
		MeleeAttack melee = new MeleeAttack();
		melee.setDamage(1);
		abilities.add(melee);
		
		abilities.add(new AddStatusEffect(StatusEffect.STATUS_EFFECT_NAME_HEAL, Ability.ABILITY_DESC_HEAL, AddStatusEffect.TYPE_HEAL));
		abilities.add(new AddStatusEffect(StatusEffect.STATUS_EFFECT_NAME_POISON, Ability.ABILITY_DESC_POISON, AddStatusEffect.TYPE_POISON));
	}
	
	public Priest(Point2DNoFxReq pointXY, String name, int level, boolean controlable, ArrayList<Ability> abilities, boolean autoIdle) {
		super(pointXY, name, BASE_MAX_HEALTH, BASE_MAX_HEALTH, level, controlable, BASE_DAMAGE, MOVEMENT_RANGE, abilities, autoIdle);
		//Basic attack for self-defense
		MeleeAttack melee = new MeleeAttack();
		melee.setDamage(1);
		abilities.add(melee);
		
		abilities.add(new AddStatusEffect(StatusEffect.STATUS_EFFECT_NAME_HEAL, Ability.ABILITY_DESC_HEAL, AddStatusEffect.TYPE_HEAL));
		abilities.add(new AddStatusEffect(StatusEffect.STATUS_EFFECT_NAME_POISON, Ability.ABILITY_DESC_POISON, AddStatusEffect.TYPE_POISON));
		
	}

}

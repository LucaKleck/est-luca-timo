package statusEffects;

import entity.Entity;

public abstract class StatusEffect {

	public final static String STATUS_EFFECT_NAME_HEAL = "Heal";
	public final static boolean IS_PASSIVE_HEAL = false;
	public final static int DURATION_HEAL = 3;
	
	public final static String STATUS_EFFECT_NAME_POISON = "Poison";
	public final static boolean IS_PASSIVE_POISON = false;
	public final static int DURATION_POISON = 2;
	
	private String name;
	private boolean passive;
	private int duration;
	private Entity target;

	public StatusEffect(String name, boolean passive, int duration, Entity target) {
		this.name = name;
		this.passive = passive;
		this.duration = duration;
		this.target = target;
	}

	public String getName() {
		return name;
	}

	public boolean isPassive() {
		return passive;
	}

	public int getDuration() {
		return duration;
	}

	public Entity getTarget() {
		return target;
	}

	public abstract void applyEffect();
}

package events.statusEffects;

import entity.Entity;

public abstract class StatusEffect {

	public final static String STATUS_EFFECT_NAME_HEAL = "Heal";
	public final static int DURATION_HEAL = 3;
	
	public final static String STATUS_EFFECT_NAME_POISON = "Poison";
	public final static int DURATION_POISON = 2;
	
	private String name;
	private int duration;
	private Entity source;
	private Entity target;
	private boolean isNegative = false;

	public StatusEffect(String name, int duration, boolean isNegative) {
		this.name = name;
		this.duration = duration;
		this.isNegative = isNegative;
	}

	public String getName() {
		return this.name;
	}

	public int getDuration() {
		return this.duration;
	}
	
	public void setDuration(int duration) {
		this.duration = duration;
	}
	
	public void reduceDuration() {
		this.duration = this.duration - 1;
	}

	public Entity getSource() {
		return this.source;
	}
	
	public Entity getTarget() {
		return this.target;
	}
	
	public Entity setSource(Entity source) {
		return this.source = source;
	}
	
	public Entity setTarget(Entity target) {
		return this.target = target;
	}

	public boolean isNegative() {
		return isNegative;
	}
	
	public abstract void applyEffect();
	
	@Override
	public String toString() {
		return "StatusEffect " + name + ", duration=" + duration + " on " + target + " from " + source;
	}
}

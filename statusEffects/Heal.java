package statusEffects;

import entity.Entity;

public class Heal extends StatusEffect {

	public Heal(String name, boolean permanent, int duration, Entity target) {
		super(name, permanent, duration, target);
	}

	@Override
	public void applyEffect() {
		this.getTarget().setCurrentHealth(this.getTarget().getCurrentHealth() + 1);
	}

}

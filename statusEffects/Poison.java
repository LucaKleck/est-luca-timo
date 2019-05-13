package statusEffects;

public class Poison extends StatusEffect {

	public Poison(String name, int duration) {
		super(name, duration, true);
	}

	@Override
	public void applyEffect() {
		if(this.getDuration() > 1) {
			this.reduceDuration();
			this.getTarget().setCurrentHealth(this.getTarget().getCurrentHealth() - 1);
		} else {
			this.reduceDuration();
			this.getTarget().setCurrentHealth(this.getTarget().getCurrentHealth() - 1);
			this.getTarget().removeStatusEffect(this);
		}
	}

}

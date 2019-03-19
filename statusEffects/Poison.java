package statusEffects;

public class Poison extends StatusEffect {

	public Poison(String name, int duration) {
		super(name, duration);
	}

	@Override
	public void applyEffect() {
		this.getTarget().setCurrentHealth(this.getTarget().getCurrentHealth() - 1);
	}

}

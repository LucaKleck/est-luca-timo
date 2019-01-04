package statusEffects;

public abstract class StatusEffect {

	private String name;
	private boolean permanent;
	private int duration;
	
	public StatusEffect(String name, boolean permanent, int duration) {
		this.name=name;
		this.permanent=permanent;
		this.duration=duration;
	}

	public String getName() {
		return name;
	}
	
	public boolean isPermanent() {
		return permanent;
	}

	public int getDuration() {
		return duration;
	}

	public abstract int applyEffect();
}

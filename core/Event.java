package core;

import abilities.Ability;
import entity.Entity;
import map.ObjectMap;
import map.MapImage.Effect;

public class Event implements Runnable {

	private Entity source;
	private Entity target;
	private Ability ability;
	private Effect effect;
	private Thread self;
	
	public Event(Entity source, Entity target, Ability ability, Effect effect) {
		this.source = source;
		this.target = target;
		this.ability = ability;
		this.effect = effect;
		this.self = new Thread(this);
		ObjectMap.getEventQueue().add(self);
	}

	public Entity getSource() {
		return source;
	}

	public Entity getTarget() {
		return target;
	}

	public Ability getAbility() {
		return ability;
	}

	public Effect getEffect() {
		return effect;
	}

	public void cancleEvent() {
		ObjectMap.getEventQueue().remove(self);
	}
	
	@Override
	public void run() {
		ability.applyAbility(source, target);
		ObjectMap.getEventQueue().remove(self);
	}
}

package core;

import abilities.Ability;
import entity.Entity;
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
		self.setName(source.toString()+" targets "+target.toString()+" with "+ability.getName());
		GameInfo.getEventQueue().add(self);
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
		GameInfo.getEventQueue().remove(self);
	}
	
	@Override
	public void run() {
		ability.applyAbility(source, target);
		GameInfo.getEventQueue().remove(self);
	}

	@Override
	public String toString() {
		return "Event [source=" + source + ", target=" + target + ", ability=" + ability + ", effect=" + effect
				+ ", self=" + self + "]";
	}
}

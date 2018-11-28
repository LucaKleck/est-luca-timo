package core;

import abilities.Ability;
import entity.Entity;
import map.MapImage.Effect;

public class Event implements Runnable {

	private Entity source;
	private Entity target;
	private Ability ability;
	private Effect effect;
	private Thread selfEventThread;
	
	public Event(Entity source, Entity target, Ability ability, Effect effect) {
		this.source = source;
		this.target = target;
		this.ability = ability;
		this.effect = effect;
		this.selfEventThread = new Thread(this);
		selfEventThread.setName(source.toString()+" targets "+target.toString()+" with "+ability.getName());
		GameInfo.getEventQueue().add(selfEventThread);
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

	public void cancelEvent() {
		GameInfo.getEventQueue().remove(selfEventThread);
	}
	
	@Override
	public void run() {
		ability.applyAbility(source, target);
		cancelEvent();
	}

	@Override
	public String toString() {
		return "Event [source=" + source + ", target=" + target + ", ability=" + ability + ", effect=" + effect
				+ ", self=" + selfEventThread + "]";
	}
}

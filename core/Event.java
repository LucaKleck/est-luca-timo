package core;

import abilities.Ability;
import effects.MapImageEffect;
import entity.Entity;

public class Event implements Runnable {

	private Entity source;
	private Entity target;
	private Ability ability;
	private MapImageEffect effect;
	private Thread eventThread;
	
	public Event(Entity source, Entity target, Ability ability, MapImageEffect effect) {
		this.source = source;
		this.target = target;
		this.ability = ability;
		this.effect = effect;
		this.eventThread = new Thread(this);
		eventThread.setName(source.toString()+" targets "+target.toString()+" with "+ability.getName());
		GameInfo.getRoundInfo().getEventList().add(this);
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

	public MapImageEffect getEffect() {
		return effect;
	}
	
	public Thread getEventThread() {
		return eventThread;
	}
	public void cancelEvent() {
		GameInfo.getRoundInfo().getEventList().remove(this);
	}
	
	@Override
	public void run() {
		ability.applyAbility(source, target);
		source.setEvent(null);
	}

	@Override
	public String toString() {
		return "Event [source=" + source + ", target=" + target + ", ability=" + ability + ", effect=" + effect
				+ ", self=" + eventThread + "]";
	}
}

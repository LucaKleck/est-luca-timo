package core;

import abilities.Ability;
import abilities.Build;
import abilities.CollectResources;
import abilities.Move;
import effects.MapImageEffect;
import entity.Entity;

public class Event implements Runnable {

	private Entity source;
	private Entity target;
	private Ability ability;
	private MapImageEffect effect;
	
	public Event(Entity source, Entity target, Ability ability, MapImageEffect effect) {
		this.source = source;
		this.target = target;
		this.ability = ability;
		this.effect = effect;
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
	
	@Override
	public void run() {
		ability.applyAbility(source, target);
	}

	@Override
	public String toString() {
		if(ability instanceof CollectResources) {
			return source.getName() + ability;
		} else if(ability instanceof Move) {
			return source.getName() +" "+ ability;
		} else if(ability instanceof Build) {
			return source.getName() + " Built " + ((Build)ability).getBuildingType();
		} else {
			return source.getName() + " used "+ ability + " on " + target.getName();
		}
	}
}

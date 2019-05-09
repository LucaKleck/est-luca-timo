package core;

import abilities.Ability;
import abilities.Build;
import abilities.CollectResources;
import abilities.CreateUnit;
import abilities.LevelUp;
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
			return source.toString() + ability;
		} else if(ability instanceof Build) {
			return source + " built " + ((Build)ability).getBuildingType();
		} else if(ability instanceof CreateUnit) {
			return source + " created " + ((CreateUnit)ability).getUnitType();
		} else if(ability instanceof LevelUp) {
			return source + " leveled up ("+(source.getLevel()+1)+")";
		} else {
			return source + " used "+ ability + " on " + target;
		}
	}
}

package abilities;

import entity.Entity;
import map.ObjectMap;

public abstract class Ability implements Runnable {
	
	String name;
	Entity target;

	public Ability(String name, Entity target) {
		this.name = name;
		this.target = target;
		ObjectMap.getEventQueue().add(new Thread(this));
	}
	
	public String getName() {
		return name;
	}

	public Entity getTarget() {
		return target;
	}
	
	@Override
	public void run() {
		
	}
	
}

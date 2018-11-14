package abilities;

import entity.Entity;
import map.ObjectMap;

public class FireBall extends Ability {

	private int damage = 1;
	
	public FireBall(String name, Entity target) {
		super(name, target);
	}
	
	@Override
	public void run() {
		
		target.setCurrentHealth(target.getCurrentHealth() - damage);
		
	}

}


package abilities;

import entity.Entity;

public class Melee extends Ability {

	private int damage = 2;
	
	public Melee(String name, Entity target) {
		super(name, target);
	}
	
	@Override
	public void run() {
		
		target.setCurrentHealth(target.getCurrentHealth() - damage);
		
	}

}


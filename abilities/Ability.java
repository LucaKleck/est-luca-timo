package abilities;

import entity.Entity;

public abstract class Ability {
	public static final String Ability_Dev_Create_Unit = "devCreateUnit";
	public static final String Ability_Dev_Create_Building = "devCreateBuilding";
	
	public static final String Ability_FIRE_BALL = "Fireball";
	public static final String Ability_MELEE = "Melee";
	String name;

	public Ability(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	public abstract void applyAbility(Entity source, Entity target);
	
}

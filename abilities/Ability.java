package abilities;

import entity.Entity;

public abstract class Ability {
	// Dev Abilities
	public static final String ABILITY_DEV_CREATE_UNIT = "devCreateUnit";
	public static final String ABILITY_DEV_CREATE_BUILDING = "devCreateBuilding";

	// Unit Abilities
	public static final String ABILITY_FIRE_BALL = "Fireball";
	public static final String ABILITY_MELEE = "Melee";
	public static final String ABILITY_MOVE = "Move";
	
	// Building Abilities
	public static final String ABILITY_COLLECT_RESOURCES = "Collect Resources";
	
	String name;

	public Ability(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	public abstract void applyAbility(Entity source, Entity target);
	
}

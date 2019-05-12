package abilities;

import entity.Entity;

public abstract class Ability {
	// DAMAGE will be searched and replaced by the actual damage the unit will deal
	// Dev Abilities
	public static final String ABILITY_DEV_CREATE_UNIT = "devCreateUnit";
	public static final String ABILITY_DESC_DEV_CREATE_UNIT = "devCreateUnit";

	public static final String ABILITY_DEV_CREATE_BUILDER = "devCreateBuilder";
	public static final String ABILITY_DESC_DEV_CREATE_BUILDER = "devCreateBuilder";

	public static final String ABILITY_DEV_CREATE_BUILDING = "devCreateBuilding";
	public static final String ABILITY_DESC_DEV_CREATE_BUILDING = "devCreateBuilding";

	//Entity Abilities
	public static final String ABILITY_DESTROY_ENTITY = "Destroy Entity";
	public static final String ABILITY_DESC_DESTROY_ENTITY = "Destroy the selected entity";
	
	// Unit Abilities
	public static final String ABILITY_FIRE_BALL = "Fireball";
	public static final String ABILITY_DESC_FIRE_BALL = "Shoots a Fireball at the target, dealing DAMAGE damage to the target";

	public static final String ABILITY_MELEE_ATTACK = "Melee";
	public static final String ABILITY_DESC_MELEE_ATTACK = "Slashes target for DAMAGE damage";

	public static final String ABILITY_RANGED_ATTACK = "Ranged";
	public static final String ABILITY_DESC_RANGED_ATTACK = "Fires an arrow on target for DAMAGE damage";
	
	public static final String ABILITY_SIEGE_ATTACK = "Siege";
	public static final String ABILITY_DESC_SIEGE_ATTACK = "Deals extra damage to Buildings for DAMAGE damage";

	public static final String ABILITY_MOVE = "Move";
	public static final String ABILITY_DESC_MOVE = "Moves to targeted spot";

	// Build Abilities
	// Resource Buildings
	public static final String ABILITY_BUILD_STONE_GETTER = "Build Quarry";
	public static final String ABILITY_DESC_BUILD_STONE_GETTER = "Builds a quarry at targeted spot";

	public static final String ABILITY_BUILD_WOOD_GETTER = "Build Lumberjack";
	public static final String ABILITY_DESC_BUILD_WOOD_GETTER = "Builds a lumberjack at targeted spot";

	public static final String ABILITY_BUILD_FOOD_GETTER = "Build Farm";
	public static final String ABILITY_DESC_BUILD_FOOD_GETTER = "Builds a farm at targeted spot";

	public static final String ABILITY_BUILD_METAL_GETTER = "Build Metal Forge";
	public static final String ABILITY_DESC_BUILD_METAL_GETTER = "Builds a metal forge at targeted spot";

	public static final String ABILITY_BUILD_GOLD_GETTER = "Build Gold Forge";
	public static final String ABILITY_DESC_BUILD_GOLD_GETTER = "Builds a gold forge at targeted spot";

	public static final String ABILITY_BUILD_MANA_GETTER = "Build MANA_GETTER";
	public static final String ABILITY_DESC_BUILD_MANA_GETTER = "Builds a MANA_GETTER at targeted spot";
	// Production Buildings
	public static final String ABILITY_BUILD_TOWN_CENTER = "Build Town Center";
	public static final String ABILITY_DESC_BUILD_TOWN_CENTER = "Builds a town center at targeted spot";

	public static final String ABILITY_BUILD_BARRACKS = "Build Barracks";
	public static final String ABILITY_DESC_BUILD_BARRACKS = "Builds barracks at targeted spot";
	
	public static final String ABILITY_BUILD_STABLE = "Build Stable";
	public static final String ABILITY_DESC_BUILD_STABLE = "Builds stable at targeted spot";
	
	public static final String ABILITY_BUILD_CHURCH = "Build Church";
	public static final String ABILITY_DESC_BUILD_CHURCH = "Builds church at targeted spot";
	
	public static final String ABILITY_BUILD_SIEGE_WORKSHOP = "Build Siege-Workshop";
	public static final String ABILITY_DESC_BUILD_SIEGE_WORKSHOP = "Builds siege-workshop at targeted spot";
	// Defense Buildings
	public static final String ABILITY_BUILD_MAGE_TOWER = "Build Mage Tower";
	public static final String ABILITY_DESC_BUILD_MAGE_TOWER = "Builds a mage tower at targeted spot";

	public static final String ABILITY_BUILD_ARCHER_TOWER = "Build Archer Tower";
	public static final String ABILITY_DESC_BUILD_ARCHER_TOWER = "Builds a archer tower at targeted spot";

	// Building Abilities
	public static final String ABILITY_COLLECT_RESOURCES = "Collect resources";
	public static final String ABILITY_DESC_COLLECT_RESOURCES = "Collects resources at the end of the round";

	public static final String ABILITY_CREATE_UNIT = "Create Unit";
	public static final String ABILITY_DESC_CREATE_UNIT = "Creates new Unit at the end of the round";

	public static final String ABILITY_CREATE_WARRIOR = "Create Warrior";
	public static final String ABILITY_DESC_CREATE_WARRIOR = "Creates new Warrior at the end of the round";

	public static final String ABILITY_CREATE_MAGE = "Create Mage";
	public static final String ABILITY_DESC_CREATE_MAGE = "Creates new Mage at the end of the round";

	public static final String ABILITY_CREATE_BUILDER = "Create Builder";
	public static final String ABILITY_DESC_CREATE_BUILDER = "Creates new Builder at the end of the round";
	
	public static final String ABILITY_CREATE_ARCHER = "Create Archer";
	public static final String ABILITY_DESC_CREATE_ARCHER = "Creates new Archer at the end of the round";
	
	public static final String ABILITY_CREATE_TREBUCHET = "Create Trebuchet";
	public static final String ABILITY_DESC_CREATE_TREBUCHET = "Creates new Trebuchet at the end of the round";
	
	public static final String ABILITY_CREATE_BATTERING_RAM = "Create Battering Ram";
	public static final String ABILITY_DESC_CREATE_BATTERING_RAM = "Creates new Battering Ram at the end of the round";
	
	public static final String ABILITY_CREATE_CAVALRY = "Create Cavalry";
	public static final String ABILITY_DESC_CREATE_CAVALRY = "Creates new Cavalry at the end of the round";
	
	public static final String ABILITY_CREATE_CAVALRY_ARCHER = "Create Cavalry Archer";
	public static final String ABILITY_DESC_CREATE_CAVALRY_ARCHER = "Creates new Cavalry Archer at the end of the round";

	public static final String ABILITY_CREATE_DRAGON = "Create Dragon";
	public static final String ABILITY_DESC_CREATE_DRAGON = "Creates new Dragon at the end of the round";
	
	public static final String ABILITY_CREATE_HERO = "Create Hero";
	public static final String ABILITY_DESC_CREATE_HERO = "Creates new Hero at the end of the round";
	
	public static final String ABILITY_CREATE_KNIGHT = "Create Knight";
	public static final String ABILITY_DESC_CREATE_KNIGHT = "Creates new Knight at the end of the round";
	
	public static final String ABILITY_CREATE_MANGONEL = "Create Mangonel";
	public static final String ABILITY_DESC_CREATE_MANGONEL = "Creates new Mangonel at the end of the round";
	
	public static final String ABILITY_CREATE_PRIEST = "Create Priest";
	public static final String ABILITY_DESC_CREATE_PRIEST = "Creates new Priest at the end of the round";
	
	// Other Abilities
	public static final String ABILITY_LEVEL_UP = "Level Up";
	public static final String ABILITY_DESC_LEVEL_UP = "Levels the selected entity at the end of the round";
	
	//Status Effect Abilities
	public static final String ABILITY_HEAL = "Heal";
	public static final String ABILITY_DESC_HEAL = "Heals the selected entity";
	
	public static final String ABILITY_POISON = "Poison";
	public static final String ABILITY_DESC_POISON = "Poisons the selected entity";

	// Ability Types
	public static final String ABILITY_TYPE_DESTROY = "destroy";
	public static final String ABILITY_TYPE_DAMAGE = "damage";
	public static final String ABILITY_TYPE_COLLECT = "collect";
	public static final String ABILITY_TYPE_PRODUCE = "produce";
	public static final String ABILITY_TYPE_BUILD = "build";
	public static final String ABILITY_TYPE_LEVEL = "level";
	public static final String ABILITY_TYPE_MOVEMENT = "movement";
	public static final String ABILITY_TYPE_STATUS_EFFECT = "statuseffect";

	private String name;
	private String description;
	public int maxRange = 3;
	private String type = "default";
	private int damage = 1;

	public Ability(String name, String description, String type) {
		this.name = name;
		this.description = description;
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public abstract void applyAbility(Entity source, Entity target);

	public boolean rangeCheckEntity(Entity source, Entity target) {
		
		int targetX = target.getXPos();
		int targetY = target.getYPos();
		int sourceX = source.getXPos();
		int sourceY = source.getYPos();
		
		int deltaX = targetX - sourceX;
		if(deltaX < 0) deltaX = -deltaX;
		
		int deltaY = targetY - sourceY;
		if(deltaY < 0) deltaY = -deltaY;
		
		if(deltaX <= maxRange && deltaY <= maxRange) {
			return true;
		} else {
			return false;
		}
	
	}
	
	public boolean rangeCheck(int unitX, int unitY, int mapTileX, int mapTileY) {
		if (mapTileX >= unitX - maxRange && mapTileX <= unitX + maxRange && mapTileY >= unitY - maxRange
				&& mapTileY <= unitY + maxRange) {
			return true;
		} else {
			return false;
		}
	}

	public int getAbilityMinimumLevel() {
		switch (this.name) {
		case Ability.ABILITY_BUILD_ARCHER_TOWER:
			return 2;
		case Ability.ABILITY_BUILD_MANA_GETTER:
			return 2;
		case Ability.ABILITY_BUILD_GOLD_GETTER:
			return 2;
		case Ability.ABILITY_BUILD_METAL_GETTER:
			return 2;
		case Ability.ABILITY_BUILD_TOWN_CENTER:
			return 2;
		case Ability.ABILITY_BUILD_BARRACKS:
			return 2;
		case Ability.ABILITY_BUILD_SIEGE_WORKSHOP:
			return 2;
		default:
			return 0;
		}
	}

	public String getType() {
		return type;
	}

	public int getDamage() {
		return damage;
	}

	public void setDamage(int damage) {
		this.damage = damage;
	}

	@Override
	public String toString() {
		return name;
	}

}

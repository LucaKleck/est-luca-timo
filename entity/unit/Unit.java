package entity.unit;

import java.util.ArrayList;

import abilities.Ability;
import abilities.Move;
import core.Point2DNoFxReq;
import entity.Entity;

public class Unit extends Entity {
	
	public static final String UNIT_MAGE = "Mage";
	public static final String UNIT_BUILDER = "Builder";
	public static final String UNIT_WARRIOR = "Warrior";
	public static final String UNIT_ARCHER = "Archer";
	public static final String UNIT_CAVALRY = "Cavalry";
	public static final String UNIT_CAVALRY_ARCHER = "Cavalry Archer";
	public static final String UNIT_TREBUCHET = "Trebuchet";
	public static final String UNIT_MANGONEL = "Mangonel";
	public static final String UNIT_PRIEST = "Priest";
	public static final String UNIT_KNIGHT = "Knight";
	public static final String UNIT_BATTERING_RAM = "Battering Ram";
	public static final String UNIT_HERO = "Hero";
	public static final String UNIT_DRAGON = "Dragon";
	
	private int baseDamage;
	private int movementRange;
	private Ability move;

	public Unit(Point2DNoFxReq pointXY, String name, int maxHealth, int currentHealth, int level, boolean controlable,  int baseDamage,  int movementRange, ArrayList<Ability> abilities) {
		super(pointXY, name, maxHealth, currentHealth, level, controlable, abilities);
		move = new Move(movementRange);
		abilities.add(move);
		this.baseDamage = baseDamage;
		this.movementRange = movementRange;
	}
	
	public int getMovementRange() {
		return movementRange;
	}
	
	public int getBaseDamage() {
		return baseDamage;
	}

	public Move getMove() {
		return (Move) move;
	}

}
package entity.unit;

import java.util.ArrayList;

import com.sun.javafx.geom.Point2D;

import abilities.Ability;
import abilities.Move;
import entity.Entity;

public class Unit extends Entity {
	
	public static final String UNIT_MAGE = "Mage";
	public static final String UNIT_BUILDER = "Builder";
	public static final String UNIT_WARRIOR = "Warrior";
	public static final String UNIT_ARCHER = "Archer";
	public static final String UNIT_TREBUCHET = "Trebuchet";
	
	private int baseDamage;
	private int movementRange;
	private Ability move = new Move();

	public Unit(Point2D pointXY, String name, int maxHealth, int currentHealth, int level, boolean controlable,  int baseDamage,  int movementRange, ArrayList<Ability> abilities) {

		super(pointXY, name, maxHealth, currentHealth, level, controlable, abilities);
		
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

	@Override
	public String toString() {
		return "Unit [ id="+super.getId()+", name=" + getName() + ", damage=" + baseDamage + ", health=" + getMaxHealth() + ", movementRange=" + movementRange + "]";
	}

	public Move getMove() {
		return (Move) move;
	}

}
package entity.unit;

import java.util.ArrayList;

import com.sun.javafx.geom.Point2D;

import abilities.Ability;
import abilities.RangedAttack;

public class Archer extends Unit {

	private static final int BASE_MAX_HEALTH = 3;
	private static final int BASE_DAMAGE = 2;
	private static final int MOVEMENT_RANGE = 2;

	public Archer(Point2D pointXY, String name, int currentHealth, int level, boolean controlable, ArrayList<Ability> abilities) {
		super(pointXY, name, BASE_MAX_HEALTH, currentHealth, level, controlable, BASE_DAMAGE, MOVEMENT_RANGE, abilities);
		abilities.add(new RangedAttack(2));
	}

}

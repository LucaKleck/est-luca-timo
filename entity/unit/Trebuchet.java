package entity.unit;

import java.util.ArrayList;

import com.sun.javafx.geom.Point2D;

import abilities.Ability;
import abilities.SiegeAttack;

public class Trebuchet extends Unit {

	private static final int BASE_MAX_HEALTH = 5;
	private static final int BASE_DAMAGE = 4;
	private static final int MOVEMENT_RANGE = 2;

	public Trebuchet(Point2D pointXY, String name, int currentHealth, int level, boolean controlable, ArrayList<Ability> abilities) {
		super(pointXY, name, BASE_MAX_HEALTH, currentHealth, level, controlable, BASE_DAMAGE, MOVEMENT_RANGE, abilities);
		abilities.add(new SiegeAttack());
	}

}

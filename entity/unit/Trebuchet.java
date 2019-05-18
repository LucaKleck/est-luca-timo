package entity.unit;

import java.util.ArrayList;

import abilities.Ability;
import abilities.SiegeAttack;
import core.Point2DNoFxReq;

public class Trebuchet extends Unit {

	private static final int BASE_MAX_HEALTH = 5;
	private static final int BASE_DAMAGE = 4;
	private static final int MOVEMENT_RANGE = 2;

	public Trebuchet(Point2DNoFxReq pointXY, String name, int currentHealth, int level, boolean controlable, ArrayList<Ability> abilities, boolean autoIdle) {
		super(pointXY, name, BASE_MAX_HEALTH, currentHealth, level, controlable, BASE_DAMAGE, MOVEMENT_RANGE, abilities, autoIdle);
		abilities.add(new SiegeAttack(4, 2));
	}
	
	public Trebuchet(Point2DNoFxReq pointXY, String name, int level, boolean controlable, ArrayList<Ability> abilities, boolean autoIdle) {
		super(pointXY, name, BASE_MAX_HEALTH, BASE_MAX_HEALTH, level, controlable, BASE_DAMAGE, MOVEMENT_RANGE, abilities, autoIdle);
		abilities.add(new SiegeAttack(4, 2));
	}

}

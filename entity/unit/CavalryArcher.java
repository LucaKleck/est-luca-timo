package entity.unit;

import java.util.ArrayList;

import core.Point2DNoFxReq;
import events.abilities.Ability;
import events.abilities.RangedAttack;

public class CavalryArcher extends Unit {

	private static final int BASE_MAX_HEALTH = 5;
	private static final int BASE_DAMAGE = 2;
	private static final int MOVEMENT_RANGE = 4;
	
	public CavalryArcher(Point2DNoFxReq pointXY, String name, int currentHealth, int level, boolean controlable, ArrayList<Ability> abilities, boolean autoIdle) {
		super(pointXY, name, BASE_MAX_HEALTH, currentHealth, level, controlable, BASE_DAMAGE, MOVEMENT_RANGE, abilities, autoIdle);
		abilities.add(new RangedAttack(BASE_DAMAGE));
	}

	public CavalryArcher(Point2DNoFxReq pointXY, String name, int level, boolean controlable, ArrayList<Ability> abilities, boolean autoIdle) {
		super(pointXY, name, BASE_MAX_HEALTH, BASE_MAX_HEALTH, level, controlable, BASE_DAMAGE, MOVEMENT_RANGE, abilities, autoIdle);
		abilities.add(new RangedAttack(BASE_DAMAGE));
	}
	
}

package entity.building;

import java.util.ArrayList;

import core.Point2DNoFxReq;
import events.abilities.Ability;
import events.abilities.FireBall;
import events.abilities.RangedAttack;

public class DefenseBuilding extends Building {
	
	public DefenseBuilding(Point2DNoFxReq pointXY, String name, int maxHealth, int currentHealth, int level, boolean controlable, ArrayList<Ability> abilities) {
		super(pointXY, name, maxHealth, currentHealth, level, controlable, abilities);
		if(name.equals(Building.ARCHER_TOWER)) {
			abilities.add(new RangedAttack());
		}
		if(name.equals(Building.MAGE_TOWER)) {
			abilities.add(new FireBall());
		}
	}
	
}

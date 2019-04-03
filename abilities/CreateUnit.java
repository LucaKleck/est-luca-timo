package abilities;

import java.util.ArrayList;
import java.util.Random;

import core.GameInfo;
import core.Point2DNoFxReq;
import entity.Entity;
import entity.unit.Archer;
import entity.unit.Builder;
import entity.unit.Mage;
import entity.unit.Trebuchet;
import entity.unit.Unit;
import entity.unit.Warrior;

public class CreateUnit extends Ability {

	private Point2DNoFxReq point;
	private String type;
	private Random random;
	private boolean controlable;

	public CreateUnit(Point2DNoFxReq point, String type, String name, String description, boolean controlable) {
		super(name, description, Ability.ABILITY_TYPE_PRODUCE);
		super.maxRange = 0;
		this.random = new Random();
		this.controlable = controlable;
		this.type = type;
		this.point = point;
	}

	@Override
	public void applyAbility(Entity source, Entity target) {
		
		Point2DNoFxReq pointXY = new Point2DNoFxReq(point.x + random.nextFloat(), point.y + random.nextFloat());
		
		if(type.equals(Unit.UNIT_BUILDER)) {
			GameInfo.getObjectMap().getEntityMap().add(new Builder(pointXY, "Builder", 3, 1, controlable, new ArrayList<>()));
		}
		if(type.equals(Unit.UNIT_WARRIOR)) {
			GameInfo.getObjectMap().getEntityMap().add(new Warrior(pointXY, "Warrior", 3, 1, controlable, new ArrayList<>()));
		}
		if(type.equals(Unit.UNIT_MAGE)) {
			GameInfo.getObjectMap().getEntityMap().add(new Mage(pointXY, "Mage", 3, 1, controlable, new ArrayList<>()));
		}
		if(type.equals(Unit.UNIT_ARCHER)) {
			GameInfo.getObjectMap().getEntityMap().add(new Archer(pointXY, "Archer", 3, 1, controlable, new ArrayList<>()));
		}
		if(type.equals(Unit.UNIT_TREBUCHET)) {
			GameInfo.getObjectMap().getEntityMap().add(new Trebuchet(pointXY, "Trebuchet", 5, 1, controlable, new ArrayList<>()));
		}
	}

}

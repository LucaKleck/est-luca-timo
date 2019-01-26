package abilities;

import java.util.ArrayList;

import com.sun.javafx.geom.Point2D;

import core.GameInfo;
import entity.Entity;
import entity.unit.Builder;
import entity.unit.Mage;
import entity.unit.Unit;
import entity.unit.Warrior;

public class CreateUnit extends Ability {

	private Point2D pointXY;
	private String type;

	public CreateUnit(Point2D pointXY, String type, String name, String description) {
		super(name, description);
		this.type = type;
		this.pointXY = pointXY;
	}

	@Override
	public void applyAbility(Entity source, Entity target) {
		if(type == Unit.UNIT_BUILDER) {
			GameInfo.getObjectMap().getEntityMap().add(new Builder(pointXY, "Builder", 3, 1, true, new ArrayList<>()));
		}
		if(type == Unit.UNIT_WARRIOR) {
			GameInfo.getObjectMap().getEntityMap().add(new Warrior(pointXY, "Warrior", 3, 1, true, new ArrayList<>()));
		}
		if(type == Unit.UNIT_MAGE) {
			GameInfo.getObjectMap().getEntityMap().add(new Mage(pointXY, "Mage", 3, 1, true, new ArrayList<>()));
		}
	}

}

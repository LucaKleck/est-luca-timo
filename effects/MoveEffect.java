package effects;

import abilities.Move;
import entity.Entity;

public class MoveEffect extends MapImageEffect {

	public MoveEffect(Entity source, Entity target, Move ability) {
		super(source, target, ability);
		this.createGraphics().drawLine(source.getXPos(), source.getYPos(), (int) ability.getMoveToPoint().getX(), (int) ability.getMoveToPoint().getY());
	}

}

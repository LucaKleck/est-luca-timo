package abilities;

import entity.Entity;
import map.ObjectMap;

public class Move extends Ability {
	
	public Move() {
		super(Ability.ABILITY_MOVE);
	}

	@Override
	public void applyAbility(Entity source, Entity target) {
		if(source.equals(target)) {
			source.setPoint(ObjectMap.getSelected().getSelectedMapTile().getXYPoint());
		}
	}
}


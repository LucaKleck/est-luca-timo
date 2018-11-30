package effects;

import abilities.Move;
import entity.Entity;
import frame.gamePanels.MapPanel;

public class MoveEffect extends MapImageEffect {

	public MoveEffect(Entity source, Entity target, Move ability) {
		super(source, target, ability);
		this.createGraphics().drawLine((int) (source.getXPos()*MapPanel.getMapImage().getMapTileSize()), (int) (source.getYPos()*MapPanel.getMapImage().getMapTileSize()), (int) (ability.getMoveToPoint().getX()*MapPanel.getMapImage().getMapTileSize()), (int) (ability.getMoveToPoint().getY()*MapPanel.getMapImage().getMapTileSize()));
	}

}

package effects;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;

import abilities.Move;
import entity.Entity;
import frame.gamePanels.MapPanel;

public class MoveEffect extends MapImageEffect {

	public MoveEffect(Entity source, Entity target, Move ability) {
		super(source, target, ability);
		Graphics2D g = this.createGraphics();
		g.setColor(Color.BLUE);
		g.setStroke(new BasicStroke(3f));
		g.drawLine((int) (source.getXPos()*MapPanel.getMapImage().getMapTileSize()+32), (int) (source.getYPos()*MapPanel.getMapImage().getMapTileSize()+32), (int) (ability.getMoveToPoint().getX()*MapPanel.getMapImage().getMapTileSize()+32), (int) (ability.getMoveToPoint().getY()*MapPanel.getMapImage().getMapTileSize()+32));
	}

	@Override
	public String toString() {
		return "MoveEffect";
	}

}

package effects;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;

import abilities.Move;
import entity.unit.Unit;
import frame.gamePanels.MapPanel;

public class MoveEffect extends MapImageEffect {

	public MoveEffect(Unit source, Unit target, Move ability) {
		super(source, target, ability);
		Graphics2D g = this.createGraphics();
		g.setColor(Color.BLUE);
		g.setStroke(new BasicStroke(3f));
		g.drawLine( (int) (source.getPoint().x*MapPanel.getMapImage().getMapTileSize())+5, (int) (source.getPoint().y*MapPanel.getMapImage().getMapTileSize())+5, (int) (source.getMove().getMoveToPoint().x*MapPanel.getMapImage().getMapTileSize())+5, (int) (source.getMove().getMoveToPoint().y*MapPanel.getMapImage().getMapTileSize()+5));
	}

	@Override
	public String toString() {
		return "MoveEffect";
	}

}

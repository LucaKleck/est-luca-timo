package effects;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;

import abilities.Move;
import core.Core;
import entity.unit.Unit;
import frame.gamePanels.MainGamePanel;
import map.MapImage;

public class MoveEffect extends MapImageEffect {

	public MoveEffect(Unit source, Unit target, Move ability) {
		super(source, target, ability);
		MapImage mi = null;
		if(Core.getMainJFrame().getCurrentComponent() instanceof MainGamePanel) {
			mi = ((MainGamePanel)Core.getMainJFrame().getCurrentComponent()).getMapPanel().getMapImage();
		}
		if(mi != null) {
			Graphics2D g = this.createGraphics();
			g.setColor(Color.BLUE);
			g.setStroke(new BasicStroke(3f));
			g.drawLine( (int) (source.getPoint().x*mi.getMapTileSize()), (int) (source.getPoint().y*mi.getMapTileSize()), (int) (source.getMove().getMoveToPoint().x*mi.getMapTileSize()), (int) (source.getMove().getMoveToPoint().y*mi.getMapTileSize()));
		}
	}

	@Override
	public String toString() {
		return "MoveEffect";
	}

}

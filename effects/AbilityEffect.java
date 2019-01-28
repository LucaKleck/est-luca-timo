package effects;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;

import abilities.Ability;
import abilities.Build;
import abilities.FireBall;
import abilities.Melee;
import abilities.Move;
import core.Core;
import entity.unit.Builder;
import entity.unit.Unit;
import frame.gamePanels.MainGamePanel;
import map.MapImage;

public class AbilityEffect extends MapImageEffect {

	Color buildColor = new Color(139,69,19);
	
	public AbilityEffect(Unit source, Unit target, Ability ability) {
		super(source, target, ability);
		MapImage mi = null;
		if (Core.getMainJFrame().getCurrentComponent() instanceof MainGamePanel) {
			mi = ((MainGamePanel) Core.getMainJFrame().getCurrentComponent()).getMapPanel().getMapImage();
		}
		if (mi != null) {
			Graphics2D g = this.createGraphics();
			if (ability instanceof Move) {
				g.setColor(Color.BLUE);
				g.setStroke(new BasicStroke(3f));
				g.drawLine((int) (source.getPoint().x * mi.getMapTileSize()),
						(int) (source.getPoint().y * mi.getMapTileSize()),
						(int) (source.getMove().getMoveToPoint().x * mi.getMapTileSize()),
						(int) (source.getMove().getMoveToPoint().y * mi.getMapTileSize()));
			} 
			if (ability instanceof Melee) {
				g.setColor(Color.BLACK);
				g.setStroke(new BasicStroke(3f));
				g.drawLine((int) (source.getPoint().x * mi.getMapTileSize()),
						(int) (source.getPoint().y * mi.getMapTileSize()),
						(int) (target.getPoint().x * mi.getMapTileSize()),
						(int) (target.getPoint().y * mi.getMapTileSize()));
			}
			if (ability instanceof FireBall) {
				g.setColor(Color.ORANGE);
				g.setStroke(new BasicStroke(3f));
				g.drawLine((int) (source.getPoint().x * mi.getMapTileSize()),
						(int) (source.getPoint().y * mi.getMapTileSize()),
						(int) (target.getPoint().x * mi.getMapTileSize()),
						(int) (target.getPoint().y * mi.getMapTileSize()));
			}
			if (ability instanceof Build) {
				g.setColor(buildColor);
				g.setStroke(new BasicStroke(3f));
				g.drawLine((int) (source.getPoint().x * mi.getMapTileSize()),
						(int) (((Builder)source).getPoint().y * mi.getMapTileSize()),
						(int) (((Builder)source).getBuild().getBuildPoint().x * mi.getMapTileSize()),
						(int) (((Builder)source).getBuild().getBuildPoint().y * mi.getMapTileSize()));
			}
		}
	}

	@Override
	public String toString() {
		return "AbilityEffect";
	}

}

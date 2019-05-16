package effects;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;

import abilities.Ability;
import abilities.Build;
import abilities.FireBall;
import abilities.MeleeAttack;
import abilities.Move;
import abilities.RangedAttack;
import abilities.SiegeAttack;
import core.Core;
import core.MapImage;
import entity.Entity;
import entity.unit.Builder;
import entity.unit.Unit;
import frame.gamePanels.MainGamePanel;

public class AbilityEffect extends MapImageEffect {

	Color buildColor = new Color(139, 69, 19);
	
	public AbilityEffect(Entity source, Entity target, Ability ability) {
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
						(int) (((Unit)source).getMove().getMoveToPoint().x * mi.getMapTileSize()),
						(int) (((Unit)source).getMove().getMoveToPoint().y * mi.getMapTileSize()));
			} 
			if (ability instanceof MeleeAttack) {
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
			if (ability instanceof RangedAttack) {
				g.setColor(Color.RED);
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
						(int) (((Builder)source).getBuildPoint().x * mi.getMapTileSize()),
						(int) (((Builder)source).getBuildPoint().y * mi.getMapTileSize()));
			}
			if (ability instanceof SiegeAttack) {
				g.setColor(Color.YELLOW);
				g.setStroke(new BasicStroke(3f));
				g.drawLine((int) (source.getPoint().x * mi.getMapTileSize()),
						(int) (source.getPoint().y * mi.getMapTileSize()),
						(int) (target.getPoint().x * mi.getMapTileSize()),
						(int) (target.getPoint().y * mi.getMapTileSize()));
			}
		}
	}

	@Override
	public String toString() {
		return "AbilityEffect";
	}

}

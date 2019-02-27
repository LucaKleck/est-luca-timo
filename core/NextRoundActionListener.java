package core;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.swing.JButton;

import com.sun.javafx.geom.Point2D;

import abilities.Ability;
import abilities.CollectResources;
import abilities.Move;
import effects.AbilityEffect;
import entity.Entity;
import entity.EntityFilter;
import entity.building.Building;
import entity.building.ProductionBuilding;
import entity.unit.Builder;
import entity.unit.Unit;
import frame.gamePanels.MainGamePanel;

public class NextRoundActionListener implements ActionListener, Runnable {
	private static final ExecutorService EXS = Executors.newFixedThreadPool(1); 
	private JButton j; 
	private EntityFilter entityFilter;
	
	public NextRoundActionListener() {
		entityFilter = new EntityFilter();
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		j = ((JButton) (e.getSource()));
		j.setEnabled(false);
		
		// TODO Collect events that are passive (Building etc.)
		// TODO Collect events from enemies, they should happen before the passive ones and after the player created events
		// TODO make it so player can't use selected.clicked(x,y) while this is going down
		EXS.execute(this);
	}
	@Override
	public void run() {
		
		ArrayList<Entity> entityMap = GameInfo.getObjectMap().getEntityMap();
		
		GameInfo.getObjectMap().getSelected().removeSelected();
		System.out.println("-----------------");
		System.out.println("Round x start");
		// player events
		goThroughEventList();
		// TODO add AI logic here and then add to event list
		ArrayList<Entity> enemyEntities = new ArrayList<Entity>();
		for(Entity entity: entityMap) {
			if(entity.isControlable() == false) {
				enemyEntities.add(entity);
			}
		}
		
		for(Entity entity: enemyEntities) {
			
			System.out.println("Test");
			Entity bestTarget = entityFilter.getBestEntityTarget(entity);
			Ability ability = null;
			if(entity instanceof Unit) {
				if(bestTarget != null && bestTarget instanceof Builder == false) {
					ability = entityFilter.getRandomAbility(entity);
					entity.setEvent(new Event(entity, entityFilter.getBestEntityTarget(entity), ability, new AbilityEffect(entity, bestTarget, ability)));
				} else if(entity instanceof Builder) {
					ability = entityFilter.getRandomAbility(entity);
					((Builder)entity).setBuildPoint(entityFilter.getRandomBuildPoint((Builder) entity)); 
					entity.setEvent(new Event(entity, entity, ability, new AbilityEffect(entity, bestTarget, ability)));
				} else {
					ability = new Move();
					((Move)ability).setMoveToPoint(new Point2D(10, 10));
					entity.setEvent(new Event(entity, entity, ability, new AbilityEffect(entity, entity, ability)));
				}
			} else if (entity instanceof ProductionBuilding) {
				ability = entityFilter.getRandomAbility(entity);
				entity.setEvent(new Event(entity, entity, ability, null));
			}
			
			
		}
		
		// Collect resources after player and AI moved so that buildings that were destroyed don't give resources
		for(Entity b : entityMap) {
			if(b instanceof Building) {
				for(Ability ab : b.getAbilities()) {
					if(ab instanceof CollectResources) {
						if(GameInfo.getRoundInfo().getNewBuildings().contains(b)) break;
						if(b.getEvent() == null) b.setEvent(new Event(b, b, ab, null));
					}
				}
			}
		}
		GameInfo.getRoundInfo().getNewBuildings().clear();
		if(Core.getMainJFrame().getCurrentComponent() instanceof MainGamePanel) {
			((MainGamePanel) Core.getMainJFrame().getCurrentComponent()).updateUI();
			((MainGamePanel) Core.getMainJFrame().getCurrentComponent()).getMapPanel().getMapImage().update();
		}
		goThroughEventList();
		
		System.out.println("Round x end");
		System.out.println("-----------------");
		// Update stuff
		if(Core.getMainJFrame().getCurrentComponent() instanceof MainGamePanel) {
			((MainGamePanel) Core.getMainJFrame().getCurrentComponent()).updateUI();
			((MainGamePanel) Core.getMainJFrame().getCurrentComponent()).getMapPanel().getMapImage().update();
		}
		j.setEnabled(true);
	}
	
	private void goThroughEventList() {
		for (Iterator<Event> iterator = GameInfo.getRoundInfo().getEventList().iterator(); iterator.hasNext();) {
			Event e = iterator.next();
			System.out.println(e);
			e.run();
			// due to concurrent modification issues iterator is used to remove the current item from the list and a helper method (removeEventWithoutRemovingFromList) is used to remove Events as intended
			e.getSource().removeEventWithoutRemovingFromList();
			iterator.remove();
			System.gc();
			try {
				Thread.sleep(500);
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
		}
	}
}

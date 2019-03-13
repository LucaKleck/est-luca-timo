package core;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.swing.JButton;

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
import entity.unit.UnitFactory;
import frame.gamePanels.MainGamePanel;

public class NextRoundActionListener implements ActionListener, Runnable {
	private static final ExecutorService EXS = Executors.newFixedThreadPool(1); 
	private JButton j; 
	private EntityFilter entityFilter;
	private int wave = 0;
	private int waveCounter = 0;
	private boolean newWave = true;
	private UnitFactory uf;
	
	public NextRoundActionListener() {
		entityFilter = new EntityFilter();
		uf = new UnitFactory();
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		j = ((JButton) (e.getSource()));
		j.setEnabled(false);
		
		// TODO Collect events that are passive (Building etc.)
		// TODO Collect events from enemies, they should happen before the passive ones and after the player created events
		// TODO make it so player can't use selected.clicked(x,y) while this is going down
		EXS.execute(this);
		waveCounter++;
		if(waveCounter >= 3) {
			wave++;
			waveCounter = 0;
			newWave = true;
		} else {
			newWave = false;
		}
	}
	@Override
	public void run() {
		
		ArrayList<Entity> entityMap = GameInfo.getObjectMap().getEntityMap();
		
		GameInfo.getObjectMap().getSelected().removeSelected();
		System.out.println("-----------------");
		System.out.println("Round x start");
		// player events
		goThroughEventList();
		ArrayList<Entity> enemyEntities = new ArrayList<Entity>();
		for(Entity entity: entityMap) {
			if(entity.isControlable() == false) {
				enemyEntities.add(entity);
			}
		}
		
		for(Entity entity: enemyEntities) {
			
			Ability ability = entityFilter.getRandomAbility(entity);
			if(entity instanceof Unit) {
				Entity bestTarget = entityFilter.getBestEntityTarget(entity);
				ability = entityFilter.getBestAbility(ability.getRangeToTarget(entity, bestTarget), entity);					
				if(ability != null && bestTarget != null && entity instanceof Builder == false && ability.rangeCheckEntity(entity, bestTarget)) {
					entity.setEvent(new Event(entity, bestTarget, ability, new AbilityEffect(entity, bestTarget, ability)));
				} else if(entity instanceof Builder) {
					ability = entityFilter.getRandomAbility(entity);
					((Builder)entity).setBuildPoint(entityFilter.getRandomBuildPoint((Builder) entity)); 
					entity.setEvent(new Event(entity, entity, ability, new AbilityEffect(entity, bestTarget, ability)));
				} else {
					for(Ability abl: entity.getAbilities()) {
						if(abl instanceof Move) {
							ability = abl;
						}
					}
					if(ability != null) {
						if(entity.getXPos() > 24) {
							((Move)ability).setMoveToPoint(new Point2DNoFxReq(entity.getXPos() - ability.maxRange, entity.getYPos()));
						} else if(entity.getXPos() <= 24) {
							((Move)ability).setMoveToPoint(new Point2DNoFxReq(entity.getXPos() + ability.maxRange, entity.getYPos()));
						}
						entity.setEvent(new Event(entity, entity, ability, new AbilityEffect(entity, entity, ability)));
					}
					
				}
			} else if (entity instanceof ProductionBuilding) {
				if(entity.getName().equals(Building.PORTAL)) {
					if(newWave) {
						switch(wave) {
						
							case 1: entityMap.add(uf.getNewPortalUnitByType(Unit.UNIT_ARCHER)); break;
							case 2: entityMap.add(uf.getNewPortalUnitByType(Unit.UNIT_TREBUCHET)); break;
							case 3: entityMap.add(uf.getNewPortalUnitByType(Unit.UNIT_MAGE)); break;
							case 4: entityMap.add(uf.getNewPortalUnitByType(Unit.UNIT_BUILDER)); break;
							case 5: entityMap.add(uf.getNewPortalUnitByType(Unit.UNIT_WARRIOR)); break;
							default: break;
						
						}
					}
				} else {
					ability = entityFilter.getRandomAbility(entity);
					entity.setEvent(new Event(entity, entity, ability, null));
				}
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
		try {
			Thread.sleep(500);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
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
			if(e.getAbility() instanceof CollectResources == false) {
				if(Core.getMainJFrame().getCurrentComponent() instanceof MainGamePanel) {
					MainGamePanel mp = (MainGamePanel) Core.getMainJFrame().getCurrentComponent();
					mp.getMapPanel().setPosition(e.getSource().getXPos(), e.getSource().getYPos());
				}
			}
			System.out.println(e);
			try {
				Thread.sleep(500);
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
			e.run();
			// due to concurrent modification issues iterator is used to remove the current item from the list and a helper method (removeEventWithoutRemovingFromList) is used to remove Events as intended
			e.getSource().removeEventWithoutRemovingFromList();
			iterator.remove();
			System.gc();
			if(Core.getMainJFrame().getCurrentComponent() instanceof MainGamePanel) {
				MainGamePanel mp = (MainGamePanel) Core.getMainJFrame().getCurrentComponent();
				mp.updateUI();
				mp.getMapPanel().getMapImage().update();
			}
			try {
				Thread.sleep(750);
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
			
		}
	}
	
}

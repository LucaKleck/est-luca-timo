package core;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.swing.JButton;

import abilities.Ability;
import abilities.CollectResources;
import entity.Entity;
import entity.building.Building;
import frame.gamePanels.MainGamePanel;

public class NextRoundActionListener implements ActionListener, Runnable {
	private static final ExecutorService EXS = Executors.newFixedThreadPool(1); 
	private JButton j; 
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
		GameInfo.getObjectMap().getSelected().removeSelected();
		System.out.println("-----------------");
		System.out.println("Round x start");
		// player events
		goThroughEventList();
		// TODO add AI logic here and then add to event list
		
		// Collect resources after player and AI moved so that buildings that were destroyed don't give resources
		for(Entity b : GameInfo.getObjectMap().getEntityMap()) {
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
		}
	}
}

package core;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import frame.gamePanels.MapPanel;
import map.ObjectMap;

public class NextRoundActionListener implements ActionListener {
	private static final ExecutorService EXS = Executors.newFixedThreadPool(1); 
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Collect events that are passive (Building etc.)
		// TODO Collect events from enemies, they should happen before the passive ones and after the player created events
		// Execute every event in a separate thread
		// TODO fix the error that appears (synchronize it!)
		for(Event event : GameInfo.getRoundInfo().getEventList()) {
			System.out.println("----------");
			System.out.println(event);
			System.out.println("----------");
			EXS.submit(event);
		}
		ObjectMap.getSelected().removeSelected();
		if(GameInfo.getRoundInfo().getEventList().isEmpty()) {
			MapPanel.getMapImage().redrawUpperLayers(); // TODO find out why this fires so early (make it wait for event to finish)
		} else {
			System.out.println("Sas");
		}
	}

}

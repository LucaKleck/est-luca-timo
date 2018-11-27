package core;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class NextRoundActionListener implements ActionListener {
	private static final ExecutorService EXS = Executors.newFixedThreadPool(1); 
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Collect events that are passive (Building etc.)
		// TODO Collect events from enemies, they should happen before the passive ones and after the player created events
		// Execute every event in a separate thread
		// TODO fix the error that appears (synchronize it!)
		for(Thread event : GameInfo.getEventQueue()) {
			System.out.println("----------");
			System.out.println(GameInfo.getEventQueue());
			System.out.println("----------");
			EXS.execute(event);
			return;
		}
		
	}

}

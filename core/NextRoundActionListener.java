package core;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.swing.JButton;

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
		for (Iterator<Event> iterator = GameInfo.getRoundInfo().getEventList().iterator(); iterator.hasNext();) {
			Event e = iterator.next();
			System.out.println(e);
			e.run();
			e.getSource().removeEvent();
			iterator.remove();
			System.gc();
		}
		if(Core.getMainJFrame().getCurrentComponent() instanceof MainGamePanel) {
			((MainGamePanel) Core.getMainJFrame().getCurrentComponent()).getMapPanel().getMapImage().update();
		}
		j.setEnabled(true);
	}

}

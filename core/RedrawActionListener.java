package core;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import frame.gamePanels.MapPanel;

public class RedrawActionListener implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
		MapPanel.getMapImage().redraw();
	}

}

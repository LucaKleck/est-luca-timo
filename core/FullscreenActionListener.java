package core;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

public class FullscreenActionListener implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
		Core.getMainJFrame().dispose();
		// put it in the left uppermost corner
		Core.getMainJFrame().setUndecorated(!Core.getMainJFrame().isUndecorated());
		Core.getMainJFrame().setBounds(0, 0, 0, 0);
		if (Core.getMainJFrame().isUndecorated()) {
			Core.getMainJFrame().setExtendedState(JFrame.MAXIMIZED_BOTH);
		} else {
			Core.getMainJFrame().setExtendedState(JFrame.NORMAL);
		}
		Core.getMainJFrame().pack();
		if (!Core.getMainJFrame().isUndecorated()) {
			Core.getMainJFrame().setSize(Integer.parseInt(Core.getSetting(Core.SETTING_DEFAULT_WIDTH)), Integer.parseInt(Core.getSetting(Core.SETTING_DEFAULT_HEIGHT)));
		}
		Core.getMainJFrame().validate();

		Core.getMainJFrame().setVisible(true);
		
		Core.saveSetting(Core.SETTING_FULLSCREEN, new Boolean(Core.getMainJFrame().isUndecorated()).toString() );
	}

}

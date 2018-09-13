package core;

import frame.MainJFrame;

public class CoreController {

	public static MainJFrame mainJFrame;
	
	public CoreController() {
		CoreController.mainJFrame = new MainJFrame();
		// add CommandHandler
	}

}

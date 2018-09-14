package core;

import frame.MainJFrame;

public class CoreController {

	public static MainJFrame mainJFrame;
	public static ControlInput controlInput;
	
	public CoreController() {
		CoreController.controlInput = new ControlInput();
		CoreController.mainJFrame = new MainJFrame();
		// add CommandHandler
		
		
	}

}
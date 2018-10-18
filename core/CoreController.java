package core;

import java.util.Set;

import frame.MainJFrame;

/**  
* This holds all the main components that make up the game
* @author Luca Kleck
*/
public class CoreController {

	public static MainJFrame mainJFrame;
	public static ControlInput controlInput;
	
	public CoreController() {
		CoreController.controlInput = new ControlInput();
		CoreController.mainJFrame = new MainJFrame();
		// add CommandHandler
//		Set<Thread> threadSet = Thread.getAllStackTraces().keySet();
		
//		System.out.println(threadSet.toString());
	}

}
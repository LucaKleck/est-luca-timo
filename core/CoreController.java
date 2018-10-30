package core;

import java.io.File;

import frame.MainJFrame;

/**
 * This holds all the main components that make up the game
 * 
 * @author Luca Kleck
 */
public class CoreController {

	public static final String GAME_PATH = System.getProperty("user.home") + File.separator + "Documents" + File.separator + "EST-SCHULPROJEKT";
	public static final String GAME_PATH_SAVES = GAME_PATH + File.separator + "saves";
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
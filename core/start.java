package core;

import java.io.File;

import frame.MainJFrame;

/**
 * This holds all the main components that make up the game
 * 
 * @author Luca Kleck
 */
public class start {

	public static final String GAME_PATH = System.getProperty("user.home") + File.separator + "Documents" + File.separator + "EST-SCHULPROJEKT";
	public static final String GAME_PATH_SAVES = GAME_PATH + File.separator + "saves";
	private static MainJFrame mainJFrame;
	private static ControlInput controlInput;
	
	public start() {
		start.controlInput = new ControlInput();
		start.mainJFrame = new MainJFrame();
//		Set<Thread> threadSet = Thread.getAllStackTraces().keySet();

//		System.out.println(threadSet.toString());
	}

	public static MainJFrame getMainJFrame() {
		return mainJFrame;
	}

	public static ControlInput getControlInput() {
		return controlInput;
	}

}
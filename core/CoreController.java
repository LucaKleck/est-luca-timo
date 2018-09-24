/**  
* CoreController.java
* @author Luca Kleck
* @version 0.01
* @since 0.01
*/
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
/**  
* ControllerInput.java - Here all the events for mouse/keyboard input will be handled
* @author Luca Kleck
* @version 0.01 
* @since 0.01
* @see CoreController
*/
package core;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControlInput {
	public static ActionListener menuChanger = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent evt) {
				try {
					CoreController.mainJFrame.getContentPane().removeAll();
					CoreController.mainJFrame.getContentPane().add((Component) ClassLoader.getSystemClassLoader().loadClass(evt.getActionCommand()).newInstance());
				} catch (InstantiationException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
		}
	
	};
	public static ActionListener tileClicked = new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			// Maybe change to mouse event then calc reverse math!
		}
	};
	
	public ControlInput() {
		
	}

}
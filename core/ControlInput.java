/**  
* ControllerInput.java - Here all the events for mouse/keyboard input will be handled
* @author Luca Kleck
* @version 0.01 
* @since 0.01
* @see CoreController
*/
package core;

import java.awt.Component;
import java.awt.KeyEventDispatcher;
import java.awt.KeyboardFocusManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.List;

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
	
	private class KeyDispatcher implements KeyEventDispatcher {
		boolean[] keyPressedList = new boolean[128];
        @Override
        public boolean dispatchKeyEvent(KeyEvent e) {
            if (e.getID() == KeyEvent.KEY_PRESSED) {
            	System.out.println(e.getKeyCode()+" key:"+e.getKeyChar());
                
            } else if (e.getID() == KeyEvent.KEY_RELEASED) {
            	
            } else if (e.getID() == KeyEvent.KEY_TYPED) {
                
            }
            return false;
        }
    }
	public ControlInput() {
		
		KeyDispatcher keyDispatcher = new KeyDispatcher();
		KeyboardFocusManager kfm = KeyboardFocusManager.getCurrentKeyboardFocusManager();
		kfm.addKeyEventDispatcher(keyDispatcher);
		System.out.println(keyDispatcher.toString());
	}

}
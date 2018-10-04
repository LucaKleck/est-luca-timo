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
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

import frame.gamePanels.MapPanel;

public class ControlInput {
	
	public static MouseWheelListener MWHL = new MouseWheelListener() {
		@Override
		public void mouseWheelMoved(MouseWheelEvent evt) {
			if(evt.getWheelRotation() < 0) {
				MapPanel.addDisplacementMultiplier(-0.1);
			}
			if(evt.getWheelRotation() > 0) {
				MapPanel.addDisplacementMultiplier(0.1);
			}
		}
	};
	
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
//		boolean[] keyPressedList = new boolean[128];
        @Override
        public boolean dispatchKeyEvent(KeyEvent e) {
            if (e.getID() == KeyEvent.KEY_PRESSED) {
            	System.out.println(e.getKeyCode()+" key on:"+e.getKeyChar());
            	// Up arrow = 38
            	if(e.getKeyCode() == 38) {
	        		try {
	        			MapPanel.addDisplacementY(5);
	        		} catch (NullPointerException exeption) {
	        		}
	        	}
            	// Down arrow = 40
            	if(e.getKeyCode() == 40) {
            		try {
            			MapPanel.addDisplacementY(-5);
            		} catch (NullPointerException exeption1) {
            		}
            	}
            	// Right arrow = 39
            	if(e.getKeyCode() == 39) {
	        		try {
	        			MapPanel.addDisplacementX(-5);
	        		} catch (NullPointerException exeption2) {
	        		}
	        	}
            	// Left arrow = 37
            	if(e.getKeyCode() == 37) {
	        		try {
	        			MapPanel.addDisplacementX(5);
	        		} catch (NullPointerException exeption3) {
	        		}
	        	}
                
            } else if (e.getID() == KeyEvent.KEY_RELEASED) {
            	System.out.println(e.getKeyCode()+" key off:"+e.getKeyChar());            	
            } else if (e.getID() == KeyEvent.KEY_TYPED) {
                
            }
            return false;
        }
    }
	public ControlInput() {
		KeyDispatcher keyDispatcher = new KeyDispatcher();
		KeyboardFocusManager kfm = KeyboardFocusManager.getCurrentKeyboardFocusManager();
		kfm.addKeyEventDispatcher(keyDispatcher);
	}

}
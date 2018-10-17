package core;

import java.awt.Component;
import java.awt.KeyEventDispatcher;
import java.awt.KeyboardFocusManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.util.ArrayList;

import frame.gamePanels.MapPanel;

/**  
* Contains all the events for mouse/keyboard input.
* @author Luca Kleck
* @see CoreController
*/
public class ControlInput {
	/**
	 * Listens to the mouse wheel and changes the MapImage size multiplier
	 * @author Luca Kleck
	 */
	public static MouseWheelListener mouseWheeListener = new MouseWheelListener() {
		
		@Override
		public void mouseWheelMoved(MouseWheelEvent evt) {
			if(evt.getWheelRotation() < 0) {
				MapPanel.addDisplacementMultiplier(0.1);
			}
			if(evt.getWheelRotation() > 0) {
				MapPanel.addDisplacementMultiplier(-0.1);
			}
		}
		
	};
	/**
	 * This ActionListener uses a ClassLoader to instantiate and then add a JPanel to a JFrame.
	 * @author Luca Kleck
	 */
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
	
	public ControlInput() {
		KeyInputDispatcher keyInputDispatcher = new KeyInputDispatcher();
		KeyboardFocusManager kfm = KeyboardFocusManager.getCurrentKeyboardFocusManager();
		kfm.addKeyEventDispatcher(keyInputDispatcher);
	}
	
	private class KeyInputDispatcher implements KeyEventDispatcher {
		ArrayList<Integer> keyCodeList = new ArrayList<>();
        @Override
        public boolean dispatchKeyEvent(KeyEvent e) {
            if (e.getID() == KeyEvent.KEY_PRESSED) {
            	if(!keyCodeList.contains(e.getKeyCode())) {
            		keyCodeList.add(e.getKeyCode());
            	}
            	System.out.println("pressed: "+keyCodeList.toString());
            	for(int i = 0; i < keyCodeList.size(); i++) {
                	// Up arrow = 38
            		if(keyCodeList.get(i) == 38) {
    	        		try {
    	        			MapPanel.addDisplacementY(5);
    	        		} catch (NullPointerException exeption) {
    	        		}
    	        	}
                	// Down arrow = 40
                	if(keyCodeList.get(i) == 40) {
                		try {
                			MapPanel.addDisplacementY(-5);
                		} catch (NullPointerException exeption1) {
                		}
                	}
                	// Right arrow = 39
                	if(keyCodeList.get(i) == 39) {
    	        		try {
    	        			MapPanel.addDisplacementX(-5);
    	        		} catch (NullPointerException exeption2) {
    	        		}
    	        	}
                	//Left arrow
                	if(keyCodeList.get(i) == 37 ) {
    	        		try {
    	        			MapPanel.addDisplacementX(5);
    	        		} catch (NullPointerException exeption3) {
    	        		}
                	}
    	        	// Backspace
                	if(keyCodeList.get(i) == KeyEvent.VK_BACK_SPACE) {
                		try {
                			MapPanel.reset();
                		} catch (NullPointerException exeption4) {
                		}
                	}
            	}
            		
            } else if (e.getID() == KeyEvent.KEY_RELEASED) {
            	System.out.println("------------");
            	System.out.println("released"+keyCodeList.toString());
            	for(int i = 0; i < keyCodeList.size(); i++) {
            		keyCodeList.remove(extractKeyCode(e, i));
            	}
            }
            return false;
        }
		private Integer extractKeyCode(KeyEvent e, int i) {
			if(keyCodeList.get(i).intValue() == e.getKeyCode()) {
				return keyCodeList.get(i);
			}
			else {
				return null;
			}
		}
	}
}
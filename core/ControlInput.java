package core;

import java.awt.Component;
import java.awt.KeyEventDispatcher;
import java.awt.KeyboardFocusManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.util.ArrayList;

import javax.swing.Timer;

import frame.gamePanels.InteractionPanel;
import frame.gamePanels.MapPanel;

/**  
* Contains all the events for mouse/keyboard input.
* @author Luca Kleck
* @see CoreController
*/
public class ControlInput {
	
	public ControlInput() {
		KeyInputDispatcher keyInputDispatcher = new KeyInputDispatcher();
		KeyboardFocusManager kfm = KeyboardFocusManager.getCurrentKeyboardFocusManager();
		kfm.addKeyEventDispatcher(keyInputDispatcher);
	}
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
	
	public static MouseListener mouseListener = new MouseListener() {
		
		@Override
		public void mouseReleased(MouseEvent e) {
			//right mouse = 3
			if(e.getButton() == 3) {
				if(InteractionPanel.getSelectionPane() == null) {
				} else {
					InteractionPanel.setSelectionPane(null);
				}
			}
		}
		
		@Override
		public void mousePressed(MouseEvent e) {
			
		}
		
		@Override
		public void mouseExited(MouseEvent e) {
			
		}
		
		@Override
		public void mouseEntered(MouseEvent e) {
			
		}
		
		@Override
		public void mouseClicked(MouseEvent e) {
			
		}
	};
	/**
	 * This inner class handles Keyboard Inputs
	 * @author Luca Kleck
	 */
	private class KeyInputDispatcher implements KeyEventDispatcher {
		
		ArrayList<Integer> keyCodeList = new ArrayList<>();
		
		private KeyChecker keyChecker = new KeyChecker();
		private boolean shiftPressed = false;
		
		
        @Override
        public boolean dispatchKeyEvent(KeyEvent e) {
        	if (e.getID() == KeyEvent.KEY_PRESSED) {
        		keyChecker.run();
            	if(!keyCodeList.contains(e.getKeyCode())) {
            		keyCodeList.add(e.getKeyCode());
            	}
            } else if (e.getID() == KeyEvent.KEY_RELEASED) {
            	
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

		private class KeyChecker implements Runnable {
			
			Timer timer = new Timer(5, new KeyInputHandler());
			
			@Override
			public void run() {
				if(!timer.isRunning()) {
					timer.setRepeats(true);
					timer.start();	
				}
			}
			
			private class KeyInputHandler implements ActionListener {

				@Override
				public void actionPerformed(ActionEvent e) {
					shiftPressed = checkForShift();
					for(int i = 0; i < keyCodeList.size(); i++) {
//						System.out.println(keyCodeList.toString());
						checkForUp(i);
	                	checkForDown(i);
	                	checkForRight(i);
	                	checkForLeft(i);
	    	        	checkForBackspace(i);
	            	}
					// action end
				}

				private boolean checkForShift() {
					boolean localShiftPressed = false;
					for(int i = 0; i < keyCodeList.size(); i++) {
						if(keyCodeList.get(i) == 16) {
							localShiftPressed = true;
						}
					}
					return localShiftPressed;
				}
			}

			public void checkForUp(int i) {
				// Up arrow = 38 && w = 87
        		if(keyCodeList.get(i) == 38 || keyCodeList.get(i) == 87) {
	        		try {
	        			if(shiftPressed) {
	        				MapPanel.addDisplacementY(3);
	        			} else {
	        				MapPanel.addDisplacementY(1);
	        			}
	        		} catch (NullPointerException exeption) {
	        		}
	        	}				
			}

			public void checkForBackspace(int i) {
				// Backspace
            	if(keyCodeList.get(i) == KeyEvent.VK_BACK_SPACE) {
            		try {
            			MapPanel.reset();
            		} catch (NullPointerException exeption4) {
            		}
            	}				
			}

			public void checkForRight(int i) {
				// Right arrow = 39 | 68 = d
            	if(keyCodeList.get(i) == 39 || keyCodeList.get(i) == 68) {
	        		try {
	        			if(shiftPressed) {
	        				MapPanel.addDisplacementX(-3);
	        			} else {
	        				MapPanel.addDisplacementX(-1);
	        			}
	        		} catch (NullPointerException exeption2) {
	        		}
	        	}				
			}

			public void checkForDown(int i) {
				// Down arrow = 40 | 83 = s
            	if(keyCodeList.get(i) == 40 || keyCodeList.get(i) == 83) {
            		try {
            			if(shiftPressed) {
            				MapPanel.addDisplacementY(-3);
	        			} else {
	        				MapPanel.addDisplacementY(-1);
	        			}
            		} catch (NullPointerException exeption1) {
            		}
            	}				
			}

			public void checkForLeft(int i) {
				//Left arrow = 37 | 65 = a
            	if(keyCodeList.get(i) == 37 || keyCodeList.get(i) == 65) {
	        		try {
	        			if(shiftPressed) {
	        				MapPanel.addDisplacementX(3);
	        			} else {
	        				MapPanel.addDisplacementX(1);
	        			}
	        		} catch (NullPointerException exeption3) {
	        		}
            	}				
			}
		}
	// inner Class end
	}
}
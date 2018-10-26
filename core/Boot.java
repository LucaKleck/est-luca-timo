package core;

import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.UnsupportedLookAndFeelException;

/**
 * Starts the game
 * 
 * @author Luca Kleck
 * @version 0.02
 */
public class Boot {

	public static void main(String[] args) {
		/*try {
			for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
					UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (Exception e) {
			// If Nimbus is not available, fall back to cross-platform
			try {
				UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
			} catch (UnsupportedLookAndFeelException ex) {
			} catch (ClassNotFoundException ex) {
			} catch (InstantiationException ex) {
			} catch (IllegalAccessException ex) {
			}
		}
*/
		new CoreController();
	}

}
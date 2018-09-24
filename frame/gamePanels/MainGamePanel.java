/**  
* MainGamePanel.java
* @author Luca Kleck
* @version 0.01
* @since 0.01
* @see MainJFrame
*/
package frame.gamePanels;

import javax.swing.JPanel;

import frame.MainJFrame;
import net.miginfocom.swing.MigLayout;

@SuppressWarnings("unused")
public class MainGamePanel extends JPanel {
	private static final long serialVersionUID = 120L;

	public MainGamePanel() {
		setLayout(new MigLayout("insets 0 0 0 0, gap 0px 0px", "[100%]", "[25px:n,fill][100%]"));
		
		GameMenuPanel menuPanel = new GameMenuPanel();
		add(menuPanel, "cell 0 0,grow");
		
		MapPanel mapPanel = new MapPanel();
		add(mapPanel, "cell 0 1,grow");
	}

}

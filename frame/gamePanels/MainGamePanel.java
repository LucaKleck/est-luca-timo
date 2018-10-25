package frame.gamePanels;

import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

import frame.MainJFrame;
import net.miginfocom.swing.MigLayout;

/**  
* Contains all JPanels that are used for the game
* @author Luca Kleck 
* @see MainJFrame
*/
public class MainGamePanel extends JLayeredPane {
	private static final long serialVersionUID = 120L;
	private static LogPanel logPanel = new LogPanel();
	
	public MainGamePanel() {
		setLayout(new MigLayout("insets 0 0 0 0, gap 0px 0px", "[75%,grow][25%]", "[25px:n,fill][75%][20%,fill][]"));
		this.setDoubleBuffered(true);
		
		GameMenuPanel menuPanel = new GameMenuPanel();
		setLayer(menuPanel, 3);
		add(menuPanel, "cell 0 0 2 1,grow");
		
		MapPanel mapPanel = new MapPanel();
		setLayer(mapPanel, 0);
		add(mapPanel, "cell 0 1 1 2,grow");
		
		JPanel logBackgroundPanel = new JPanel();
		logBackgroundPanel.setBackground(new Color(0,0,0,120));
		setLayer(logBackgroundPanel, 1);
		
		setLayer(logPanel, 2);
		add(logPanel, "cell 0 2,grow");
		
		InfoPanel infoPanel = new InfoPanel();
		setLayer(infoPanel, 2);
		add(infoPanel, "cell 1 2,grow");
		
		AbilityPanel abilityPanel = new AbilityPanel();
		setLayer(abilityPanel, 2);
		add(abilityPanel, "flowx,cell 0 3,grow");
		
		JButton btnTest = new JButton("Test");
		abilityPanel.add(btnTest, "cell 0 0");
		
		InteractionPanel interactionPanel = new InteractionPanel();
		setLayer(interactionPanel, 2);
		add(interactionPanel, "cell 1 1, grow");
		
	}
	
	public static LogPanel getLogPanel() {
		return logPanel;
	}
}

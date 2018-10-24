package frame.gamePanels;

import javax.swing.JPanel;

import core.ControlInput;
import frame.MainJFrame;
import map.ObjectMap;
import net.miginfocom.swing.MigLayout;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

/**  
* Contains all JPanels that are used for the game
* @author Luca Kleck 
* @see MainJFrame
*/
public class MainGamePanel extends JPanel {
	private static final long serialVersionUID = 120L;
	private static LogPanel infoPanel = new LogPanel();
	
	public MainGamePanel() {
		setLayout(new MigLayout("insets 0 0 0 0, gap 0px 0px", "[75%][25%]", "[25px:n,fill][75%][25%]"));
		
		GameMenuPanel menuPanel = new GameMenuPanel();
		add(menuPanel, "cell 0 0 2 1,grow");
		
		MapPanel mapPanel = new MapPanel();
		add(mapPanel, "cell 0 1,grow");
		
		add(infoPanel, "cell 0 2,grow");
		
		InteractionPanel interactionPanel = new InteractionPanel();
		add(interactionPanel, "cell 1 1, grow");
		
		JButton btnRemakeMap = new JButton("Remake Map");
		interactionPanel.add(btnRemakeMap);
		btnRemakeMap.setActionCommand("frame.gamePanels.MainGamePanel");
		btnRemakeMap.addActionListener(ControlInput.menuChanger);
		btnRemakeMap.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				@SuppressWarnings("unused")
				ObjectMap objectMap = new ObjectMap();
			}
		});
		InfoPanel infoPanel = new InfoPanel();
		add(infoPanel, "cell 1 2, grow");
	}
	
	public static LogPanel getInfoPanel() {
		return infoPanel;
	}
}

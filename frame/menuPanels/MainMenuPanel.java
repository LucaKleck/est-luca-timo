package frame.menuPanels;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import core.actions.ControlInput;
import frame.customPresets.JButton_01;
import frame.customPresets.JPanelCustomBg;
import frame.graphics.ResourceManager;
import net.miginfocom.swing.MigLayout;

/**
 * Here the user can go to the different menus
 * 
 * @author Luca Kleck
 * @see frame.MainJFrame
 */
public class MainMenuPanel extends JPanelCustomBg {
	private static final long serialVersionUID = 111L;

	public MainMenuPanel() {
		super(ResourceManager.getBackground_05());
		setLayout(new MigLayout("insets 0 0 0 0", "[33%][33%][33%]", "[100%,fill]"));
		
		JPanelCustomBg container = new JPanelCustomBg(ResourceManager.getBackground_03(), true);
		container.setLayout(new MigLayout("insets 0 0 0 0", "[10%][80%][10%]", "[30%][6%]"));
		
		add(container, "cell 1 0,grow");
		
		JButton btnNewGame = new JButton_01("New Game");
		btnNewGame.setActionCommand("frame.menuPanels.GameSettingsPanel");
		btnNewGame.addActionListener(ControlInput.menuChanger);
		container.add(btnNewGame, "cell 1 1,grow");

		JButton btnLoadGame = new JButton_01("Load Game");
		btnLoadGame.setActionCommand("frame.menuPanels.LoadMenuPanel");
		btnLoadGame.addActionListener(ControlInput.menuChanger);
		container.add(btnLoadGame, "cell 1 2,grow");

		JButton btnOptions = new JButton_01("Options");
		btnOptions.setActionCommand("frame.menuPanels.OptionMenuPanel");
		btnOptions.addActionListener(ControlInput.menuChanger);
		container.add(btnOptions, "cell 1 3,grow");
		
		JButton btnExit = new JButton_01("Exit");
		btnExit.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
			
		});
		container.add(btnExit, "cell 1 4,grow");

	}

}
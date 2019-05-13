package frame.menuPanels;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import core.ControlInput;
import frame.JButton_01;
import frame.JPanelBg;
import net.miginfocom.swing.MigLayout;

/**
 * Here the user can go to the different menus
 * 
 * @author Luca Kleck
 * @see frame.MainJFrame
 */
public class MainMenuPanel extends JPanelBg {
	private static final long serialVersionUID = 111L;

	public MainMenuPanel() {
		setLayout(new MigLayout("", "[33%][33%,center][33%]", "[20%][:10%:10%][:10%:10%][:10%:10%][:10%:10%][:40%:40%]"));

		JButton btnNewGame = new JButton_01("New Game");
		btnNewGame.setActionCommand("frame.menuPanels.GameSettingsPanel");
		btnNewGame.addActionListener(ControlInput.menuChanger);
		add(btnNewGame, "cell 1 1,grow");

		JButton btnLoadGame = new JButton_01("Load Game");
		btnLoadGame.setActionCommand("frame.menuPanels.LoadMenuPanel");
		btnLoadGame.addActionListener(ControlInput.menuChanger);
		add(btnLoadGame, "cell 1 2,grow");

		JButton btnOptions = new JButton_01("Options");
		btnOptions.setActionCommand("frame.menuPanels.OptionMenuPanel");
		btnOptions.addActionListener(ControlInput.menuChanger);
		add(btnOptions, "cell 1 3,grow");
		
		JButton btnExit = new JButton_01("Exit");
		btnExit.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
			
		});
		add(btnExit, "cell 1 4,grow");

	}

}
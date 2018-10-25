package frame.menuPanels;

import java.awt.SystemColor;

import javax.swing.JButton;
import javax.swing.JPanel;

import core.ControlInput;
import net.miginfocom.swing.MigLayout;

/**
 * Here the user can go to the different menus
 * 
 * @author Luca Kleck
 * @see frame.MainJFrame
 */
public class MainMenuPanel extends JPanel {
	private static final long serialVersionUID = 111L;

	public MainMenuPanel() {
		setBackground(SystemColor.desktop);
		setLayout(new MigLayout("", "[30%]", "[:10%:10%][:10%:10%][:10%:10%][:70%:70%]"));

		JButton btnNewGame = new JButton("New Game");
		btnNewGame.setActionCommand("frame.menuPanels.GameSettingsPanel");
		btnNewGame.addActionListener(ControlInput.menuChanger);
		add(btnNewGame, "cell 0 0,grow");

		JButton btnLoadGame = new JButton("Load Game");
		btnLoadGame.setActionCommand("frame.menuPanels.LoadMenuPanel");
		btnLoadGame.addActionListener(ControlInput.menuChanger);
		add(btnLoadGame, "cell 0 1,grow");

		JButton btnOptions = new JButton("Options");
		btnOptions.setActionCommand("frame.menuPanels.OptionMenuPanel");
		btnOptions.addActionListener(ControlInput.menuChanger);
		add(btnOptions, "cell 0 2,grow");

	}

}
package frame.menuPanels;

import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JPanel;

import core.ControlInput;
import net.miginfocom.swing.MigLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * Here the user can go to the different menus
 * 
 * @author Luca Kleck
 * @see frame.MainJFrame
 */
public class MainMenuPanel extends JPanel {
	private static final long serialVersionUID = 111L;

	public MainMenuPanel() {
		setBackground(Color.LIGHT_GRAY);
		setLayout(new MigLayout("", "[33%][33%,center][33%]", "[3%][:10%:10%][:10%:10%][:10%:10%][:10%:10%][:57%:57%]"));

		JButton btnNewGame = new JButton("New Game");
		btnNewGame.setActionCommand("frame.menuPanels.GameSettingsPanel");
		btnNewGame.addActionListener(ControlInput.menuChanger);
		add(btnNewGame, "cell 1 1,grow");

		JButton btnLoadGame = new JButton("Load Game");
		btnLoadGame.setActionCommand("frame.menuPanels.LoadMenuPanel");
		btnLoadGame.addActionListener(ControlInput.menuChanger);
		add(btnLoadGame, "cell 1 2,grow");

		JButton btnOptions = new JButton("Options");
		btnOptions.setActionCommand("frame.menuPanels.OptionMenuPanel");
		btnOptions.addActionListener(ControlInput.menuChanger);
		add(btnOptions, "cell 1 3,grow");
		
		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
			
		});
		add(btnExit, "cell 1 4,grow");

	}

}
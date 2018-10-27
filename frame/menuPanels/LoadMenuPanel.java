package frame.menuPanels;

import java.awt.Color;

import javax.swing.JPanel;

import core.ControlInput;
import net.miginfocom.swing.MigLayout;
import javax.swing.JButton;

/**
 * Here the user can load a save by A: Typing the save-name B: Clicking on a
 * saved game
 * 
 * @author Luca Kleck
 * @see frame.MainJFrame
 */
public class LoadMenuPanel extends JPanel {
	private static final long serialVersionUID = 114L;

	public LoadMenuPanel() {
		this.setBackground(Color.BLUE);
		setLayout(new MigLayout("", "[]", "[10%][80%][10%]"));

		JButton btnBack = new JButton("Back");
		btnBack.setActionCommand("frame.menuPanels.MainMenuPanel");
		btnBack.addActionListener(ControlInput.menuChanger);
		add(btnBack, "cell 0 3");
	}

}
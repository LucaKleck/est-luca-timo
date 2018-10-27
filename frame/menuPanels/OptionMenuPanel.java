package frame.menuPanels;

import javax.swing.JButton;
import javax.swing.JPanel;

import core.ControlInput;
import net.miginfocom.swing.MigLayout;
import java.awt.Color;

/**
 * User can make general settings
 * 
 * @author Luca Kleck
 * @see frame.MainJFrame
 */
public class OptionMenuPanel extends JPanel {
	private static final long serialVersionUID = 112L;

	// TODO add buttons & make them write into the settings.xml or something
	public OptionMenuPanel() {
		setBackground(Color.LIGHT_GRAY);
		setLayout(new MigLayout("", "[]", "[10%][80%][10%]"));

		JButton btnBack = new JButton("Back");
		btnBack.setActionCommand("frame.menuPanels.MainMenuPanel");
		btnBack.addActionListener(ControlInput.menuChanger);
		add(btnBack, "cell 0 3");

	}

}
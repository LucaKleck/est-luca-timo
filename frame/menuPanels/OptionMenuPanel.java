package frame.menuPanels;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JPanel;

import core.ControlInput;
import core.Core;
import net.miginfocom.swing.MigLayout;

/**
 * Place where the settings for the game can be changed
 * 
 * @author Luca Kleck
 * @see frame.MainJFrame
 */
public class OptionMenuPanel extends JPanel {
	private static final long serialVersionUID = 112L;

	public OptionMenuPanel() {
		setBackground(Color.LIGHT_GRAY);
		setLayout(new MigLayout("", "[]", "[fill][fill][100%][10%][]"));

		JButton btnBack = new JButton("Back");
		btnBack.setActionCommand("frame.menuPanels.MainMenuPanel");
		btnBack.addActionListener(ControlInput.menuChanger);
		add(btnBack, "cell 0 4");
		
		JCheckBox chckbxFullscreen = new JCheckBox("Fullscreen");
		chckbxFullscreen.setSelected(Core.getMainJFrame().isUndecorated());
		add(chckbxFullscreen, "cell 0 0");
		
		JCheckBox chckbxAskBeforeDeleting = new JCheckBox("Ask before deleting saves");
		chckbxAskBeforeDeleting.setSelected(new Boolean(Core.getSetting("askSaveDelete")));
		chckbxAskBeforeDeleting.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Boolean b = new Boolean(Core.getSetting("askSaveDelete"));
				b = !b;
				Core.saveSetting("askSaveDelete", b.toString());
			}
		});
		add(chckbxAskBeforeDeleting, "cell 0 1");


	}

}
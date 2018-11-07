package frame.menuPanels;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JSpinner;
import javax.swing.SwingConstants;

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
		setBackground(Color.DARK_GRAY);
		setLayout(new MigLayout("", "[][][][60px:n,grow,fill][100%]", "[fill][fill][100%][10%][]"));

		JButton btnBack = new JButton("Back");
		btnBack.setActionCommand("frame.menuPanels.MainMenuPanel");
		btnBack.addActionListener(ControlInput.menuChanger);
		
		JLabel lblWindowWidth = new JLabel("Window Width");
		lblWindowWidth.setForeground(Color.WHITE);
		add(lblWindowWidth, "cell 2 0,alignx left");
		
		JSpinner spnWidth = new JSpinner();
		add(spnWidth, "cell 3 0,grow");
		
		JSeparator separator = new JSeparator();
		separator.setOrientation(SwingConstants.VERTICAL);
		add(separator, "cell 1 0 1 2");
		
		JLabel lblWindowHeight = new JLabel("Window Height");
		lblWindowHeight.setForeground(Color.WHITE);
		add(lblWindowHeight, "cell 2 1,alignx left");
		
		JSpinner spnHeight = new JSpinner();
		add(spnHeight, "cell 3 1,grow");
		add(btnBack, "cell 0 4");
		
		JCheckBox chckbxFullscreen = new JCheckBox("Fullscreen");
		chckbxFullscreen.setBackground(Color.LIGHT_GRAY);
		chckbxFullscreen.setSelected(Core.getMainJFrame().isUndecorated());
		chckbxFullscreen.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Core.getMainJFrame().dispose();
				// put it in the left uppermost corner
				Core.getMainJFrame().setUndecorated(!Core.getMainJFrame().isUndecorated());
				Core.getMainJFrame().setBounds(0, 0, 0, 0);
				if (Core.getMainJFrame().isUndecorated()) {
					Core.getMainJFrame().setExtendedState(JFrame.MAXIMIZED_BOTH);
				} else {
					Core.getMainJFrame().setExtendedState(JFrame.NORMAL);
				}
				Core.getMainJFrame().pack();
				if (!Core.getMainJFrame().isUndecorated()) {
					Core.getMainJFrame().setSize(800, 600);
				}
				Core.getMainJFrame().validate();

				Core.getMainJFrame().setVisible(true);
				
				Core.saveSetting("fullscreen", new Boolean(Core.getMainJFrame().isUndecorated()).toString() );
			}
		});
		add(chckbxFullscreen, "cell 0 0,growx,aligny center");
		
		JCheckBox chckbxAskBeforeDeleting = new JCheckBox("Ask before deleting saves");
		chckbxAskBeforeDeleting.setBackground(Color.LIGHT_GRAY);
		chckbxAskBeforeDeleting.setSelected(new Boolean(Core.getSetting("askSaveDelete")));
		chckbxAskBeforeDeleting.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Boolean b = new Boolean(Core.getSetting("askSaveDelete"));
				b = !b;
				Core.saveSetting("askSaveDelete", b.toString());
			}
		});
		add(chckbxAskBeforeDeleting, "cell 0 1,grow");


	}

}
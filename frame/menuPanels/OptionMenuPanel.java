package frame.menuPanels;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JSeparator;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.text.NumberFormatter;

import core.ControlInput;
import core.Core;
import core.FullscreenActionListener;
import core.ResourceManager;
import frame.customPresets.JButton_01;
import frame.customPresets.JPanelCustomBg;
import net.miginfocom.swing.MigLayout;

/**
 * Place where the settings for the game can be changed
 * 
 * @author Luca Kleck
 * @see frame.MainJFrame
 */
public class OptionMenuPanel extends JPanelCustomBg {
	private static final long serialVersionUID = 112L;

	public OptionMenuPanel() {
		super(ResourceManager.getBackground_05());
		setLayout(new MigLayout("insets 0 0 0 0", "[33%][33%][33%]", "[100%,fill]"));
		
		JPanelCustomBg container = new JPanelCustomBg(ResourceManager.getBackground_03(), true);
		container.setLayout(new MigLayout("insets 10 10 10 10", "[]", "[]"));
		
		add(container, "cell 1 0,grow");

		JButton btnBack = new JButton_01("Back");
		btnBack.setActionCommand("frame.menuPanels.MainMenuPanel");
		btnBack.addActionListener(ControlInput.menuChanger);
		
		JLabel lblWindowWidth = new JLabel("Window Width");
		lblWindowWidth.setForeground(Color.WHITE);
		container.add(lblWindowWidth, "cell 2 0,alignx left");
		
		JSpinner spnWidth = new JSpinner();
		spnWidth.setMinimumSize(new Dimension(60, 20));
		spnWidth.setModel(new SpinnerNumberModel(Core.getMainJFrame().getWidth(), 0, (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth(), (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth()/10));
		spnWidth.setEditor(new JSpinner.NumberEditor(spnWidth));
		JFormattedTextField spnWidthTxt = ((JSpinner.NumberEditor) spnWidth.getEditor()).getTextField();
		((NumberFormatter) spnWidthTxt.getFormatter()).setAllowsInvalid(false);
		spnWidth.addChangeListener(new ChangeListener() {
			
			@Override
			public void stateChanged(ChangeEvent e) {
				if( Double.parseDouble(spnWidth.getValue().toString()) >= Core.getMainJFrame().getMinimumSize().getWidth()) {
					Core.saveSetting(Core.SETTING_DEFAULT_WIDTH, ""+Integer.parseInt(spnWidth.getValue().toString()) );
					Core.getMainJFrame().setSize(Integer.parseInt(spnWidth.getValue().toString()), Core.getMainJFrame().getHeight());
				} else {
					spnWidth.setValue((int) Core.getMainJFrame().getMinimumSize().getWidth());
				}
			}
		});
		container.add(spnWidth, "cell 3 0,grow");
		
		JSeparator separator = new JSeparator();
		separator.setOrientation(SwingConstants.VERTICAL);
		container.add(separator, "cell 1 0 1 2");
		
		JLabel lblWindowHeight = new JLabel("Window Height");
		lblWindowHeight.setForeground(Color.WHITE);
		container.add(lblWindowHeight, "cell 2 1,alignx left");
		
		JSpinner spnHeight = new JSpinner();
		spnHeight.setMinimumSize(new Dimension(60, 20));
		spnHeight.setModel(new SpinnerNumberModel(Core.getMainJFrame().getHeight(), 0, (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight(), (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight()/10));
		spnHeight.setEditor(new JSpinner.NumberEditor(spnHeight));
		JFormattedTextField spnHeightTxt = ((JSpinner.NumberEditor) spnHeight.getEditor()).getTextField();
		((NumberFormatter) spnHeightTxt.getFormatter()).setAllowsInvalid(false);
		spnHeight.addChangeListener(new ChangeListener() {
			
			@Override
			public void stateChanged(ChangeEvent e) {
				if( Double.parseDouble(spnHeight.getValue().toString())  >= Core.getMainJFrame().getMinimumSize().getHeight()) {
					Core.saveSetting(Core.SETTING_DEFAULT_HEIGHT, ""+Integer.parseInt(spnHeight.getValue().toString()) );
					Core.getMainJFrame().setSize(Core.getMainJFrame().getWidth(), Integer.parseInt(spnHeight.getValue().toString()) );
				} else {
					spnHeight.setValue((int) Core.getMainJFrame().getMinimumSize().getHeight());
				}
			}
		});
		container.add(spnHeight, "cell 3 1,grow");
		container.add(btnBack, "cell 0 4");
		
		JCheckBox chckbxFullscreen = new JCheckBox("Fullscreen");
		chckbxFullscreen.setBackground(Color.LIGHT_GRAY);
		chckbxFullscreen.setSelected(Core.getMainJFrame().isUndecorated());
		chckbxFullscreen.addActionListener(new FullscreenActionListener());
		container.add(chckbxFullscreen, "cell 0 0,growx,aligny center");
		
		JCheckBox chckbxAskBeforeDeleting = new JCheckBox("Ask before deleting saves");
		chckbxAskBeforeDeleting.setBackground(Color.LIGHT_GRAY);
		chckbxAskBeforeDeleting.setSelected(new Boolean(Core.loadSetting(Core.SETTING_ASK_SAVE_DELETE)));
		chckbxAskBeforeDeleting.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Boolean b = new Boolean(Core.loadSetting(Core.SETTING_ASK_SAVE_DELETE));
				b = !b;
				Core.saveSetting(Core.SETTING_ASK_SAVE_DELETE, b.toString());
			}
		});
		container.add(chckbxAskBeforeDeleting, "cell 0 1,grow");


	}
	
}
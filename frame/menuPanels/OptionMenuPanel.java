package frame.menuPanels;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import core.ControlInput;
import core.Core;
import core.FullscreenActionListener;
import core.ResourceManager;
import frame.MainJFrame;
import frame.customPresets.CustomJCheckBox;
import frame.customPresets.CustomJComboBox;
import frame.customPresets.JButton_01;
import frame.customPresets.JPanelCustomBg;
import frame.customPresets.CustomLable;
import net.miginfocom.swing.MigLayout;

/**
 * Place where the settings for the game can be changed
 * 
 * @author Luca Kleck
 * @see frame.MainJFrame
 */
public class OptionMenuPanel extends JPanelCustomBg {
	private static final long serialVersionUID = 112L;
	private static final CustomDimension[] RESOULUTION_DIMENSIONS = {new CustomDimension(1024, 768), new CustomDimension(1280, 720), new CustomDimension(1366, 768), new CustomDimension(1440, 900), new CustomDimension(1600, 900), new CustomDimension(1920, 1080), new CustomDimension(1920, 1440), new CustomDimension(2160, 1440), new CustomDimension(3840, 2160)};
	private static String headlineStyle = MainJFrame.makeCssStyle("font-size: 1.2em");
	
	public OptionMenuPanel() {
		super(ResourceManager.getBackground_05());
		setLayout(new MigLayout("insets 0 0 0 0", "[33%][33%,fill][33%]", "[100%,fill]"));
		
		JPanelCustomBg container = new JPanelCustomBg(ResourceManager.getBackground_03(), true);
		container.setLayout(new MigLayout("insets 30 30 30 30, fillx", "[100%]", "[100%]"));
		
		add(container, "cell 1 0,grow");
		
		JLabel optionsText = new CustomLable(MainJFrame.makeCssStyle("font-size: 1.7em")+"Options", true);
		optionsText.setBorder(BorderFactory.createEmptyBorder(2, 0, 4, 0));
		optionsText.setHorizontalAlignment(SwingConstants.CENTER);
		container.add(optionsText, "cell 0 0, ay top, ax center, spanx 2, flowy, newline, growx");
		
		JLabel optionsGeneral = new CustomLable(headlineStyle+"General", true);
		optionsGeneral.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 20));
		container.add(optionsGeneral, "cell 0 0, gaptop 15px");
		
		JCheckBox chckbxFullscreen = new CustomJCheckBox("Fullscreen", true);
		chckbxFullscreen.setSelected(Core.getMainJFrame().isUndecorated());
		chckbxFullscreen.addActionListener(new FullscreenActionListener());
		container.add(chckbxFullscreen, "cell 0 0, growx");
		
		JComboBox<CustomDimension> resoulutionBox = new CustomJComboBox<CustomDimension>(RESOULUTION_DIMENSIONS);
		CustomDimension cd = new CustomDimension(Core.getMainJFrame().getWidth(), Core.getMainJFrame().getHeight(), true);
		resoulutionBox.insertItemAt(cd, 0);
		resoulutionBox.setSelectedItem(cd);
		resoulutionBox.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Core.getMainJFrame().setSize((Dimension) resoulutionBox.getSelectedItem());
			}
		});
		container.add(resoulutionBox, "cell 0 0, growx");
		
		JLabel optionsLoading = new CustomLable(headlineStyle+"Loading", true);
		optionsLoading.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 20));
		container.add(optionsLoading, "cell 0 0, gaptop 15px");
		
		JCheckBox chckbxAskBeforeDeleting = new CustomJCheckBox("Ask before deleting saves", true);
		chckbxAskBeforeDeleting.setSelected(new Boolean(Core.loadSetting(Core.SETTING_ASK_SAVE_DELETE)));
		chckbxAskBeforeDeleting.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Boolean b = new Boolean(Core.loadSetting(Core.SETTING_ASK_SAVE_DELETE));
				b = !b;
				Core.saveSetting(Core.SETTING_ASK_SAVE_DELETE, b.toString());
			}
		});
		container.add(chckbxAskBeforeDeleting, "cell 0 0, growx");
		
		JButton btnBack = new JButton_01("Back");
		btnBack.setActionCommand("frame.menuPanels.MainMenuPanel");
		btnBack.addActionListener(ControlInput.menuChanger);
		container.add(btnBack, "cell 0 1, ay bottom, ax right");


	}
	
	private static class CustomDimension extends Dimension {
		private static final long serialVersionUID = 776982080628486875L;
		private boolean isCurrent = false;
		
		public CustomDimension(int width, int height) {
			super(width, height);
		}
		
		public CustomDimension(int width, int height, boolean isCurrent) {
			super(width, height);
			this.isCurrent = isCurrent;
		}
		
		@Override
			public String toString() {
				if(isCurrent) {
					return "Current resoulution: " + (int) getWidth()+"x"+(int) getHeight();
				}
				return (int) getWidth()+"x"+(int) getHeight();
			}
	}
	
}
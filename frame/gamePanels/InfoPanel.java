package frame.gamePanels;

import java.awt.Graphics;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import core.GameInfo;
import core.ResourceManager;
import frame.MainJFrame;
import frame.customPresets.CustomProgressBar;
import frame.customPresets.JPanelCustomBg;
import frame.gamePanels.SelectionPanel.SelectionPaneElement;
import net.miginfocom.swing.MigLayout;

public class InfoPanel extends JPanelCustomBg {
	private static final long serialVersionUID = 125L;
	private static final String style = MainJFrame.makeCssStyle("color: yellow;");
	
	private JLabel lblHealth = new JLabel(new ImageIcon(SelectionPaneElement.class.getResource("/resources/healthIcon.png")));
	private JLabel lblName = new JLabel(style);
	private CustomProgressBar healthStatus;

	public InfoPanel() {
		super(ResourceManager.getBackground_05(), false, true);
		setLayout(new MigLayout("insets 12 12 3 0, gap 4 2", "[]", "[][][]"));

		add(lblName, "cell 0 0");

		add(lblHealth, "cell 0 1");

		healthStatus = new CustomProgressBar();
		add(healthStatus, "cell 0 1");
		
		update();
	}

	public void update() {
		if(GameInfo.getObjectMap().getSelected().getSelectedEntity() != null) {
			lblName.setText(style+GameInfo.getObjectMap().getSelected().getSelectedEntity().getName());
			healthStatus.setMaximum(GameInfo.getObjectMap().getSelected().getSelectedEntity().getMaxHealth());
			healthStatus.setValue(GameInfo.getObjectMap().getSelected().getSelectedEntity().getCurrentHealth());
			healthStatus.setString(GameInfo.getObjectMap().getSelected().getSelectedEntity().getCurrentHealth()+ "/" + GameInfo.getObjectMap().getSelected().getSelectedEntity().getMaxHealth() );
			
			lblName.setVisible(true);
			lblHealth.setVisible(true);
			healthStatus.setVisible(true);
		} else {
			lblName.setVisible(false);
			lblHealth.setVisible(false);
			healthStatus.setVisible(false);
		}
	}
	
	@Override
	public void paint(Graphics g) {
		g.drawImage(ResourceManager.getBackground_02(), 0, 0, getWidth(), getHeight(), null);
		super.paint(g);
	}
}

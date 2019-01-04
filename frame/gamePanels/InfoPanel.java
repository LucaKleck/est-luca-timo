package frame.gamePanels;

import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;

import core.GameInfo;
import frame.MainJFrame;
import net.miginfocom.swing.MigLayout;

public class InfoPanel extends JScrollPane {
	private static final long serialVersionUID = 125L;
	private JLabel lblHealth = new JLabel("Health: ");
	private JLabel lblName = new JLabel("Name: ");
	private JProgressBar healthStatus;

	public InfoPanel() {
		this.setBackground(Color.LIGHT_GRAY);

		JPanel panel = new JPanel();
		panel.setBackground(Color.LIGHT_GRAY);
		setViewportView(panel);
		panel.setLayout(new MigLayout("", "[]", "[][][]"));

		panel.add(lblName, "cell 0 0");

		panel.add(lblHealth, "cell 0 1");

		JLabel lblCurrentlySelected = new JLabel(MainJFrame.htmlStyleDefault+"Currently Selected");
		setColumnHeaderView(lblCurrentlySelected);
		
		healthStatus = new JProgressBar();
		healthStatus.setMaximum(0);
		healthStatus.setMinimum(0);
		healthStatus.setValue(0);
		healthStatus.setStringPainted(true);
		healthStatus.setString("0/0");
		panel.add(healthStatus, "cell 0 2");
	}

	public void update() {
		if(GameInfo.getObjectMap().getSelected().getSelectedEntity() != null) {
			lblName.setText("Name: " + GameInfo.getObjectMap().getSelected().getSelectedEntity().getName());
			lblHealth.setText("Health:");
			healthStatus.setMaximum(GameInfo.getObjectMap().getSelected().getSelectedEntity().getMaxHealth());
			healthStatus.setMinimum(0);
			healthStatus.setValue(GameInfo.getObjectMap().getSelected().getSelectedEntity().getCurrentHealth());
			healthStatus.setStringPainted(true);
			healthStatus.setString(GameInfo.getObjectMap().getSelected().getSelectedEntity().getCurrentHealth()+ "/" + GameInfo.getObjectMap().getSelected().getSelectedEntity().getMaxHealth() );
		} else {
			lblName.setText("Name: ");
			lblHealth.setText("Health:");
			healthStatus.setMaximum(0);
			healthStatus.setMinimum(0);
			healthStatus.setValue(0);
			healthStatus.setStringPainted(true);
			healthStatus.setString("0/0");
		}
	}
}

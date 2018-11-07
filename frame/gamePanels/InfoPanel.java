package frame.gamePanels;

import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import map.ObjectMap;
import net.miginfocom.swing.MigLayout;

public class InfoPanel extends JScrollPane {
	private static final long serialVersionUID = 125L;
	private static JLabel lblHealth = new JLabel("Health: ");
	private static JLabel lblName = new JLabel("Name: ");

	public InfoPanel() {
		this.setBackground(Color.LIGHT_GRAY);

		JPanel panel = new JPanel();
		panel.setBackground(Color.LIGHT_GRAY);
		setViewportView(panel);
		panel.setLayout(new MigLayout("", "[]", "[][][]"));

		panel.add(lblName, "cell 0 0");

		panel.add(lblHealth, "cell 0 1");

		JLabel lblCurrentlySelected = new JLabel("Currently Selected");
		setColumnHeaderView(lblCurrentlySelected);
	}

	public static void refresh() {
		try {
			lblName.setText("Name: " + ObjectMap.getSelected().getSelectedEntity().getName());
			lblHealth.setText("Health: " + ObjectMap.getSelected().getSelectedEntity().getCurrentHealth());
		} catch (NullPointerException nl) {
			lblName.setText("Name: ");
			lblHealth.setText("Health: ");
		}
	}
}

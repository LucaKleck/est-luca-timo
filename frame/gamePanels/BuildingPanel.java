package frame.gamePanels;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

import entity.building.Building;
import net.miginfocom.swing.MigLayout;

public class BuildingPanel extends JScrollPane {
	private static final long serialVersionUID = 1L;

	private Building building;

	public BuildingPanel(Building building) {
		setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

		this.building = building;

		JPanel panel = new JPanel();
		setViewportView(panel);
		panel.setLayout(new MigLayout("", "[][][][][][][]", "[][][][][][][][][][][][]"));

		JLabel lblBuildingName = new JLabel("Name");
		panel.add(lblBuildingName, "cell 0 0");

		JLabel lblNewLabel = new JLabel(building.getName());
		panel.add(lblNewLabel, "cell 1 0");

		JLabel lblLevel = new JLabel("Level:" + this.building.getLevel());
		panel.add(lblLevel, "cell 0 1");

		JLabel lblNewLabel_1 = new JLabel(Integer.toString(building.getLevel()));
		panel.add(lblNewLabel_1, "cell 1 1");

		JButton btnLevelUp = new JButton("Level Up");
		panel.add(btnLevelUp, "cell 0 3,alignx center,aligny bottom");

		JLabel lblCost = new JLabel("Cost: ");
		panel.add(lblCost, "cell 1 3");
		
	}

}

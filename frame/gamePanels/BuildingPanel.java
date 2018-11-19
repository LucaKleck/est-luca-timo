package frame.gamePanels;

import javax.swing.JScrollPane;

import entity.building.Building;

import javax.swing.JPanel;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;

public class BuildingPanel extends JScrollPane {
	
	private Building building;
	
	public BuildingPanel(Building building) {
		
		this.building = building;
		
		JPanel panel = new JPanel();
		setViewportView(panel);
		panel.setLayout(new MigLayout("", "[]", "[]"));
		
		JLabel lblBuildingName = new JLabel("Name" + building.getName());
		panel.add(lblBuildingName, "cell 0 0");
	}

	
	
}

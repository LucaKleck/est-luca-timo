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
	private JLabel lblNextEvent;

	public BuildingPanel(Building building) {
		setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

		this.building = building;

		JPanel panel = new JPanel();
		setViewportView(panel);
		panel.setLayout(new MigLayout("", "[][][][][][][]", "[][][][][][][][][][][][]"));

		JLabel lblBuildingName = new JLabel("Name: " + building.getName());
		panel.add(lblBuildingName, "cell 0 0");

		JLabel lblLevel = new JLabel("Level:" + this.building.getLevel());
		panel.add(lblLevel, "cell 0 1");

		JButton btnLevelUp = new JButton("Level Up");
		panel.add(btnLevelUp, "cell 0 3");

		JLabel lblCost = new JLabel("Cost: ");
		panel.add(lblCost, "cell 0 3");

		if (this.building.getEvent() != null) {
			lblNextEvent = new JLabel("Event: " + this.building.getEvent().getAbility().getName());
			panel.add(lblNextEvent, "cell 0 4");
		} else {
			lblNextEvent = new JLabel("Event: " + "No Event");
			panel.add(lblNextEvent, "cell 0 4");
		}

	}

	public void updateEventText(String eventText) {
		if (eventText != null) {
			lblNextEvent.setText("Event: " + eventText);
		} else {
			lblNextEvent.setText("Event: " + "No Event");
		}
	}

}

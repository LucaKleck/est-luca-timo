package frame.gamePanels;

import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

import abilities.Ability;
import abilities.CollectResources;
import abilities.CreateUnit;
import abilities.LevelUp;
import core.Core;
import core.Event;
import core.GameInfo;
import entity.Entity;
import net.miginfocom.swing.MigLayout;

public class EntityPanel extends JScrollPane {
	private static final long serialVersionUID = 1L;

	private Entity entity;
	private JLabel lblNextEvent;
	private String lblCostText = "Cost: ";
	private Font font = new Font("MS PGothic", Font.BOLD, 16);

	public EntityPanel(Entity entity) {
		setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

		this.entity = entity;

		JPanel panel = new JPanel();
		setViewportView(panel);
		panel.setLayout(new MigLayout("", "[][][][][][][]", "[][][][][][][][][][][][]"));
		panel.setFont(font);

		JLabel lblEntityName = new JLabel("Name: " + entity.getName());
		panel.add(lblEntityName, "cell 0 0");

		JLabel lblLevel = new JLabel("Level: " + this.entity.getLevel());
		panel.add(lblLevel, "cell 0 1");

		JButton btnLevelUp = new JButton("Level Up");
		btnLevelUp.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {

				entity.setEvent(new Event(entity, entity, new LevelUp(), null));
				lblNextEvent.setText("Event: " + "Level Up");

			}

		});
		if (entity.getLevel() == 3) {
			btnLevelUp.setEnabled(false);
			lblCostText = "Max Level Reached!";
		}
		panel.add(btnLevelUp, "cell 0 3");

		JLabel lblCost = new JLabel(lblCostText);
		panel.add(lblCost, "cell 0 3");

		if (this.entity.getEvent() != null) {
			lblNextEvent = new JLabel("Event: " + this.entity.getEvent().getAbility().getName());
		} else {
			lblNextEvent = new JLabel("Event: " + "No Event");
		}
		panel.add(lblNextEvent, "cell 0 4");

		JLabel lblAbilities = new JLabel("Abilities: ");
		panel.add(lblAbilities, "cell 0 5");

		int index = 6;

		for (Ability ability : entity.getAbilities()) {
			if (!(ability instanceof CollectResources)) {

				JButton jButton = new JButton(ability.getName());
				jButton.setToolTipText(ability.getDescription());
				jButton.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						lblNextEvent.setText("Event: " + ability.getName());
						if (ability.maxRange > 0) {
							((MainGamePanel) Core.getMainJFrame().getCurrentComponent()).getMapPanel().getMapImage()
									.drawAbilityLayer(ability.maxRange,
											GameInfo.getObjectMap().getSelected().getSelectedEntity().getXPos(),
											GameInfo.getObjectMap().getSelected().getSelectedEntity().getYPos());
							((MainGamePanel) Core.getMainJFrame().getCurrentComponent()).getMapPanel().getMapImage()
									.drawCombinedImage();
						}
						GameInfo.getObjectMap().getSelected().setSelectedAbility(ability);
						if (ability instanceof CreateUnit) {
							((EntityPanel) InteractionPanel.getCurrentPanel()).updateEventText(ability.getName());
							GameInfo.getObjectMap().getSelected().clickedOnTile(0, 0, true);
						}
					}
				});
				panel.add(jButton, ("cell 0 " + index + ", grow"));
			}
			index++;
		}

		for (Component component : panel.getComponents()) {

			component.setFont(font);

		}

	}

	public void updateEventText(String eventText) {
		if (eventText != null) {
			lblNextEvent.setText("Event: " + eventText);
		} else {
			lblNextEvent.setText("Event: " + "No Event");
		}
		InteractionPanel.getCurrentPanel().repaint();
		InteractionPanel.getCurrentPanel().revalidate();
	}

}
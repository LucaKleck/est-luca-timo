package frame.gamePanels;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

import abilities.Ability;
import abilities.CreateUnit;
import abilities.LevelUp;
import core.Core;
import core.Event;
import core.GameInfo;
import entity.Entity;
import frame.MainJFrame;
import net.miginfocom.swing.MigLayout;

public class EntityPanel extends JScrollPane {
	private static final long serialVersionUID = 1L;

	private Entity entity;
	private JLabel lblNextEvent;
	//Just in Development
	private String lblCostText = "Cost: 30 Food";
	private Font font = new Font("MS PGothic", Font.BOLD, 16);
	private String cssYellow = MainJFrame.makeCssStyle("color: #F0F900;");

	public EntityPanel(Entity entity) {
		setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		
		setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
		
		this.entity = entity;

		JPanel panel = new PanelWithVerticalBackground();
		setViewportView(panel);
		panel.setLayout(new MigLayout("", "[][][][][][][]", "[][][][][][][][][][][][]"));
		panel.setFont(font);

		JLabel lblEntityName = new JLabel(cssYellow+"Name: " + entity.getName());
		panel.add(lblEntityName, "cell 0 0");

		JLabel lblLevel = new JLabel(cssYellow+"Level: " + this.entity.getLevel());
		panel.add(lblLevel, "cell 0 1");

		setForeground(Color.yellow);
		panel.setForeground(Color.yellow);
		JButton btnLevelUp = new JButton(cssYellow+"Level Up");
		btnLevelUp.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {

				entity.setEvent(new Event(entity, entity, new LevelUp(), null));
				lblNextEvent.setText(cssYellow+"Event: " + "Level Up");

			}

		});
		if (entity.getLevel() == 3) {
			btnLevelUp.setEnabled(false);
			lblCostText = cssYellow+"Max Level Reached!";
		} 
		//Just in Development
		else if (GameInfo.getPlayerStats().getPlayerResources().getFood() <= 30) {
			btnLevelUp.setEnabled(false);
			lblCostText = cssYellow+"Cost: 30 Food";
		}
		panel.add(btnLevelUp, "cell 0 3");

		JLabel lblCost = new JLabel(lblCostText);
		panel.add(lblCost, "cell 0 3");

		if (this.entity.getEvent() != null) {
			lblNextEvent = new JLabel(cssYellow+"Event: " + this.entity.getEvent().getAbility().getName());
		} else {
			lblNextEvent = new JLabel(cssYellow+"Event: " + "No Event");
		}
		panel.add(lblNextEvent, "cell 0 4");

		JLabel lblAbilities = new JLabel(cssYellow+"Abilities: ");
		panel.add(lblAbilities, "cell 0 5");

		int index = 6;

		for (Ability ability : entity.getAbilities()) {
				JButton jButton = new JButton(cssYellow+ability.getName());
				jButton.setToolTipText(ability.getDescription());
				jButton.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						lblNextEvent.setText(cssYellow+"Event: " + ability.getName());
						if (ability.maxRange > 0) {
							((MainGamePanel) Core.getMainJFrame().getCurrentComponent()).getMapPanel().getMapImage()
									.drawAbilityLayer(ability,
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
				if(entity.getLevel() < ability.getAbilityMinimumLevel()) {
					jButton.setEnabled(false);
				}
				panel.add(jButton, ("cell 0 " + index + ", grow"));
			index++;
		}

		for (Component component : panel.getComponents()) {

			component.setFont(font);

		}
	}

	public void updateEventText(String eventText) {
		if (eventText != null) {
			lblNextEvent.setText(cssYellow+"Event: " + eventText);
		} else {
			lblNextEvent.setText(cssYellow+"Event: " + "No Event");
		}
		InteractionPanel.getCurrentPanel().repaint();
		InteractionPanel.getCurrentPanel().revalidate();
	}

}

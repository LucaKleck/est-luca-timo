package frame.gamePanels;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import abilities.Ability;
import abilities.Build;
import abilities.CreateUnit;
import abilities.LevelUp;
import core.Core;
import core.Event;
import core.GameInfo;
import core.ResourceManager;
import entity.Entity;
import entity.building.Building;
import frame.JButtonCustomBg;
import frame.JButton_01;
import frame.JPanelCustomBg;
import frame.JScrollPaneBg;
import frame.MainJFrame;
import net.miginfocom.swing.MigLayout;

public class EntityPanel extends JScrollPaneBg {
	private static final long serialVersionUID = 1L;

	private Entity entity;
	private JLabel lblNextEvent;
	//Just in Development
	private String lblCostText = "Cost: 30 Food";
	private Font font = new Font("MS PGothic", Font.BOLD, 16);
	private String cssYellow = MainJFrame.makeCssStyle("color: #F0F900;");

	private JPanelCustomBg abilityPanel;

	public EntityPanel(Entity entity) {
		super(ResourceManager.getBackground_04(), true);
		
		this.entity = entity;
		JPanel jPanel = new JPanelCustomBg(new BufferedImage(9, 9, BufferedImage.TYPE_INT_ARGB));
		getViewport().setView(jPanel);
		
		jPanel.setLayout(new MigLayout("insets 12 12 3 0", "[]", "[][][][][][][]"));
		jPanel.setFont(font);

		JLabel lblEntityName = new JLabel(cssYellow+"Name: " + entity.getName());
		jPanel.add(lblEntityName, "cell 0 0");

		JLabel lblLevel = new JLabel(cssYellow+"Level: " + this.entity.getLevel());
		jPanel.add(lblLevel, "cell 0 1");

		JButton btnLevelUp = new JButton_01(cssYellow+"Level Up");
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
		jPanel.add(btnLevelUp, "cell 0 3");

		JLabel lblCost = new JLabel(lblCostText);
		jPanel.add(lblCost, "cell 0 3");

		if (this.entity.getEvent() != null) {
			lblNextEvent = new JLabel(cssYellow+"Event: " + this.entity.getEvent().getAbility().getName());
		} else {
			lblNextEvent = new JLabel(cssYellow+"Event: " + "No Event");
		}
		jPanel.add(lblNextEvent, "cell 0 4");

		JLabel lblAbilities = new JLabel(cssYellow+"Abilities: ");
		if(!entity.getAbilities().isEmpty()) {
			jPanel.add(lblAbilities, "cell 0 5");
		}
		abilityPanel = new JPanelCustomBg(ResourceManager.getBackground_01());
		abilityPanel.setLayout(new MigLayout("insets 12 12 12 12 alignx left flowy", "[]", "[]"));
		if(!entity.getAbilities().isEmpty()) {
			jPanel.add(abilityPanel, "cell 0 6");
		}
		
		updateUserInterface();
	}
	
	private static BufferedImage getAbilityImage(Ability ability) {
		BufferedImage abilityImage = null;
		if(ability instanceof Ability) abilityImage = ResourceManager.getSpellBook01_93();
		if(ability instanceof Build) {
			if(((Build) ability).getBuildingName().matches(Building.WALL)) abilityImage = ResourceManager.getSpellBook05_72();
			else abilityImage = ResourceManager.getSpellBook01_67();
			
			
		};
		return abilityImage;
	}
	
	public void updateUserInterface() {
		abilityPanel.removeAll();
		int i = 0;
		for (Ability ability : entity.getAbilities()) {
				i++;
				JButton jButton = new JButtonCustomBg(getAbilityImage(ability), true);
				if(ability instanceof Build) jButton.setText(cssYellow+((Build) ability).getBuildingName());
				jButton.setToolTipText(ability.getDescription());
				jButton.setPreferredSize(new Dimension(70, 70));
				jButton.setMaximumSize(new Dimension(70, 70));
				jButton.setMinimumSize(new Dimension(70, 70));
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
				if(i%2 == 0) {
					abilityPanel.add(jButton, "wrap 5");
				} else {
					abilityPanel.add(jButton);
				}
		}

		for (Component component : getComponents()) {

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

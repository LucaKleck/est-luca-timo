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
import abilities.AddStatusEffect;
import abilities.Build;
import abilities.CreateUnit;
import abilities.FireBall;
import abilities.LevelUp;
import abilities.MeleeAttack;
import abilities.Move;
import core.Core;
import core.Event;
import core.GameInfo;
import core.ResourceManager;
import cost.Cost;
import entity.Entity;
import entity.building.Building;
import entity.unit.Unit;
import frame.JButtonCustomBg;
import frame.JButton_01;
import frame.JPanelCustomBg;
import frame.JScrollPaneBg;
import frame.MainJFrame;
import net.miginfocom.swing.MigLayout;
import statusEffects.StatusEffect;

public class EntityPanel extends JScrollPaneBg {
	private static final long serialVersionUID = 1L;

	private Entity entity;
	private JLabel lblNextEvent;
	// Just in Development
	private String lblCanBeLeveledText = "";
	private static final String LBL_LEVEL_UP_COST_BASE_TEXT = "Leveling Costs: \n";
	private static final String LBL_LEVEL_UP_COST_MAX_LEVEL_REACHED_TEXT = "";
	private String lblLevelUpCostText;

	private Font font = new Font("MS PGothic", Font.BOLD, 16);
	private String cssYellow = MainJFrame.makeCssStyle("color: #F0F900;");

	private JPanelCustomBg abilityPanel;

	public EntityPanel(Entity entity) {
		super(ResourceManager.getBackground_04(), true);
		
		this.entity = entity;
		
		Cost levelUpCost = null;
		
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
		
		if (entity.canBeLeveled()) {
			lblCanBeLeveledText = cssYellow+"Can be Leveled!";
			levelUpCost = GameInfo.getPlayerStats().getCostManager().getLevelUpCostManager().getLevelUpCost(entity);
		} else if (entity.canBeLeveled() == false) {
			btnLevelUp.setEnabled(false);
			if(entity.getLevel() == Entity.MAX_LEVEL) {
				lblCanBeLeveledText = cssYellow+"Max Level Reached!";
			} else {
				lblCanBeLeveledText = cssYellow+"Not Enough Resources!";
				levelUpCost = GameInfo.getPlayerStats().getCostManager().getLevelUpCostManager().getLevelUpCost(entity);
			}
		} 
	
		jPanel.add(btnLevelUp, "cell 0 3");

		JLabel lblCanBeLeveled = new JLabel(lblCanBeLeveledText);
		jPanel.add(lblCanBeLeveled, "cell 0 3");
		
		if(levelUpCost != null) {
			lblLevelUpCostText = cssYellow + LBL_LEVEL_UP_COST_BASE_TEXT;
			if(levelUpCost.getFoodCost() > 0) {
				lblLevelUpCostText += cssYellow + "Food: " + levelUpCost.getFoodCost() + "\n";
			}
			if(levelUpCost.getWoodCost() > 0) {
				lblLevelUpCostText += cssYellow + "Wood: " + levelUpCost.getWoodCost() + "\n";
			}
			if(levelUpCost.getStoneCost() > 0) {
				lblLevelUpCostText += cssYellow + "Stone: " + levelUpCost.getStoneCost() + "\n";
			}
			if(levelUpCost.getMetalCost() > 0) {
				lblLevelUpCostText += cssYellow + "Metal: " + levelUpCost.getMetalCost() + "\n";
			}
			if(levelUpCost.getGoldCost() > 0) {
				lblLevelUpCostText += cssYellow + "Gold: " + levelUpCost.getGoldCost() + "\n";
			}
			if(levelUpCost.getManaStoneCost() > 0) {
				lblLevelUpCostText += cssYellow + "ManaStone: " + levelUpCost.getManaStoneCost() + "\n";
			}
		} else {
			lblLevelUpCostText = LBL_LEVEL_UP_COST_MAX_LEVEL_REACHED_TEXT;
		}
				
		JLabel lblCost = new JLabel(lblLevelUpCostText);
		jPanel.add(lblCost, "cell 0 4");

		if (this.entity.getEvent() != null) {
			lblNextEvent = new JLabel(cssYellow+"Event: " + this.entity.getEvent().getAbility().getName());
		} else {
			lblNextEvent = new JLabel(cssYellow+"Event: " + "No Event");
		}
		jPanel.add(lblNextEvent, "cell 0 5");

		String statusEffects = "\n";
		for(StatusEffect statusEffect: this.entity.getStatusEffects()) {
			statusEffects += statusEffect.getName() + "\n";
		}
		
		JLabel lblStatusEffects = new JLabel(cssYellow+"Status Effects: " + statusEffects);
		jPanel.add(lblStatusEffects, "cell 0 6");
		
		JLabel lblAbilities = new JLabel(cssYellow+"Abilities: ");
		if(!entity.getAbilities().isEmpty()) {
			jPanel.add(lblAbilities, "cell 0 7");
		}
		abilityPanel = new JPanelCustomBg(ResourceManager.getBackground_01());
		abilityPanel.setLayout(new MigLayout("insets 12 12 12 12 alignx left flowy", "[]", "[]"));
		if(!entity.getAbilities().isEmpty()) {
			jPanel.add(abilityPanel, "cell 0 8");
		}
		
		updateUserInterface();
	}

	private static BufferedImage getAbilityImage(Ability ability) {
		BufferedImage abilityImage = null;
		if (ability instanceof Ability)
			abilityImage = ResourceManager.getSpellBook01_93();
		if (ability instanceof Build) {
			if (((Build) ability).getBuildingType().matches(Building.MAGE_TOWER))
				abilityImage = ResourceManager.getSGI_81();
			else if (((Build) ability).getBuildingType().matches(Building.ARCHER_TOWER))
				abilityImage = ResourceManager.getSpellBook05_22();

			else if (((Build) ability).getBuildingType().matches(Building.BARRACKS))
				abilityImage = ResourceManager.getSpellBook05_20();
			else if (((Build) ability).getBuildingType().matches(Building.TOWN_CENTER))
				abilityImage = ResourceManager.getSpellBook05_17();
			else if (((Build) ability).getBuildingType().matches(Building.SIEGE_WORKSHOP))
				abilityImage = ResourceManager.getSpellBook05_76();

			else if (((Build) ability).getBuildingType().matches(Building.WOOD_GETTER))
				abilityImage = ResourceManager.getSpellBook05_75();
			else if (((Build) ability).getBuildingType().matches(Building.FOOD_GETTER))
				abilityImage = ResourceManager.getSpellBook05_28();
			else if (((Build) ability).getBuildingType().matches(Building.STONE_GETTER))
				abilityImage = ResourceManager.getSpellBook05_77();
			else if (((Build) ability).getBuildingType().matches(Building.METAL_GETTER))
				abilityImage = ResourceManager.getSpellBook05_06();
			else if (((Build) ability).getBuildingType().matches(Building.MANA_GETTER))
				abilityImage = ResourceManager.getSGI_addons_170();
			else if (((Build) ability).getBuildingType().matches(Building.GOLD_GETTER))
				abilityImage = ResourceManager.getSpellBook05_95();
			else
				abilityImage = ResourceManager.getSpellBook01_67();
		}
		;
		if (ability instanceof CreateUnit) {
			if (((CreateUnit) ability).getUnitType().matches(Unit.UNIT_MAGE))
				abilityImage = ResourceManager.getSGI_46();
			else if (((CreateUnit) ability).getUnitType().matches(Unit.UNIT_ARCHER))
				abilityImage = ResourceManager.getSGI_09();
			else if (((CreateUnit) ability).getUnitType().matches(Unit.UNIT_TREBUCHET))
				abilityImage = ResourceManager.getSGI_138();
			else if (((CreateUnit) ability).getUnitType().matches(Unit.UNIT_WARRIOR))
				abilityImage = ResourceManager.getSGI_63();
			else if (((CreateUnit) ability).getUnitType().matches(Unit.UNIT_BUILDER))
				abilityImage = ResourceManager.getSGI_157();
			else
				abilityImage = ResourceManager.getSGI_157();
		}
		;
		if (ability instanceof AddStatusEffect) {
			if (((AddStatusEffect) ability).getStatusEffectType().equals(AddStatusEffect.TYPE_HEAL))
				abilityImage = ResourceManager.getSpellBook01_40();
			else
				abilityImage = ResourceManager.getSpellBook01_93();
		}
		;
		if (ability instanceof Move) {
			abilityImage = ResourceManager.getSpellBook01_22();
		}
		;
		if (ability instanceof MeleeAttack) {
			abilityImage = ResourceManager.getSGI_11();
		}
		;
		if (ability instanceof FireBall) {
			abilityImage = ResourceManager.getSpellBook01_46();
		}
		;
		return abilityImage;
	}

	public void updateUserInterface() {
		abilityPanel.removeAll();
		int i = 0;
		for (Ability ability : entity.getAbilities()) {
			i++;
			JButton jButton = new JButtonCustomBg(getAbilityImage(ability), true);
			
			String toolTipText = ability.getDescription() + "\n";
			
			if (ability instanceof Build) {
				jButton.setText(cssYellow + ((Build) ability).getBuildingType());
				Cost buildingCost = GameInfo.getPlayerStats().getCostManager().getBuildingCostManager().getBuildingCost(((Build) ability).getBuildingType());
				if (buildingCost.getFoodCost() > 0) {
					toolTipText += " | Food: " + buildingCost.getFoodCost() + "\n";
				}
				if (buildingCost.getWoodCost() > 0) {
					toolTipText += " | Wood: " + buildingCost.getWoodCost() + "\n";
				}
				if (buildingCost.getStoneCost() > 0) {
					toolTipText += " | Stone: " + buildingCost.getStoneCost() + "\n";
				}
				if (buildingCost.getMetalCost() > 0) {
					toolTipText += " | Metal: " + buildingCost.getMetalCost() + "\n";
				}
				if (buildingCost.getGoldCost() > 0) {
					toolTipText += " | Gold: " + buildingCost.getGoldCost() + "\n";
				}
				if (buildingCost.getManaStoneCost() > 0) {
					toolTipText += " | ManaStone: " + buildingCost.getManaStoneCost() + "\n";
				}
			}
			if (ability instanceof CreateUnit) {
				jButton.setText(cssYellow + ((CreateUnit) ability).getUnitType());
				Cost productionCost = GameInfo.getPlayerStats().getCostManager().getProductionCostManager().getProductionCost(((CreateUnit) ability).getUnitType());
				if (productionCost.getFoodCost() > 0) {
					toolTipText += " | Food: " + productionCost.getFoodCost() + "\n";
				}
				if (productionCost.getWoodCost() > 0) {
					toolTipText += " | Wood: " + productionCost.getWoodCost() + "\n";
				}
				if (productionCost.getStoneCost() > 0) {
					toolTipText += " | Stone: " + productionCost.getStoneCost() + "\n";
				}
				if (productionCost.getMetalCost() > 0) {
					toolTipText += " | Metal: " + productionCost.getMetalCost() + "\n";
				}
				if (productionCost.getGoldCost() > 0) {
					toolTipText += " | Gold: " + productionCost.getGoldCost() + "\n";
				}
				if (productionCost.getManaStoneCost() > 0) {
					toolTipText += " | ManaStone: " + productionCost.getManaStoneCost() + "\n";
				}
			}
			jButton.setToolTipText(toolTipText);
			jButton.setPreferredSize(new Dimension(70, 70));
			jButton.setMaximumSize(new Dimension(70, 70));
			jButton.setMinimumSize(new Dimension(70, 70));
			jButton.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					lblNextEvent.setText(cssYellow + "Event: " + ability.getName());
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
			if (entity.getLevel() < ability.getAbilityMinimumLevel()) {
				jButton.setEnabled(false);
			}
			if(ability instanceof Build) {
				if(((Build)ability).buildingCanBeBuild() == false) {
					jButton.setEnabled(false);
				}
			}
			if(ability instanceof CreateUnit) {
				if(((CreateUnit)ability).unitCanBeCreated() == false) {
					jButton.setEnabled(false);
				}
			}
			if (i % 2 == 0) {
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
			lblNextEvent.setText(cssYellow + "Event: " + eventText);
		} else {
			lblNextEvent.setText(cssYellow + "Event: " + "No Event");
		}
		InteractionPanel.getCurrentPanel().repaint();
		InteractionPanel.getCurrentPanel().revalidate();
	}

}

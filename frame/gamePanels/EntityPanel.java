package frame.gamePanels;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import abilities.Ability;
import abilities.AddStatusEffect;
import abilities.Build;
import abilities.CollectResources;
import abilities.CreateUnit;
import abilities.Destroy;
import abilities.FireBall;
import abilities.Idle;
import abilities.LevelUp;
import abilities.MeleeAttack;
import abilities.Move;
import abilities.RangedAttack;
import abilities.SiegeAttack;
import core.Core;
import core.Event;
import core.GameInfo;
import core.ResourceManager;
import cost.Cost;
import entity.Entity;
import entity.building.Building;
import entity.building.BuildingRessources;
import entity.building.ResourceBuilding;
import entity.unit.Unit;
import frame.MainJFrame;
import frame.customPresets.CustomJCheckBox;
import frame.customPresets.JButtonCustomBg;
import frame.customPresets.JButton_01;
import frame.customPresets.JPanelCustomBg;
import frame.customPresets.JScrollPaneBg;
import frame.customPresets.StatisticLabel;
import net.miginfocom.swing.MigLayout;
import statusEffects.StatusEffect;

public class EntityPanel extends JScrollPaneBg {
	private static final long serialVersionUID = 1L;

	private Entity entity;
	private StatisticLabel lblNextEvent;
	// Just in Development
	private String lblCanBeLeveledText = "";
	private static final String LBL_LEVEL_UP_COST_BASE_TEXT = "<html>Leveling Costs: <br>";
	private static final String LBL_LEVEL_UP_COST_MAX_LEVEL_REACHED_TEXT = "";
	private static final String CELL_CONSTRAINT_STRING = ", gap 10, gapright 20";
	private static final String BR = "<br>";
	private String lblLevelUpCostText;

	private Font font = new Font("MS PGothic", Font.BOLD, 16);

	private JPanelCustomBg abilityPanel;

	private int mx = 0;
	
	public EntityPanel(Entity entity) {
		super(ResourceManager.getBackground_04(), true);
		
		this.entity = entity;
		
		Cost levelUpCost = null;
		
		JPanel jPanel = new JPanelCustomBg(new BufferedImage(9, 9, BufferedImage.TYPE_INT_ARGB));
		getViewport().setView(jPanel);
		
		jPanel.setLayout(new MigLayout("insets 12 12 3 0, fillx", "[]", "[]"));
		jPanel.setFont(font);

		StatisticLabel lblEntityName = new StatisticLabel(entity.getName(), true);
		jPanel.add(lblEntityName, "cell 0 0, newline, flowy, ay top, ax left, growx"+CELL_CONSTRAINT_STRING);
		
		StatisticLabel lblLevel = new StatisticLabel("Level: " + this.entity.getLevel(), true);
		StatisticLabel lblCanBeLeveled = new StatisticLabel(lblCanBeLeveledText, true);
		JButton btnLevelUp = new JButton_01("Level Up");
		

		if (entity.canBeLeveled()) {
			lblCanBeLeveledText = "Can be Leveled!";
			levelUpCost = GameInfo.getPlayerStats().getCostManager().getLevelUpCostManager().getLevelUpCost(entity);
		} else if (entity.canBeLeveled() == false) {
			btnLevelUp.setEnabled(false);
			if(entity.getLevel() == Entity.MAX_LEVEL) {
				lblCanBeLeveledText = "Max Level Reached!";
			} else {
				lblCanBeLeveledText = "Not Enough Resources!";
				levelUpCost = GameInfo.getPlayerStats().getCostManager().getLevelUpCostManager().getLevelUpCost(entity);
			}
		}
		lblCanBeLeveled.setText(lblCanBeLeveledText);
		lblCanBeLeveled.setHorizontalAlignment(SwingConstants.CENTER);
		
		btnLevelUp.addActionListener(e -> {
				entity.setEvent(new Event(entity, entity, new LevelUp(), null));
				lblNextEvent.setText("Event: " + "Level Up");
			});
		
		jPanel.add(lblLevel, "cell 0 1, growx, flowx, ay top, ax left"+CELL_CONSTRAINT_STRING+", gapright 0");
		jPanel.add(lblCanBeLeveled, "cell 0 1, growx"+CELL_CONSTRAINT_STRING+", gapright 0, gapleft 5");
		jPanel.add(btnLevelUp, "cell 0 1, growprio 1 1"+CELL_CONSTRAINT_STRING+", gapleft 5");
		
		if(levelUpCost != null) {
			lblLevelUpCostText = LBL_LEVEL_UP_COST_BASE_TEXT;
			if(levelUpCost.getFoodCost() > 0) {
				lblLevelUpCostText += "Food: " + levelUpCost.getFoodCost() + BR;
			}
			if(levelUpCost.getWoodCost() > 0) {
				lblLevelUpCostText += "Wood: " + levelUpCost.getWoodCost() + BR;
			}
			if(levelUpCost.getStoneCost() > 0) {
				lblLevelUpCostText += "Stone: " + levelUpCost.getStoneCost() + BR;
			}
			if(levelUpCost.getMetalCost() > 0) {
				lblLevelUpCostText += "Metal: " + levelUpCost.getMetalCost() + BR;
			}
			if(levelUpCost.getGoldCost() > 0) {
				lblLevelUpCostText += "Gold: " + levelUpCost.getGoldCost() + BR;
			}
			if(levelUpCost.getManaStoneCost() > 0) {
				lblLevelUpCostText += "ManaStone: " + levelUpCost.getManaStoneCost() + BR;
			}
		} else {
			lblLevelUpCostText = LBL_LEVEL_UP_COST_MAX_LEVEL_REACHED_TEXT;
		}
				
		StatisticLabel lblCost = new StatisticLabel(lblLevelUpCostText, true);
		if (entity.canBeLeveled() == true) {
			jPanel.add(lblCost, "cell 0 2, growx"+CELL_CONSTRAINT_STRING);
		}

		if (this.entity.getEvent() != null) {
			lblNextEvent = new StatisticLabel("Event: " + this.entity.getEvent().getAbility().getName(), true);
		} else {
			lblNextEvent = new StatisticLabel("Event: " + "No Event", true);
		}
		jPanel.add(lblNextEvent, "cell 0 3, flowy, ay top, ax left, growx"+CELL_CONSTRAINT_STRING);

		String statusEffects = BR;
		for(StatusEffect statusEffect: this.entity.getStatusEffects()) {
			statusEffects += statusEffect.getName() + BR;
		}
		
		StatisticLabel lblStatusEffects = new StatisticLabel("<html>Status Effects: " + statusEffects, true);
		if(!statusEffects.isEmpty()) {
			jPanel.add(lblStatusEffects, "cell 0 3, growx"+CELL_CONSTRAINT_STRING);
		}
		
		CustomJCheckBox boxAutoIdle = new CustomJCheckBox("Auto Idle", true);
		boxAutoIdle.setSelected(entity.isAutoIdle());
		boxAutoIdle.addActionListener(a -> boxAutoIdle.setSelected(!boxAutoIdle.isSelected()));
		jPanel.add(lblStatusEffects, "cell 0 3, growx"+CELL_CONSTRAINT_STRING);
		
		JButton btnCancleEvent =  new JButton_01("Cancle Event");
		btnCancleEvent.addActionListener(a -> {
			entity.setEvent(null);
			updateUserInterface();
			updateEventText(null);
			if (Core.getMainJFrame().getCurrentComponent() instanceof MainGamePanel) {
				MainGamePanel mp = (MainGamePanel) Core.getMainJFrame().getCurrentComponent();
				mp.updateUI();
				mp.getMapPanel().getMapImage().update();
			}
		});
		jPanel.add(btnCancleEvent, "cell 0 3"+CELL_CONSTRAINT_STRING);
		
		abilityPanel = new JPanelCustomBg(ResourceManager.getBackground_05());
		abilityPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 5));
		mx  = (InteractionPanel.getInteractionPanel().getWidth()-79);
		abilityPanel.setLayout(new MigLayout("insets 4 4 4 4, ax left, fillx, nogrid", "["+mx+":"+mx+":"+mx+"]", "[]"));
		if(!entity.getAbilities().isEmpty()) {
			jPanel.add(abilityPanel, "cell 0 3"+CELL_CONSTRAINT_STRING);
		}
		
		if(entity instanceof ResourceBuilding) {
			StatisticLabel lblAmountOfResources = new StatisticLabel(BuildingRessources.toString((ResourceBuilding) entity), true);
			jPanel.add(lblAmountOfResources, "cell 0 3, growx"+CELL_CONSTRAINT_STRING);
			
			StatisticLabel lblEfficiency = new StatisticLabel( ("Base Efficiency: "+((ResourceBuilding)entity).getEfficiency()), true);
			jPanel.add(lblEfficiency, "cell 0 3, growx"+CELL_CONSTRAINT_STRING);
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
				abilityImage = ResourceManager.getSGI_150();
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
		};
		if (ability instanceof AddStatusEffect) {
			if (((AddStatusEffect) ability).getStatusEffectType().equals(AddStatusEffect.TYPE_HEAL))
				abilityImage = ResourceManager.getGreen_20();
			else
				abilityImage = ResourceManager.getSpellBook01_93();
		};
		if (ability instanceof Move) {
			abilityImage = ResourceManager.getSpellBook01_22();
		};
		if (ability instanceof MeleeAttack) {
			abilityImage = ResourceManager.getSGI_11();
		};
		if (ability instanceof FireBall) {
			abilityImage = ResourceManager.getSpellBook01_46();
		};
		if (ability instanceof RangedAttack) {
			abilityImage = ResourceManager.getYellow_36();
		};
		if (ability instanceof SiegeAttack) {
			abilityImage = ResourceManager.getSGI_27();
		};
		if (ability instanceof Destroy) {
			abilityImage = ResourceManager.getSpellBook05_120();
		};
		
		if (ability instanceof Idle) {
			abilityImage = ResourceManager.getSpellBook01_11();
		};
		
		if (ability instanceof CollectResources) {
			
			int resourceType = ((CollectResources)ability).getResourceType();
			
			if(resourceType == CollectResources.RESOURCE_TYPE_FOOD) 
				abilityImage = ResourceManager.getSGI_162();
			else if(resourceType == CollectResources.RESOURCE_TYPE_WOOD) 
				abilityImage = ResourceManager.getSGI_128();
			else if(resourceType == CollectResources.RESOURCE_TYPE_STONE) 
				abilityImage = ResourceManager.getSGI_90();
			else if(resourceType == CollectResources.RESOURCE_TYPE_METAL) 
				abilityImage = ResourceManager.getSGI_addons_172();
			else if(resourceType == CollectResources.RESOURCE_TYPE_GOLD) 
				abilityImage = ResourceManager.getSGI_164();
			else if(resourceType == CollectResources.RESOURCE_TYPE_MANA_STONE) 
				abilityImage = ResourceManager.getSGI_addons_170();
			else
				abilityImage = ResourceManager.getSGI_128();
		}
		;
		return abilityImage;
	}

	public void updateUserInterface() {
		abilityPanel.removeAll();
		int i = -1;
		for (Ability ability : entity.getAbilities()) {
			i++;
			JButton abilityBtn = new JButtonCustomBg(getAbilityImage(ability), true, "");
			
			String toolTipText = ability.getDescription();
			
			if (ability instanceof Build) {
				toolTipText = MainJFrame.makeCssStyle("")+ability.getDescription() + BR;
				
				abilityBtn.setText(((Build) ability).getBuildingType());
				Cost buildingCost = GameInfo.getPlayerStats().getCostManager().getBuildingCostManager().getBuildingCost(((Build) ability).getBuildingType());
				if (buildingCost.getFoodCost() > 0) {
					toolTipText += "Food: " + buildingCost.getFoodCost() + BR;
				}
				if (buildingCost.getWoodCost() > 0) {
					toolTipText += "Wood: " + buildingCost.getWoodCost() + BR;
				}
				if (buildingCost.getStoneCost() > 0) {
					toolTipText += "Stone: " + buildingCost.getStoneCost() + BR;
				}
				if (buildingCost.getMetalCost() > 0) {
					toolTipText += "Metal: " + buildingCost.getMetalCost() + BR;
				}
				if (buildingCost.getGoldCost() > 0) {
					toolTipText += "Gold: " + buildingCost.getGoldCost() + BR;
				}
				if (buildingCost.getManaStoneCost() > 0) {
					toolTipText += "ManaStone: " + buildingCost.getManaStoneCost() + BR;
				}
			}
			if (ability instanceof CreateUnit) {
				toolTipText = MainJFrame.makeCssStyle("")+ability.getDescription() + BR;
				
				abilityBtn.setText(((CreateUnit) ability).getUnitType());
				Cost productionCost = GameInfo.getPlayerStats().getCostManager().getProductionCostManager().getProductionCost(((CreateUnit) ability).getUnitType());
				if (productionCost.getFoodCost() > 0) {
					toolTipText += "Food: " + productionCost.getFoodCost() + BR;
				}
				if (productionCost.getWoodCost() > 0) {
					toolTipText += "Wood: " + productionCost.getWoodCost() + BR;
				}
				if (productionCost.getStoneCost() > 0) {
					toolTipText += "Stone: " + productionCost.getStoneCost() + BR;
				}
				if (productionCost.getMetalCost() > 0) {
					toolTipText += "Metal: " + productionCost.getMetalCost() + BR;
				}
				if (productionCost.getGoldCost() > 0) {
					toolTipText += "Gold: " + productionCost.getGoldCost() + BR;
				}
				if (productionCost.getManaStoneCost() > 0) {
					toolTipText += "ManaStone: " + productionCost.getManaStoneCost() + BR;
				}
			}
			if (ability instanceof CollectResources) {
				abilityBtn.setEnabled(false);
			}
			if (ability.getType().matches(Ability.ABILITY_TYPE_DAMAGE)) {
				toolTipText = String.format(toolTipText, ability.getDamage());
			}
			
			abilityBtn.setToolTipText(toolTipText);
			abilityBtn.setPreferredSize(new Dimension(70, 70));
			abilityBtn.setMaximumSize(new Dimension(70, 70));
			abilityBtn.setMinimumSize(new Dimension(70, 70));
			abilityBtn.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					lblNextEvent.setText("Event: " + ability.getName());
					if (ability.maxRange > 0) {
						((MainGamePanel) Core.getMainJFrame().getCurrentComponent()).getMapPanel().getMapImage()
								.drawAbilityLayer(ability,
										GameInfo.getObjectMap().getSelected().getSelectedEntity().getXPos(),
										GameInfo.getObjectMap().getSelected().getSelectedEntity().getYPos());
						((MainGamePanel) Core.getMainJFrame().getCurrentComponent()).getMapPanel().getMapImage()
								.drawCombinedImage();
					}
					GameInfo.getObjectMap().getSelected().setSelectedAbility(ability);
					if(ability.maxRange <= 0) {
						entity.setEvent(new Event(entity, entity, ability, null));
					}
					if (ability instanceof CreateUnit) {
						((EntityPanel) InteractionPanel.getCurrentPanel()).updateEventText(ability.getName());
						GameInfo.getObjectMap().getSelected().clickedOnTile(0, 0, true);
					}
					((MainGamePanel) Core.getMainJFrame().getCurrentComponent()).updateUI();
				}
			});
			if (entity.getLevel() < ability.getAbilityMinimumLevel()) {
				abilityBtn.setEnabled(false);
			}
			if(ability instanceof Build) {
				if(((Build)ability).buildingCanBeBuild() == false) {
					abilityBtn.setEnabled(false);
				}
			}
			if(ability instanceof CreateUnit) {
				if(((CreateUnit)ability).unitCanBeCreated() == false) {
					abilityBtn.setEnabled(false);
				}
			}
			int mw = (mx-15)/abilityBtn.getPreferredSize().width;
			if(i%mw == 0) {
				abilityPanel.add(abilityBtn, "newline");
			} else {
				abilityPanel.add(abilityBtn, "");	
			}
		}

		for (Component component : getComponents()) {

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

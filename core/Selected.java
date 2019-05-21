package core;

import java.awt.Point;
import java.util.ArrayList;

import entity.Entity;
import entity.building.Building;
import entity.unit.Builder;
import entity.unit.Unit;
import events.Event;
import events.abilities.Ability;
import events.abilities.AddStatusEffect;
import events.abilities.Build;
import events.abilities.CreateUnit;
import events.abilities.Move;
import frame.gamePanels.EntityPanel;
import frame.gamePanels.InteractionPanel;
import frame.gamePanels.MainGamePanel;
import frame.gamePanels.SelectionPanel;
import frame.graphics.effects.AbilityEffect;
import map.MapTile;
import map.ObjectMap;

/**
 * Keeps track of the selected entities and handles the modes that define what
 * actions a click will bring with it
 * 
 * @author Luca Kleck
 * @version 6
 */
public class Selected {
	/**
	 * selectionMode = 0 = nothing is selected<br>
	 * selectionMode = 1 = mapTile is selected<br>
	 * selectionMode = 2 = mapTile and unit are selected<br>
	 * selectionMode = 3 = mapTile, unit and ability are selected<br>
	 * selectionMode = 4 = mapTile and building are selected<br>
	 * selectionMode = 5 = mapTile, building and ability are selected<br>
	 * selectionMode = 10 = illegal state<br>
	 */
	private int selectionMode = 0;
	private MapTile selectedMapTile = null;
	private Entity selectedEntity = null;
	private Ability selectedAbility = null;

	/**
	 * controls player-input acts on last selectionMode and then changes it to what
	 * it would be after changes
	 * 
	 * @param x           position on the map
	 * @param y           position on the map
	 * @param isLeftClick
	 */
	public void clickedOnTile(float xD, float yD, boolean isLeftClick) {
//		long start = System.nanoTime();
		int x = (int) xD;
		int y = (int) yD;
		// System.out.println("SelectionModeStart["+selectionMode+"]");
		if (!ObjectMap.inBounds(x, y)) {
			removeSelected();
		} else {
			if (isLeftClick) {
				switch (selectionMode) {
				case 0:
					selectedMapTile = GameInfo.getObjectMap().getMap()[x][y];
					checkForSingleEntity(x, y);
					break;
				case 1:
				case 2:
				case 4:
					if (!selectedMapTile.getXYPoint().equals(new Point(x, y))) {
						selectedMapTile = GameInfo.getObjectMap().getMap()[x][y];
					}
					selectedEntity = null;
					if (!GameInfo.getObjectMap().getEntitiesOnTile(x, y).isEmpty()) {
						checkForSingleEntity(x, y);
					} else {
						InteractionPanel.setCurrentPanel(null);
					}
					break;
				case 3:
				case 5:
					selectedMapTile = GameInfo.getObjectMap().getMap()[x][y];
					if (selectedAbility instanceof Move) {
						if ((selectedAbility.rangeCheck(selectedEntity.getXPos(), selectedEntity.getYPos(),
								selectedMapTile.getXPos(), selectedMapTile.getYPos()))) {
							((Unit) this.getSelectedEntity()).getMove().setMoveToPoint(new Point2DNoFxReq(xD, yD));
							this.getSelectedEntity().setEvent(new Event(selectedEntity, selectedEntity, selectedAbility,
									new AbilityEffect((Unit) selectedEntity, (Unit) selectedEntity, selectedAbility)));
						}
						removeSelected();
						((MainGamePanel) Core.getMainJFrame().getCurrentComponent()).getEventlessSelectionQueue().selectFirstInRow();
						break;
					}
					if (selectedAbility instanceof CreateUnit) {
						this.getSelectedEntity()
								.setEvent(new Event(selectedEntity, selectedEntity, selectedAbility, null));
						removeSelected();
						break;
					}
					if (selectedAbility instanceof Build) {
						if ((selectedAbility.rangeCheck(selectedEntity.getXPos(), selectedEntity.getYPos(),
								selectedMapTile.getXPos(), selectedMapTile.getYPos()))
								&& ((Build) selectedAbility).positionIsBuildable(x, y)) {
							{ // create event
								// set target point for building
								((Builder) this.getSelectedEntity()).setBuildPoint(new Point2DNoFxReq(xD, yD));
								
								this.getSelectedEntity().setEvent
									(
										new Event(selectedEntity, selectedEntity, selectedAbility,
										new AbilityEffect((Unit) selectedEntity, (Unit) selectedEntity, selectedAbility))
									);
							}
						}
						removeSelected();
						((MainGamePanel) Core.getMainJFrame().getCurrentComponent()).getEventlessSelectionQueue().selectFirstInRow();
						break;
					}
					
					/**
					 * Attack with type damage
					 */
					if (selectedAbility.getType().equals(Ability.ABILITY_TYPE_DAMAGE)) {
						if ((selectedAbility.rangeCheck(selectedEntity.getXPos(), selectedEntity.getYPos(),
								selectedMapTile.getXPos(), selectedMapTile.getYPos()))) {
							ArrayList<Entity> entitiesOnTile = GameInfo.getObjectMap().getEntitiesOnTile(x,y);
							if (!entitiesOnTile.isEmpty()) {
								if(entitiesOnTile.size() > 1) {
									InteractionPanel.setCurrentPanel(new SelectionPanel(x, y));
								} else {
									if(entitiesOnTile.get(0).isControllable()) {
										InteractionPanel.setCurrentPanel(null);
										break;
									}
									// create event
									GameInfo.getObjectMap().getSelected().getSelectedEntity().setEvent(
										new Event(selectedEntity,
											entitiesOnTile.get(0),
											selectedAbility,
											new AbilityEffect(
												selectedEntity,
												entitiesOnTile.get(0),
												selectedAbility
											)
										) 
									);
									
									GameInfo.getObjectMap().getSelected().removeSelected();
									InteractionPanel.setCurrentPanel(null);
									((MainGamePanel) Core.getMainJFrame().getCurrentComponent()).getEventlessSelectionQueue().selectFirstInRow();
								}
							} else {
								InteractionPanel.setCurrentPanel(null);
							}
							break;
						}
						removeSelected();
						break;
					}
					/**
					 * Use status-effect on x,y
					 */
					if (selectedAbility.getType().equals(Ability.ABILITY_TYPE_STATUS_EFFECT)) {
						if ((selectedAbility.rangeCheck(selectedEntity.getXPos(), selectedEntity.getYPos(),
								selectedMapTile.getXPos(), selectedMapTile.getYPos()))) {
							ArrayList<Entity> entitiesOnTile = GameInfo.getObjectMap().getEntitiesOnTile(x, y);
							if (!entitiesOnTile.isEmpty()) {
								if(entitiesOnTile.size() > 1) {
									InteractionPanel.setCurrentPanel(new SelectionPanel(x, y));
								} else {
									if(entitiesOnTile.get(0).isControllable() && ((AddStatusEffect) selectedAbility).getStatusEffect().isNegative() ||
											!entitiesOnTile.get(0).isControllable() && !((AddStatusEffect) selectedAbility).getStatusEffect().isNegative() ) {
										InteractionPanel.setCurrentPanel(null);
										break;
									} else {
										// create event
										GameInfo.getObjectMap().getSelected().getSelectedEntity().setEvent(
												new Event(selectedEntity,
													entitiesOnTile.get(0),
													selectedAbility,
													new AbilityEffect(
														selectedEntity,
														entitiesOnTile.get(0),
														selectedAbility
													)
												) 
											);
									}
									GameInfo.getObjectMap().getSelected().removeSelected();
									InteractionPanel.setCurrentPanel(null);
									((MainGamePanel) Core.getMainJFrame().getCurrentComponent()).getEventlessSelectionQueue().selectFirstInRow();
								}
							} else {
								InteractionPanel.setCurrentPanel(null);
							}
							break;
						}
						removeSelected();
						break;
					}
					
					removeSelected();
					if (!GameInfo.getObjectMap().getEntitiesOnTile(x, y).isEmpty()) {
						InteractionPanel.setCurrentPanel(new SelectionPanel(x, y));
					} else {
						InteractionPanel.setCurrentPanel(null);
					}
					break;
				default: // == 10 (reset everything)
					removeSelected();
					break;
				}
			} else {
				InteractionPanel.setCurrentPanel(null);
				GameInfo.getObjectMap().getSelected().removeSelected();
			}
		}

		// mandatory stuff
		changeSelectionMode();
		if (Core.getMainJFrame().getCurrentComponent() instanceof MainGamePanel) {
			((MainGamePanel) Core.getMainJFrame().getCurrentComponent()).getMapPanel().getMapImage().update();
			((MainGamePanel) Core.getMainJFrame().getCurrentComponent()).updateUI();
		}
		System.gc();
//		System.out.println((System.nanoTime() - start) / 1000000 + "ms");
	}

	private void checkForSingleEntity(int x, int y) {
		ArrayList<Entity> entitiesOnTile = GameInfo.getObjectMap().getEntitiesOnTile(x,y);
		if(entitiesOnTile.size() > 1) {
			InteractionPanel.setCurrentPanel(new SelectionPanel(x, y));
			return;
		}
		if (!entitiesOnTile.isEmpty()) {
			if(entitiesOnTile.get(0).isControllable()) {
				InteractionPanel.setCurrentPanel(new EntityPanel(entitiesOnTile.get(0)));
				selectedEntity = entitiesOnTile.get(0);
			} else {
				InteractionPanel.setCurrentPanel(new SelectionPanel(x, y));
			}
		} else if (InteractionPanel.getCurrentPanel() instanceof SelectionPanel) {
			InteractionPanel.setCurrentPanel(null);
		}
	}

	/**
	 * Changes the selectionMode depending on what current states the selections
	 * hold<br>
	 * <br>
	 * mapTile unit building ability selectionMode<br>
	 * 0 0 0 0 0<br>
	 * 1 0 0 0 1<br>
	 * 1 1 0 0 2<br>
	 * 1 1 0 1 3<br>
	 * 1 0 1 0 4<br>
	 * 1 0 1 1 5<br>
	 * Other states will result in selectionMode = 10 <br>
	 * 
	 * @author Luca Kleck
	 */
	private void changeSelectionMode() {
		if (selectedMapTile != null) {
			selectionMode = 1;
			if (selectedEntity instanceof Building) {
				if (selectedAbility == null) {
					selectionMode = 4;
					// System.out.println("ChangeSelectionMode["+selectionMode+"]");
					return;
				} else {
					selectionMode = 5;
					// System.out.println("ChangeSelectionMode["+selectionMode+"]");
					return;
				}
			}
			if (selectedEntity instanceof Unit) {
				if (selectedAbility == null) {
					selectionMode = 2;
					// System.out.println("ChangeSelectionMode["+selectionMode+"]");
					return;
				} else {
					selectionMode = 3;
					// System.out.println("ChangeSelectionMode["+selectionMode+"]");
					return;
				}
			}
		} else if (selectedAbility == null && selectedEntity == null) {
			selectionMode = 0;
		} else {
			selectionMode = 10;
		}
		// System.out.println("ChangeSelectionMode["+selectionMode+"]");
	}

	public MapTile getSelectedMapTile() {
		return selectedMapTile;
	}

	public Entity getSelectedEntity() {
		return selectedEntity;
	}

	public Ability getSelectedAbility() {
		return selectedAbility;
	}

	public int getSelectionMode() {
		return selectionMode;
	}

	public void setSelectedAbility(Ability selectedAbility) {
		this.selectedAbility = selectedAbility;
		changeSelectionMode();
	}

	public void setSelectedEntity(Entity toBeSelected) {
		this.selectedEntity = toBeSelected;
		if (Core.getMainJFrame().getCurrentComponent() instanceof MainGamePanel) {
			((MainGamePanel) Core.getMainJFrame().getCurrentComponent()).updateUI();
		}
		changeSelectionMode();
	}

	public void setSelectedMapTile(int x, int y) {
		selectedMapTile = GameInfo.getObjectMap().getMap()[x][y];
	}

	public void removeSelected() {
		((MainGamePanel) Core.getMainJFrame().getCurrentComponent()).getMapPanel().getMapImage().clearAbilityLayer();
		if (!(selectedAbility instanceof CreateUnit)) {
			if (Core.getMainJFrame().getCurrentComponent() instanceof MainGamePanel) {
				((MainGamePanel) Core.getMainJFrame().getCurrentComponent()).updateUI();
			}
			InteractionPanel.setCurrentPanel(null);
		}
		this.selectedAbility = null;
		this.selectedEntity = null;
		this.selectedMapTile = null;
		// TODO make some refresh or something
		changeSelectionMode();
	}

}

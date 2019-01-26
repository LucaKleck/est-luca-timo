package core;

import java.awt.Point;

import com.sun.javafx.geom.Point2D;

import abilities.Ability;
import abilities.Build;
import abilities.CollectResources;
import abilities.FireBall;
import abilities.Melee;
import abilities.Move;
import effects.MoveEffect;
import entity.Entity;
import entity.building.Building;
import entity.unit.Builder;
import entity.unit.Unit;
import frame.gamePanels.BuildingPanel;
import frame.gamePanels.InteractionPanel;
import frame.gamePanels.MainGamePanel;
import frame.gamePanels.SelectionPanel;
import map.MapImage;
import map.MapTile;
import map.ObjectMap;

/** Keeps track of the selected entities and handles the modes that define what actions a click will bring with it
 * @author Luca Kleck
 * @version 6
 */
public class Selected {
	/**
	 *  selectionMode = 0 = nothing is selected<br>
	 *  selectionMode = 1 = mapTile is selected<br>
	 *  selectionMode = 2 = mapTile and unit are selected<br>
	 *  selectionMode = 3 = mapTile, unit and ability are selected<br>
	 *  selectionMode = 4 = mapTile and building are selected<br>
	 *  selectionMode = 5 = mapTile, building and ability are selected<br>
	 *  selectionMode = 10 = illegal state<br>
	 *  selectionMode = 69 = ability and map tile (dev mode stuff);<br>
	 */
	private int selectionMode = 0;
	private MapTile selectedMapTile = null;
	private Entity selectedEntity = null;
	private Ability selectedAbility = null;

	/**
	 * controls player-input
	 * acts on last selectionMode and then changes it to what it would be after changes
	 * @param x position on the map
	 * @param y position on the map
	 * @param isLeftClick
	 */
	public void clickedOnTile(float xD, float yD, boolean isLeftClick) {
		long start = System.nanoTime();
		int x = (int) xD;
		int y = (int) yD;
//		System.out.println("SelectionModeStart["+selectionMode+"]");
		if(!ObjectMap.inBounds(x, y)) {
			removeSelected();
		} else {
			if(isLeftClick) {
				switch(selectionMode) {
				case 0:
					selectedMapTile = GameInfo.getObjectMap().getMap()[x][y];
					if(isntEmpty(x, y)) {
						InteractionPanel.setCurrentPanel(new SelectionPanel(x, y));
					} else if(InteractionPanel.getCurrentPanel() instanceof SelectionPanel) {
						InteractionPanel.setCurrentPanel(null);
					}
					break;
				case 1: case 2: case 4:
					if(!selectedMapTile.getXYPoint().equals(new Point(x, y))) {
						selectedMapTile = GameInfo.getObjectMap().getMap()[x][y];
					}
					selectedEntity = null;
					if(isntEmpty(x, y)) {
						InteractionPanel.setCurrentPanel(new SelectionPanel(x, y));
					} else {
						InteractionPanel.setCurrentPanel(null);
					}
					break;
				case 3: case 5:
					selectedMapTile = GameInfo.getObjectMap().getMap()[x][y];
					MapImage mapImage = ((MainGamePanel)Core.getMainJFrame().getCurrentComponent()).getMapPanel().getMapImage();
					if(selectedAbility instanceof Move) {
						if((selectedAbility.rangeCheck(selectedEntity.getXPos(), selectedEntity.getYPos(), selectedMapTile.getXPos(), selectedMapTile.getYPos()))) {
							((Unit)this.getSelectedEntity()).getMove().setMoveToPoint(new Point2D(xD, yD));
							this.getSelectedEntity().setEvent(new Event(selectedEntity, selectedEntity, selectedAbility, new MoveEffect((Unit) selectedEntity, (Unit) selectedEntity, (Move) selectedAbility)));
							removeSelected();
							mapImage.clearAbilityLayer();
							break;
						}
						mapImage.clearAbilityLayer();
					}
					if(selectedAbility instanceof CollectResources) {
						removeSelected();
						break;
					}
					if(selectedAbility instanceof Build) {
						if((selectedAbility.rangeCheck(selectedEntity.getXPos(), selectedEntity.getYPos(), selectedMapTile.getXPos(), selectedMapTile.getYPos()))) {
							((Builder)this.getSelectedEntity()).getBuild().setBuildPoint(new Point2D(xD, yD));
							this.getSelectedEntity().setEvent(new Event(selectedEntity, selectedEntity, selectedAbility, null));
							removeSelected();
							mapImage.clearAbilityLayer();
							break;
						}
						mapImage.clearAbilityLayer();
					}
					if(selectedAbility instanceof Melee || selectedAbility instanceof FireBall) {
						if((selectedAbility.rangeCheck(selectedEntity.getXPos(), selectedEntity.getYPos(), selectedMapTile.getXPos(), selectedMapTile.getYPos()))) {
							if(isntEmpty(x, y)) {
								InteractionPanel.setCurrentPanel(new SelectionPanel(x, y));
							} else {
								InteractionPanel.setCurrentPanel(null);
							}
							mapImage.clearAbilityLayer();
							break;
						}
						mapImage.clearAbilityLayer();
					}
					mapImage.clearAbilityLayer();
					removeSelected();
					InteractionPanel.setCurrentPanel(new SelectionPanel(x, y));
					break;
					// dev mode
				case 69:
					selectedMapTile = GameInfo.getObjectMap().getMap()[x][y];
					selectedAbility.applyAbility(null, null);
					setSelectedAbility(null);
					InteractionPanel.setCurrentPanel(new SelectionPanel(x, y));
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
		if(Core.getMainJFrame().getCurrentComponent() instanceof MainGamePanel) {
			((MainGamePanel)Core.getMainJFrame().getCurrentComponent()).getMapPanel().getMapImage().update();
			((MainGamePanel)Core.getMainJFrame().getCurrentComponent()).updateUI();
		}
		System.gc();
		System.out.println((System.nanoTime()-start)/1000000+"ms");
	}
	
	/**
	 * Changes the selectionMode depending on what current states the selections hold<br>
	 * <br>
	 *		mapTile		unit	building	ability		selectionMode<br>
	 * 		0           0       0           0           0<br>
	 *  	1			0		0			0			1<br>
	 *  	1			1		0			0			2<br>
	 *  	1			1		0			1			3<br>
	 *  	1			0		1			0			4<br>
	 *  	1			0		1			1			5<br>
	 *  	1			0		0			1			69<br>
	 * 		0			0		0			1			69<br>
	 *  Other states will result in selectionMode = 10 <br>
	 * @author Luca Kleck
	 */
	private void changeSelectionMode() {
		if(selectedMapTile != null) {
			selectionMode = 1;
			if(selectedEntity instanceof Building) {
				InteractionPanel.setCurrentPanel(new BuildingPanel ((Building) selectedEntity));
				if(selectedAbility == null) {
					selectionMode = 4;
//					System.out.println("ChangeSelectionMode["+selectionMode+"]");
					return;
				} else {
					selectionMode = 5;
//					System.out.println("ChangeSelectionMode["+selectionMode+"]");
					return;
				}
			}
			if(selectedEntity instanceof Unit) {
				if(selectedAbility == null) {
					selectionMode = 2;
//					System.out.println("ChangeSelectionMode["+selectionMode+"]");
					return;
				} else {
					selectionMode = 3;
//					System.out.println("ChangeSelectionMode["+selectionMode+"]");
					return;
				}
			}
			if(selectedAbility != null) {
				selectionMode = 69;
			}
		} else if(selectedAbility == null && selectedEntity == null){
			selectionMode = 0;
		} else if(selectedAbility != null && selectedEntity == null) {
			selectionMode = 69;
		} else {
			selectionMode = 10;
		}
//		System.out.println("ChangeSelectionMode["+selectionMode+"]");
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
	
	/** checks if there is an entity at that spot
	 * @param x coordinate of the map to be checked
	 * @param y coordinate of the map to be checked
	 * @return
	 */
	private boolean isntEmpty(int x, int y) {
		boolean test = false;
		try {
			for (int i = 0; i < GameInfo.getObjectMap().getEntityMap().size(); i++) {
				if (GameInfo.getObjectMap().getEntityMap().get(i).getXPos() == x && GameInfo.getObjectMap().getEntityMap().get(i).getYPos() == y) {
					if(GameInfo.getObjectMap().getEntityMap().get(i) != null) {
						test = true;
					}
				}
			}
		} catch (ArrayIndexOutOfBoundsException ex) {
		}
		return test;
	}

	public void setSelectedAbility(Ability selectedAbility) {
		this.selectedAbility = selectedAbility;
		changeSelectionMode();
	}
	
	public void setSelectedEntity(Entity toBeSelected) {
		this.selectedEntity = toBeSelected;
		if(Core.getMainJFrame().getCurrentComponent() instanceof MainGamePanel) {
			((MainGamePanel)Core.getMainJFrame().getCurrentComponent()).updateUI();
		}
		changeSelectionMode();
	}

	public void removeSelected() {
		this.selectedAbility = null;
		this.selectedEntity = null;
		this.selectedMapTile = null;
		if(Core.getMainJFrame().getCurrentComponent() instanceof MainGamePanel) {
			((MainGamePanel)Core.getMainJFrame().getCurrentComponent()).updateUI();
		}
		InteractionPanel.setCurrentPanel(null);
		// TODO make some refresh or something
		changeSelectionMode();
	}
	
}

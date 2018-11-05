package core;

import entity.Ability;
import entity.Entity;
import entity.building.Building;
import entity.unit.Unit;
import frame.gamePanels.AbilityPanel;
import frame.gamePanels.InfoPanel;
import frame.gamePanels.InteractionPanel;
import frame.gamePanels.MapPanel;
import frame.gamePanels.SelectionPanel;
import map.MapImage;
import map.MapTile;
import map.ObjectMap;

/** Keeps track of the selected entities and handles the modes that define what actions a click will bring with it
 * @author Luca Kleck
 *
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
	 * @param x position on the map
	 * @param y position on the map
	 * @param isLeftClick
	 */
	public void clickedOnTile(int x, int y, boolean isLeftClick) {
		if(isLeftClick) {
			if(selectionMode == 0 || selectionMode == 1 || selectionMode == 2  || selectionMode == 3  || selectionMode == 4  || selectionMode == 5 ) {
				try {
					selectedMapTile = ObjectMap.getMap()[x][y];
				} catch (IndexOutOfBoundsException e) {
					selectedMapTile = null;
				}
				if (isntEmpty(x, y)) {
					selectedEntity = null;
					InteractionPanel.setSelectionPane(new SelectionPanel(x, y, selectionMode));
				} else {
					selectedEntity = null;
					InteractionPanel.setSelectionPane(null);
				}
				
			} else if(selectionMode == 69) {
				if(selectedAbility.getName().matches("dev_create_unit")) {
					for(int z = 0; z < ObjectMap.getEntityMap()[x][y].length; z++) {
						selectedMapTile = ObjectMap.getMap()[x][y];
						if(ObjectMap.getEntityMap()[x][y][z] == null) {
							ObjectMap.getEntityMap()[x][y][z] = new Unit(x, y, "devUnit", 1, 1, 1);
							setSelectedAbility(null);
							InteractionPanel.setSelectionPane(new SelectionPanel(x, y, selectionMode));
							break;
						}
					}
				}
			}
		} else {
			InteractionPanel.setSelectionPane(null);
			ObjectMap.getSelected().removeSelected();
			if(MapImage.getMapImage() != null) {
				MapImage.getMapImage().redrawArea(0, 0, 0, 0);
			}
		}
		
		// mandatory stuff
		try {
			changeSelectionMode();
		} catch (NullPointerException nl) {
			nl.printStackTrace();
		}
		AbilityPanel.checkAbilities();
		InfoPanel.refresh();
		if(MapImage.getMapImage() != null) {
			MapImage.getMapImage().redrawArea(x,x,y,y);
		}
		MapPanel.refresh.run();
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
				if(selectedAbility == null) {
					selectionMode = 4;
					System.out.println("Selection mode: "+selectionMode);
					return;
				} else {
					selectionMode = 5;
					System.out.println("Selection mode: "+selectionMode);
					return;
				}
			}
			if(selectedEntity instanceof Unit) {
				if(selectedAbility == null) {
					selectionMode = 2;
					System.out.println("Selection mode: "+selectionMode);
					return;
				} else {
					selectionMode = 3;
					System.out.println("Selection mode: "+selectionMode);
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
		System.out.println("Selection mode: "+selectionMode);
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
			for (int i = 0; i < ObjectMap.getEntityMap()[x][y].length; i++) {
				if (ObjectMap.getEntityMap()[x][y][i] != null) {
					test = true;
				}
			}
		} catch (ArrayIndexOutOfBoundsException ex) {
		}
		return test;
	}

	public void setSelectedAbility(Ability selectedAbility) {
		this.selectedAbility = selectedAbility;
		AbilityPanel.checkAbilities();
		changeSelectionMode();
	}
	
	public void setSelectedEntity(Entity toBeSelected) {
		this.selectedEntity = toBeSelected;
		InfoPanel.refresh();
		changeSelectionMode();
		
	}

	public void removeSelected() {
		this.selectedAbility = null;
		this.selectedEntity = null;
		this.selectedMapTile = null;
		AbilityPanel.checkAbilities();
		changeSelectionMode();
	}
}

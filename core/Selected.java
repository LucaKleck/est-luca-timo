package core;

import entity.Ability;
import entity.Entity;
import frame.gamePanels.InfoPanel;
import frame.gamePanels.InteractionPanel;
import frame.gamePanels.MapPanel;
import frame.gamePanels.SelectionPanel;
import map.MapImage;
import map.MapTile;
import map.ObjectMap;

public class Selected {
	/*
	 *  selectionMode = 0 = nothing is selected
	 *  selectionMode = 1 = mapTile is selected
	 *  selectionMode = 2 = mapTile and unit are selected
	 *  selectionMode = 3 = mapTile, unit and ability are selected
	 *  selectionMode = 4 = mapTile and building are selected
	 *  selectionMode = 5 = mapTile, building and ability are selected
	 *  selectionMode = 10 = illegal state
	 *  selectionMode = 69 = ability and map tile (dev mode stuff);
	 */
	private int selectionMode = 0;
	private MapTile selectedMapTile = null;
	private Entity selectedEntity = null;
	private Ability selectedAbility = null;

	public void clickedOnTile(int x, int y, boolean isLeftClick) {
		if(isLeftClick) {
			if(selectionMode == 0) {
				try {
					selectedMapTile = ObjectMap.getMap()[x][y];
				} catch (IndexOutOfBoundsException e) {
					selectedMapTile = null;
				}
				if (isntEmpty(x, y)) {
					selectedEntity = null;
					InteractionPanel.setSelectionPane(new SelectionPanel(x, y));
				} else {
					selectedEntity = null;
					InteractionPanel.setSelectionPane(null);
				}
				
			}
		} else {
			InteractionPanel.setSelectionPane(null);
			ObjectMap.getSelected().removeSelected();
			MapImage.getMapImage().redrawArea(0, 0, 0, 0);
		}
		
		// mandatory stuff
//		changeSelectionMode();
		InfoPanel.refresh();
		MapImage.getMapImage().redrawArea(x, x, y, y);
		MapPanel.refresh.run();
	}
	/**
	 * Changes the selectionMode depending on what current states the selections hold<br>
	 * <br>
	 *		mapTile		unit	building	ability		selectionMode<br>
	 * 		0			0		0			0			0<br>
	 *  	1			0		0			0			1<br>
	 *  	1			1		0			0			2<br>
	 *  	1			1		0			1			3<br>
	 *  	1			0		1			0			4<br>
	 *  	1			0		1			1			5<br>
	 *  	1			0		0			1			69<br>
	 *  Other states will result in selectionMode = 10 <br>
	 * @author Luca Kleck
	 */
	@SuppressWarnings("unused")
	private void changeSelectionMode() {
		if(selectedMapTile != null) {
			selectionMode = 1;
		} else {
			selectionMode = 10;
		}
	}

	public void setSelectedEntity(Entity toBeSelected) {
		this.selectedEntity = toBeSelected;
		InfoPanel.refresh();
	}

	public MapTile getSelectedMapTile() {
		return selectedMapTile;
	}

	public Entity getSelectedEntity() {
		return selectedEntity;
	}

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

	public Ability getSelectedAbility() {
		return selectedAbility;
	}

	public void setSelectedAbility(Ability selectedAbility) {
		this.selectedAbility = selectedAbility;
	}

	public void removeSelected() {
		this.selectedAbility = null;
		this.selectedEntity = null;
		this.selectedMapTile = null;
	}
}

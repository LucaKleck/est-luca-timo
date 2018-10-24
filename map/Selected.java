package map;

import entity.Entity;
import frame.gamePanels.InteractionPanel;
import frame.gamePanels.SelectionPanel;

public class Selected {
	private MapTile selectedMapTile = null;
	private Entity selectedEntity = null;

	public void resetSelected(int x, int y) {
		try {			
			this.selectedMapTile = ObjectMap.getMap()[x][y];
		} catch(IndexOutOfBoundsException e) {
			this.selectedMapTile = null;
		}
		if(isntEmpty(x, y)) {
			InteractionPanel.setSelectionPane(new SelectionPanel(x,y));
		} else {
			InteractionPanel.setSelectionPane(null);
			InteractionPanel.staticValidate();
		}
		System.out.println(InteractionPanel.getSelectionPane());
		try {
			System.out.println(selectedEntity.getName());
		} catch (NullPointerException ex) {
		}
	}
	public void setSelectedEntity(Entity toBeSelected) {
		this.selectedEntity = toBeSelected;
	}
	public MapTile getSelectedMapTile() {
		return selectedMapTile;
	}
	
	public Entity getSelectedEntity() {
		return selectedEntity;
	}
	private boolean isntEmpty(int x, int y) {
		boolean test = false;
		for(int i = 0; i < ObjectMap.getEntityMap()[x][y].length; i++) {
			if(ObjectMap.getEntityMap()[x][y][i] != null) {
				test = true;
			}
		}
		return test;
	}
}

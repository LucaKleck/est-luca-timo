package map;

import entity.Entity;
import frame.gamePanels.InteractionPanel;
import frame.gamePanels.SelectionPanel;

public class Selected {
	private MapTile selectedMapTile = null;
	private Entity selectedEntity = null;
	private Entity target = null;
	private Ability selectedAbility = null;

	public void reselect(int x, int y) {
		try {
			this.selectedMapTile = ObjectMap.getMap()[x][y];
		} catch (IndexOutOfBoundsException e) {
			this.selectedMapTile = null;
		}
		if (isntEmpty(x, y)) {
			selectedEntity = null;
			InteractionPanel.setSelectionPane(new SelectionPanel(x, y));
		} else {
			this.selectedEntity = null;
			InteractionPanel.setSelectionPane(null);
			InteractionPanel.staticValidate();
		}
//		System.out.println("selection pane: " + InteractionPanel.getSelectionPane());
		try {
			System.out.println("selected unit name: " + selectedEntity.getName());
		} catch (NullPointerException ex) {
			System.out.println("nothing selected");
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

	public Entity getTarget() {
		return target;
	}

	public void setTarget(Entity target) {
		this.target = target;
	}

	public Ability getSelectedAbility() {
		return selectedAbility;
	}

	public void setSelectedAbility(Ability selectedAbility) {
		this.selectedAbility = selectedAbility;
	}
}

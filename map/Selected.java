package map;

import entity.Entity;

public class Selected {
	private MapTile selectedMapTile = null;
	private Entity selectedEntity = null;
	private int z = 0;

	public void resetSelected(int x, int y) {
		try {			
			this.selectedMapTile = ObjectMap.getMap()[x][y];
		} catch(IndexOutOfBoundsException e) {
			this.selectedMapTile = null;
		}
		
		if(selectedEntity != null && ObjectMap.getEntityMap()[x][y][z+1] != null && x == selectedEntity.getxPos() && y == selectedEntity.getyPos()) {
			z++;
		} else {
			z=0;
		}
		
		try {
			this.selectedEntity = ObjectMap.getEntityMap()[x][y][z];
		} catch(IndexOutOfBoundsException e) {
			this.selectedEntity = null;
		}
	}
	
	public MapTile getSelectedMapTile() {
		return selectedMapTile;
	}
	
	public Entity getSelectedEntity() {
		return selectedEntity;
	}
	
	public int getZ() {
		return z;
	}
	
}

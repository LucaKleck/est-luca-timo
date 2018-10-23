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
		System.out.println(z);
		try {			
			if(x == selectedEntity.getxPos() && y == selectedEntity.getyPos()) {
				z++;
			} else {
				z=0;
			}
		} catch (NullPointerException e) {
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
	
}

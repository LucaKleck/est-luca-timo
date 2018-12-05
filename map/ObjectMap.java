/**
 * ObjectMap.java - This keeps all the information of the map in itself, that means: unit array && mapTile array.
 * @author Luca Kleck
 */
package map;

import java.util.ArrayList;

import core.MapCreator;
import core.Selected;
import entity.Entity;
import frame.gamePanels.MapPanel;

public class ObjectMap {

	private Selected selected = new Selected();
	private MapTile[][] map;
	private ArrayList<Entity> entityMap;
	

	public ObjectMap() {
		map = MapCreator.createMap();
		selected = new Selected();
		entityMap = new ArrayList<>();
	}
	
	public ObjectMap(MapTile[][] map, ArrayList<Entity> entityMap) {
		this.map = map;
		this.entityMap = entityMap;
		selected = new Selected();
	}

	public MapTile[][] getMap() {
		return map;
	}

	public Selected getSelected() {
		return selected;
	}

	public ArrayList<Entity> getEntityMap() {
		return entityMap;
	}
	
	public void remakeMap() {
		map = MapCreator.createMap();
		try {
			MapPanel.getMapPanel().repaint();
		} catch (NullPointerException nl) {
		}
	}
}

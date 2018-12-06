/**
 * ObjectMap.java - This keeps all the information of the map in itself, that means: unit array && mapTile array.
 * @author Luca Kleck
 */
package map;

import java.util.ArrayList;

import core.GameInfo;
import core.MapCreator;
import core.Selected;
import entity.Entity;

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
	}
	
	public static boolean inBounds(int c) {
		if(c >= 0 && c < GameInfo.getObjectMap().getMap().length) {
			return true;
		}
		return false;
	}
	
	public static boolean inBounds(int x, int y) {
		if(inBounds(x) && inBounds(y)) {
			return true;
		}
		return false;
	}
}

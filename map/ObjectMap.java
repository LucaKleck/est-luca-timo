/**
 * ObjectMap.java - This keeps all the information of the map in itself, that means: unit array && mapTile array.
 * @author Luca Kleck
 */
package map;

import core.MapCreator;
import core.Selected;
import entity.Entity;
import frame.gamePanels.MapPanel;

public class ObjectMap {

	private static Selected selected = new Selected();
	private static MapTile[][] map;
	private static Entity[][][] entityMap;

	public ObjectMap() {
		map = MapCreator.createMap();
		entityMap = new Entity[49][49][10];
		selected = new Selected();
		
//		System.out.println("best target = " + new EntityFilter(entityMap).getBestEntityTarget(entityMap[0][0][0]).getName());

	}
	
	public ObjectMap(MapTile[][] map, Entity[][][] entityMap) {
		ObjectMap.map = map;
		ObjectMap.entityMap = entityMap;
		selected = new Selected();
	}

	public static MapTile[][] getMap() {
		return map;
	}

	public static Selected getSelected() {
		return selected;
	}

	public static Entity[][][] getEntityMap() {
		return entityMap;
	}

	public static void remakeMap() {
		map = MapCreator.createMap();
		try {
			MapImage.staticRepaint();
			MapPanel.refresh.run();
		} catch (NullPointerException nl) {
		}
	}
}

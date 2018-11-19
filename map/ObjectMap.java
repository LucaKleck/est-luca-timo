/**
 * ObjectMap.java - This keeps all the information of the map in itself, that means: unit array && mapTile array.
 * @author Luca Kleck
 */
package map;

import java.util.ArrayList;

import core.MapCreator;
import core.Selected;
import entity.Entity;
import entity.building.Building;
import frame.gamePanels.MapPanel;

public class ObjectMap {

	private static Selected selected = new Selected();
	private static MapTile[][] map;
	private static ArrayList<Entity> entityMap;
	private static ArrayList<Thread> eventQueue;

	public ObjectMap() {
		map = MapCreator.createMap();
		selected = new Selected();
		entityMap = new ArrayList<>();
		entityMap.add(new Building(0, 0, "Townsquare", 100));
		eventQueue = new ArrayList<>();
		
//		System.out.println("best target = " + new EntityFilter(entityMap).getBestEntityTarget(entityMap[0][0][0]).getName());

	}
	
	public ObjectMap(MapTile[][] map, ArrayList<Entity> entityMap) {
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

	public static ArrayList<Entity> getEntityMap() {
		return entityMap;
	}
	
	public static ArrayList<Thread> getEventQueue() {
		return eventQueue;
	}

	public static void remakeMap() {
		map = MapCreator.createMap();
		try {
			MapPanel.getMapImage().redraw();
			MapPanel.getMapPanel().repaint();
		} catch (NullPointerException nl) {
		}
	}
}

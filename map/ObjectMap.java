/**
 * ObjectMap.java - This keeps all the information of the map in itself, that means: unit array && mapTile array.
 * @author Luca Kleck
 */
package map;

import core.MapCreator;
import entity.Entity;
import entity.unit.Warrior;

public class ObjectMap {

	private static Selected selected = new Selected();
	private static MapTile[][] map;
	private static Entity entityMap[][][] = new Entity[49][49][10];

	public ObjectMap() {
		map = MapCreator.createMap();
		
		entityMap[0][0][0] = new Warrior("lass", 22, 109, 3, 0, 0);
		entityMap[0][0][1] = new Warrior("Lego", 22, 109, 3, 0, 0);
		entityMap[0][0][2] = new Warrior("Lego", 22, 109, 3, 0, 0);
		entityMap[0][0][3] = new Warrior("Lego", 22, 109, 3, 0, 0);
		
		entityMap[1][1][0] = new Warrior("Gimli", 22, 109, 3, 1, 1);
		entityMap[1][1][1] = new Warrior("Gimli", 22, 109, 3, 1, 1);
		entityMap[1][1][2] = new Warrior("Gimli", 22, 109, 3, 1, 1);
		entityMap[1][1][3] = new Warrior("Gimli", 22, 109, 3, 1, 1);
		
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

}

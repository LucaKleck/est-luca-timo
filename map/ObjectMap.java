/**
 * ObjectMap.java - This keeps all the information of the map in itself, that means: unit array && mapTile array.
 * @author Luca Kleck
 * @version 0.01
 * @since 0.01
 */
package map;

import core.MapCreator;
import entity.Entity;
import entity.unit.Unit;
import entity.unit.Warrior;

public class ObjectMap {

	// 0 = X; 1 = Y
	private static Selected selected = new Selected();
	private static MapTile[][] map;
	private static Entity entityMap[][][] = new Entity[49][49][10];
	private static Unit[][] unitMap = new Unit[49][49]; //TODO Make depth as 3rd dimension

	public ObjectMap() {
		map = MapCreator.createMap();
		
		entityMap[0][0][0] = new Warrior("lass", 22, 109, 3, 0, 0);

		unitMap[2][3] = new Warrior("Hans", 30, 30, 5, 0, 0);
		unitMap[3][4]= new Warrior("Holger", 20, 5, 6, 0, 0);
		unitMap[1][5] = new Unit("Florenz", 10, 100, 4, 0, 0);
		unitMap[8][6] = new Warrior("Dieter", 5, 80, 3, 0, 0);
		unitMap[5][7] = new Unit("Franz", 45, 60, 4, 0, 0);
		
	}

	public static MapTile[][] getMap() {
		return map;
	}

	public static Unit[][] getUnitMap() {
		return unitMap;
	}

	public static Selected getSelected() {
		return selected;
	}
	
	public static Entity[][][] getEntityMap() {
		return entityMap;
	}

}

/**
 * ObjectMap.java - This keeps all the information of the map in itself, that means: unit array && mapTile array.
 * @author Luca Kleck
 * @version 0.01
 * @since 0.01
 */
package map;

import core.MapCreator;
import entity.unit.Unit;
import entity.unit.UnitFilter;
import entity.unit.Warrior;

public class ObjectMap {

	// 0 = X; 1 = Y
	public static int[] selectedMapTile = new int[2];

	private static MapTile[][] map;
	private static Unit[][] unitMap = new Unit[49][49];

	public ObjectMap() {
		map = MapCreator.createMap();

		unitMap[2][3] = new Warrior("Hans", 30, 30, 5);
		unitMap[3][4] = new Warrior("Holger", 20, 5, 6);
		unitMap[1][5] = new Unit("Florenz", 10, 100, 4);
		unitMap[8][6] = new Warrior("Dieter", 5, 80, 3);
		unitMap[5][7] = new Unit("Franz", 45, 60, 4);
		
		new UnitFilter(unitMap);
	}

	public static MapTile[][] getMap() {
		return map;
	}

	public static Unit[][] getUnitMap() {
		return unitMap;
	}
	
	public static int[] getSelectedMapTile() {
		return selectedMapTile;
	}

}

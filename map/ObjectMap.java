/**
 * ObjectMap.java - This keeps all the information of the map in itself, that means: unit array && mapTile array.
 * @author Luca Kleck
 * @version 0.01
 * @since 0.01
 */
package map;

import core.MapCreator;
import unit.Unit;
import unit.UnitFilter;
import unit.Warrior;

public class ObjectMap {

	public static int[][] selectedMapTile = new int[1][1];
	private static MapTile[][] map;
	private static Unit[][] unitMap;

	public ObjectMap() {
		map = MapCreator.createMap();

		unitMap[2][3] = new Warrior("Hans", 30, 30);
		unitMap[3][4] = new Warrior("Holger", 20, 5);
		unitMap[1][5] = new Unit("Florenz", 10, 100);
		unitMap[8][6] = new Warrior("Dieter", 5, 80);
		unitMap[5][7] = new Unit("Franz", 45, 60);
		
		new UnitFilter(unitMap).sort(20, 10);

		// TODO CreateMap.java that loads the info of each map tile that is created from
		// XML files placed in a game_data folder
	}

	public static MapTile[][] getMap() {
		return map;
	}

	public static Unit[][] getUnitMap() {
		return unitMap;
	}

}

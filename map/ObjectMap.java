/**
 * ObjectMap.java - This keeps all the information of the map in itself, that means: unit array && mapTile array.
 * @author Luca Kleck
 * @version 0.01
 * @since 0.01
 */
package map;

import core.MapCreator;

public class ObjectMap {
	
	private static MapTile[][] map;
//	private static Unit[][] unitMap;
	
	public ObjectMap() {
		map = MapCreator.createMap();
		// TODO CreateMap.java that loads the info of each map tile that is created from XML files placed in a game_data folder
	}
	public static MapTile[][] getMap() {
		return map;
	}
	/*
	 public static Unit[][] getUnitMap() {
	 	return unitMap;
	 }
	 */
}

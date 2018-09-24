/**  
* MapCreator.java - Contains all the methods used to create a new map  
* @author Luca Kleck
* @version 0.01 
* @since 0.01
*/
package core;

import map.MapTile;

public class MapCreator {

	// map building constants
//	private static final int RIVER_COUNT = 3; // can be changed to a constructor parameter
//	private static final int COMMON_RADIUS = 5; // this is the radius that most methods will use to determine the sizes
	// types
	private static final int PLAIN = 0;
	private static final int FOREST = 1;
//	private static final int MOUNTAIN = 2;
//	private static final int LIQUID = 3;
	
	public static MapTile[][] createMap() {
		MapTile[][] map = new MapTile[49][49];
		createTiles(map);
//		plantTrees(map);
//		letTimePass(map); // this makes the map more varied... certain tiles have a chance to change to different tiles
		/*
		 * the top of the map is cold
		 * the middle is tropic
		 * the bottom cold again
		 */
		/*
		 * variations are not types just names, but they can still change the way certain things interact with the tile
		 * Changes may be:
		 * - Different Graphics
		 * - Unit Interactions
		 * - Building Interactions
		 * variations may be:
		 * - Caves/Caverns instead of mountains
		 * - Tundra instead of Plain
		 * - Jungle instead of Forest
		 */
//		smashContinentalPlatesTogether(map); // this creates Mountians, not sure how to do it yet
//		floodTheLand(map); // this puts in all the bodies of water etc.
		return map;
	}
	private static void createTiles(MapTile[][] map) {
		for( int xRow = 0; xRow < map.length; xRow++) {
			for( int yColumn = 0; yColumn < map[0].length; yColumn++) {
				map[xRow][yColumn] = createPlane(xRow,yColumn);
			}
		}
	}
	@SuppressWarnings("unused")
	private static void createSqarePatch(int xPos, int yPos, int type, String name) {
		// let it be created close to the edge of the array, just check each time for: isInBounds(int xpos, int yPos);
		
	}
	private static MapTile createPlane(int xPos, int yPos) {
		MapTile plane = new MapTile(xPos, yPos, PLAIN, "Plain");
		return plane;
	}
	@SuppressWarnings("unused")
	private static MapTile createForest(int xPos, int yPos) {
		MapTile forest = new MapTile(xPos, yPos, FOREST, "Forest"); 
		return forest;
	}
}

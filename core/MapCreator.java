package core;

import java.util.Random;

import map.MapTile;

/**  
* MapCreator.java - Contains all the methods used to create a new map  
* @author Luca Kleck
*/
public class MapCreator {

	// map building constants
		private static int forestCount = 3;
		private static int riverCount = 3;
	//	private static final int COMMON_RADIUS = 5; // this is the radius that most methods will use to determine the sizes
	/*
	 * Plant trees works like this:
	 *	1. create a vector
	 *	2. choose a random point on the map
	 *	3. create a forest with a radius on each point of the vector's path
	 *	4. reduce the vector's length by 1-2 for x or/and y,
	 *		maybe swap x and y or negate them to change direction
	 *		maybe split into another vector that will call a similar method with predefined point and vector
	 *	5. use the end point of the last vector as start point and repeat from step 3 until vector is too small
	 *
	 * the top of the map is cold
	 * the middle is tropic
	 * the bottom cold again
	 *
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
	 /**
	 * @return MapTile[][] Returns an array of MapTiles that were processed to look like a map
	 * @param riverCount The amount of rivers that will be generated
	 * @param forestCount The amount of forests that will be generated
	 */
	public static MapTile[][] createMap(int riverCount, int forestCount) {
		MapTile[][] map = new MapTile[49][49];
		createTiles(map);
		plantTrees(map);
		
//		letTimePass(map); // this makes the map more varied... certain tiles have a chance to change to different tiles
//		smashContinentalPlatesTogether(map); // this creates Mountians, not sure how to do it yet
//		floodTheLand(map); // this puts in all the bodies of water etc.
		return map;
	}
	private static void plantTrees(MapTile[][] map) {
		Random r = new Random();
		int[] vector = new int[2];
		for(int forests = 1; forests < MapCreator.forestCount; forests++) {
			vector[0] = r.nextInt(map.length);
			vector[1] = r.nextInt(map.length);
		}
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
		MapTile plane = new MapTile(xPos, yPos, MapTile.PLAIN, "Plain");
		return plane;
	}
	private static MapTile createForest(int xPos, int yPos) {
		MapTile forest = new MapTile(xPos, yPos, MapTile.FOREST, "Forest"); 
		return forest;
	}
}

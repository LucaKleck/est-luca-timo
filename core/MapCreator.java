package core;

import java.util.Random;

import map.MapTile;

/**
 * MapCreator.java - Contains all the methods used to create a new map
 * 
 * @author Luca Kleck
 */
public class MapCreator {

	// map building constants
	private static int forestCount = 25;
	@SuppressWarnings("unused")
	private static int riverCount = 3;
	private static final int COMMON_RADIUS = 7; // this is the radius that most methods will use to determine the sizes
	private static final Random rand = new Random();

	/**
	 * the top of the map is cold the middle is tropic the bottom cold again<br>
	 *<br>
	 * variations are not types just names, but they can still change the way<br>
	 * certain things interact with the tile Changes may be: - Different Graphics -<br>
	 * Unit Interactions - Building Interactions variations may be:<br>
	 *  - Caves/Caverns instead of mountains <br>
	 *  - Tundra instead of Plain <br>
	 *  - Jungle instead of Forest<br>
	 *
	 * @return MapTile[][] Returns an array of MapTiles that were processed to look
	 *         like a map
	 */
	public static MapTile[][] createMap() {
		MapTile[][] map = new MapTile[49][49];
		createTiles(map);
		plantTrees(map);

//		letTimePass(map); // this makes the map more varied... certain tiles have a chance to change to different tiles
//		smashContinentalPlatesTogether(map); // this creates Mountians, not sure how to do it yet
//		floodTheLand(map); // this puts in all the bodies of water etc.
		return map;
	}
	/**
	 * plantTrees works like this: <br>
	 * 1. create a vector <br>
	 * 2. choose a random point on the map <br>
	 * 3. create a forest with a radius on each point of the vector's path<br>
	 * 4. reduce the vector's length by 1-2 for x or/and y, maybe swap x and y, negate them to change direction or maybe split into another vector that will call a similar method with predefined point and vector <br>
	 * 5. use the end point of the last vector as start point and repeat from step 3 until vector is too small <br>
	 *<br>
	 * @param map @see {@link MapTile}
	 */
	private static void plantTrees(MapTile[][] map) {
		for (int forests = 1; forests < MapCreator.forestCount; forests++) {
			vectorHandlerForest(map, rand.nextInt(map.length), rand.nextInt(map.length));
		}
	}

	private static void vectorHandlerForest(MapTile[][] map, int xPos, int yPos) {
		int[] vector = new int[2];
		vector[0] = rand.nextInt(30) + COMMON_RADIUS;
		vector[1] = rand.nextInt(30) + COMMON_RADIUS;
		createSqarePatch(xPos - vector[0], yPos - vector[1], MapTile.FOREST, "Forest", map);
//		System.out.println("-----------------");
		while (Math.sqrt((Math.pow(vector[0], 2) + Math.pow(vector[1], 2))) > 1.43) {
//			System.out.println(Math.sqrt( (Math.pow(vector[0], 2)+Math.pow(vector[1], 2))) );
//			System.out.println("v1: " + vector[0]);
//			System.out.println("v2: " + vector[1]);
			if ((vector[0] - 1) > 0) {
				vector[0] -= 1;
			} else {
				return;
			}
			if ((vector[1] - 1) > 0) {
				vector[1] -= 1;
			} else {
				return;
			}
			createSqarePatch(xPos + vector[0], yPos + vector[1], MapTile.FOREST, "Forest", map);
		}
	}

	private static void createSqarePatch(int xPos, int yPos, int type, String name, MapTile[][] map) {
		for (int x = 0; x < COMMON_RADIUS; x++) {
			int xFinal;
			if (rand.nextBoolean()) {
				xFinal = x + xPos;
			} else {
				xFinal = x - xPos;
			}
			for (int y = 0; y < COMMON_RADIUS; y++) {
				int yFinal;
				if (rand.nextBoolean()) {
					yFinal = y + yPos;
				} else {
					yFinal = y - yPos;
				}
				if (isInBounds(xFinal, yFinal, map.length, map[0].length)) {
					if (rand.nextInt(100) + 1 < 65) {
						map[xFinal][yFinal] = createForest(xFinal, yFinal);
					}
				}
			}
		}
	}

	private static boolean isInBounds(int x, int y, int xMax, int yMax) {
		boolean isInBounds = false;
		if (x < xMax && y < yMax && x >= 0 && y >= 0) {
			isInBounds = true;
		}

		return isInBounds;
	}

	private static void createTiles(MapTile[][] map) {
		for (int xRow = 0; xRow < map.length; xRow++) {
			for (int yColumn = 0; yColumn < map[0].length; yColumn++) {
				map[xRow][yColumn] = createPlain(xRow, yColumn);
			}
		}
	}

	private static MapTile createPlain(int xPos, int yPos) {
		MapTile plane = new MapTile(xPos, yPos, MapTile.PLAIN, "Plain");
		return plane;
	}

	private static MapTile createForest(int xPos, int yPos) {
		MapTile forest = new MapTile(xPos, yPos, MapTile.FOREST, "Forest");
		return forest;
	}

	public static void setForestCount(int count) {
		MapCreator.forestCount = count;
	}

	public static void setRiverCount(int count) {
		MapCreator.riverCount = count;
	}
}

package core;

import java.util.Random;

import map.MapTile;

/**
 * MapCreator.java - Contains all the methods used to create a new map
 * 
 * @author Luca Kleck
 * @version 3
 */
public class MapCreator {

	// map building values
	private static int forestCount = 25; // make a pre-game setting
	private static int riverCount = 5; // make a pre-game setting
	private static int mountainCount = 15; // make a pre-game setting
	private static final int COMMON_RADIUS = 7; // this is the radius that most methods will use to determine the sizes
	private static final Random RAND = new Random();

	/**
	 * not implemented yet
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
//		letTimePass(map); // this makes the map more varied.. make GoL here
		smashContinentalPlatesTogether(map); // this creates Mountains, not sure how to do it yet
		floodTheLand(map); // this puts in all the bodies of water etc.
		buildRoads(map);
		
		return map;
	}
	/**
	 * This will create mountains.
	 * @param map - 2D array of @see {@link MapTile}
	 */
	private static void smashContinentalPlatesTogether(MapTile[][] map) {
		for(int i=0; i < mountainCount; i++) {
			int x = RAND.nextInt(map.length);
			int y = RAND.nextInt(map[0].length);
			for(int c = 0; c < 5; c++) {
				for(int xRow = 0; xRow < 2; xRow++) {
					for(int yCol = 0; yCol < 2; yCol++) {
						if(RAND.nextBoolean() && inBounds(map, xRow+x+c, yCol+y+c)) {
							map[xRow+x+c][yCol+y+c] = new MapTile( (xRow+x+c), (yCol+y+c), MapTile.TYPE_MOUNTAIN, MapTile.NAME_MOUNTAIN);
						} else if(inBounds(map, xRow+x-c, yCol+y+c)) {
							map[xRow+x-c][yCol+y+c] = new MapTile( (xRow+x-c), (yCol+y+c), MapTile.TYPE_MOUNTAIN, MapTile.NAME_MOUNTAIN);
						}
					}
				}
			}
		}
	}
	/**
	 * This will put in rivers and other bodies of water
	 * @param map - 2D array of @see {@link MapTile}
	 */
	private static void floodTheLand(MapTile[][] map) {
		int minRiverLength=12;
		for(int i=0; i < riverCount; i++) {
			boolean riverCreated = false;
			while(!riverCreated) {
				for(int y = 0; y <map[0].length; y++) {
					if( 1 > RAND.nextInt(1000) ) {
						for(int x = 0; x < map.length; x++) {
							if( 333 > RAND.nextInt(1000) ) {
								// Up
								if(inBounds(map, x, y)) {
									map[x][y] = new MapTile(x, y, MapTile.TYPE_LIQUID, MapTile.NAME_RIVER);
								}
								y++;
								
							} else if( 333 > RAND.nextInt(1000) ) {
								// Down
								if(inBounds(map, x, y)) {
									map[x][y] = new MapTile(x, y, MapTile.TYPE_LIQUID, MapTile.NAME_RIVER);
								}
								y--;
							}
							if(inBounds(map, x, y)) {
								map[x][y] = new MapTile(x, y, MapTile.TYPE_LIQUID, MapTile.NAME_RIVER);
//								if(inBounds(map, x, y+1)) {
//									map[x][y+1] = new MapTile(x, y, MapTile.TYPE_LIQUID, MapTile.NAME_RIVER);
//								}
							} else {
								if(x<minRiverLength) {
									i--;
								}
								break;
							}
						}
						riverCreated=true;
						break;
					}
				}
			}
		}
	}
	
	/**
	 * This will put in roads
	 * @param map - 2D array of @see {@link MapTile}
	 */
	private static void buildRoads(MapTile[][] map) {
		map[3][3].setRoad(true);
		map[4][4].setRoad(true);
		Random r = new Random();

		int column = 5;
		int row = 5;

		for(int index  = 3; index < 44; index++) {

			map[row][column].setRoad(true);
			row++;
			column++;

		}

		column = 3;
		row = 4;

		for(int index  = 3; index < 45; index++) {

			if(r.nextBoolean()) {
				map[row][column].setRoad(true);
			} else {
				map[row-1][column+1].setRoad(true);
			}
			row++;
			column++;
		}
	}
	
	private static boolean inBounds(MapTile[][] map, int x, int y) {
		boolean isInBounds = false;
		if(x >= 0 && y >= 0) {
			if(x < map.length && y < map[map.length-1].length) {
				isInBounds = true;
			}
		}
		return isInBounds;
	}
	/**
	 * plantTrees works like this: <br>
	 * 1. create a vector <br>
	 * 2. choose a random point on the map <br>
	 * 3. create a forest with a radius on each point of the vector's path<br>
	 * 4. reduce the vector's length by 1-2 for x or/and y, maybe swap x and y, negate them to change direction or maybe split into another vector that will call a similar method with predefined point and vector <br>
	 * 5. use the end point of the last vector as start point and repeat from step 3 until vector is too small <br>
	 *<br>
	 * @param map - 2D array of @see {@link MapTile}
	 */
	private static void plantTrees(MapTile[][] map) {
		for (int forests = 1; forests < MapCreator.forestCount; forests++) {
			vectorHandlerForest(map, RAND.nextInt(map.length), RAND.nextInt(map.length));
		}
	}

	private static void vectorHandlerForest(MapTile[][] map, int xPos, int yPos) {
		int[] vector = new int[2];
		vector[0] = RAND.nextInt(30) + COMMON_RADIUS;
		vector[1] = RAND.nextInt(30) + COMMON_RADIUS;
		createSquarePatch(xPos - vector[0], yPos - vector[1], MapTile.TYPE_FOREST, MapTile.NAME_FOREST, map);
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
			createSquarePatch(xPos + vector[0], yPos + vector[1], MapTile.TYPE_FOREST, MapTile.NAME_FOREST, map);
		}
	}

	private static void createSquarePatch(int xPos, int yPos, int type, String name, MapTile[][] map) {
		for (int x = 0; x < COMMON_RADIUS; x++) {
			int xFinal;
			if (RAND.nextBoolean()) {
				xFinal = x + xPos;
			} else {
				xFinal = x - xPos;
			}
			for (int y = 0; y < COMMON_RADIUS; y++) {
				int yFinal;
				if (RAND.nextBoolean()) {
					yFinal = y + yPos;
				} else {
					yFinal = y - yPos;
				}
				if (isInBounds(xFinal, yFinal, map.length, map[0].length)) {
					if (RAND.nextInt(100) + 1 < 65) {
						map[xFinal][yFinal] = new MapTile(xFinal, yFinal, type, name);
					}
				}
			}
		}
	}

	/**
	 * Method for creating a square patch, checks if it's in bounds of an area
	 * @param x
	 * @param y
	 * @param xMax
	 * @param yMax
	 * @return
	 */
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
		MapTile plane = new MapTile(xPos, yPos, MapTile.TYPE_PLAIN, MapTile.NAME_PLAIN);
		return plane;
	}

	public static void setForestCount(int count) {
		MapCreator.forestCount = count;
	}

	public static void setRiverCount(int count) {
		MapCreator.riverCount = count;
	}
	
	public static void setMountainCount(int count) {
		MapCreator.mountainCount
		= count;
	}
	
}

/**
 * ObjectMap.java - This keeps all the information of the map in itself, that means: unit array && mapTile array.
 * @author Luca Kleck
 */
package map;

import core.MapCreator;
import entity.Entity;
import entity.EntityFilter;
import entity.unit.Warrior;

public class ObjectMap {

	private static Selected selected = new Selected();
	private static MapTile[][] map;
	private static Entity entityMap[][][] = new Entity[49][49][10];

	public ObjectMap() {
		map = MapCreator.createMap();
		
		entityMap[0][0][0] = new Warrior("Onsssssssssssssssssssssssse", 22, 2, 3, 0, 0);
		entityMap[0][0][1] = new Warrior("2", 22, 3, 3, 0, 0);
		entityMap[0][0][2] = new Warrior("3", 22, 4, 3, 0, 0);
		entityMap[0][0][3] = new Warrior("4", 22, 3, 3, 0, 0);
		entityMap[0][0][4] = new Warrior("5", 22, 3, 3, 0, 0);
		entityMap[0][0][5] = new Warrior("6", 22, 3, 3, 0, 0);
		entityMap[0][0][6] = new Warrior("7", 22, 3, 3, 0, 0);
		entityMap[0][0][7] = new Warrior("8", 22, 3, 3, 0, 0);
		entityMap[0][0][8] = new Warrior("9", 22, 3, 3, 0, 0);
		
		entityMap[1][1][0] = new Warrior("10", 22, 4, 3, 1, 1);
		entityMap[1][1][1] = new Warrior("11", 22, 3, 3, 1, 1);
		entityMap[1][1][2] = new Warrior("12", 22, 2, 3, 1, 1);
		entityMap[1][1][3] = new Warrior("13", 22, 4, 3, 1, 1);
		
		System.out.println("best target = " + new EntityFilter(entityMap).getBestEntityTarget(entityMap[0][0][0]).getName());
		
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

	public static void remakeMap() {
		map = MapCreator.createMap();
		try {
			MapImage.staticRepaint();
		} catch (NullPointerException nl) {
		}
	}
}

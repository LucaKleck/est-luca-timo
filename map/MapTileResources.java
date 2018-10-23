/**  
* MapTileResources.java - Decides what are in the MapTile and the Efficiency at which they can be collected
* @author Luca Kleck
* @see MapTile
*/
package map;

import java.util.Random;

public class MapTileResources {
	private int gold = 0;
	private int food = 0;
	private int wood = 0;
	private int stone = 0;
	private int metal = 0;
	private int manaStone = 0;
	/*
	 * Types:
	 * 0 = plains
	 * 1 = forests
	 * 2 = mountains
	 * 3 = bodies of water
	 */
	public MapTileResources(int type) {
		Random r = new Random();		
		int chance = r.nextInt(100)+1;
		if(type == 0) {
			food = 100;
			if (chance <= 40) {
				gold = r.nextInt(101);
			} else if (chance <=50) {
				wood = r.nextInt(101);
			} else if (chance <= 70) {
				stone = r.nextInt(101);
			} else if (chance <= 90) {
				metal = r.nextInt(101);
			} else if (chance <= 100) {
				manaStone = r.nextInt(101);
			}
		} else if (type == 1) {
			wood = 100;
			if (chance <= 40) {
				gold = r.nextInt(101);
			} else if (chance <=50) {
				food = r.nextInt(101);
			} else if (chance <= 70) {
				stone = r.nextInt(101);
			} else if (chance <= 90) {
				metal = r.nextInt(101);
			} else if (chance <= 100) {
				manaStone = r.nextInt(101);
			}
		} else if (type == 2) {
			stone = 80;
			metal = 80;
			
		} else if (type == 3) {
			manaStone = 70;
			food = 70;
		}
	}
	
	public int getGoldPercent() {
		return gold;
	}
	public int getFoodPercent() {
		return food;
	}
	public int getWoodPercent() {
		return wood;
	}
	public int getStonePercent() {
		return stone;
	}
	public int getMetalPercent() {
		return metal;
	}
	public int getManaStonePercent() {
		return manaStone;
	}
}
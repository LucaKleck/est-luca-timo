/**  
* MapTileResources.java - Decides what are in the MapTile and the Efficiency at which they can be collected
* @author Luca Kleck
* @see MapTile
*/
package core;

import java.util.Random;

public class MapTileResources {
	private int gold = 0;
	private int food = 0;
	private int wood = 0;
	private int stone = 0;
	private int metal = 0;
	private int manaStone = 0;

	/*
	 * Types: 0 = plains 1 = forests 2 = mountains 3 = bodies of water
	 */
	public MapTileResources(int type) {
		Random r = new Random();
		int chance = r.nextInt(100) + 1;
		if (type == MapTile.TYPE_PLAIN) {
			food = 100;
			if (chance <= 10) {
				gold = 10+r.nextInt(91);
			} else if (chance <= 30) {
				wood = 10+r.nextInt(91);
			} else if (chance <= 50) {
				stone = 10+r.nextInt(91);
			} else if (chance <= 70) {
				metal = 10+r.nextInt(91);
			} else if (chance <= 90) {
				manaStone = 10+r.nextInt(91);
			}
		} else if (type == MapTile.TYPE_FOREST) {
			wood = 100;
			if (chance <= 10) {
				gold = 10+r.nextInt(91);
			} else if (chance <= 30) {
				food = 10+r.nextInt(91);
			} else if (chance <= 50) {
				stone = 10+r.nextInt(91);
			} else if (chance <= 80) {
				metal = 10+r.nextInt(91);
			} else if (chance <= 100) {
				manaStone = 10+r.nextInt(91);
			}
		} else if (type == MapTile.TYPE_MOUNTAIN) {
			stone = 80;
			metal = 80;
			if(chance <= 20) {
				gold = 20+r.nextInt(81);
			}

		} else if (type == MapTile.TYPE_LIQUID) {
			manaStone = 70;
			food = 70;
		}
	}
	public MapTileResources(int gold,int food, int wood, int stone, int metal, int manaStone) {
		this.gold = gold;
		this.food = food;
		this.wood = wood;
		this.stone = stone;
		this.metal = metal;
		this.manaStone = manaStone;
	}

	@Override
	public String toString() {
		return "MapTileResources [gold=" + gold + ", food=" + food + ", wood=" + wood + ", stone=" + stone + ", metal="
				+ metal + ", manaStone=" + manaStone + "]";
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
package entity.building;

import map.ObjectMap;

public class BuildingRessources {

	private int collectableRessources;
	private int type;
	
	/*Types are: 	1 - Gold
	 * 				2 - Wood
	 * 				3 - Food
	 * 				4 - Stone
	 * 				5 - Metal
	 * 				6 - Manastone
	  */

	public BuildingRessources(int y, int x, String name) {
		if (name.matches(Building.goldgetter)) {
			collectableRessources = getGold(x, y);
		} else if (name.matches(Building.woodgetter)) {
			collectableRessources = getWood(x, y);
		} else if (name.matches(Building.foodgetter)) {
			collectableRessources = getFood(x, y);
		} else if (name.matches(Building.stonegetter)) {
			collectableRessources = getStone(x, y);
		} else if (name.matches(Building.metalgetter)) {
			collectableRessources = getMetal(x, y);
		} else if (name.matches(Building.manastonegetter)) {
			collectableRessources = getManaStone(x, y);
		}
	}
	
	private int getGold(int xCenter, int yCenter) {
		int ressources = 0;
		for (int x = (xCenter - 1); x <= (xCenter + 1); x++) {
			for (int y = (yCenter - 1); y <= (yCenter + 1); y++) {
				try {
					ressources += ObjectMap.getMap()[x][y].getMapTileResources().getGoldPercent();
				} catch (NullPointerException nl) {
				}
			}
		}
		type = 1;
		return ressources;
	}

	private int getWood(int xCenter, int yCenter) {
		int ressources = 0;
		for (int x = (xCenter - 1); x <= (xCenter + 1); x++) {
			for (int y = (yCenter - 1); y <= (yCenter + 1); y++) {
				try {
					ressources += ObjectMap.getMap()[x][y].getMapTileResources().getWoodPercent();
				} catch (NullPointerException nl) {
				}
			}
		}
		type = 2;
		return ressources;
	}

	private int getFood(int xCenter, int yCenter) {
		int ressources = 0;
		for (int x = (xCenter - 1); x <= (xCenter + 1); x++) {
			for (int y = (yCenter - 1); y <= (yCenter + 1); y++) {
				try {
					ressources += ObjectMap.getMap()[x][y].getMapTileResources().getFoodPercent();
				} catch (NullPointerException nl) {
				}
			}
		}
		type = 3;
		return ressources;
	}

	private int getStone(int xCenter, int yCenter) {
		int ressources = 0;
		for (int x = (xCenter - 1); x <= (xCenter + 1); x++) {
			for (int y = (yCenter - 1); y <= (yCenter + 1); y++) {
				try {
					ressources += ObjectMap.getMap()[x][y].getMapTileResources().getStonePercent();
				} catch (NullPointerException nl) {
				}
			}
		}
		type = 4;
		return ressources;
	}

	private int getMetal(int xCenter, int yCenter) {
		int ressources = 0;
		for (int x = (xCenter - 1); x <= (xCenter + 1); x++) {
			for (int y = (yCenter - 1); y <= (yCenter + 1); y++) {
				try {
					ressources += ObjectMap.getMap()[x][y].getMapTileResources().getMetalPercent();
				} catch (NullPointerException nl) {
				}
			}
		}
		type = 5;
		return ressources;
	}

	private int getManaStone(int xCenter, int yCenter) {
		int ressources = 0;
		for (int x = (xCenter - 1); x <= (xCenter + 1); x++) {
			for (int y = (yCenter - 1); y <= (yCenter + 1); y++) {
				try {
					ressources += ObjectMap.getMap()[x][y].getMapTileResources().getManaStonePercent();
				} catch (NullPointerException nl) {
				}
			}
		}
		type = 6;
		return ressources;
	}

}

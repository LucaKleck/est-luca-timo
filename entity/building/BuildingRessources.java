package entity.building;

import map.ObjectMap;

public class BuildingRessources {

	private int collectibleRessources;
	private int type;

	public BuildingRessources(int y, int x, String name) {
		if (name.matches(Building.woodgetter)) {
			collectibleRessources = getWood(x, y);
		} else if (name.matches(Building.goldgetter)) {
			collectibleRessources = getGold(x, y);
		} else if (name.matches(Building.foodgetter)) {
			collectibleRessources = getFood(x, y);
		} else if (name.matches(Building.stonegetter)) {
			collectibleRessources = getStone(x, y);
		} else if (name.matches(Building.metalgetter)) {
			collectibleRessources = getMetal(x, y);
		} else if (name.toLowerCase().matches("manastonegetter")) {
			collectibleRessources = getManaStone(x, y);
		}
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
		return ressources;
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
		return ressources;
	}

}

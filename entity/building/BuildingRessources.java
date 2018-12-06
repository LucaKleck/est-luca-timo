package entity.building;

import core.GameInfo;
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

	public BuildingRessources(int x, int y, String name) {
		collectableRessources = getRessources(x, y, name);
	}
	
	private int getRessources(int xCenter, int yCenter, String name) {
		int ressources = 0;
		for (int x = (xCenter - 1); x <= (xCenter + 1); x++) {
			for (int y = (yCenter - 1); y <= (yCenter + 1); y++) {
				if(ObjectMap.inBounds(x, y)) {
					if (name.matches(Building.GOLD_GETTER)) {
						ressources += GameInfo.getObjectMap().getMap()[x][y].getMapTileResources().getGoldPercent();
						type = 1;
					} else if (name.matches(Building.WOOD_GETTER)) {
						ressources += GameInfo.getObjectMap().getMap()[x][y].getMapTileResources().getWoodPercent();
						type = 2;
					} else if (name.matches(Building.FOOD_GETTER)) {
						ressources += GameInfo.getObjectMap().getMap()[x][y].getMapTileResources().getFoodPercent();
						type = 3;
					} else if (name.matches(Building.STONE_GETTER)) {
						ressources += GameInfo.getObjectMap().getMap()[x][y].getMapTileResources().getStonePercent();
						type = 4;
					} else if (name.matches(Building.METAL_GETTER)) {
						ressources += GameInfo.getObjectMap().getMap()[x][y].getMapTileResources().getMetalPercent();
						type = 5;
					} else if (name.matches(Building.MANA_GETTER)) {
						ressources += GameInfo.getObjectMap().getMap()[x][y].getMapTileResources().getManaStonePercent();
						type = 6;
					}
				}
			}
		}
		return ressources;
	}

	public int getCollectableRessources() {
		return collectableRessources;
	}

	public int getType() {
		return type;
	}

}

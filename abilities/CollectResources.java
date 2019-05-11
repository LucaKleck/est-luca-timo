package abilities;

import core.GameInfo;
import entity.Entity;
import entity.building.ResourceBuilding;

public class CollectResources extends Ability {

	/*Types are: 	</br>
	 * 				1 - Gold</br>
	 * 				2 - Wood</br>
	 * 				3 - Food</br>
	 * 				4 - Stone</br>
	 * 				5 - Metal</br>
	 * 				6 - Manastone</br>
	  */
	
	public static final int RESOURCE_TYPE_GOLD = 1;
	public static final int RESOURCE_TYPE_WOOD = 2;
	public static final int RESOURCE_TYPE_FOOD = 3;
	public static final int RESOURCE_TYPE_STONE = 4;
	public static final int RESOURCE_TYPE_METAL = 5;
	public static final int RESOURCE_TYPE_MANA_STONE = 6;
	
	private ResourceBuilding b;
	public CollectResources(ResourceBuilding b) {
		super(ABILITY_COLLECT_RESOURCES, ABILITY_DESC_COLLECT_RESOURCES, Ability.ABILITY_TYPE_COLLECT);
		super.maxRange = 0;
		this.b = b;
	}

	@Override
	public void applyAbility(Entity source, Entity target) {
		int resources = b.getRessources().getCollectableRessources() / 100 * b.getEfficiency();
		switch (b.getRessources().getType()) {
		case 1:
			GameInfo.getPlayerStats().getPlayerResources().addGold(resources);
			GameInfo.getPlayerStats().addTotalGoldCollected(resources);
			break;
		case 2:
			GameInfo.getPlayerStats().getPlayerResources().addWood(resources);
			GameInfo.getPlayerStats().addTotalWoodCollected(resources);
			break;
		case 3:
			GameInfo.getPlayerStats().getPlayerResources().addFood(resources);
			GameInfo.getPlayerStats().addTotalFoodCollected(resources);
			break;
		case 4:
			GameInfo.getPlayerStats().getPlayerResources().addStone(resources);
			GameInfo.getPlayerStats().addTotalStoneCollected(resources);
			break;
		case 5:
			GameInfo.getPlayerStats().getPlayerResources().addMetal(resources);
			GameInfo.getPlayerStats().addTotalMetalCollected(resources);
			break;
		case 6:
			GameInfo.getPlayerStats().getPlayerResources().addManaStone(resources);
			GameInfo.getPlayerStats().addTotalManaStoneCollected(resources);
			break;
		default:
			break;
		}
	}
	@Override
	public String toString() {
		return "Collected " + (b.getRessources().getCollectableRessources() / 100 * b.getEfficiency()) + " " + typeToString(b.getRessources().getType());
	}
	
	public static String typeToString(int type) {
		switch (type) {
		case RESOURCE_TYPE_FOOD:
			return "Food";
		case RESOURCE_TYPE_GOLD:
			return "Gold";
		case RESOURCE_TYPE_MANA_STONE:
			return "Mana Stone";
		case RESOURCE_TYPE_METAL:
			return "Metal";
		case RESOURCE_TYPE_STONE:
			return "Stone";
		case RESOURCE_TYPE_WOOD:
			return "Wood";

		default:
			return "TYPE UNKNOWN";
		}
	}
	
	public int getResourcesToBeCollected() {
		return (b.getRessources().getCollectableRessources() / 100 * b.getEfficiency());
	}
	
	public int getResourceType() {
		return b.getRessources().getType();
	}
	
}

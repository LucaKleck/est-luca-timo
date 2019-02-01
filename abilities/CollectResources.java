package abilities;

import core.GameInfo;
import entity.Entity;
import entity.building.RessourceBuilding;

public class CollectResources extends Ability {

	/*Types are: 	</br>
	 * 				1 - Gold</br>
	 * 				2 - Wood</br>
	 * 				3 - Food</br>
	 * 				4 - Stone</br>
	 * 				5 - Metal</br>
	 * 				6 - Manastone</br>
	  */
	private RessourceBuilding b;
	public CollectResources(RessourceBuilding b) {
		super(ABILITY_COLLECT_RESOURCES, ABILITY_DESC_COLLECT_RESOURCES);
		super.maxRange = 0;
		this.b = b;
	}

	@Override
	public void applyAbility(Entity source, Entity target) {
		int resources = b.getRessources().getCollectableRessources() / 100 * b.getEfficiency();
		switch (b.getRessources().getType()) {
		case 1:
			GameInfo.getPlayerStats().getPlayerResources().addGold(resources);
			break;
		case 2:
			GameInfo.getPlayerStats().getPlayerResources().addWood(resources);
			break;
		case 3:
			GameInfo.getPlayerStats().getPlayerResources().addFood(resources);
			break;
		case 4:
			GameInfo.getPlayerStats().getPlayerResources().addStone(resources);
			break;
		case 5:
			GameInfo.getPlayerStats().getPlayerResources().addMetal(resources);
			break;
		case 6:
			GameInfo.getPlayerStats().getPlayerResources().addManaStone(resources);
			break;
		default:
			break;
		}
	}
	@Override
	public String toString() {
		return "Collect resources[type="+b.getRessources().getType()+", amount="+(b.getRessources().getCollectableRessources() / 100 * b.getEfficiency())+"]";
	}
}

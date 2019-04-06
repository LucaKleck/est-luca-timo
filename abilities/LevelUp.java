package abilities;

import core.GameInfo;
import core.PlayerStats.PlayerResources;
import cost.Cost;
import cost.LevelUpCostManager;
import entity.Entity;

public class LevelUp extends Ability {
	
	private PlayerResources playerResources;
	private LevelUpCostManager levelUpCostManager;
	
	public LevelUp() {
		super(Ability.ABILITY_LEVEL_UP, Ability.ABILITY_DESC_LEVEL_UP, Ability.ABILITY_TYPE_LEVEL);
		levelUpCostManager = new LevelUpCostManager();
	}
	
	@Override
	public void applyAbility(Entity source, Entity target) {
		
		if(playerResources == null) {
			playerResources = GameInfo.getPlayerStats().getPlayerResources();
		}
		
		Cost levelUpCost = levelUpCostManager.getLevelUpCost(target);
		
		playerResources.reduceFoodBy(levelUpCost.getFoodCost());
		playerResources.reduceWoodBy(levelUpCost.getWoodCost());
		playerResources.reduceStoneBy(levelUpCost.getStoneCost());
		playerResources.reduceManaStoneBy(levelUpCost.getManaStoneCost());
		playerResources.reduceGoldBy(levelUpCost.getGoldCost());
		playerResources.reduceMetalBy(levelUpCost.getMetalCost());
		
		target.addLevel();
		
	}

}


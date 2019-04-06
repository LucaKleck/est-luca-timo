package abilities;

import core.GameInfo;
import core.PlayerStats.PlayerResources;
import entity.Entity;
import entity.LevelUpCost;
import entity.LevelUpCostManager;

public class LevelUp extends Ability {
	
	private PlayerResources playerResources;
	private LevelUpCostManager levelUpCostManager;
	
	public LevelUp() {
		super(Ability.ABILITY_LEVEL_UP, Ability.ABILITY_DESC_LEVEL_UP, Ability.ABILITY_TYPE_LEVEL);
		playerResources = GameInfo.getPlayerStats().getPlayerResources();
		levelUpCostManager = new LevelUpCostManager();
	}
	
	@Override
	public void applyAbility(Entity source, Entity target) {
		
		LevelUpCost levelUpCost = levelUpCostManager.getLevelUpCost(target);
		
		playerResources.reduceFoodBy(levelUpCost.getFoodCost());
		playerResources.reduceWoodBy(levelUpCost.getWoodCost());
		playerResources.reduceStoneBy(levelUpCost.getStoneCost());
		playerResources.reduceManaStoneBy(levelUpCost.getManaStoneCost());
		playerResources.reduceGoldBy(levelUpCost.getGoldCost());
		playerResources.reduceMetalBy(levelUpCost.getMetalCost());
		
		target.addLevel();
		
	}

}


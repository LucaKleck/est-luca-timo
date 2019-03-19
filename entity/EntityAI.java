package entity;

import java.util.ArrayList;
import java.util.Random;

import abilities.Ability;
import abilities.Move;
import core.GameInfo;
import core.Point2DNoFxReq;
import entity.unit.Builder;

public class EntityAI {

	private Random random = new Random();
	
	public EntityAI() {

	}

	public Ability getRandomAbility(Entity source) {
		ArrayList<Ability> abilities = source.getAbilities();
		Ability chosenAbility;
		if(abilities != null) {
			if(abilities.size() > 0) {
				do {
					chosenAbility = abilities.get(random.nextInt(abilities.size()));
				} while(chosenAbility instanceof Move);
				return chosenAbility;
			} else {
				return null;
			}
		}
		return null;
	}
	
	public Ability getBestAbility(int rangeToTarget, Entity source) {
		ArrayList<Ability> abilities = source.getAbilities();
		Ability chosenAbility = null;
		if(abilities != null) {
			if(abilities.size() > 0) {
				for(Ability abl: abilities) {
					if(abl instanceof Move == false && abl.maxRange >= rangeToTarget) {
						if(chosenAbility == null) {
							chosenAbility = abl;
						} else if(chosenAbility.getDamage() < abl.getDamage()) {
							chosenAbility = abl;
						}
					}
				}
			} else {
				return null;
			}
		} 
		return chosenAbility;
	}
	
	public Point2DNoFxReq getRandomBuildPoint(Builder builder) {
		
		int xPos = builder.getXPos();
		int yPos = builder.getYPos();
		
		return new Point2DNoFxReq(xPos + random.nextInt(7) - 3, yPos + random.nextInt(7) - 3);
		
		
	}
	
	public Entity getBestEntityTarget(Entity source) {

		int bestTargetPriorityPoints = 0;
		Entity bestTarget = null;
		
		for(Entity entity: GameInfo.getObjectMap().getEntityMap()) {
			
			if(entity.isControlable()) {
				if(bestTarget == null ) {
					bestTarget = entity;
					bestTargetPriorityPoints = calculatePriorityPoints(entity, source);
				} else if(bestTargetPriorityPoints < calculatePriorityPoints(entity, source)) {
					bestTarget = entity;
					bestTargetPriorityPoints = calculatePriorityPoints(entity, source);
				}
			}
		}
		
		return bestTarget;
	}

	private int calculatePriorityPoints(Entity targetEntity, Entity sourceEntity) {

		int healthPoints = (100 / targetEntity.getCurrentHealth());
		int distancePoints = calculateDifference(targetEntity.getXPos(), sourceEntity.getXPos())
				+ calculateDifference(targetEntity.getYPos(), sourceEntity.getYPos());
		return healthPoints + distancePoints;

	}

	private int calculateDifference(int x1, int x2) {

		int difference;

		if (x1 > x2) {
			difference = x1 - x2;
		} else {
			difference = x2 - x1;
		}

		return difference;
	}

}

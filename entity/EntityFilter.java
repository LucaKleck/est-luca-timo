package entity;

import java.util.ArrayList;
import java.util.Random;

import com.sun.javafx.geom.Point2D;

import abilities.Ability;
import abilities.Move;
import core.GameInfo;
import entity.unit.Builder;

public class EntityFilter {

	private Random random = new Random();
	
	public EntityFilter() {

	}

	public Ability getRandomAbility(Entity source) {
		ArrayList<Ability> abilities = source.getAbilities();
		Ability chosenAbility;
		if(abilities.size() > 0) {
			do {
				chosenAbility = abilities.get(random.nextInt(abilities.size()));
			} while(chosenAbility instanceof Move);
			return chosenAbility;
		} else {
			return null;
		}
		
	}
	
	public Point2D getRandomBuildPoint(Builder builder) {
		
		int xPos = builder.getXPos();
		int yPos = builder.getYPos();
		
		return new Point2D(xPos + random.nextInt(7) - 3, yPos + random.nextInt(7) - 3);
		
		
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

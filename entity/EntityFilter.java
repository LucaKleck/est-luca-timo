package entity;

import java.util.ArrayList;
import java.util.Random;

import abilities.Ability;
import abilities.Move;
import core.GameInfo;
import core.Point2DNoFxReq;
import entity.unit.Builder;
import entity.unit.Unit;

public class EntityFilter {

	private Random random = new Random();
	
	public EntityFilter() {

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
	
	public Ability getBestBuilderAbility(Builder builder) {
		ArrayList<Entity> entityMapInRange = new ArrayList<Entity>();
		Ability chosenAbility = null;
		
		int builderX = builder.getXPos();
		int builderY = builder.getYPos();
		
		for(Entity entity: GameInfo.getObjectMap().getEntityMap()) {
			
			int deltaX = (int) Math.sqrt(Math.pow(builderX - entity.getXPos(), 2));
			int deltaY = (int) Math.sqrt(Math.pow(builderY - entity.getYPos(), 2));
			
			if(deltaX > builder.getMaxRange() == false && deltaY > builder.getMaxRange() == false) {
				entityMapInRange.add(entity);
			}
		}
		
		ArrayList<Point2DNoFxReq> availablePoints = new ArrayList<Point2DNoFxReq>();
		
		for(int y = builderY - 3; y < builderY + 3; y++) {
			for(int x = builderX - 3; x < builderX + 3; x++) {
				for(Entity entity: entityMapInRange) {
					if(builder.positionIsBuildable(x, y, entity)) {
						availablePoints.add(new Point2DNoFxReq(x, y));
					}
				}
			}
		}
				
		Random random = new Random();
		
		if(availablePoints.size() > 0) {
			Point2DNoFxReq point = availablePoints.get(random.nextInt(availablePoints.size()));
			chosenAbility = getRandomAbility(builder);
			builder.setBuildPoint(point);
			return chosenAbility;
		} else {
			chosenAbility = builder.getMove();
			builder.getMove().setMoveToPoint(new Point2DNoFxReq(random.nextInt(builder.getXPos() - (builder.getMaxRange()) - builder.getMaxRange()), builder.getYPos() - (random.nextInt(builder.getMaxRange()) - builder.getMaxRange())));
			return chosenAbility;
		}
	}
	
	public Point2DNoFxReq getNextMovePoint(Unit unit) {
		
		int xPos = unit.getXPos();
		int range = unit.getMovementRange();
		for(int y = 0; y < 49; y++) {
			
			if(GameInfo.getObjectMap().getMap()[xPos - range][y].isRoad()) {
				
				return new Point2DNoFxReq(xPos - range, y);
				
			}
			
		}
		
		return null;
		
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

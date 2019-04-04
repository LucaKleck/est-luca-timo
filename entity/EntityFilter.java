package entity;

import java.util.ArrayList;
import java.util.Random;

import abilities.Ability;
import abilities.Build;
import abilities.Move;
import core.Event;
import core.GameInfo;
import core.Point2DNoFxReq;
import entity.building.Building;
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
		Ability chosenAbility = null;
		
		Point2DNoFxReq buildalbePoint = getBuildablePoint(builder);
		
		if(buildalbePoint != null) {
				chosenAbility = getRandomAbility(builder);
				builder.setBuildPoint(buildalbePoint);
				return chosenAbility;
		} else {
			chosenAbility = builder.getMove();
			builder.getMove().setMoveToPoint(getNextMovePoint(builder));
			return chosenAbility;
		}
	}
	
	public Point2DNoFxReq getBuildablePoint(Builder builder) {
		
		Point2DNoFxReq chosenPoint = null;
		ArrayList<Point2DNoFxReq> unbuildablePoints = new ArrayList<Point2DNoFxReq>();
		Random random = new Random();
		
		for(Entity entity: GameInfo.getObjectMap().getEntityMap()) {
			
			int deltaX = (int) Math.sqrt(Math.pow(builder.getXPos() - entity.getXPos(), 2));
			int deltaY = (int) Math.sqrt(Math.pow(builder.getYPos() - entity.getYPos(), 2));
			
			if(deltaX > builder.getMaxRange() == false && deltaY > builder.getMaxRange() == false) {
				unbuildablePoints.add(new Point2DNoFxReq(entity.getXPos(), entity.getYPos()));
			}
		}
		
		//Has a 75% chance to try to find a point, if none found or didn't try return null so the chosenAbility will be move			
		if(random.nextInt(4) < 3) {
			Point2DNoFxReq randomPoint = new Point2DNoFxReq(builder.getXPos() + (random.nextInt(7) - 3), builder.getYPos() + (random.nextInt(7) - 3));
			if(unbuildablePoints.contains(randomPoint) == false) {	
				chosenPoint = randomPoint;
			}
		}
		
		return chosenPoint;
	}
	
	public boolean positionIsBuildable(int xPos, int yPos) {
		for(Entity entity: GameInfo.getObjectMap().getEntityMap()) {
			if(entity instanceof Building) {
				if(entity.getXPos() == xPos && entity.getYPos() == yPos) {
					return false;
				}
			}
		}		
		for (Event event : GameInfo.getRoundInfo().getEventList()) {

			if (event.getAbility() instanceof Build) {

				Builder builder = (Builder) event.getSource();

				if ((int) builder.getBuildPoint().x == xPos && (int) builder.getBuildPoint().y == yPos) {
					return false;
				}
			}
		}
		return true;
	}
	
	public boolean positionIsBuildable(int xPos, int yPos, Entity entity) {
		System.out.println(entity.getName());
		if(entity instanceof Building) {
			System.out.println(entity.getClass());
			if(entity.getXPos() == xPos && entity.getYPos() == yPos) {
				return false;
			}
		}
		for (Event event : GameInfo.getRoundInfo().getEventList()) {

			if (event.getAbility() instanceof Build) {

				Builder builder = (Builder) event.getSource();

				if ((int) builder.getBuildPoint().x == xPos && (int) builder.getBuildPoint().y == yPos) {
					return false;
				}
			}
		}
		return true;
	}
	
	public Point2DNoFxReq getNextMovePoint(Unit unit) {
		
		int xPos = unit.getXPos();
		int range = unit.getMovementRange();
		for(int y = 0; y < 49; y++) {
			
			if(GameInfo.getObjectMap().getMap()[xPos - range][y].isRoad()) {
				
				return new Point2DNoFxReq(xPos - range + random.nextFloat(), y + random.nextFloat());
				
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

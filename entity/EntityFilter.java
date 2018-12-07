package entity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

@SuppressWarnings("rawtypes")
public class EntityFilter implements Comparable {

	private static Entity[][][] entityMap;
	ArrayList<Entity> sortedUnitList = new ArrayList<>();

	public EntityFilter(Entity[][][] entityMap) {
		EntityFilter.entityMap = entityMap;
	}

	public Entity getBestEntityTarget(Entity entity) {

		return null;

	}

	public static Entity[][][] getEntityMap() {
		return entityMap;
	}

	public static void setUnitMap(Entity[][][] entityMap) {
		EntityFilter.entityMap = entityMap;
	}

	public void sortEntityList(ArrayList<Entity> entityList, Entity sourceEntity) {
		Collections.sort(entityList, new Comparator<Entity>() {

			@Override
			public int compare(Entity entity1, Entity entity2) {

				Integer priorityPointsEntity1 = calculatePriorityPoints(entity1, sourceEntity);
				Integer priorityPointsEntity2 = calculatePriorityPoints(entity2, sourceEntity);

				return priorityPointsEntity1.compareTo(priorityPointsEntity2);
			}
		});

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

	@Override
	public int compareTo(Object o) {
		return 0;
	}

}

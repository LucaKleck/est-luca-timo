package entity;

import java.util.ArrayList;

@SuppressWarnings("rawtypes")
public class EntityFilter implements Comparable {

	private static Entity[][][] entityMap;
	ArrayList<Entity> sortedUnitList = new ArrayList<>();

	public EntityFilter(Entity[][][] entityMap) {
		EntityFilter.entityMap = entityMap;
	}

	public Entity getBestEntityTarget(Entity entity) {

		Entity bestEntity = null;
		int distancePoints;
		int healthPoints;
		int priorityPoints;
		int bestEntityPriorityPoints = 0;
		int radius = entity.getMaxRange() / 2;

		try {

			for (int x = 0 - radius; x < entity.getMaxRange() - radius; x++) {
				for (int y = 0 - radius; y < entity.getMaxRange() - radius; y++) {
					for (int z = 0; z < entityMap[0][1].length; z++) {

						try {
							if (x > 0 && y > 0) {

								distancePoints = 100 / (calculateDifference(entity.getXPos(), x)
										+ calculateDifference(entity.getYPos(), y) + 1);
								healthPoints = 500 / entityMap[x][y][z].getMaxHealth();
								priorityPoints = distancePoints + healthPoints;

								if (priorityPoints > bestEntityPriorityPoints && x != entity.getXPos()
										&& y != entity.getYPos()) {
									bestEntityPriorityPoints = priorityPoints;
									bestEntity = entityMap[x][y][z];
								}

							}
						} catch (java.lang.NullPointerException e) {

						}

					}
				}
			}

		} catch (IndexOutOfBoundsException e) {

		}

		return bestEntity;
	}

	public static Entity[][][] getEntityMap() {
		return entityMap;
	}

	public static void setUnitMap(Entity[][][] entityMap) {
		EntityFilter.entityMap = entityMap;
	}
	/*
	 * Old Sorting Algorithm - Saving for future if needed
	 * 
	 * public void sort(int xPosition, int yPosition) {
	 * 
	 * sortedUnitList.clear(); int distancePoints; int healthPoints; int deltaX; int
	 * deltaY; int index = 0;
	 * 
	 * for (int row = 0; row < entityMap.length; row++) { for (int column = 0;
	 * column < entityMap[row].length; column++) {
	 * 
	 * try {
	 * 
	 * deltaX = calculateDifference(xPosition, row); deltaY =
	 * calculateDifference(yPosition, column);
	 * 
	 * distancePoints = 100 / (deltaX + deltaY + 1); healthPoints = 100 /
	 * entityMap[row][column].getHealth();
	 * 
	 * entityMap[row][column].setPriorityPoints(distancePoints + healthPoints);
	 * sortedUnitList.add(entityMap[row][column]);
	 * System.out.println(sortedUnitList.get(index).toString()); index++;
	 * 
	 * } catch (java.lang.NullPointerException e) {
	 * 
	 * }
	 * 
	 * }
	 * 
	 * }
	 * 
	 * System.out.println();
	 * 
	 * Collections.sort(sortedUnitList, new Comparator<Entity>() {
	 * 
	 * @Override public int compare(Entity entity1, Entity entity2) {
	 * 
	 * int value1 = entity1.getPriorityPoints(); int value2 =
	 * entity2.getPriorityPoints();
	 * 
	 * return Integer.compare(value1, value2); } });
	 * 
	 * for (int i = 0; i < sortedUnitList.size(); i++) {
	 * System.out.println(sortedUnitList.get(i).toString()); }
	 * 
	 * }
	 */

	public int calculateDifference(int x1, int x2) {

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

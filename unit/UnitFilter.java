package unit;

import java.util.ArrayList;

public class UnitFilter {

	private static Unit[][] unitMap;
	private Unit[] sortingList;
	private Unit[] sortedUnitList;

	public UnitFilter(Unit[][] unitMap) {
		this.unitMap = unitMap;
	}

	public static Unit[][] getUnitMap() {
		return unitMap;
	}

	public static void setUnitMap(Unit[][] unitMap) {
		UnitFilter.unitMap = unitMap;
	}

	public void sort(int xPosition, int yPosition) {

		sortedUnitList = null;
		sortingList = null;
		int distancePoints;
		int healthPoints;
		int deltaX;
		int deltaY;
		int index = 0;

		for (int row = 0; row < unitMap.length; row++) {
			for (int column = 0; column < unitMap[row].length; column++) {

				if (unitMap[row][column].getClass() == Unit.class) {

					deltaX = calculateDifference(xPosition, row);
					deltaY = calculateDifference(yPosition, column);

					distancePoints = 100 / (deltaX + deltaY);
					healthPoints = 100 / unitMap[row][column].getHealth();

					unitMap[row][column].setPriorityPoints(distancePoints + healthPoints);
					sortingList[index] = unitMap[row][column];
					index++;

				}

			}

		}

		for (int i = 0; i < sortingList.length; i++) {
			if (sortingList[i].getPriorityPoints() <= sortedUnitList[i].getPriorityPoints() || sortedUnitList[i] == null) {
				sortedUnitList[i] = sortingList[i];
			}
		}
		
		for (int i = 0; i < sortingList.length; i++) {
			System.out.println(sortedUnitList[i].getPriorityPoints());
		}

	}

	public int calculateDifference(int x1, int x2) {

		int difference = 0;

		if (x1 > x2) {
			difference = x1 - x2;
		} else {
			difference = x2 - x1;
		}

		return difference;
	}

}

package entity.unit;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

@SuppressWarnings("rawtypes")
public class UnitFilter implements Comparable {

	private static Unit[][] unitMap;
	ArrayList<Unit> sortedUnitList = new ArrayList<>();

	public UnitFilter(Unit[][] unitMap) {
		UnitFilter.unitMap = unitMap;
	}

	public int[] getBestUnitTarget(int xPos, int yPos) {

		int[] bestUnitPos = { 0, 0 };

		try {

			for (int row = 0; row < 5; row++) {
				for (int column = 0; column < 5; column++) {

					// calulate

				}
			}

		} catch (IndexOutOfBoundsException e) {

		}

		return bestUnitPos;
	}

	public static Unit[][] getUnitMap() {
		return unitMap;
	}

	public static void setUnitMap(Unit[][] unitMap) {
		UnitFilter.unitMap = unitMap;
	}

	public void sort(int xPosition, int yPosition) {

		sortedUnitList.clear();
		int distancePoints;
		int healthPoints;
		int deltaX;
		int deltaY;
		int index = 0;

		for (int row = 0; row < unitMap.length; row++) {
			for (int column = 0; column < unitMap[row].length; column++) {

				try {

					deltaX = calculateDifference(xPosition, row);
					deltaY = calculateDifference(yPosition, column);

					distancePoints = 100 / (deltaX + deltaY + 1);
					healthPoints = 100 / unitMap[row][column].getHealth();

					unitMap[row][column].setPriorityPoints(distancePoints + healthPoints);
					sortedUnitList.add(unitMap[row][column]);
					System.out.println(sortedUnitList.get(index).toString());
					index++;

				} catch (java.lang.NullPointerException e) {

				}

			}

		}

		System.out.println();

		Collections.sort(sortedUnitList, new Comparator<Unit>() {
			@Override
			public int compare(Unit unit1, Unit unit2) {

				int value1 = unit1.getPriorityPoints();
				int value2 = unit2.getPriorityPoints();

				return Integer.compare(value1, value2);
			}
		});

		for (int i = 0; i < sortedUnitList.size(); i++) {
			System.out.println(sortedUnitList.get(i).toString());
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

	@Override
	public int compareTo(Object o) {
		return 0;
	}

}

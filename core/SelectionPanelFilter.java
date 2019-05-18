package core;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import entity.Entity;
import entity.building.Building;
import entity.unit.Unit;

public class SelectionPanelFilter {

	public SelectionPanelFilter() {

	}

	public void sortEntityList(ArrayList<Entity> entityList) {
		
		ArrayList<Entity> friendlyBuildingList = new ArrayList<>();
		ArrayList<Entity> enemyBuildingList = new ArrayList<>();		
		ArrayList<Entity> friendlyEntityList = new ArrayList<>();
		ArrayList<Entity> enemyEntityList = new ArrayList<>();

		for (Entity entity : entityList) {
			if (entity instanceof Unit) {
				if (entity.isControllable()) {
					friendlyEntityList.add(entity);
				} else {
					enemyEntityList.add(entity);
				}
			}
			if (entity instanceof Building) {
				if (entity.isControllable()) {
					friendlyBuildingList.add(entity);
				} else {
					enemyBuildingList.add(entity);
				}
			}

		}
		
		sortList(friendlyBuildingList);
		sortList(enemyBuildingList);
		sortList(friendlyEntityList);
		sortList(enemyEntityList);
		
		entityList.clear();
		
		entityList.addAll(friendlyBuildingList);
		entityList.addAll(enemyBuildingList);
		entityList.addAll(friendlyEntityList);
		entityList.addAll(enemyEntityList);
		

	}
	
	private void sortList(ArrayList<Entity> entityList) {
		Collections.sort(entityList, new Comparator<Entity>() {

			@Override
			public int compare(Entity entity1, Entity entity2) {

				Integer value1 = (Integer) entity1.getCurrentHealth();
				Integer value2 = (Integer) entity2.getCurrentHealth();

				return value1.compareTo(value2);
			}
		});
	}

}

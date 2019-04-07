/**
 * ObjectMap.java - This keeps all the information of the map in itself, that means: unit array && mapTile array.
 * @author Luca Kleck
 */
package map;

import java.util.ArrayList;

import core.GameInfo;
import core.MapCreator;
import core.Point2DNoFxReq;
import core.Selected;
import entity.Entity;
import entity.building.Building;
import entity.building.DefenseBuilding;
import entity.building.ProductionBuilding;
import entity.building.ResourceBuilding;
import entity.unit.Archer;
import entity.unit.Builder;
import entity.unit.Mage;
import entity.unit.Trebuchet;
import entity.unit.Warrior;

public class ObjectMap {

	private Selected selected = new Selected();
	private MapTile[][] map;
	private ArrayList<Entity> entityMap;
	private static final int PORTAL_POINT_X = 45;
	private static final int PORTAL_POINT_Y = 45;
	private static final Point2DNoFxReq PORTAL_POINT = new Point2DNoFxReq(PORTAL_POINT_X, PORTAL_POINT_Y); ;

	public ObjectMap() {
		map = MapCreator.createMap();
		selected = new Selected();
		entityMap = new ArrayList<>();
		setInitalState();
	}
	
	private void setInitalState() {
		
		entityMap.add(new ProductionBuilding(new Point2DNoFxReq(3, 3), ProductionBuilding.TOWN_CENTER, 15, 15, 0, true, new ArrayList<>()));
		entityMap.add(new ProductionBuilding(PORTAL_POINT, ProductionBuilding.PORTAL, 150, 150, 0, false, new ArrayList<>()));
		entityMap.add(new ResourceBuilding(new Point2DNoFxReq(0, 0), Building.METAL_GETTER, 10, 10, 0, true, new ArrayList<>()));
		entityMap.add(new ResourceBuilding(new Point2DNoFxReq(0, 1), Building.STONE_GETTER, 10, 10, 0, true, new ArrayList<>()));
		entityMap.add(new ResourceBuilding(new Point2DNoFxReq(0, 2), Building.MANA_GETTER, 10, 10, 0, true, new ArrayList<>()));
		entityMap.add(new ResourceBuilding(new Point2DNoFxReq(0, 3), Building.WOOD_GETTER, 10, 10, 0, true, new ArrayList<>()));
		entityMap.add(new ResourceBuilding(new Point2DNoFxReq(0, 4), Building.FOOD_GETTER, 10, 10, 0, true, new ArrayList<>()));
		entityMap.add(new ResourceBuilding(new Point2DNoFxReq(0, 5), Building.GOLD_GETTER, 10, 10, 0, true, new ArrayList<>()));
		entityMap.add(new ProductionBuilding(new Point2DNoFxReq(1, 0), Building.BARRACKS, 10, 10, 0, true, new ArrayList<>()));
		entityMap.add(new ProductionBuilding(new Point2DNoFxReq(1, 1), Building.SIEGE_WORKSHOP, 10, 10, 0, true, new ArrayList<>()));
		entityMap.add(new DefenseBuilding(new Point2DNoFxReq(2, 0), Building.ARCHER_TOWER, 10, 10, 0, true, new ArrayList<>()));
		entityMap.add(new DefenseBuilding(new Point2DNoFxReq(2, 1), Building.MAGE_TOWER, 10, 10, 0, true, new ArrayList<>()));
		
		entityMap.add(new Builder(new Point2DNoFxReq(4, 1), "Builder", 3, 0, true, new ArrayList<>()));
		entityMap.add(new Warrior(new Point2DNoFxReq(4, 2), "Warrior", 3, 0, true, new ArrayList<>()));
		entityMap.add(new Mage(new Point2DNoFxReq(4, 3), "Mage", 3, 0, true, new ArrayList<>()));
		entityMap.add(new Archer(new Point2DNoFxReq(4, 4), "Archer", 3, 0, true, new ArrayList<>()));
		entityMap.add(new Trebuchet(new Point2DNoFxReq(4, 5), "Trebuchet", 5, 0, true, new ArrayList<>()));
		// FOR UNIT STACK (check scroll and other stuff)
	}
	
	public ObjectMap(MapTile[][] map, ArrayList<Entity> entityMap) {
		this.map = map;
		this.entityMap = entityMap;
		// TODO change it so that this is loaded from the actual position
		selected = new Selected();
	}

	public MapTile[][] getMap() {
		return map;
	}

	public Selected getSelected() {
		return selected;
	}

	public ArrayList<Entity> getEntityMap() {
		return entityMap;
	}
	
	public Point2DNoFxReq getPortalPoint() {
		return PORTAL_POINT;
	}
	
	public void remakeMap() {
		map = MapCreator.createMap();
	}
	
	public static boolean inBounds(int c) {
		if(c >= 0 && c < GameInfo.getObjectMap().getMap().length) {
			return true;
		}
		return false;
	}
	
	public static boolean inBounds(int x, int y) {
		if(inBounds(x) && inBounds(y)) {
			return true;
		}
		return false;
	}
}

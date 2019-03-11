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
import entity.unit.Builder;
import entity.unit.Unit;
import entity.unit.Warrior;

public class ObjectMap {

	private Selected selected = new Selected();
	private MapTile[][] map;
	private ArrayList<Entity> entityMap;
	private Point2DNoFxReq portalPoint;

	public ObjectMap() {
		map = MapCreator.createMap();
		selected = new Selected();
		entityMap = new ArrayList<>();
		portalPoint = new Point2DNoFxReq(45, 45);
		setInitalState();
	}
	
	private void setInitalState() {
		entityMap.add(new ProductionBuilding(new Point2DNoFxReq(3, 3), ProductionBuilding.TOWN_CENTER, 15, 15, 1, true, new ArrayList<>()));
		entityMap.add(new ProductionBuilding(portalPoint, ProductionBuilding.PORTAL, 15, 15, 1, false, new ArrayList<>()));
		entityMap.add(new DefenseBuilding(new Point2DNoFxReq(7, 3), Building.WALL, 15, 15, 1, false, new ArrayList<>()));
		entityMap.add(new Builder(new Point2DNoFxReq(5, 5), Unit.UNIT_BUILDER,  3, 1, true, new ArrayList<>()));
		entityMap.add(new Warrior(new Point2DNoFxReq(5, 3), Unit.UNIT_WARRIOR,  3, 1, false, new ArrayList<>()));
	}
	
	public ObjectMap(MapTile[][] map, ArrayList<Entity> entityMap) {
		this.map = map;
		this.entityMap = entityMap;
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
		return portalPoint;
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

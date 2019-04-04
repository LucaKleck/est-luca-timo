/**
 * ObjectMap.java - This keeps all the information of the map in itself, that means: unit array && mapTile array.
 * @author Luca Kleck
 */
package map;

import java.util.ArrayList;
import java.util.Random;

import core.GameInfo;
import core.MapCreator;
import core.Point2DNoFxReq;
import core.Selected;
import entity.Entity;
import entity.building.Building;
import entity.building.DefenseBuilding;
import entity.building.ProductionBuilding;
import entity.unit.Builder;
import entity.unit.Mage;
import entity.unit.Unit;

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
		
		Random r = new Random();
		
		entityMap.add(new ProductionBuilding(new Point2DNoFxReq(3, 3), ProductionBuilding.TOWN_CENTER, 15, 15, 1, true, new ArrayList<>()));
		entityMap.add(new ProductionBuilding(portalPoint, ProductionBuilding.PORTAL, 15, 15, 1, false, new ArrayList<>()));
		entityMap.add(new DefenseBuilding(new Point2DNoFxReq(7, 3), Building.ARCHER_TOWER, 15, 15, 1, false, new ArrayList<>()));
		entityMap.add(new Builder(new Point2DNoFxReq(5 + r.nextFloat(), 5 + r.nextFloat()), Unit.UNIT_BUILDER,  3, 1, true, new ArrayList<>()));
		entityMap.add(new Mage(new Point2DNoFxReq(5 + r.nextFloat(), 5 + r.nextFloat()), Unit.UNIT_MAGE,  3, 1, true, new ArrayList<>()));
		// FOR UNIT STACK (check scroll and other stuff)
	}
	
	public ObjectMap(MapTile[][] map, ArrayList<Entity> entityMap) {
		this.map = map;
		this.entityMap = entityMap;
		// TODO change it so that this is loaded from the actual position
		portalPoint = new Point2DNoFxReq(45, 45);
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

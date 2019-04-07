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
import entity.building.ProductionBuilding;

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

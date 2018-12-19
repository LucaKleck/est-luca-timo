/**  
* MapTile.java - Represents one tile of the map
* @author Luca Kleck
* @see ObjectMap
*/
package map;

import java.awt.Point;

public class MapTile {
	public static final int TYPE_PLAIN = 0;
	public static final int TYPE_FOREST = 1;
	public static final int TYPE_MOUNTAIN = 2;
	public static final int TYPE_LIQUID = 3;
	
	public static final String NAME_PLAIN = "Plain";
	public static final String NAME_FOREST = "Forest";
	public static final String NAME_MOUNTAIN = "Mountain";
	public static final String NAME_RIVER = "River";
	
	private int type;
	private boolean isRoad;

	private int xPos;
	private int yPos;
	
	private String name;
	private MapTileResources mapTileResources;
//	private BuildingEffect buildingEffect = null;
//	private UnitEffect unitEffect = null;

	public MapTile(int xPos, int yPos, int type, String name) {
		this.xPos = xPos;
		this.yPos = yPos;
		this.type = type;
		this.name = name;
		this.mapTileResources = new MapTileResources(type);
		this.isRoad = false;
	}
	
	public MapTile(int xPos, int yPos, int type, String name, MapTileResources mapTileResources, boolean isRoad) {
		this.xPos = xPos;
		this.yPos = yPos;
		this.type = type;
		this.name = name;
		this.mapTileResources = mapTileResources;
		this.isRoad = isRoad;
	}
	@Override
	public String toString() {
		return "MapTile [xPos=" + xPos + ", yPos=" + yPos + ", type=" + type + ", name=" + name + ", mapTileResources="
				+ mapTileResources + ", isRoad=" + isRoad + "]";
	}

	// TODO add constructors with UnitEffect &/or BuildingEffect
	public int getXPos() {
		return xPos;
	}

	public int getYPos() {
		return yPos;
	}

	public int getType() {
		return type;
	}

	public String getName() {
		return name;
	}

	public MapTileResources getMapTileResources() {
		return mapTileResources;
	}
	
	public boolean isRoad() {
		return isRoad;
	}
	
	public Point getXYPoint() {
		return new Point(xPos,yPos);
	}
	/*
	 * public BuildingEffect getBuildingEffect() { return buildingEffect } public
	 * UnitEffect getUnitEffect() { return unitEffect }
	 */
}

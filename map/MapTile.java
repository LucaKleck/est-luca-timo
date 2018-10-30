/**  
* MapTile.java - Represents one tile of the map
* @author Luca Kleck
* @see ObjectMap
*/
package map;

public class MapTile {
	public static final int PLAIN = 0;
	public static final int FOREST = 1;
	public static final int MOUNTAIN = 2;
	public static final int LIQUID = 3;
	
	private int xPos;
	private int yPos;
	private int type;

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
	}
	
	public MapTile(int xPos, int yPos, int type, String name, MapTileResources mapTileResources) {
		this.xPos = xPos;
		this.yPos = yPos;
		this.type = type;
		this.name = name;
		this.mapTileResources = mapTileResources;
	}
	@Override
	public String toString() {
		return "MapTile [xPos=" + xPos + ", yPos=" + yPos + ", type=" + type + ", name=" + name + ", mapTileResources="
				+ mapTileResources + "]";
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
	/*
	 * public BuildingEffect getBuildingEffect() { return buildingEffect } public
	 * UnitEffect getUnitEffect() { return unitEffect }
	 */
}

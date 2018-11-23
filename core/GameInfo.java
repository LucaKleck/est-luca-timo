package core;

import map.ObjectMap;

public class GameInfo {
	private static ObjectMap objectMap;
	private static PlayerStats playerStats;
	
	public GameInfo(ObjectMap objectMap, PlayerStats playerStats) {
		GameInfo.objectMap = objectMap;
		GameInfo.playerStats = playerStats;
	}
	
	public GameInfo() {
		GameInfo.objectMap = new ObjectMap();
		GameInfo.playerStats = new PlayerStats();
	}

	public static synchronized ObjectMap getObjectMap() {
		return objectMap;
	}

	public static synchronized PlayerStats getPlayerStats() {
		return playerStats;
	}
	
}

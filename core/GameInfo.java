package core;

import java.util.ArrayList;

import map.ObjectMap;

public class GameInfo {
	private static ObjectMap objectMap;
	private static PlayerStats playerStats;
	private static ArrayList<Thread> eventQueue;
	
	public GameInfo(ObjectMap objectMap, PlayerStats playerStats) {
		GameInfo.objectMap = objectMap;
		GameInfo.playerStats = playerStats;
		eventQueue = new ArrayList<>();
	}
	
	public GameInfo() {
		GameInfo.objectMap = new ObjectMap();
		GameInfo.playerStats = new PlayerStats();
		eventQueue = new ArrayList<>();
	}

	public static synchronized ObjectMap getObjectMap() {
		return objectMap;
	}

	public static synchronized PlayerStats getPlayerStats() {
		return playerStats;
	}
	
	public static synchronized ArrayList<Thread> getEventQueue() {
		return eventQueue;
	}
	
}

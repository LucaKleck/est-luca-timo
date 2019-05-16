package core;

/**
 * @author Luca Kleck
 *
 */
public class GameInfo {
	private static ObjectMap objectMap;
	private static PlayerStats playerStats;
	private static RoundInfo roundInfo;
	
	/**
	 * @param objectMap - map you want to put into the current game
	 * @param playerStats - player stats you want to load
	 */
	public GameInfo(ObjectMap objectMap, PlayerStats playerStats, RoundInfo roundInfo) {
		GameInfo.objectMap = objectMap;
		GameInfo.playerStats = playerStats;
		GameInfo.roundInfo = roundInfo;
	}
	
	public GameInfo() {
		GameInfo.objectMap = new ObjectMap();
		GameInfo.playerStats = new PlayerStats();
		GameInfo.roundInfo = new RoundInfo(1);
	}

	public static synchronized ObjectMap getObjectMap() {
		return objectMap;
	}

	public static synchronized PlayerStats getPlayerStats() {
		return playerStats;
	}
	
	public static synchronized RoundInfo getRoundInfo() {
		return roundInfo;
	}
	
}

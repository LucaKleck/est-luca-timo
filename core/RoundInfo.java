package core;

import java.util.ArrayList;
import java.util.LinkedList;

import entity.building.Building;
import events.Event;
import events.EventHandler;

public class RoundInfo {
	private int round;
	private EventHandler eventHandler;
	private ArrayList<Building> newBuildings = new ArrayList<Building>();
	
	public RoundInfo(int round) {
		this.round = round;
		eventHandler = new EventHandler( new LinkedList<Event>() );
	}
	
	public RoundInfo(EventHandler eventHandler, int round) {
		this(round);
		this.eventHandler = eventHandler;
	}
	
	public synchronized EventHandler getEventHandler() {
		return eventHandler;
	}
	
	public synchronized ArrayList<Building> getNewBuildings() {
		return newBuildings;
	}
	
	public synchronized int getRoundNumber() {
		return round;
	}
	
	public void increRoundNum() {
		round++;
	}
}

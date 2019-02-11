package core;

import java.util.ArrayList;
import java.util.LinkedList;

import entity.building.Building;

public class RoundInfo {
	private LinkedList<Event> eventList = new LinkedList<>();
	private ArrayList<Building> newBuildings = new ArrayList<>();
	
	public RoundInfo() {
		
	}
	
	public RoundInfo(LinkedList<Event> eventList) {
		this.eventList = eventList;
	}
	
	public synchronized LinkedList<Event> getEventList() {
		return eventList;
	}

	public synchronized ArrayList<Building> getNewBuildings() {
		return newBuildings;
	}
}

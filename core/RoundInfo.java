package core;

import java.util.ArrayList;
import java.util.LinkedList;

import entity.building.Building;

public class RoundInfo {
	private int round;
	private LinkedList<Event> eventList = new LinkedList<>();
	private ArrayList<Building> newBuildings = new ArrayList<>();
	
	public RoundInfo(int round) {
		this.round = 30;
	}
	
	public RoundInfo(LinkedList<Event> eventList, int round) {
		this.eventList = eventList;
		this.round = round;
	}
	
	public synchronized LinkedList<Event> getEventList() {
		return eventList;
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

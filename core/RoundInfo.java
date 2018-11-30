package core;

import java.util.ArrayList;

public class RoundInfo {
	private ArrayList<Event> eventList = new ArrayList<>();
	
	public RoundInfo() {
		
	}
	
	public RoundInfo(ArrayList<Event> eventList) {
		this.eventList = eventList;
	}
	
	public synchronized ArrayList<Event> getEventList() {
		return eventList;
	}
}

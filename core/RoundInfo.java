package core;

import java.util.LinkedList;

public class RoundInfo {
	private LinkedList<Event> eventList = new LinkedList<>();
	
	public RoundInfo() {
		
	}
	
	public RoundInfo(LinkedList<Event> eventList) {
		this.eventList = eventList;
	}
	
	public synchronized LinkedList<Event> getEventList() {
		return eventList;
	}
}

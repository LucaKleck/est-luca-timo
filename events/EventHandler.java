package events;

import java.util.LinkedList;

public class EventHandler {

	private LinkedList<Event> eventList = new LinkedList<Event>();
	
	public EventHandler(LinkedList<Event> events) {
		this.eventList = events;
	}
	
	public synchronized LinkedList<Event> getEventList() {
		return eventList;
	}
	
	public synchronized void addEvent(Event e) {
		eventList.add(e);
	}

}

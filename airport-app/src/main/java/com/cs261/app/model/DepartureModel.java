package com.cs261.app.model;

// TODO need to remove planes so only the current ones in the queue are in here?

public class DepartureModel {
	private AirCraftMap aircraftMap;
	private TakeOffQueue takeoffQueue;
	private IModelOutput output = null;
	
	public DepartureModel(AirCraftMap map, TakeOffQueue queue) {
		this.aircraftMap = map;
		this.takeoffQueue = queue;
	}
	
	public void updateDepartures(AirCraft newPlane, int currentTime, int prevTime) {
		takeoffQueue.enqueue(newPlane);
	}

}

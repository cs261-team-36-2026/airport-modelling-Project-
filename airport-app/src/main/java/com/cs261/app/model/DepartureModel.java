package com.cs261.app.model;

// TODO need to add where time enter queue stored

public class DepartureModel {
	private AirCraftMap aircraftMap;
	private TakeOffQueue takeoffQueue;
	private IModelOutput output = null;
	
	public DepartureModel(AirCraftMap map, TakeOffQueue queue) {
		this.aircraftMap = map;
		this.takeoffQueue = queue;
	}
	
	// public void updateDepartures(AirCraft newPlane, int currentTime, int prevTime) {
	// 	takeoffQueue.enqueue(newPlane);
	// }
 
	public void clearedForDeparture(AirCraft newPlane, int currentTime, int prevTime) {
		takeoffQueue.enqueue(newPlane);
	}

	public void planeTakesOff(AirCraft newPlane, int currentTime, int prevTime) {
		takeoffQueue.dequeue();
	}

}

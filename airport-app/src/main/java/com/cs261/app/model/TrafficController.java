package com.cs261.app.model;

public class TrafficController {
	// TODO: add all data structures/
	// TODO: constructor
	// TODO: updateTraffic()..
	
	private AirCraftMap aircraftMap;
	private RunWayMap runways;
	private HoldingQueue holdingPattern;
	private TakeOffQueue takeoffQueue;
	private IModelOutput modelOutput;
	
	public TrafficController(AirCraftMap aircraftMap, RunWayMap runways, HoldingQueue holdingPattern,TakeOffQueue takeoffQueue) {
		this.aircraftMap = aircraftMap;
		this.runways = runways;
		this.holdingPattern = holdingPattern;
		this.takeoffQueue = takeoffQueue;
	}
	/**
	 * @return the modelOutput
	 */
	public IModelOutput getModelOutput() {
		return modelOutput;
	}

	public void updateTraffic(int currentTime, int privateTime) {
		// get stuff out of holding queue
		// get stuff out of take off queue
		// check diversions
		// convert runway
		// 
	}
	
	
}

package com.cs261.app.model;

import com.cs261.app.model.RunWay.OperatingMode;


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


		// moving planes into runways
		RunWay arrivalRunway = runways.getRunway(OperatingMode.LANDING);
		RunWay departRunway = runways.getRunway(OperatingMode.TAKEOFF);

		if (arrivalRunway != null){
			AirCraft nextLand = holdingPattern.dequeue();
			arrivalRunway.addPlane(nextLand.getCallSign());
			nextLand.addToRunway(arrivalRunway.getRunwayNumber());
		}

		if (departRunway != null){
			// get next plane in takeoff queue
			// do add plane
			// do add to runway
		}

		// updating other runways 
		// first get a list of unavailable runways of each type

	}
	
	
}

package com.cs261.app.model;

import java.util.Set;

public class ArrivalModel {
	private AirCraftMap aircraftMap;
	private HoldingQueue holdingPattern;
	private IModelOutput output = null;
	private int totalArrivals;


	public ArrivalModel(AirCraftMap map, HoldingQueue queue) {
		this.aircraftMap = map;
		this.holdingPattern = queue;
		totalArrivals = 0;
	}
	
	public void updateArrivals(AirCraft newPlane, int currentTime, int prevTime) {
		
		// increment the number of planes that have arrived ever
		// increment the number of planes that have been in the holding queue 

		holdingPattern.updateHolding();
	}

	public Set<String> getHoldingPatternAircraftIds() {
		return holdingPattern.getAircraftIds();
	}
}

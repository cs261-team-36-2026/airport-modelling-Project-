package com.cs261.app.model;

import java.util.Set;

public class ArrivalModel {
	private AirCraftMap aircraftMap;
	private HoldingQueue holdingPattern;
	private IModelOutput output = null;
	
	public ArrivalModel(AirCraftMap map, HoldingQueue queue) {
		this.aircraftMap = map;
		this.holdingPattern = queue;
	}
	
	public void updateArrivals(AirCraft newPlane, int currentTime, int prevTime) {
		holdingPattern.enqueue(newPlane);
		holdingPattern.updateHolding();
	}

	public Set<String> getHoldingPatternAircraftIds() {
		return holdingPattern.getAircraftIds();
	}
}

package com.cs261.app.model;

import java.util.ArrayList;

import com.cs261.app.model.RunWay.OperatingMode;
import com.cs261.app.model.RunWay.OperationStatus;

/**
 * Stores an array of runways. 
 * Fixed size, can't or remove runways after simulation has been started/configuration of airport has been chosen
 */
public class RunWayMap {
    /*
    TODO: extends hashmap. for now, just a constructor that does super() and nothing else. 
    Alternatively could be an array list cause its just max 10 values
    */
	RunWay[] runways;
	
	public RunWayMap(RunWay[] runwayList) {
		this.runways = runwayList;
	}
		
	/**
	 * Retrieve RunWay object from array
	 * @param number runway number of the requested runway
	 * @return RunWay object requested or none if no such runway exists
	 */
	public RunWay getRunway(String number) {
		for (int i = 0; i < runways.length; i++) {
			if (runways[i].getRunwayNumber() == number) return runways[i];
		}
		return null;
	}
	
	
	/**
	 * Get first available runway of a specific type 
	 * @param mode type of runway needed
	 * @return runway of that type or mixed runway that  is available, or null if no runway is available 
	 */
	public RunWay getRunway(OperatingMode mode) {
		for (int i = 0; i < runways.length; i++) {
			if (runways[i].getMixedModeTurn() == mode && runways[i].getStatus() == OperationStatus.AVAILABLE) return runways[i]; 
		}
		return null;
	}
	
	
	// list of unavailable runways or an update method on them

	public ArrayList<RunWay> getBusyRunway(OperatingMode mode){
		ArrayList<RunWay> list = new ArrayList<>();
		for (int i = 0; i < runways.length; i++){
			if (runways[i].getMixedModeTurn() == mode && runways[i].getStatus() == OperationStatus.UNAVAIL){
				list.add(runways[i]);
			}
		}
		return list;
	}
}

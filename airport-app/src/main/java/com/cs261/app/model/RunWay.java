package com.cs261.app.model;

import com.cs261.app.model.AirCraft.EmergencyStatus;

public class RunWay {
    /*
    TODO: fields, getters, setters, convert to... methods as described in design doc class diagrma
    */
	
	public static enum OperatingMode {
		LANDING(1),
		TAKEOFF(2),
		MIXED(3),
		;

		private final int code;
		private OperatingMode(int code){
			this.code = code;
		}
		public int getCode(){
			return this.code;
		}
	}
	
	public static enum OperationStatus {
		AVAILABLE,
		INSPECTION,
		SNOW,
		EQUIPFAIL,
		UNAVAIL, // because plane is on it 
		;
	}
	
	private String runwayNumber; // actually should be string instead
	private int length;
	private int bearing;
	private String currentPlane; // occupying aircraft callsign
	private OperationStatus status;
	private OperatingMode mode;
	private int timeSpent; // time current aircraft has spent on the runway
	private AirCraftMap airCraftMap;

	private OperatingMode mixedModeTurn;
	
	public RunWay(int length, int bearing, OperatingMode mode, OperationStatus status, AirCraftMap aircrafts) {
		this.length = length;
		this.bearing = bearing;
		this.mode = mode;
		this.status = status;
		this.currentPlane = "";
		this.timeSpent = 0;
		this.airCraftMap = aircrafts;
		
		int number = Math.round(bearing / 10.0f); // divide by 10 and round to round to nearest 10 degrees, and as math.round returns integer truncates last digit
		if (number < 10 && number >= 0) { // convert to string, but add 0 if the rounded and truncated bearing is a single digit
			runwayNumber = "0" + Integer.toString(number);
		} else {
			runwayNumber = Integer.toString(number);
		}
		
		if (mode == OperatingMode.MIXED) {
			this.mixedModeTurn = OperatingMode.LANDING; // start with landings
		} else {
			this.mixedModeTurn = mode; // doesnt change
		}
	}

	/**
	 * @return the mixedModeTurn
	 */
	public OperatingMode getMixedModeTurn() {
		return mixedModeTurn;
	}

	/**
	 * @return the currentPlane
	 */
	public String getCurrentPlane() {
		return currentPlane;
	}

	/**
	 * @param currentPlane the currentPlane to set
	 */
	public void addPlane(String currentPlane, AirCraft.EmergencyStatus status) {
		AirCraft plane = airCraftMap.get(currentPlane);
		if (plane.getFlightType().getCode() == mixedModeTurn.getCode()){
			this.currentPlane = currentPlane;
			plane.addToRunway(runwayNumber);
			this.status = OperationStatus.UNAVAIL;

			// switch mode of mixed runway, if there was no emergency 
			if (this.mode == OperatingMode.MIXED && status == AirCraft.EmergencyStatus.NONE) {
				if (this.mixedModeTurn == OperatingMode.LANDING) {
					this.mixedModeTurn = OperatingMode.TAKEOFF;
				} else {
					this.mixedModeTurn = OperatingMode.LANDING;
				}
			}
		}
	}

	/**
	 * TODO: add error checking on this
	 * remove plane
	 * @return plane removed
	 */
	public String removePlane(){
		String temp = this.currentPlane;
		this.currentPlane = null;
		this.status = OperationStatus.AVAILABLE;
		return temp;
	}



	/**
	 * @return the status
	 */
	public OperationStatus getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(OperationStatus status) {
		this.status = status;
	}

	/**
	 * @return the runwayNumber
	 */
	public String getRunwayNumber() {
		return runwayNumber;
	}

	/**
	 * @return the length
	 */
	public int getLength() {
		return length;
	}

	/**
	 * @return the bearing
	 */
	public int getBearing() {
		return bearing;
	}

	/**
	 * @return the mode
	 */
	public OperatingMode getMode() {
		return mode;
	}

	/**
	 * @return the timeSpent
	 */
	public int getTimeSpent() {
		return timeSpent;
	}
	
	
	/**
	 *TODO: NEED: UPDATE TIME SPENT (SET/RESET/CHECK), SET MODE BY CONVERTING MODE (WHICH IS AN UPDATE METHOD), 
	 */

	/**
	 * increment time spent by tick mins
	 * @return true if it has been able to increase it, false if no increase because the time has elapsed
	 * @return plane removed
	 */
	public String updateTime(){
		if (timeSpent >= Constants.runwayTime || !hasPlane()){ // need to return the plane removed 
			return resetTimeSpent();
		}
		else {
			timeSpent += Constants.timeInc;
			return null;
		}
	}
	/**
	 * TODO: add error checking for this
	 * reset time spent to 0 and make plane exit the simulation
	 * @return true if it has been able to reset it false otherwise
	 * @return current plane removed if it is
	 */
	public String resetTimeSpent(){
		this.timeSpent = 0;
		return removePlane();
	}

	/**
	 * checks if a plane is currently assigned
	 */
	public boolean hasPlane(){
		if (currentPlane == null){
			return false;
		} else {
			return true;
		}
	}
	
}

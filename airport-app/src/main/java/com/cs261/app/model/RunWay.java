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

	private OperatingMode mixedModeTurn;
	
	public RunWay(int length, int bearing, OperatingMode mode, OperationStatus status) {
		this.length = length;
		this.bearing = bearing;
		this.mode = mode;
		this.status = status;
		this.currentPlane = "";

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
	 * @param currentPlane the currentPlane to set
	 */
	public void addPlane(AirCraft plane) {
		if (plane.getFlightType().getCode() == mixedModeTurn.getCode()){
			this.currentPlane = plane.getCallSign();
			plane.addToRunway(runwayNumber);
			this.status = OperationStatus.UNAVAIL;

			// switch mode of mixed runway, if there was no emergency 
			if (this.mode == OperatingMode.MIXED && plane.getEmergencyStatus() == EmergencyStatus.NONE) {
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

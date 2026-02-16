package com.cs261.app.model;

public class RunWay {
    /*
    TODO: fields, getters, setters, convert to... methods as described in design doc class diagrma
    */
	
	public static enum OperatingMode {
		LANDING,
		TAKEOFF,
		MIXED,
		;
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
	
	private OperatingMode mixedModeTurn;
	
	public RunWay(int length, int bearing, OperatingMode mode, OperationStatus status) {
		this.length = length;
		this.bearing = bearing;
		this.mode = mode;
		this.status = status;
		this.currentPlane = null;
		this.timeSpent = 0;
		
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
	public void addPlane(String currentPlane) {
		this.currentPlane = currentPlane;
		this.status = OperationStatus.UNAVAIL;
		if (this.mode == OperatingMode.MIXED) {
			if (this.mixedModeTurn == OperatingMode.LANDING) {
				this.mixedModeTurn = OperatingMode.TAKEOFF;
			} else {
				this.mixedModeTurn = OperatingMode.LANDING;
			}
		}
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
	
}

package com.cs261.app.model;

import com.cs261.app.model.AirCraft.EmergencyStatus;

public class RunWay {
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
	
	private String runwayNumber; 
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
		this.runwayNumber = calcID(bearing);
		
		if (mode == OperatingMode.MIXED) {
			this.mixedModeTurn = OperatingMode.LANDING; // mixed runways start with arrivals
		} else {
			this.mixedModeTurn = mode; // if the runway is not a mixed runway, the mixed mode turn is the mode of the runway and it never changes
		}
	}

	/**
	 * E.g. bearing = 124, runwayNumber =  "12"
	 * @param bearing
	 * @return runway number (which is the runway id) based on its bearing
	 */
	private String calcID(int bearing){
		int number = Math.round(bearing / 10.0f); // divide by 10 and round to round to nearest 10 degrees, and as math.round returns integer truncates last digit
		String id;
		if (number < 10 && number >= 0) { // convert to string, but add 0 if the rounded and truncated bearing is a single digit
			id = "0" + Integer.toString(number);
		} else {
			id = Integer.toString(number);
		}
		return id;
	}

	/**
	 * Assign an occupying plane to the runway
	 * @param currentPlane the plane to set
	 */
	public void addPlane(AirCraft plane) {
		if (plane.getFlightType().getCode() == mixedModeTurn.getCode()){ // check the plane is of the correct type for the runway
			this.currentPlane = plane.getCallSign();
			plane.addToRunway(runwayNumber); // update plane with the runway it is occupying
			this.status = OperationStatus.UNAVAIL; // runway is no longer available

			if (this.mode == OperatingMode.MIXED) {
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

}

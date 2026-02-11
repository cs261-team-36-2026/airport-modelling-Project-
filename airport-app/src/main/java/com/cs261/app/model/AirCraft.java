package com.cs261.app.model;

import java.time.LocalDateTime;

public class AirCraft {
    /*
    TODO: fields, getters and setters. 
    */

    public static enum ZoneStatus{
        LAND,
        TAKEOFF,
        QUEUE,
        CANCEL,
        DIVERT,
        RUNWAY,
        ;
    }

    public static enum FlightType{
        ARRIVAL,
        DEPARTURE,
        ;
    }

    public static enum EmergencyStatus {
        NONE(0),
        HEALTH(-1),
        FUEL(-2),
        MECH(-3),
        ;


        private final int statusCode;
        private EmergencyStatus(int status){
            this.statusCode = status;
        }

        public int getStatusCode(){
            return statusCode;
        }
    }


    private String callSign; // id
    private String operator;
    private String origin;
    private String destination;
    private LocalDateTime scheduled; // SCHEDULED arrival/departure time
    private LocalDateTime entryTime; // ACTUAL arrival/departure time i.e. entry time into queue., just the time it enters the simulation, not necessarily the time it leaves the simulation. 
    private LocalDateTime exitTime; // ACTUAL exit time from the simulation.
    private double altitude;
    private double fuel;
    private double speed; // ground speed
    private EmergencyStatus emergencyStatus;
    private ZoneStatus zoneStatus; // arrived, took off, cancelled, diverted 
    private FlightType flightType; // arrival or departure
    
    private final double fuelBurn = 2.70;


    public AirCraft(FlightType type, String callsign, String operator, String origin, String dest, LocalDateTime scheduled, LocalDateTime entryTime, float speed, float fuel, float alt, EmergencyStatus status){
        flightType = type;
        callSign = callsign;
        this.operator = operator;
        this.origin = origin;
        destination = dest;
        this.scheduled = scheduled;
        this.entryTime = entryTime;
        this.speed = speed;
        this.fuel = fuel;
        this.altitude = alt;
        this.emergencyStatus = status;

        this.exitTime = null;
        this.zoneStatus =  ZoneStatus.QUEUE;
    }
    /**
	 * @return the entryTime
	 */
	public LocalDateTime getEntryTime() {
		return entryTime;
	}
	/**
	 * @return the exitTime
	 */
	public LocalDateTime getExitTime() {
		return exitTime;
	}

	/**
	 * @param exitTime the exitTime to set
	 */
	public void setExitTime(LocalDateTime exitTime) {
		this.exitTime = exitTime;
	}

	/**
	 * @return the zoneStatus
	 */
	public ZoneStatus getZoneStatus() {
		return zoneStatus;
	}

	/**
	 * @param zoneStatus the zoneStatus to set
	 */
	public void setZoneStatus(ZoneStatus zoneStatus) {
		this.zoneStatus = zoneStatus;
	}

	/**
	 * @return the callSign
	 */
	public String getCallSign() {
		return callSign;
	}

	/**
	 * @return the operator
	 */
	public String getOperator() {
		return operator;
	}

	/**
	 * @return the origin
	 */
	public String getOrigin() {
		return origin;
	}

	/**
	 * @return the destination
	 */
	public String getDestination() {
		return destination;
	}

	/**
	 * @return the scheduled
	 */
	public LocalDateTime getScheduled() {
		return scheduled;
	}

	/**
	 * @return the altitude
	 */
	public double getAltitude() {
		return altitude;
	}

	/**
	 * @return the fuel
	 */
	public double getFuel() {
		return fuel;
	}

	/**
	 * @return the speed
	 */
	public double getSpeed() {
		return speed;
	}

	/**
	 * @return the emergencyStatus
	 */
	public EmergencyStatus getEmergencyStatus() {
		return emergencyStatus;
	}

	/**
	 * @return the flightType
	 */
	public FlightType getFlightType() {
		return flightType;
	}

	// update: fuel, altitude (idk yet for that), emergency according to FUEL
	// setting: exit time, zone status
	// stays constant: callsign, operator, origin, dest, entry time, flighttype, speed
	
	/**
     * Decrease fuel by constant amount according to time t
     * @param t fuel decrease in t minutes
     */
    public void updateFuel(int t){
        this.fuel = this.fuel - (((this.speed * 1.852) * (t/60) * this.fuelBurn) / 0.8029);
    }
    
    /**
     * @return the number of hours of fuel remaining 
     */
    public double fuelRemainingHrs(){
    	double hrs = ((this.fuel * 0.8029) / this.fuelBurn) * (1 / this.speed);
    	return hrs;
    }
    
    /**
     * TODO: FIX UPDATE METHOD FOR ALTITUDE
     */
    public void updateAltitude(){
    	this.altitude -= 1000;
    }
    /**
     * Updates emergency status if there is a fuel emergency.
     * @return true if there is a fuel emergency, false otherwise.
     */
    public boolean updateFuelEmergency(){
    	if (fuelRemainingHrs() <= 0.25) {
    		this.emergencyStatus = EmergencyStatus.FUEL;
    		return true; // there is a fuel emergency
    	} else {
    		return false; // no fuel emergency
    	}
    }
}

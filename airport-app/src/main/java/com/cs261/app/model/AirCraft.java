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
        RUNWAY;
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
    private float altitude;
    private float fuel;
    private float speed; // ground speed
    private EmergencyStatus emergencyStatus;
    private ZoneStatus zoneStatus; // arrived, took off, cancelled, diverted 
    private FlightType flightType; // arrival or departure



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

// update: fuel, altitude (idk yet for that), emergency according to FUEL
// setting: exit time, zone status
// stays constant: callsign, operator, origin, dest, entry time, flighttype, speed


    /**
     * Decrease fuel by constant amount according to time t
     * @param t fuel decrease in t minutes
     */
    public void updateFuel(int t){
        this.fuel = this.fuel - (()/0.);;
    }
}

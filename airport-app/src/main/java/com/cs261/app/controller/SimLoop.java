package com.cs261.app.controller;

import javafx.concurrent.Task;
import java.util.concurrent.TimeUnit;
import com.cs261.app.model.AirCraftMap;
import com.cs261.app.model.ArrivalModel;
import com.cs261.app.model.HoldingQueue;
import com.cs261.app.model.IModelOutput;
import com.cs261.app.model.RunWayMap;
import com.cs261.app.model.TakeOffQueue;
import com.cs261.app.model.TrafficController;
import com.cs261.app.model.AirCraft;
import com.cs261.app.model.RunWay;


public class SimLoop extends Task<IModelOutput>{
	private AirCraftMap aircraftMap;
	private HoldingQueue holdingPattern;
	private TakeOffQueue takeOffQueue;
	private RunWayMap runways;
	private ArrivalModel arrivalModel;
	private TrafficController trafficModel;
	private int runtime;
	private int currentTime;
	private int prevTime;
	private final int timeInc = 2;
	
	// TODO: add data gen and departure model
	
	public SimLoop(RunWay[] userRunways, int userRuntime) {
		this.runways = new RunWayMap(userRunways);
		this.runtime = userRuntime;
		this.currentTime = 0;
		this.prevTime = 0;
		this.aircraftMap = new AirCraftMap();
		this.holdingPattern = new HoldingQueue(500);
		// take off queue,datagen, departure model
		this.arrivalModel = new ArrivalModel(aircraftMap, holdingPattern);
		this.trafficModel = new TrafficController(this.aircraftMap, this.runways, this.holdingPattern, this.takeOffQueue)
	}
	
	@Override
	protected IModelOutput call() throws Exception {
		// TODO Auto-generated method stub
		while (currentTime != runtime) {
			// traffic controller update
			// arrivals update
			arrivalModel.updateArrivals(null, currentTime, prevTime);
			// departures update
			currentTime += timeInc;
		}
		
		return null;
	}

}

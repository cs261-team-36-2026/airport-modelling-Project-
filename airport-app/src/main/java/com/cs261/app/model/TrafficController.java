package com.cs261.app.model;

import java.lang.reflect.Array;
import java.util.ArrayList;

import com.cs261.app.model.RunWay.OperatingMode;


public class TrafficController {
	// TODO: add all data structures/
	// TODO: constructor
	// TODO: updateTraffic()..
	
	private AirCraftMap aircraftMap;
	private RunWayMap runways;
	private HoldingQueue holdingPattern;
	private TakeOffQueue takeoffQueue;
	private IModelOutput modelOutput;
	
	public TrafficController(AirCraftMap aircraftMap, RunWayMap runways, HoldingQueue holdingPattern,TakeOffQueue takeoffQueue) {
		this.aircraftMap = aircraftMap;
		this.runways = runways;
		this.holdingPattern = holdingPattern;
		this.takeoffQueue = takeoffQueue;
	}
	/**
	 * @return the modelOutput
	 */
	public IModelOutput getModelOutput() {
		return modelOutput;
	}

	public void updateTraffic(int currentTime, int prevTime) {
		// get stuff out of holding queue/
		// get stuff out of take off queue
		// check diversions/
		// convert runway

		// moving planes into runways
		RunWay arrivalRunway = runways.getRunway(OperatingMode.LANDING);
		RunWay departRunway = runways.getRunway(OperatingMode.TAKEOFF);


		/**
		 * HANDLING SIMULATION CASES OF AIRCRAFT THAT ARE ARRIVING 
		 */
		if (arrivalRunway != null){
			/**
			 * Cases:
			 * 1. runway available, no queue HANDLEDD
			 * 2. runway available, first in queue, no emergency HANDLED
			 * 3. runway available, first in queue, emergency HANDLED
			 * 		handled because it will be dequeued and put in the available runway
			 * 4. no runway, first is queue, no emergency HANDLED
			 * 		handled because arrival runway returned as null
			 * 5. no runway, first in queue, emergency
			 * 		need to find a mixed runway REGARDLESS OF MODE 
			 * 		need to store an 'emergency time' ie the point at which it went into emergency mode
			 * 		need to override mixed runway mode and add it to the runway, and carry onto mixed runway turn
			 * 		otherwise if no available mixed runway dnt dequeue anything and check its time 
			 * 6. no runway, not first in queue, emergency 
			 * 		holding queue needs to check for all emergency aircraft
			 * 		and return ones that need to be diverted
			 * 		and traffic controller will divert them 
			 */
			AirCraft nextLand = holdingPattern.dequeue();
			arrivalRunway.addPlane(nextLand);
			nextLand.setExitTime(Constants.convertTicksToDate(currentTime));
			// does this in addPlane nextLand.addToRunway(arrivalRunway.getRunwayNumber());
		} else {
			if (holdingPattern.top().getEmergencyStatus() != AirCraft.EmergencyStatus.NONE){
				// if it is in emergency
				RunWay mixedAvail = runways.getMixedRunWay(); 
				if (mixedAvail != null){ // land because theres one available
					AirCraft nextLand = holdingPattern.dequeue();
					mixedAvail.addPlane(nextLand);
					nextLand.setExitTime(Constants.convertTicksToDate(currentTime));
				} //else , we cant do anything apart from check what needs to be diverted
			}
		}

		ArrayList<AirCraft> emergencies = holdingPattern.getEmergencyPlanes();

		for (AirCraft a : emergencies){
			if (currentTime - a.getEmergencyTime() >= Constants.timeInc * 2){
				// divert
				a.setExitTime(Constants.convertTicksToDate(currentTime));
				a.setZoneStatus(AirCraft.ZoneStatus.DIVERT);
			}
		}


		/**
		 * FIX: instead of having a time update function in runways, just keep track of the difference in time
		 * because exit time is the time it enters the runway 
		 */

		/**
		 * TODO: HANDLING SIMULATION CASES OF AIRCRAFT THAT ARE DEPARTING
		 */
		if (departRunway != null){
			// get next plane in takeoff queue
			// do add plane
			// do add to runway
		}



		// updating other runways 
		// first get a list of unavailable runways of each type
		ArrayList<RunWay> busyArrivals = runways.getBusyRunway(OperatingMode.LANDING);
		ArrayList<RunWay> busyDepart = runways.getBusyRunway(OperatingMode.TAKEOFF);

		// planes that have finished their time in the simulation


		/**
		 * instead of doing this, check entry time 
		 */


		ArrayList<String> exitedPlanes = new ArrayList<>();
		//  TODO: need to update the exit times for these planes and their zones !!!
		for (RunWay r : busyArrivals) {
			String p = r.updateTime();
			if (r != null){
				exitedPlanes.add(p);
			}
		}
		for (RunWay r : busyDepart) {
			String p = r.updateTime();
			if (r != null){
				exitedPlanes.add(p);
			}
		}
	}
	
	
}

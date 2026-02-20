package com.cs261.app.model;

import java.util.ArrayList;

import com.cs261.app.model.RunWay.OperatingMode;
import com.cs261.app.model.RunWay.OperationStatus;

/**
 * Stores an array of runways. 
 * Fixed size, can't or remove runways after simulation has been started/configuration of airport has been chosen
 */
public class RunWayMap {
	RunWay[] runways;
	
	ArrayList<RunWay> busyDepart;
	ArrayList<RunWay> busyArrive;

	ArrayList<RunWay> freeDepart;
	ArrayList<RunWay> freeArrive;

	public RunWayMap(RunWay[] runwayList) {
		this.runways = runwayList;

		busyDepart = new ArrayList<>();
		busyArrive = new ArrayList<>();

		freeArrive = new ArrayList<>();
		freeDepart = new ArrayList<>();

		// O(10)
		for (int i = 0; i < runways.length; i++){
			if (runways[i].getMixedModeTurn() == OperatingMode.LANDING){
				freeArrive.add(runways[i]);
			}
		}

		for (int i = 0; i < runways.length; i++){
			if (runways[i].getMixedModeTurn() == OperatingMode.TAKEOFF){
				freeDepart.add(runways[i]);
			}
		}
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
	 * @return Get list of free arrival runways
	 */
	public ArrayList<RunWay> getFreeArrive() {
		return freeArrive;
	}
	/**
	 * @return Get list of free departure runways
	 */
	public ArrayList<RunWay> getFreeDepart() {
		return freeDepart;
	}
	/**
	 * @return Get list of busy arrival runways
	 */
	public ArrayList<RunWay> getBusyArrive() {
		return busyArrive;
	}
	/**
	 * @return Get list of busy departure runways
	 */
	public ArrayList<RunWay> getBusyDepart() {
		return busyDepart;
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
	
	/**
	 * Retrieve an available mixed runway regardless of its turn
	 * @return
	 */
	public RunWay getMixedRunWay(){
		for (int i = 0; i < runways.length; i++){
			if (runways[i].getMode() == OperatingMode.MIXED && runways[i].getStatus() == OperationStatus.AVAILABLE){
				return runways[i];
			}
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

	/**
	 * swap from unavailable to available
	 * @param r
	 */
	public void swapBusyRunway(RunWay r, OperatingMode mode){
		if (mode == OperatingMode.LANDING){
			busyArrive.remove(r);
			freeArrive.add(r);
		} else { // takeoff
			busyDepart.remove(r);
			freeDepart.add(r);
		}
	}

	/**
	 * swap from available to unavailable
	 */
	public void swapFreeRunway(RunWay r, OperatingMode mode){
		if (mode == OperatingMode.LANDING){
			freeArrive.remove(r);
			busyArrive.add(r);
		} else {
			freeDepart.remove(r);
			busyDepart.add(r);
		}
	}

}

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
			if (runways[i].getRunwayNumber().equals(number)) return runways[i];
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
	/**
	 * @return number of runways 
	 */
	public int size(){
		return runways.length;
	}
	/**
	 * Make the specified runway switch from busy to free (ie removing airplane from it)
	 * @param r to be switched
	 * @param mode mode of the runway
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

	/**
	 * @return list of all runways
	 */
	public RunWay[] getRunways() {
		return runways; 
	}
}

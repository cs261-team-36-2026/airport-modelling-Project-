package com.cs261.app.model;

import java.util.ArrayList;

import com.cs261.app.model.AirCraft.FlightType;
import java.util.HashSet;
import java.util.Set;

public class HoldingQueue {
	AirCraft[] heap; // internally an array
	int size;
	int max;
	int capacity;
	
	public HoldingQueue(int cap) {
		this.capacity = cap;
		heap = new AirCraft[cap];
		max = 0;
	}
	
	/**
	 * @return number of elements in heap
	 */
	public int size() {
		return size;
	}
	
	/**
	 * resize heap to twice its original size
	 */
	private void resize() {
		capacity *= 2;
		AirCraft[] newHeap = new AirCraft[capacity];
		System.arraycopy(heap, 0, newHeap, 0, size);
		this.heap = newHeap;
		newHeap = null;
	}
	/**
	 * Swap two entries, heap[i] and heap[j]
	 * @param i 
	 * @param j
	 */
	private void swap(int i, int j) {
		AirCraft first = heap[i];
		heap[i] = heap[j];
		heap[j] = first;
		first = null;
	}
	/**
	 * returns left child of some node with index i
	 * @param i
	 * @return left child
	 */
	private AirCraft getLeft(int i) {
		if (i >= (capacity / 2)) {
			return null;
		}
		return heap[(2 * i) + 1];
	}
	/**
	 * returns right child of some node i
	 * @param i
	 * @return right child
	 */
	private AirCraft getRight(int i) {
		if (i >= (capacity / 2)) {
			return null;
		}
		return heap[(2 * i) + 2];
	}
	/**
	 * return parent of some node i
	 * @param i
	 * @return parent 
	 */
	private AirCraft getParent(int i) {
		if (i == 0) {
			return null;
		}
		return heap[(int) (i - 1) / 2];
	}
	/**
	 * @return true if the heap is empty, false otherwise
	 */
	public boolean isEmpty() {
		return this.size == 0;
	}
	/**
	 * add plane to holding queue and then sort priority
	 * @param plane to be added
	 */
	public void enqueue(AirCraft plane) {
		if (plane.getFlightType() != FlightType.ARRIVAL) {
			return;
		}
		
		if (size >= capacity) {
			resize();
		}
		
		heap[size] = plane;
		size++;
		upHeap(size - 1);
	}
	/**
	 * get highest priority plane in queue
	 * @return root of heap
	 */
	public AirCraft dequeue() {
		AirCraft result = heap[0];
		heap[0] = heap[size - 1]; // move the lowest rightmost node on tree to here
		heap[size - 1] = null; // no duplicate planes
		downheap(0);
		return result;
	}
	/**
	 * basically if it is the same emegerency status, or less important (less important means higher integer emergency enum)
	 * dont do anything
	 * but if the ith node has a higher emergency status (lower value enum) then swap it with its parent
	 * @param i node you are moving up the tree
	 */
	private void upHeap(int i) {
		if (i == 0) {
			return;
		}
		if (heap[i].getEmergencyStatus().getStatusCode() < getParent(i).getEmergencyStatus().getStatusCode()) {
			int j = (int) (i - 1) / 2;
			swap(i, j);
			upHeap(j);
		} else {
			return;
		}
	}
	/**
	 * down heap when the root gets removed
	 * if the root has a lower priority (as in HIGHER number) than a child, swap it with the highest priority child
	 * @param i node to down heap from
	 */
	private void downheap(int i) {
		if (i >= size) {
			return;
		}
		
		// find child with the greatest emergency priority
		int check = 0;
		if (getLeft(i).getEmergencyStatus().getStatusCode() > getRight(i).getEmergencyStatus().getStatusCode()) {
			check = (2 * i) + 2;
		} else {
			check = (2 * i) + 1; // if theyre the same just check left
		}
		// if the node i is more important (strictly less)
		if (heap[i].getEmergencyStatus().getStatusCode() < heap[check].getEmergencyStatus().getStatusCode()) {
			swap(i, check); // swap 
			downheap(check); // new index of node i
		} else {
			return; // if they are the same or node i is less important (more than/equal to) dont swap
		}
	}
	
	/**
	 * Traverse heap O(n)
	 * worst case O(nlogn) if it so happens that every plane has a fuel emergency for some reason
	 * @param t current time in simulation as # of ticks
	 */
	public void updateHolding(int t) {
		for (int i = 0; i < size; i++) {
			if (heap[i].updateHoldingFlight(t)) {
				upHeap(i); // might need to move it up the heap
			} else { // no fuel emergency for now
				continue;
			}
		}
	}

	/**
	 * return first node in priority queue without removing it
	 */
	public AirCraft top(){
		if (isEmpty()) {
			return null;
		} else {
			return heap[0];
		}
	}
	

	/**
	 * Get all the planes that are in emergency right now 
	 */

	public ArrayList<AirCraft> getEmergencyPlanes(){
		ArrayList<AirCraft> temp = new ArrayList<>();
		for (int i = 0; i < heap.length; i++){
			// if the current node has a 0 emergency status, just stop
			if (heap[i].getEmergencyStatus().getStatusCode() == 0){
				break;
			} else {
				temp.add(heap[i]);
			}
		}

		return temp;
	}

	/**
	 * @return hashset of all of the plane ids in the holding pattern right now 
	 */
	public Set<String> getAircraftIds(){
		Set<String> ids = new HashSet<>();
		for (int i = 0; i < heap.length; i++){
				ids.add(heap[i].getCallSign());
		}
		return ids;
	}	
}

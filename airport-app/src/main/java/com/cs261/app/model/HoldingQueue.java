package com.cs261.app.model;

public class HoldingQueue {
	/** XXX: basically i realised you dont need a queue at all to check the priority you just need to check the emergency
	 * status. TEST THIS !!!
	 * 
	 * 
	 * 
	 * Each heap entry is a nested class object. This is because the key of the airplane in the holding queue is not
	 * related to the airplane object basic information. 
	 */
		//	protected class HoldingEntry {
		//		private int key;
		//		private AirCraft plane;
		//		
		//		protected HoldingEntry(AirCraft plane) {
		//			this.plane = plane;
		//		}
		//		
		//		protected void setKey(int k) {
		//			this.key = k;
		//		}
		//		
		//		protected int getKey() {
		//			return this.key;
		//		}
		//		
		//		protected AirCraft getPlane() {
		//			return this.plane;
		//		}
		//	}
	
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
	 * TODO: swap(i,j) function/
	 * TODO: calculate key function
	 * TODO: getLeft, getRight, etc function/
	 * TODO: resize/, add, remove, etc
	 * TODO: upheap (Adding)/, downheap (Removals)/
	 */
	
}

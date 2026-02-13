package com.cs261.app.model;

public class HoldingQueue {
    /*
    TODO: min heap inner array
    */
	
	/**
	 * addding it below the highest negative number
	 * traverse tree - if you find a node with less priority, place as its subtree root 
	 */
	
	/**
	 * Each heap entry is a nested class object. This is because the key of the airplane in the holding queue is not
	 * related to the airplane object basic information. 
	 */
	protected class HoldingEntry {
		private int key;
		private AirCraft plane;
		
		protected HoldingEntry(AirCraft plane) {
			this.plane = plane;
		}
		
		protected void setKey(int k) {
			this.key = k;
		}
		
		protected int getKey() {
			return this.key;
		}
		
		protected AirCraft getPlane() {
			return this.plane;
		}
	}
	
	HoldingEntry[] heap; // internally an array
	int size;
	int max;
	int capacity;
	
	public HoldingQueue(int cap) {
		this.capacity = cap;
		heap = new HoldingEntry[cap];
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
		HoldingEntry[] newHeap = new HoldingEntry[capacity];
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
		HoldingEntry first = heap[i];
		heap[i] = heap[j];
		heap[j] = first;
		first = null;
	}
	/**
	 * returns left child of some node with index i
	 * @param i
	 * @return left child
	 */
	private HoldingEntry getLeft(int i) {
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
	private HoldingEntry getRight(int i) {
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
	private HoldingEntry getParent(int i) {
		if (i == 0) {
			return null;
		}
		return heap[(i - 1 / 2)];
	}
	
	/**
	 * TODO: swap(i,j) function/
	 * TODO: calculate key function
	 * TODO: getLeft, getRight, etc function/
	 * TODO: resize/, add, remove, etc
	 * TODO: holdingentry/
	 */
	
}

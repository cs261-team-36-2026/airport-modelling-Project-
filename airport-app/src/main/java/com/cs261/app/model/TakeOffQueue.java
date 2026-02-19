package com.cs261.app.model;
import java.util.LinkedList;

public class TakeOffQueue {
    /** 
     * is there anything else we want the queue to do? TODO max takeoff queue length
     * test, calculate metrics here?
     */

    private LinkedList<AirCraft> queue;

    public TakeOffQueue() {
        this.queue = new LinkedList<>();
    }
    // adds aircraft to the back of the queue
    public void enqueue(AirCraft aircraft) {
        queue.addLast(aircraft);
    }

    // removes aircraft from front of queue
    public AirCraft dequeue() {
        if (queue.isEmpty()) {
            return null;  // TODO might want to change to throw exception
        }
        return queue.removeFirst();
    }

    // returns the aircraft at a specific position in the queue
    public AirCraft getAircraftAt(int index) {
        if (index < 0 || index >= queue.size()) {
            return null; // TODO might want to change to throw exception
        }
        return queue.get(index);
    }
    // checks if queue is empty
    public boolean isEmpty() {
        return queue.isEmpty();
    }

    // returns the size of the queue
    public int size() {
        return queue.size();
    }

}


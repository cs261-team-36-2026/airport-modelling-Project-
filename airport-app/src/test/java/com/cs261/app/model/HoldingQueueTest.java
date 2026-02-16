package com.cs261.app.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class HoldingQueueTest {

    @Test
    void emergencyPriorityOrdering() {
        // TODO:
        // Create aircraft with different emergencies (mechanical > fuel > health > none)
        // Add to HoldingQueue
        // Assert it returns mechanical first

        assertTrue(true);
    }

    @Test
    void tieBreakerByArrivalTime() {
        // TODO:
        // Two aircraft same emergency level, different arrival times
        // Earlier arrival should be served first

        assertTrue(true);
    }
}

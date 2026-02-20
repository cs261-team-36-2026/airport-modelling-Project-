package com.cs261.app.model;
import java.util.*;

// collates data that must be outputted to view
public class ModelOutput implements IModelOutput {
    private DepartureModel departureModel;
    private ArrivalModel arrivalModel;

    public ModelOutput(DepartureModel departureModel, ArrivalModel arrivalModel) {
        this.departureModel = departureModel;
        this.arrivalModel = arrivalModel;
    }

    // Queue
    @Override
    public List<String> getHoldingQueueOrder() {
        /* what does this do? seems similar to getHoldingAircraftds, except has order; is there a point in having both? */
        return Collections.emptyList();
    }

    @Override
    public List<String> getTakeOffQueueOrder() {
        /* what does this do? seems similar to getTakeoffAircraftds */
        return Collections.emptyList();
    }


    // Runway
    @Override
    public Map<String, String> getRunwayOccupancy() {
        return Collections.emptyMap();
    }

    @Override
    public Map<String, String> getRunwayModes() {
        return Collections.emptyMap();
    }

    @Override
    public Map<String, Boolean> getRunwayClosureStatus() {
        return Collections.emptyMap();
    }


    // Aircraft
    @Override
    public Set<String> getHoldingAircraftIds() {
        return arrivalModel.getHoldingPatternAircraftIds();
    }

    @Override
    public Set<String> getTakeOffAircraftIds() {
        return departureModel.getTakeOffQueueAircraftIds();
    }

    @Override
    public Set<String> getRunwayAircraftIds() {
        return Collections.emptySet();
    }

    @Override
    public Set<String> getExitedAircraftIds() {
        return Collections.emptySet();
    }

    
    // Metrics
    @Override
    public int getTotalArrivalsGenerated() {
        // discuss if want this to incremented in data gen, or when they enter holding/takeoff queues
        // similar to getTotalAircraftTakenOff() if when enter queues
        return 0;
    }

    @Override
    public int getTotalDeparturesGenerated() {
        // discuss if want this to incremented in data gen, or when they enter holding/takeoff queues
        return 0;
    }

    @Override
    public int getTotalAircraftLanded() {
        // ask afra about updateArrivals in ArrivalModel before changing (will be similar to mine in DepartureModel)
        return 0;
    }

    @Override
    public int getTotalAircraftTakenOff() {
        return departureModel.getTotalAircraftTakenOff();
    }

    @Override
    public int getMaxHoldingQueueLength() {
        // TODO ask afra how to get max from holding queue
        return 0;
    }

    @Override
    public int getMaxTakeOffQueueLength() {
        return departureModel.getMaxTakeOffQueueLength();
    }

    @Override
    public double getAverageHoldingWaitTime() {
        // TODO need to figure out how/where times enter/exit the queue is stored
        return 0.0;
    }

    @Override
    public double getAverageTakeOffWaitTime() {
        // TODO need to figure out how/where times enter/exit the queue is stored
        return 0.0;
    }
}

    
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
        return 0;
    }

    @Override
    public int getTotalDeparturesGenerated() {
        return 0;
    }

    @Override
    public int getTotalAircraftLanded() {
        return 0;
    }

    @Override
    public int getTotalAircraftTakenOff() {
        return 0;
    }

    @Override
    public int getMaxHoldingQueueLength() {
        return 0;
    }

    @Override
    public int getMaxTakeOffQueueLength() {
        return 0;
    }

    @Override
    public double getAverageHoldingWaitTime() {
        return 0.0;
    }

    @Override
    public double getAverageTakeOffWaitTime() {
        return 0.0;
    }
}

    
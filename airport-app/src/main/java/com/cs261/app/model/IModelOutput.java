package com.cs261.app.model;

public interface IModelOutput {
    /**
    doneTODO: add getMaxArrivals etc stuff  
    */ 
    //queue
    List<String> getHoldingQueueOrder();
    List<String> getTakeOffQueueOrder();
    //runway
    Map<String, String> getRunwayOccupancy();
    Map<String, String> getRunwayModes();
    Map<String, Boolean> getRunwayClosureStatus();
    //aircraft
    Set<String> getHoldingAircraftIds();
    Set<String> getTakeOffAircraftIds();
    Set<String> getRunwayAircraftIds();
    Set<String> getExitedAircraftIds();

    //metrics
    int getTotalArrivalsGenerated();
    int getTotalDeparturesGenerated();
    int getTotalAircraftLanded();
    int getTotalAircraftTakenOff();
    int getMaxHoldingQueueLength();
    int getMaxTakeOffQueueLength();
    double getAverageHoldingWaitTime();
    double getAverageTakeOffWaitTime()
    
}

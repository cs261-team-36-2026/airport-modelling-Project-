package com.cs261.app.model;

public interface IModelOutput {
    /**
    doneTODO: add getMaxArrivals etc stuff  
    */ 
	public void updateOutput();
	public int maxHoldingSize();
	public int maxTakeOffSize();
	public float maxArriveDelay();
	public float maxDepartDelay();
	public float avgArriveDelay();
	public float avgDepartDelay();
	public float avgDepartQueueTime(); // takeoff queue avg time
	public float avgArriveQueueTime(); // holding queue avg  time
}

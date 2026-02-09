package com.cs261.app;

import java.util.HashMap;
import javafx.beans.property.SimpleMapProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableMap;
import javafx.concurrent.Service;
import javafx.concurrent.Task;

public class TestService extends Service<String> {
    private String input1;
    private String input2;

    private SimpleMapProperty<Integer, TestObj> mapProperty;
    private ObservableMap<Integer, TestObj> map;

    public TestService(){
        // set it to a map expression which is an observable map implementation
        mapProperty = new SimpleMapProperty<>(map);
    }

    @Override
    protected Task<String> createTask(){
        return new TestThread(input1, input2, map);
    }   

    public void start(String a, String b, HashMap<Integer, TestObj> stuff){
        map = FXCollections.observableMap(stuff);
        input1 = a;
        input2 = b;
        start();
    }

    public SimpleMapProperty<Integer, TestObj> getPropertyMap(){
        return mapProperty;
    }

}

package com.cs261.app;

import javafx.concurrent.Task;
import javafx.collections.ObservableMap;

import java.util.concurrent.TimeUnit;

public class TestThread extends Task<String> {
    private String input1;
    private String input2;
    private String output;
    private ObservableMap<Integer, TestObj> map;

    public TestThread(String a, String b, ObservableMap<Integer, TestObj> stuff) {
        output = "";
        input1 = a;
        input2 = b; 
        map = stuff;
    }

    @Override
    protected String call() throws Exception {
        int current = 0;
        output = output + input1 + input2;
        while (current < 15){
            System.out.println(current);
            output += map.get(current).getName();
            current+=1;
            TimeUnit.SECONDS.sleep(3);
        }
        return output;
    }
}

/*
1. Make a class thats a bunch of inputs
2. the inputs are pproperties
3. bind the task's input property to the app's input property, and add a change listener that updates both together when input is changed. 

make a class that stores input details, ext


operational states - separate from other inputs which you only input once (make list of inputs)

other inputs
runways (1 to 10): observable map, in modelInput wrapped by MapProperty 
runtime :: just int no property
cancellation max waiting time :: just int no property

double model input and their runway maps are bound 

gets & sets for all above 


*/
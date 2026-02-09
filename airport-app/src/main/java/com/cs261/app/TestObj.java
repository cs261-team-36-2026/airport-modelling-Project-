package com.cs261.app;

public class TestObj {
    private int id;
    private String name;

    public TestObj(int i, String s){
        this.id = i;
        this.name = s;
    }

    public void setName(String n){
        this.name = n;
    }

    public String getName(){
        return name;
    }

    public int getId(){
        return id;
    }
}

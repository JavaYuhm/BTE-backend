package com.codestates.assignment1.hello;

public class RestWebFlux {
    String name;

    public RestWebFlux(){

    }

    public RestWebFlux(String name){
        this.name = name;
    }

    public String getName(){
        return this.getName();
    }

    public void setName(String name){
        this.name = name;
    }

    @Override
    public String toString(){
        return "{" +
                "name : " + name + "Hello :" + name +
                "}";
    }
}

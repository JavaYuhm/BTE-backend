package com.codestates.assignment1.hello;

public class RestWebFlux {
    private String to;
    private String message;

    public RestWebFlux() {
    }

    public RestWebFlux(String to, String message) {
        this.to = to;
        this.message = message;
    }

    public String getTo(){
        return this.to;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setTo(String to){
        this.to = to;
    }
}

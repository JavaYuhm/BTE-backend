package com.codestates.assignment1.hello;

public class RestWebFlux {
    private String to;
    private String message;

    private String job;

    public RestWebFlux() {
    }

    public RestWebFlux(String to, String job) {
        this.to = to;
        this.job = job;
    }
    public RestWebFlux(String to, String message, String job) {
        this.to = to;
        this.message = message;
        this.job = job;
    }
    public String getTo(){
        return this.to;
    }

    public String getMessage() {
        return this.message;
    }
    public String getJob() {
        return this.job;
    }
    public void setMessage(String message) {
        this.message = message;
    }

    public void setTo(String to){
        this.to = to;
    }
}

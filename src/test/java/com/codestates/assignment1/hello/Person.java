package com.codestates.assignment1.hello;

public class Person {
    String name;
    String email;
    String number;

    Person(String name, String email, String number){
        this.name = name;
        this.email = email;
        this.number = number;
    }

    public String getName() {
        return name;
    }
}

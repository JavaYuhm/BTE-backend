package com.codestates.assignment1.hello;

/*
Lombok Constractor, Getter, Setter 설정이 제대로 안먹어, 수기로 생성함.
원인 파악 필요
*/
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

package com.aor.refactoring.example4;

public class Person {
    private String name;
    private String phone;


    public Person(String name, String phone) {
        this.name = name;
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }
}

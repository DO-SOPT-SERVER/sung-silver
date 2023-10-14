package com.server.dosopt.seminar.sample;

import lombok.Builder;
import lombok.Getter;

@Getter
public class Person {
    private String lastName;
    private String firstName;

    @Builder
    public Person(String lastName, String firstName) {
        this.lastName = lastName;
        this.firstName = firstName;
    }

    @Override
    public String toString() {
        return "lastName='" + lastName + '\'' +
                ", firstName='" + firstName ;
    }
}

package org.example.model;

import lombok.Getter;

@Getter
public class PatientMemento {
    private String lastName;
    private int id;
    private String firstName;
    private String phoneNumber;
    private String address;

    public PatientMemento(String lastName, int id, String firstName, String phoneNumber, String address) {
        this.lastName = lastName;
        this.id = id;
        this.firstName = firstName;
        this.phoneNumber = phoneNumber;
        this.address = address;
    }
}

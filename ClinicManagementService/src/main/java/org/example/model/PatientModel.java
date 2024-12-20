package org.example.model;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@ToString
@NoArgsConstructor
public class PatientModel extends UserModel {
    private int patientId;
    private List<PatientModel> patients;

    public PatientModel(String lastName, int id, String firstName, String phoneNumber, String address) {
        super(lastName, id, firstName, phoneNumber, address);
        this.patients = new ArrayList<>();

    }

    @Override
    public UserModel createUser(String lastName, int id, String firstName, String phoneNumber, String address) {
        return new PatientModel(lastName, id, firstName, phoneNumber, address);
    }

    public PatientMemento save() {
        return new PatientMemento(getLastName(), getId(), getFirstName(), getPhoneNumber(), getAddress());
    }

    public void restore(PatientMemento memento) {
        setLastName(memento.getLastName());
        setId(memento.getId());
        setFirstName(memento.getFirstName());
        setPhoneNumber(memento.getPhoneNumber());
        setAddress(memento.getAddress());
    }

    /**
     * This method check if the patient user is found in the list of patients and validates.
     *
     * @return true if the patient user is valid and false if the patient is invalid.
     */
    @Override
    public boolean isUserValid(int id, String lastName) {
        if (isValidId(id)) {
            System.out.println("Invalid ID. ID should  be positive numbers.");
            return false;
        }

        if (isValidLastName(lastName)) {
            System.out.println("Invalid last name. Last name should contain letters.");
            return false;
        }

        for (PatientModel patient : patients) {
            if (patient.getId() == id && patient.getLastName().equals(lastName)) {
                return true;
            }
        }
        return false;
    }
}

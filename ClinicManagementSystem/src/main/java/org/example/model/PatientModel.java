package org.example.model;

import java.util.ArrayList;
import java.util.List;

public class PatientModel extends UserModel {

    private List<PatientModel> patients;

    public PatientModel(String lastName, int id, String firstName, String phoneNumber, String address) {
        super(lastName, id, firstName, phoneNumber, address);
        this.patients = new ArrayList<>();

    }

    public List<PatientModel> getPatients() {
        return patients;
    }

    public void setPatients(List<PatientModel> patients) {
        this.patients = patients;
    }

    @Override
    public String toString() {
        return "PatientModel{" +
                "patients=" + patients +
                '}';
    }

    /**
     * This method check if the patient user is found in the list of patients and validates.
     * @return true if the patient user is valid and false if the patient is invalid.
     */
    @Override
    public boolean isUserValid(int id, String lastName) {
        if (!isValidId(id)) {
            System.out.println("Invalid ID. ID should  be positive numbers.");
            return false;
        }

        if (!isValidLastName(lastName)) {
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

package org.example.model;

import java.util.ArrayList;
import java.util.List;

public class DoctorModel extends UserModel {
    private List<DoctorModel> doctors;

    public DoctorModel(String lastName, int id, String firstName, String phoneNumber, String address) {
        super(lastName, id, firstName, phoneNumber, address);
        this.doctors = new ArrayList<>();
    }

    public List<DoctorModel> getDoctors() {
        return doctors;
    }

    public void setDoctors(List<DoctorModel> doctors) {
        this.doctors = doctors;
    }

    @Override
    public String toString() {
        return "DoctorModel{" +
                "doctors=" + doctors +
                '}';
    }

    /**
     * This method check if the doctor user is found in the list of doctors and validates.
     *
     * @return true if the doctor user is valid and false if the doctor is invalid.
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

        for (DoctorModel doctor : doctors) {
            if (doctor.getId() == id && doctor.getLastName().equals(lastName)) {
                return true;
            }
        }
        return false;
    }
}




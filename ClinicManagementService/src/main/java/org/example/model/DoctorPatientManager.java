package org.example.model;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class DoctorPatientManager {

    private static DoctorPatientManager instance;

    private final List<DoctorModel> doctors;
    private final List<PatientModel> patients;

    private DoctorPatientManager() {
        doctors = new ArrayList<>();
        patients = new ArrayList<>();
    }

    /**
     * This method get an instance of the objects
     * @return the instance
     */
    public static DoctorPatientManager getInstance() {
        if (instance == null) {
            synchronized (DoctorPatientManager.class) {
                if (instance == null) {
                    instance = new DoctorPatientManager();
                }
            }
        }
        return instance;
    }
}

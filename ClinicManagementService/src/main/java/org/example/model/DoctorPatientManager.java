package org.example.model;

import java.util.ArrayList;
import java.util.List;

public class DoctorPatientManager {

    private static DoctorPatientManager instance;

    private final List<DoctorModel> doctors;
    private final List<PatientModel> patients;

    private DoctorPatientManager() {
        doctors = new ArrayList<>();
        patients = new ArrayList<>();
    }

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

    public List<DoctorModel> getDoctors() {
        return doctors;
    }

    public List<PatientModel> getPatients() {
        return patients;
    }
}

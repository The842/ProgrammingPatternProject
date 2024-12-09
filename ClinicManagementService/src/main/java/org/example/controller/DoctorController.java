package org.example.controller;

import org.example.model.AppointmentModel;
import org.example.model.DoctorModel;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class DoctorController {

private final ExecutorService threadPool = Executors.newFixedThreadPool(10);

    /**
     *  Add a new doctor to the database
     * @param doctor doctor to add
     */
    public void addNewDoctor(DoctorModel doctor) {
        threadPool.submit(() -> {
            DatabaseController.insertDoctor(doctor);
        });
    }

    /**
     *  View all doctors in the system
      */
    public void displayAllDoctors() {
        threadPool.submit(() -> {
            for (DoctorModel doctor : DatabaseController.getAllDoctors()) {
                System.out.println(doctor);
            }
        });
    }

    /**
     * View the doctor assigned to a specific patient
     * @param patientId id of patient
     */
    public void viewDoctorByPatientId(int patientId) {
        threadPool.submit(() -> {
            DoctorModel doctor = DatabaseController.getDoctorByPatientId(patientId);
            if (doctor != null) {
                System.out.println(doctor);
            } else {
                System.out.println("No doctor found for this patient.");
            }
        });
    }
}

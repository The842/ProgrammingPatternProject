package org.example.controller;

import org.example.model.PatientModel;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class PatientController {
    private final ExecutorService threadPool = Executors.newFixedThreadPool(10);

    /**
     * Add a new patient to the database
     *
     * @param patient patient to add
     */
    public void addNewPatient(PatientModel patient) {
        threadPool.submit(() -> {
            DatabaseController.insertPatient(patient);
        });
    }

    /**
     * Delete a patient by ID
     *
     * @param patientId patient id to delete
     */
    public void deletePatientById(int patientId) {
        threadPool.submit(() -> {
            try {
                DatabaseController.deletePatient(patientId);
                System.out.println("Patient deleted successfully.");
            } catch (Exception e) {
                System.out.println("Failed to delete patients by id: " + e.getMessage());
            }
        });
    }

    /**
     * View all patients in the system
     */
    public void displayAllPatients() {
        threadPool.submit(() -> {
            try {
                List<PatientModel> patients = DatabaseController.getAllPatients();
                for (PatientModel patient : patients) {
                    System.out.println(patient);
                }
            } catch (Exception e) {
                System.out.println("Failed to view all patients: " + e.getMessage());
            }
        });
    }

    /**
     * View a specific patient by ID
     *
     * @param patientId id of a patient
     */
    public void viewPatientById(int patientId) {
        threadPool.submit(() -> {
            PatientModel patient = DatabaseController.getPatientById(patientId);
            if (patient != null) {
                System.out.println(patient);
            } else {
                System.out.println("Patient not found.");
            }
        });
    }

    /**
     * View all patients of a specific doctor
     *
     * @param doctorId id of doctor
     */
    public void viewPatientsByDoctorId(int doctorId) {
        threadPool.submit(() -> {
            try {
                List<PatientModel> patients = DatabaseController.getPatientsByDoctorId(doctorId);
                for (PatientModel patient : patients) {
                    System.out.println(patient);
                }
            } catch (Exception e) {
                System.out.println("Failed to view patients by doctor: " + e.getMessage());
            }
        });
    }
}

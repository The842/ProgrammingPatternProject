package org.example.controller;


import org.example.model.MedicalRecordModel;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MedicalRecordController {
    private final ExecutorService threadPool;

    public MedicalRecordController() {
        this.threadPool = Executors.newFixedThreadPool(10);
    }

    /**
     * Add a new medical record.
     *
     * @param medicalRecord The medical record to add.
     */
    public void addMedicalRecord(MedicalRecordModel medicalRecord) {
        threadPool.submit(() -> {
            try {
                DatabaseController.insertTreatment(medicalRecord.getTreatment());
                DatabaseController.insertMedicalRecord(medicalRecord);
                System.out.println("Medical record added successfully.");
            } catch (Exception e) {
                System.err.println("Error adding medical record: " + e.getMessage());
            }
        });
    }

    /**
     * View all medical records.
     */
    public void viewAllMedicalRecords() {
        threadPool.submit(() -> {
            try {
                List<MedicalRecordModel> records = DatabaseController.getAllMedicalRecords();
                System.out.println("All Medical Records:");
                records.forEach(System.out::println);
            } catch (Exception e) {
                System.err.println("Error fetching medical records: " + e.getMessage());
            }
        });
    }

    /**
     * View all medical records for a specific patient.
     *
     * @param patientId The ID of the patient.
     */
    public void viewMedicalRecordsByPatientId(int patientId) {
        threadPool.submit(() -> {
            try {
                List<MedicalRecordModel> records = DatabaseController.getMedicalRecordsByPatientId(patientId);
                System.out.println("Medical Records for Patient ID " + patientId + ":");
                records.forEach(System.out::println);
            } catch (Exception e) {
                System.err.println("Error fetching medical records for patient: " + e.getMessage());
            }
        });
    }
}

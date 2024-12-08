package org.example.controller;

import org.example.model.MedicalRecordModel;
import org.example.model.Treatment;

import org.example.util.MedicalRecordDML;

public class MedicalRecordController {

    private MedicalRecordDML medicalRecordDML;

    public MedicalRecordController() {
        this.medicalRecordDML = new MedicalRecordDML();
    }

    /**
     * View the medical record of a patient by their patient ID.
     * @param patientId the ID of the patient
     */
    public void viewMedicalRecord(int patientId) {
        MedicalRecordModel medicalRecord = MedicalRecordDML.getMedicalRecordById(patientId);

        if (medicalRecord != null) {
            System.out.println("Medical Record for Patient ID: " + patientId);
            System.out.println(medicalRecord);
        } else {
            System.out.println("Medical Record not found for patient ID: " + patientId);
        }
    }

    /**
     * Update the medical record for a patient.
     * @param patientId the ID of the patient whose record will be updated
     * @param newDiagnosis the new diagnosis to update
     * @param newTreatment the new treatment plan to update
     */
    public void updateMedicalRecord(int patientId, String newDiagnosis, Treatment newTreatment) {
        MedicalRecordModel medicalRecord = MedicalRecordDML.getMedicalRecordById(patientId);

        if (medicalRecord != null) {
            medicalRecord.setDiagnosis(newDiagnosis);
            medicalRecord.setTreatment(newTreatment);

            MedicalRecordDML.updateMedicalRecord(medicalRecord);
            System.out.println("Medical Record updated successfully for Patient ID: " + patientId);
        } else {
            System.out.println("Medical Record not found for patient ID: " + patientId);
        }
    }

    /**
     * View a medical record by its ID.
     * @param medicalRecordId the ID of the medical record
     */
    public void viewMedicalRecordById(int medicalRecordId) {
        MedicalRecordModel medicalRecord = MedicalRecordDML.getMedicalRecordById(medicalRecordId);

        if (medicalRecord != null) {
            System.out.println("Medical Record Details: " + medicalRecord);
        } else {
            System.out.println("Medical Record not found.");
        }
    }
}

package org.example.controller;

import org.example.model.MedicalRecordModel;
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
}

package org.example.model;

import lombok.*;

import java.util.Objects;

@Setter
@Getter
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class MedicalRecordModel {
    private int medicalRecordID;
    private int appointmentID;
    private String diagnosis;
    private Treatment treatment;
    private double bill;

    public MedicalRecordModel(int medicalRecordID, int appointmentID, String diagnosis, Treatment treatment, double bill) {
        this.medicalRecordID = medicalRecordID;
        this.appointmentID = appointmentID;
        this.diagnosis = diagnosis;
        this.treatment = treatment;
        this.bill = bill;
    }
}

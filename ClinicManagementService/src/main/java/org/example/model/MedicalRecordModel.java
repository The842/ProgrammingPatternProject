package org.example.model;

import java.util.Objects;

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

    public int getMedicalRecordID() {
        return medicalRecordID;
    }

    public void setMedicalRecordID(int medicalRecordID) {
        this.medicalRecordID = medicalRecordID;
    }

    public int getAppointmentID() {
        return appointmentID;
    }

    public void setAppointmentID(int appointmentID) {
        this.appointmentID = appointmentID;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }

    public Treatment getTreatment() {
        return treatment;
    }

    public void setTreatment(Treatment treatment) {
        this.treatment = treatment;
    }

    public double getBill() {
        return bill;
    }

    public void setBill(double bill) {
        this.bill = bill;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MedicalRecordModel that = (MedicalRecordModel) o;
        return medicalRecordID == that.medicalRecordID && appointmentID == that.appointmentID && Double.compare(bill, that.bill) == 0 && Objects.equals(diagnosis, that.diagnosis) && Objects.equals(treatment, that.treatment);
    }

    @Override
    public int hashCode() {
        return Objects.hash(medicalRecordID, appointmentID, diagnosis, treatment, bill);
    }

    @Override
    public String toString() {
        return "MedicalRecordModel{" +
                "medicalRecordID=" + medicalRecordID +
                ", appointmentID=" + appointmentID +
                ", diagnosis='" + diagnosis + '\'' +
                ", treatment=" + treatment +
                ", bill=" + bill +
                '}';
    }
}

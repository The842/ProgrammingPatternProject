package org.example.controller;

import org.example.model.AppointmentModel;
import org.example.model.DoctorModel;
import org.example.model.MedicalRecordModel;
import org.example.model.Treatment;
import org.example.util.AppointmentDML;
import org.example.util.MedicalRecordDML;

import java.util.List;

public class DoctorController {

    private DoctorModel doctor;

    public DoctorController(DoctorModel doctor) {
        this.doctor = doctor;
    }

    /**
     * View all appointments for a specific doctor.
     */
    public void viewAppointments() {
        List<AppointmentModel> appointments = AppointmentDML.getAllAppointments(doctor.getId());
        System.out.println("Appointments for Dr. " + doctor.getLastName() + ":");
        for (AppointmentModel appointment : appointments) {
            System.out.println(appointment);
        }
    }

    /**
     * View all medical records for a specific doctor from the database.
     */
    public void viewMedicalRecords() {
        List<MedicalRecordModel> medicalRecords = MedicalRecordDML.getAllMedical(doctor.getId());
        System.out.println("Medical Records for Dr. " + doctor.getLastName() + ":");
        for (MedicalRecordModel record : medicalRecords) {
            System.out.println(record);
        }
    }

    /**
     * Add a new appointment for the doctor into the database.
     */
    public void addAppointment(int id, java.util.Date appointmentDate, java.sql.Time appointmentTime, int patientID) {
        AppointmentModel appointment = new AppointmentModel(id, appointmentDate, appointmentTime, doctor.getId(), patientID);
        AppointmentDML.addAppointment(appointment);
        System.out.println("Appointment added successfully.");
    }

    public void updateAppointment(int id, java.util.Date newDate, java.sql.Time newTime) {
        AppointmentModel appointment = AppointmentDML.getAppointmentById(id);

        if (appointment != null) {
            appointment.setAppointmentDate(newDate);
            appointment.setAppointmentTime(newTime);

            AppointmentDML.updateAppointment(appointment);
            System.out.println("Appointment updated successfully.");
        } else {
            System.out.println("Appointment not found.");
        }
    }

    /**
     * Delete an appointment by ID from the database.
     */
    public void deleteAppointment(int id) {
        AppointmentModel appointment = AppointmentDML.getAppointmentById(id);
        if (appointment != null) {
            AppointmentDML.deleteAppointment(id);
            System.out.println("Appointment deleted successfully.");
        } else {
            System.out.println("Appointment not found.");
        }
    }

    public void addMedicalRecord(int medicalRecordID, int appointmentID, String diagnosis, Treatment treatment, double bill) {
        MedicalRecordModel record = new MedicalRecordModel(medicalRecordID, appointmentID, diagnosis, treatment, bill);
        MedicalRecordDML.addMedicalRecord(record);
        System.out.println("Medical record added successfully.");
    }

    public void updateMedicalRecord(int id, String newDiagnosis, double newBill, Treatment newTreatment) {
        MedicalRecordModel record = MedicalRecordDML.getMedicalRecordById(id);

        if (record != null) {
            record.setDiagnosis(newDiagnosis);
            record.setBill(newBill);
            record.setTreatment(newTreatment);

            MedicalRecordDML.updateMedicalRecord(record);

            System.out.println("Medical record updated successfully.");
        } else {
            System.out.println("Medical record not found.");
        }
    }

    public void deleteMedicalRecord(int id) {
        MedicalRecordModel record = MedicalRecordDML.getMedicalRecordById(id);

        if (record != null) {
            MedicalRecordDML.deleteMedicalRecord(id);
            System.out.println("Medical record deleted successfully.");
        } else {
            System.out.println("Medical record not found.");
        }
    }
}

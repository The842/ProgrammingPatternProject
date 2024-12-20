package org.example;


import org.example.controller.*;
import org.example.model.*;

import javax.swing.*;
import java.sql.Date;
import java.sql.Time;


public class Main {
    public static void main(String[] args) {

        // Create the main frame

        // Initialize the controller and pass the JFrame (primaryStage)
       new MainFrameController();


        testViewAllDoctors();
        testViewAllPatients();


        // View appointments by patient ID and doctor ID
        testViewAppointmentsByPatientId(1);
        testViewAppointmentsByDoctorId(1);

        // Test viewing medical records by patient ID
        testViewMedicalRecordsByPatientId(1);

        // Test updating and deleting appointments
        testUpdateAppointment(1, Date.valueOf("2025-01-10"), Time.valueOf("11:00:00"));
        testDeleteAppointment(1);

        // Test deleting a patient
        testDeletePatient(1);
    }

    // Test adding a new doctor
    public static void testAddDoctor() {
        DoctorModel doctor = new DoctorModel("jimmy", 2, "ADAM", "438-766-5234", "123 behind St");
        DoctorController doctorController = new DoctorController();
        doctorController.addNewDoctor(doctor);
        System.out.println("Doctor added successfully.");
    }

    // Test adding a new patient
    public static void testAddPatient() {
        PatientModel patient = new PatientModel("Willow", 2, "Sara", "525-973-5678", "421 jardin St");
        PatientController patientController = new PatientController();
        patientController.addNewPatient(patient);
        System.out.println("Patient added successfully.");
    }

    // Test adding a new appointment
    public static void testAddAppointment() {
        AppointmentModel appointment = new AppointmentModel(2, Date.valueOf("2024-12-21"), Time.valueOf("12:30:00"), 1, 1);
        AppointmentController appointmentController = new AppointmentController();
        appointmentController.addNewAppointment(appointment);
        System.out.println("Appointment added successfull.");
    }

    // Test adding a new treatment
    public static void testAddTreatment() {
        Treatment treatment = new Treatment(7, "Emergency", "Broken Arm");
        DatabaseController.insertTreatment(treatment);
        System.out.println("Treatment added successfully.");
    }

    // Test adding a new medicine
    public static void testAddMedicine() {
        Medicine medicine = new Medicine(1, "Aspirin", "Pain relief medicine", 1);
        DatabaseController.insertTreatment(medicine);
        System.out.println("Medicine added successfully.");
    }

    // Test adding a new operation
    public static void testAddOperation() {
        Operation operation = new Operation(1, "Heart Surgery", "Surgery for heart issues", "Dr. Smith", Date.valueOf("2024-12-15"));
        DatabaseController.insertTreatment(operation);
        System.out.println("Operation added successfully.");
    }
    public static void testAddMedicalRecord() {
        MedicalRecordController medicalRecordController = new MedicalRecordController();
        Treatment treatment = new Treatment(10, "General Checkup", "has a cold");
        MedicalRecordModel medicalRecord=new MedicalRecordModel( 2,  1, "cold",  treatment,  1000);
        medicalRecordController.addMedicalRecord( medicalRecord);
        System.out.println("Medical Record added successfully.");
    }

    // Test viewing all doctors
    public static void testViewAllDoctors() {
        DoctorController doctorController = new DoctorController();
        doctorController.displayAllDoctors();
    }

    // Test viewing all patients
    public static void testViewAllPatients() {
        PatientController patientController = new PatientController();
        patientController.displayAllPatients();
    }

    // Test viewing appointments by patient ID
    public static void testViewAppointmentsByPatientId(int patientId) {
        AppointmentController appointmentController = new AppointmentController();
        appointmentController.displayAppointmentsByPatientId(patientId);
    }

    // Test viewing appointments by doctor ID
    public static void testViewAppointmentsByDoctorId(int doctorId) {
        AppointmentController appointmentController = new AppointmentController();
        appointmentController.displayAppointmentsByDoctorId(doctorId);
    }

    // Test viewing medical records by patient ID
    public static void testViewMedicalRecordsByPatientId(int patientId) {
        MedicalRecordController medicalRecordController = new MedicalRecordController();
        medicalRecordController.viewMedicalRecordsByPatientId(patientId);
    }

    // Test updating an appointment
    public static void testUpdateAppointment(int appointmentId, Date newDate, Time newTime) {
        AppointmentModel updatedAppointment = new AppointmentModel(appointmentId, newDate, newTime, 1, 1);
        AppointmentController appointmentController = new AppointmentController();
        appointmentController.updateAppointment(appointmentId, newDate, newTime);
        System.out.println("Appointment updated successfully.");
    }

    // Test deleting an appointment
    public static void testDeleteAppointment(int appointmentId) {
        AppointmentController appointmentController = new AppointmentController();
        appointmentController.deleteAppointmentById(appointmentId);
        System.out.println("Appointment deleted successfully.");
    }

    // Test deleting a patient
    public static void testDeletePatient(int patientId) {
        PatientController patientController = new PatientController();
        patientController.deletePatientById(patientId);
        System.out.println("Patient deleted successfully.");
    }
}
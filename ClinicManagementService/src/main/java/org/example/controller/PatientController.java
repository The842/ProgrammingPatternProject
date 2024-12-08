package org.example.controller;

import org.example.model.AppointmentModel;
import org.example.model.PatientModel;
import org.example.util.AppointmentDML;
import org.example.util.PatientDML;

import java.sql.Time;
import java.util.Date;
import java.util.List;

public class PatientController {

    private final AppointmentDML appointmentDML;
    private final PatientDML patientDML;

    public PatientController() {
        appointmentDML = new AppointmentDML();
        patientDML = new PatientDML();
    }

    /**
     * View all appointments for a specific patient.
     */
    public void viewAppointmentsForPatient(int patientId) {
        List<AppointmentModel> appointments = AppointmentDML.getAllAppointments(patientId);
        if (appointments.isEmpty()) {
            System.out.println("No appointments found for this patient.");
        } else {
            for (AppointmentModel appointment : appointments) {
                System.out.println(appointment);
            }
        }
    }

    /**
     * View a specific appointment for a patient.
     */
    public void viewAppointmentById(int appointmentId) {
        AppointmentModel appointment = AppointmentDML.getAppointmentById(appointmentId);
        if (appointment != null) {
            System.out.println(appointment);
        } else {
            System.out.println("Appointment not found.");
        }
    }

    public void createAppointmentForPatient(int doctorId, int patientId, Date appointmentDate, Time appointmentTime) {
        AppointmentModel newAppointment = new AppointmentModel(
                0,
                appointmentDate,
                appointmentTime,
                doctorId,
                patientId
        );

        AppointmentDML.addAppointment(newAppointment);

        System.out.println("Appointment created successfully for patient " + patientId);
    }

    /**
     * Update an existing appointment for a patient.
     */
    public void updateAppointmentForPatient(int appointmentId, Date newDate, Time newTime) {
        AppointmentModel appointment = AppointmentDML.getAppointmentById(appointmentId);
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
     * Delete an appointment for a patient.
     */
    public void deleteAppointmentForPatient(int appointmentId) {
        AppointmentModel appointment = AppointmentDML.getAppointmentById(appointmentId);
        if (appointment != null) {
            AppointmentDML.deleteAppointment(appointmentId);
            System.out.println("Appointment deleted successfully.");
        } else {
            System.out.println("Appointment not found.");
        }
    }

    /**
     * View a specific patient by ID.
     */
    public void viewPatient(int patientId) {
        PatientModel patient = patientDML.getAllPatients().stream()
                .filter(p -> p.getId() == patientId)
                .findFirst()
                .orElse(null);

        if (patient != null) {
            System.out.println(patient);
        } else {
            System.out.println("Patient not found.");
        }
    }
}

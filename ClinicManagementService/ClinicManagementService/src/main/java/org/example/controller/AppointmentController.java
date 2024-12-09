package org.example.controller;

import org.example.model.AppointmentModel;

import java.sql.Time;
import java.sql.Date;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class AppointmentController {
    private final ExecutorService threadPool = Executors.newFixedThreadPool(10);

    /**
     * Add a new appointment
     *
     * @param appointment appointment to add
     */
    public void addNewAppointment(AppointmentModel appointment) {
        threadPool.submit(() -> {
            DatabaseController.insertAppointment(appointment);
            System.out.println("Appointment added successfully.");
        });
    }

    /**
     * Reschedule Appointment
     *
     * @param appointmentId id of appointment to change
     * @param newDate       new date of appointment
     * @param newTime       new time of appointment
     */
    public void updateAppointment(int appointmentId, Date newDate, Time newTime) {
        threadPool.submit(() -> {
            try {
                DatabaseController.updateAppointment(appointmentId, newDate, newTime);
                System.out.println("Appointment updated successfully.");
            } catch (Exception e) {
                System.out.println("Failed to update appointment: " + e.getMessage());
            }
        });
    }

    /**
     * // Delete an appointment by ID
     *
     * @param appointmentId
     */
    public void deleteAppointmentById(int appointmentId) {
        threadPool.submit(() -> {
            try {
                DatabaseController.deleteAppointment(appointmentId);
                System.out.println("Appointment deleted successfully.");
            } catch (Exception e) {
                System.out.println("Failed to delete appointment by id: " + e.getMessage());
            }
        });
    }

    /**
     * View all appointments of a specific patient
     *
     * @param patientId id of patient to check
     */
    public void displayAppointmentsByPatientId(int patientId) {
        threadPool.submit(() -> {
            List<AppointmentModel> appointments = DatabaseController.getAppointmentsByPatientId(patientId);
            if (appointments.isEmpty()) {
                System.out.println("No appointments found for patient with ID: " + patientId);
            } else {
                appointments.forEach(System.out::println);
            }
        });
    }

    /**
     * View all appointments of a specific doctor
     *
     * @param doctorId id of doctor
     */
    public void displayAppointmentsByDoctorId(int doctorId) {
        threadPool.submit(() -> {
            List<AppointmentModel> appointments = DatabaseController.getAppointmentsByDoctorId(doctorId);
            if (appointments.isEmpty()) {
                System.out.println("No appointments found for doctor with ID: " + doctorId);
            } else {
                appointments.forEach(System.out::println);
            }
        });
    }
}

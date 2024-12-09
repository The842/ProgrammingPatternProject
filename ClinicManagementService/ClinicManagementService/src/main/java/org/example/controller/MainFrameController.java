
package org.example.controller;

import org.example.model.*;
import org.example.view.MainFrameView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Locale;

public class MainFrameController {
    private MainFrameView view;
    private Locale currentLocale;

    public MainFrameController() {
        currentLocale = Locale.of("en","Ca"); // Set initial locale as English
        view = new MainFrameView(currentLocale); // Initialize the view with the current locale
        view.frame.setVisible(true); // Make the frame visible
        initializeListeners();
    }

    private void initializeListeners() {
        // Sign in button action (simplified)
        view.btnSignIn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleRoleSelection();
            }
        });

        // Language switch buttons
        view.btnEnglish.addActionListener(e -> updateLocale(Locale.of("en","CA")));
        view.btnFrench.addActionListener(e -> updateLocale(Locale.of("fr","CA")));

        // Appointment button actions (Doctor)
        view.btnAppointments.addActionListener(e -> handleAppointments());

        // Medical Record button actions (Doctor)
        view.btnMedicalRecords.addActionListener(e -> handleMedicalRecords());

        // Doctor and Patient role buttons
        view.btnDoctor.addActionListener(e -> handleDoctorSelection());
        view.btnPatient.addActionListener(e -> handlePatientSelection());
    }

    private void handleRoleSelection() {
        // No need for ID/Last name validation for now
        boolean isDoctor = view.btnDoctor.isSelected();  // Check if the Doctor button is selected

        if (isDoctor) {
            showDoctorView();
        } else {
            showPatientView();
        }
    }

    private void handleDoctorSelection() {
        // Directly allow doctor view without sign-in
        showDoctorView();
    }

    private void handlePatientSelection() {
        // Directly allow patient view without sign-in
        showPatientView();
    }

    private void showDoctorView() {
        // Display doctor buttons and options
        view.btnAppointments.setEnabled(true);
        view.btnMedicalRecords.setEnabled(true);

    }

    private void showPatientView() {
        // Display patient buttons and options
        view.btnAppointments.setEnabled(true);
        view.btnMedicalRecords.setEnabled(true);
    }

    private void handleAppointments() {
        String userId = view.txtUserId.getText();
        String role = view.btnDoctor.getText();

        if (role.equals("Doctor")) {
            AppointmentController appointmentController = new AppointmentController();
            appointmentController.displayAppointmentsByDoctorId(Integer.parseInt(userId));
        } else if (role.equals("Patient")) {
            AppointmentController appointmentController = new AppointmentController();
            appointmentController.displayAppointmentsByPatientId(Integer.parseInt(userId));
        }
    }

    private void handleMedicalRecords() {
        String userId = view.txtUserId.getText();
        String role = view.btnDoctor.getText();

        if (role.equals("Doctor")) {
            MedicalRecordController medicalRecordController = new MedicalRecordController();
            medicalRecordController.viewAllMedicalRecords();
        } else if (role.equals("Patient")) {
            MedicalRecordController medicalRecordController = new MedicalRecordController();
            medicalRecordController.viewMedicalRecordsByPatientId(Integer.parseInt(userId));
        }
    }

    private void updateLocale(Locale locale) {
        currentLocale = locale;
        view.updateView();
    }

    private void showAlert(String title, String message) {
        JOptionPane.showMessageDialog(null, message, title, JOptionPane.ERROR_MESSAGE);
    }
}

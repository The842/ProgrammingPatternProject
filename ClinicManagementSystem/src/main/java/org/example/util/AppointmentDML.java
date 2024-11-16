package org.example.util;

import org.example.model.AppointmentModel;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AppointmentDML {

    public void addAppointment(AppointmentModel appointment) {
        String sql = "INSERT INTO Appointment (appointmentDate, appointmentTime, doctorID, patientID) VALUES (?, ?, ?, ?)";
        try (Connection conn = DatabaseUtil.connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, appointment.getAppointmentDate().toString());
            stmt.setString(2, appointment.getAppointmentTime().toString());
            stmt.setInt(3, appointment.getDoctorID());
            stmt.setInt(4, appointment.getPatientID());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<AppointmentModel> getAllAppointments() {
        List<AppointmentModel> appointments = new ArrayList<>();
        String sql = "SELECT * FROM Appointment";
        try (Connection conn = DatabaseUtil.connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                AppointmentModel appointment = new AppointmentModel(
                        rs.getInt("id"),
                        Date.valueOf(rs.getString("appointmentDate")),
                        Time.valueOf(rs.getString("appointmentTime")),
                        rs.getInt("doctorID"),
                        rs.getInt("patientID")
                );
                appointments.add(appointment);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return appointments;
    }

    public void updateAppointment(AppointmentModel appointment) {
        String sql = "UPDATE Appointment SET appointmentDate = ?, appointmentTime = ?, doctorID = ?, patientID = ? WHERE id = ?";
        try (Connection conn = DatabaseUtil.connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, appointment.getAppointmentDate().toString());
            stmt.setString(2, appointment.getAppointmentTime().toString());
            stmt.setInt(3, appointment.getDoctorID());
            stmt.setInt(4, appointment.getPatientID());
            stmt.setInt(5, appointment.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteAppointment(int appointmentId) {
        String sql = "DELETE FROM Appointment WHERE id = ?";
        try (Connection conn = DatabaseUtil.connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, appointmentId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

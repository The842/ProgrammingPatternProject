package org.example.util;

import org.example.model.MedicalRecordModel;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MedicalRecordDML {

    public void addMedicalRecord(MedicalRecordModel record) {
        String sql = "INSERT INTO MedicalRecord (appointmentID, diagnosis, treatment, bill) VALUES (?, ?, ?, ?)";
        try (Connection conn = DatabaseUtil.connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, record.getAppointmentID());
            stmt.setString(2, record.getDiagnosis());
            stmt.setString(3, record.getTreatment().toString());
            stmt.setDouble(4, record.getBill());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateMedicalRecord(MedicalRecordModel record) {
        String sql = "UPDATE MedicalRecord SET appointmentID = ?, diagnosis = ?, treatment = ?, bill = ? WHERE id = ?";
        try (Connection conn = DatabaseUtil.connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, record.getAppointmentID());
            stmt.setString(2, record.getDiagnosis());
            stmt.setString(3, record.getTreatment().toString());
            stmt.setDouble(4, record.getBill());
            stmt.setInt(5, record.getMedicalRecordID());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteMedicalRecord(int recordId) {
        String sql = "DELETE FROM MedicalRecord WHERE id = ?";
        try (Connection conn = DatabaseUtil.connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, recordId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

package org.example.util;

import org.example.model.DoctorModel;
import org.example.model.MedicalRecordModel;
import org.example.model.Treatment;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MedicalRecordDML {

    public static void addMedicalRecord(MedicalRecordModel record) {
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

    public static void updateMedicalRecord(MedicalRecordModel record) {
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

    public static void deleteMedicalRecord(int recordId) {
        String sql = "DELETE FROM MedicalRecord WHERE id = ?";
        try (Connection conn = DatabaseUtil.connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, recordId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

//    public static List<MedicalRecordModel> getAllMedical(int doctorId) {
//        List<MedicalRecordModel> medicalRecords = new ArrayList<>();
//        String sql = "SELECT id, appointmentID, diagnosis, treatment, bill FROM MedicalRecord WHERE doctorID = ?"; // Add a condition to fetch records for the specific doctor
//
//        try (Connection conn = DatabaseUtil.connect();
//             PreparedStatement stmt = conn.prepareStatement(sql)) {
//
//            stmt.setInt(1, doctorId); // Set the doctor ID parameter
//
//            try (ResultSet rs = stmt.executeQuery()) {
//                while (rs.next()) {
//                    int recordId = rs.getInt("id"); // Changed the variable name to recordId
//                    int appointmentID = rs.getInt("appointmentID");
//                    String diagnosis = rs.getString("diagnosis");
//                    String treatmentStr = rs.getString("treatment");
//                    double bill = rs.getDouble("bill");
//
//                    Treatment treatment = getTreatmentFromString(treatmentStr);
//                    MedicalRecordModel record = new MedicalRecordModel(recordId, appointmentID, diagnosis, treatment, bill);
//                    medicalRecords.add(record);
//                }
//            }
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return medicalRecords;
//    }



//    private static Treatment getTreatmentFromString(String treatmentStr) {
//        String[] parts = treatmentStr.split(",");
//        if (parts.length == 3) {
//            String name = parts[0];
//            String description = parts[1];
//            int doctorId = Integer.parseInt(parts[2]);
//
//            DoctorModel doctor = getDoctorById(doctorId);
//            return new Treatment(id,name, description);
//        }
//        return null;
//    }

    private static DoctorModel getDoctorById(int doctorId) {
        DoctorModel doctor = null;
        String sql = "SELECT id, lastName, firstName, phoneNumber, address FROM Doctor WHERE id = ?";

        try (Connection conn = DatabaseUtil.connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, doctorId);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    String lastName = rs.getString("lastName");
                    String firstName = rs.getString("firstName");
                    String phoneNumber = rs.getString("phoneNumber");
                    String address = rs.getString("address");

                    doctor = new DoctorModel(lastName, doctorId, firstName, phoneNumber, address);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return doctor;
    }

//    public static MedicalRecordModel getMedicalRecordById(int id) {
//        String sql = "SELECT * FROM MedicalRecord WHERE id = ?";
//        try (Connection conn = DatabaseUtil.connect();
//             PreparedStatement stmt = conn.prepareStatement(sql)) {
//            stmt.setInt(1, id);
//            ResultSet rs = stmt.executeQuery();
//
//            if (rs.next()) {
//                String treatmentStr = rs.getString("treatment");
//                Treatment treatment = new Treatment(treatmentStr, "Some description");
//
//                return new MedicalRecordModel(
//                        rs.getInt("id"),
//                        rs.getInt("appointmentID"),
//                        rs.getString("diagnosis"),
//                        treatment,
//                        rs.getDouble("bill")
//                );
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
}

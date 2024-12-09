//package org.example.util;
//
//import org.example.model.PatientModel;
//
//import java.sql.*;
//import java.util.ArrayList;
//import java.util.List;
//
//public class PatientDML {
//
//    public void addPatient(PatientModel patient) {
//        String sql = "INSERT INTO Patient (lastName, firstName, phoneNumber, address) VALUES (?, ?, ?, ?)";
//        try (Connection conn = DatabaseUtil.connect();
//             PreparedStatement stmt = conn.prepareStatement(sql)) {
//            stmt.setString(1, patient.getLastName());
//            stmt.setString(2, patient.getFirstName());
//            stmt.setString(3, patient.getPhoneNumber());
//            stmt.setString(4, patient.getAddress());
//            stmt.executeUpdate();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public List<PatientModel> getAllPatients() {
//        List<PatientModel> patients = new ArrayList<>();
//        String sql = "SELECT * FROM Patient";
//        try (Connection conn = DatabaseUtil.connect();
//             Statement stmt = conn.createStatement();
//             ResultSet rs = stmt.executeQuery(sql)) {
//            while (rs.next()) {
//                PatientModel patient = new PatientModel(
//                        rs.getString("lastName"),
//                        rs.getInt("id"),
//                        rs.getString("firstName"),
//                        rs.getString("phoneNumber"),
//                        rs.getString("address")
//                );
//                patients.add(patient);
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return patients;
//    }
//
//    public void updatePatient(PatientModel patient) {
//        String sql = "UPDATE Patient SET lastName = ?, firstName = ?, phoneNumber = ?, address = ? WHERE id = ?";
//        try (Connection conn = DatabaseUtil.connect();
//             PreparedStatement stmt = conn.prepareStatement(sql)) {
//            stmt.setString(1, patient.getLastName());
//            stmt.setString(2, patient.getFirstName());
//            stmt.setString(3, patient.getPhoneNumber());
//            stmt.setString(4, patient.getAddress());
//            stmt.setInt(5, patient.getId());
//            stmt.executeUpdate();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public void deletePatient(int patientId) {
//        String sql = "DELETE FROM Patient WHERE id = ?";
//        try (Connection conn = DatabaseUtil.connect();
//             PreparedStatement stmt = conn.prepareStatement(sql)) {
//            stmt.setInt(1, patientId);
//            stmt.executeUpdate();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//}

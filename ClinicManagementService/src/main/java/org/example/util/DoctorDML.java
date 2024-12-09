//package org.example.util;
//
//
//import org.example.model.DoctorModel;
//
//import java.sql.*;
//import java.util.ArrayList;
//import java.util.List;
//
////import static org.example.controller.DatabaseUtil.LOCK;
////import static org.example.util.DatabaseUtil.getConnection;
//
//public class DoctorDML {
//
//    public void addDoctor(DoctorModel doctor) {
////        String sql = "INSERT INTO Doctor (lastName, firstName, phoneNumber, address) VALUES (?, ?, ?, ?)";
////        try (Connection conn = DatabaseUtil.connect();
////             PreparedStatement stmt = conn.prepareStatement(sql)) {
////            stmt.setString(1, doctor.getLastName());
////            stmt.setString(2, doctor.getFirstName());
////            stmt.setString(3, doctor.getPhoneNumber());
////            stmt.setString(4, doctor.getAddress());
////            stmt.executeUpdate();
////        } catch (SQLException e) {
////            e.printStackTrace();
////        }
////    }
////
////    public List<DoctorModel> getAllDoctors() {
////        List<DoctorModel> doctors = new ArrayList<>();
////        String sql = "SELECT * FROM Doctor";
////        try (Connection conn = DatabaseUtil.connect();
////             Statement stmt = conn.createStatement();
////             ResultSet rs = stmt.executeQuery(sql)) {
////            while (rs.next()) {
////                DoctorModel doctor = new DoctorModel(
////                        rs.getString("lastName"),
////                        rs.getInt("id"),
////                        rs.getString("firstName"),
////                        rs.getString("phoneNumber"),
////                        rs.getString("address")
////                );
////                doctors.add(doctor);
////            }
////        } catch (SQLException e) {
////            e.printStackTrace();
////        }
////        return doctors;
////    }
////
////    public void updateDoctor(DoctorModel doctor) {
////        String sql = "UPDATE Doctor SET lastName = ?, firstName = ?, phoneNumber = ?, address = ? WHERE id = ?";
////        try (Connection conn = DatabaseUtil.connect();
////             PreparedStatement stmt = conn.prepareStatement(sql)) {
////            stmt.setString(1, doctor.getLastName());
////            stmt.setString(2, doctor.getFirstName());
////            stmt.setString(3, doctor.getPhoneNumber());
////            stmt.setString(4, doctor.getAddress());
////            stmt.setInt(5, doctor.getId());
////            stmt.executeUpdate();
////        } catch (SQLException e) {
////            e.printStackTrace();
////        }
////    }
////
////    public void deleteDoctor(int doctorId) {
////        String sql = "DELETE FROM Doctor WHERE id = ?";
////        try (Connection conn = DatabaseUtil.connect();
////             PreparedStatement stmt = conn.prepareStatement(sql)) {
////            stmt.setInt(1, doctorId);
////            stmt.executeUpdate();
////        } catch (SQLException e) {
////            e.printStackTrace();
////        }
//        /**
//         * Adds a doctor
//         *
//         * @param doctor doctor to insert
//         */
//        public static void insertDoctor(DoctorModel doctor) {
//            LOCK.writeLock().lock();
//            String sql = "INSERT INTO doctor (id, firstName, lastName, phoneNumber, address) VALUES (?, ?, ?, ?, ?)";
//
//            try (Connection connection = DatabaseUtil.getConnection();
//                 PreparedStatement statement = connection.prepareStatement(sql)) {
//                statement.setInt(1, doctor.getId());
//                statement.setString(2, doctor.getFirstName());
//                statement.setString(3, doctor.getLastName());
//                statement.setString(4, doctor.getPhoneNumber());
//                statement.setString(5, doctor.getAddress());
//                statement.executeUpdate();
//            } catch (SQLException e) {
//                throw new RuntimeException(e);
//            } finally {
//                DatabaseUtil.LOCK.writeLock().unlock();
//            }
//        }
//
//    }
//}

package org.example.controller;

import org.example.model.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class DatabaseController {
    public static final String BASE_URL = "jdbc:sqlite:./src/main/resources/database/data.db";
    private static final ReentrantReadWriteLock LOCK = new ReentrantReadWriteLock();

    private static Connection getConnection() {
        Connection connection;

        try {
            connection = DriverManager.getConnection(BASE_URL);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return connection;
    }

    /**
     * create doctor table
     */
    public static void createDoctorTable() {
        String sql = """
                CREATE TABLE IF NOT EXISTS doctor (
                    id INTEGER PRIMARY KEY,
                    firstName TEXT NOT NULL,
                    lastName TEXT NOT NULL,
                    phoneNumber TEXT NOT NULL,
                    address TEXT NOT NULL
                )
                """;
        executeSQL(sql);
    }

    /**
     * create patient table
     */
    public static void createPatientTable() {
        String sql = """
                CREATE TABLE IF NOT EXISTS patient (
                    id INTEGER PRIMARY KEY,
                    firstName TEXT NOT NULL,
                    lastName TEXT NOT NULL,
                    phoneNumber TEXT NOT NULL,
                    address TEXT NOT NULL
                )
                """;
        executeSQL(sql);
    }

    /**
     * create appointment table
     */
    public static void createAppointmentTable() {
        String sql = """
                CREATE TABLE IF NOT EXISTS appointment (
                    id INTEGER PRIMARY KEY,
                    appointmentDate TEXT NOT NULL,
                    appointmentTime TEXT NOT NULL,
                    doctorId INTEGER NOT NULL,
                    patientId INTEGER NOT NULL,
                    FOREIGN KEY (doctorId) REFERENCES doctor (id),
                    FOREIGN KEY (patientId) REFERENCES patient (id)
                )
                """;
        executeSQL(sql);
    }

    /**
     * create treatment table
     */
    public static void createTreatmentTable() {
        String sql = """
                CREATE TABLE IF NOT EXISTS treatment (
                    id INTEGER PRIMARY KEY AUTOINCREMENT,
                    name TEXT NOT NULL,
                    description TEXT NOT NULL,
                    type TEXT NOT NULL
                )
                """;
        executeSQL(sql);
    }

    /**
     * create medicine table
     */
    public static void createMedicineTable() {
        String sql = """
                CREATE TABLE IF NOT EXISTS medicine (
                    id INTEGER PRIMARY KEY,
                    doctorId INTEGER NOT NULL,
                    FOREIGN KEY (id) REFERENCES treatment (id),
                    FOREIGN KEY (doctorId) REFERENCES doctor (id)
                )
                """;
        executeSQL(sql);
    }

    /**
     * create operation table
     */
    public static void createOperationTable() {
        String sql = """
                CREATE TABLE IF NOT EXISTS operation (
                    id INTEGER PRIMARY KEY,
                    surgeonName TEXT NOT NULL,
                    operationDate TEXT NOT NULL,
                    FOREIGN KEY (id) REFERENCES treatment (id)
                )
                """;
        executeSQL(sql);
    }

    /**
     * create medical record table
     */
    public static void createMedicalRecordTable() {
        String sql = """
                CREATE TABLE IF NOT EXISTS medicalRecord (
                    id INTEGER PRIMARY KEY,
                    appointmentId INTEGER NOT NULL,
                    treatmentId INTEGER NOT NULL,
                    bill REAL NOT NULL,
                    FOREIGN KEY (appointmentId) REFERENCES appointment (id),
                    FOREIGN KEY (treatmentId) REFERENCES treatment (id)
                )
                """;
        executeSQL(sql);
    }

    private static void executeSQL(String sql) {
        try (Connection connection = getConnection();
             Statement statement = connection.createStatement()) {
            statement.execute(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    /**
     * Initialize all tables in the database.
     */
    public static void initializeDatabase() {
        LOCK.writeLock().lock();
        try {
            createDoctorTable();
            createPatientTable();
            createAppointmentTable();
            createTreatmentTable();
            createMedicineTable();
            createOperationTable();
            createMedicalRecordTable();
        } finally {
            LOCK.writeLock().unlock();
        }
    }


    /**
     * Adds a doctor
     *
     * @param doctor doctor to insert
     */
    public static void insertDoctor(DoctorModel doctor) {
        LOCK.writeLock().lock();
        String sql = "INSERT INTO doctor (id, firstName, lastName, phoneNumber, address) VALUES (?, ?, ?, ?, ?)";

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, doctor.getId());
            statement.setString(2, doctor.getFirstName());
            statement.setString(3, doctor.getLastName());
            statement.setString(4, doctor.getPhoneNumber());
            statement.setString(5, doctor.getAddress());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            LOCK.writeLock().unlock();
        }
    }

    /**
     * Adds a patient
     *
     * @param patient patient to add
     */
    public static void insertPatient(PatientModel patient) {
        LOCK.writeLock().lock();
        String sql = "INSERT INTO patient (id, firstName, lastName, phoneNumber, address) VALUES (?, ?, ?, ?, ?)";

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, patient.getId());
            statement.setString(2, patient.getFirstName());
            statement.setString(3, patient.getLastName());
            statement.setString(4, patient.getPhoneNumber());
            statement.setString(5, patient.getAddress());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            LOCK.writeLock().unlock();
        }
    }

    /**
     * Adds an appointment
     *
     * @param appointment appointment to add
     */
    public static void insertAppointment(AppointmentModel appointment) {
        LOCK.writeLock().lock();
        String sql = "INSERT INTO appointment (id, appointmentDate, appointmentTime, doctorId, patientId) VALUES (?, ?, ?, ?, ?)";

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, appointment.getAppointmentId());
            statement.setString(2, appointment.getAppointmentDate().toString());
            statement.setString(3, appointment.getAppointmentTime().toString());
            statement.setInt(4, appointment.getDoctorID());
            statement.setInt(5, appointment.getPatientID());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            LOCK.writeLock().unlock();
        }
    }

    /**
     * Adds treatment based on either medicine or operation
     *
     * @param treatment treatment to add
     */
    public static void insertTreatment(Treatment treatment) {
        LOCK.writeLock().lock();
        String sql = "INSERT INTO treatment (id, name, description, type) VALUES (?, ?, ?, ?)";

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, treatment.getId());
            statement.setString(2, treatment.getName());
            statement.setString(3, treatment.getDescription());
            statement.setString(4, treatment instanceof Medicine ? "Medicine" : "Operation");
            statement.executeUpdate();

            if (treatment instanceof Medicine) {
                insertMedicine((Medicine) treatment);
            } else if (treatment instanceof Operation) {
                insertOperation((Operation) treatment);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            LOCK.writeLock().unlock();
        }
    }

    /**
     * Helper method for inserting treatment. It inserts medicine information if the treatment is medicine
     *
     * @param medicine medicine to add
     */
    private static void insertMedicine(Medicine medicine) {
        String sql = "INSERT INTO medicine (id, doctorId) VALUES (?, ?)";

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, medicine.getId());
            statement.setLong(2, medicine.getDoctorId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private static void insertOperation(Operation operation) {
        String sql = "INSERT INTO operation (id, surgeonName, operationDate) VALUES (?, ?, ?)";

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, operation.getId());
            statement.setString(2, operation.getSurgeonName());
            statement.setString(3, operation.getDate().toString());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Adds a medical record
     *
     * @param record record to insert
     */
    public static void insertMedicalRecord(MedicalRecordModel record) {
        LOCK.writeLock().lock();
        String sql = "INSERT INTO medicalRecord (id, appointmentId, treatmentId, bill) VALUES (?, ?, ?, ?)";

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, record.getMedicalRecordID());
            statement.setInt(2, record.getAppointmentID());
            statement.setLong(3, record.getTreatment().getId());
            statement.setDouble(4, record.getBill());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            LOCK.writeLock().unlock();
        }
    }

    /**
     * Deletes a patient by its id
     *
     * @param patientId id of the patient to delete
     */
    public static void deletePatient(int patientId) {
        LOCK.writeLock().lock();
        String sql = "DELETE FROM patient WHERE id = ?";
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, patientId);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            LOCK.writeLock().unlock();
        }
    }

    /**
     * Query patients by id
     *
     * @param patientId id of patient to select
     * @return
     */
    public static PatientModel getPatientById(int patientId) {
        LOCK.readLock().lock();
        String sql = "SELECT * FROM patient WHERE id = ?";
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, patientId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return new PatientModel(
                        resultSet.getString("lastName"),
                        resultSet.getInt("id"),
                        resultSet.getString("firstName"),
                        resultSet.getString("phoneNumber"),
                        resultSet.getString("address")
                );
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            LOCK.readLock().unlock();
        }
        return null;
    }

    /**
     * view all patients
     *
     * @return list of the patients in the system
     */
    public static List<PatientModel> getAllPatients() {
        LOCK.readLock().lock();
        List<PatientModel> patients = new ArrayList<>();
        String sql = "SELECT * FROM patient";
        try (Connection connection = getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            while (resultSet.next()) {
                patients.add(new PatientModel(
                        resultSet.getString("lastName"),
                        resultSet.getInt("id"),
                        resultSet.getString("firstName"),
                        resultSet.getString("phoneNumber"),
                        resultSet.getString("address")
                ));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            LOCK.readLock().unlock();
        }
        return patients;
    }

    /**
     * View all patients of a specific doctor
     *
     * @param doctorId id of the doctor
     * @return the list of patients of that doctor
     */
    public static List<PatientModel> getPatientsByDoctorId(int doctorId) {
        LOCK.readLock().lock();
        List<PatientModel> patients = new ArrayList<>();
        String sql = """
                SELECT p.* FROM patient p
                JOIN appointment a ON p.id = a.patientId
                WHERE a.doctorId = ?
                """;
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, doctorId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                patients.add(new PatientModel(
                        resultSet.getString("lastName"),
                        resultSet.getInt("id"),
                        resultSet.getString("firstName"),
                        resultSet.getString("phoneNumber"),
                        resultSet.getString("address")
                ));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            LOCK.readLock().unlock();
        }
        return patients;
    }

    /**
     * view all doctors
     *
     * @return list of the doctors in the system
     */
    public static List<DoctorModel> getAllDoctors() {
        LOCK.readLock().lock();
        List<DoctorModel> doctors = new ArrayList<>();
        String sql = "SELECT * FROM doctor";
        try (Connection connection = getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            while (resultSet.next()) {
                doctors.add(new DoctorModel(
                        resultSet.getString("lastName"),
                        resultSet.getInt("id"),
                        resultSet.getString("firstName"),
                        resultSet.getString("phoneNumber"),
                        resultSet.getString("address")
                ));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            LOCK.readLock().unlock();
        }
        return doctors;
    }

    /**
     * View doctor of a patient
     *
     * @param patientId id of the patient
     * @return doctor of the patient
     */
    public static DoctorModel getDoctorByPatientId(int patientId) {
        LOCK.readLock().lock();
        String sql = """
                SELECT d.* FROM doctor d
                JOIN appointment a ON d.id = a.doctorId
                WHERE a.patientId = ?
                """;
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, patientId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return new DoctorModel(
                        resultSet.getString("lastName"),
                        resultSet.getInt("id"),
                        resultSet.getString("firstName"),
                        resultSet.getString("phoneNumber"),
                        resultSet.getString("address")
                );
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            LOCK.readLock().unlock();
        }
        return null;
    }

    /**
     * Reschedule Appointment
     *
     * @param appointmentId id of appointment to change
     * @param newDate       new date of appointment
     * @param newTime       new time of appointment
     */
    public static void updateAppointment(int appointmentId, Date newDate, Time newTime) {
        LOCK.writeLock().lock();
        String sql = "UPDATE appointment SET appointmentDate = ?, appointmentTime = ? WHERE id = ?";
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, newDate.toString());
            statement.setString(2, newTime.toString());
            statement.setInt(3, appointmentId);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Failed to update appointment with ID: " + appointmentId, e);
        } finally {
            LOCK.writeLock().unlock();
        }
    }

    /**
     * Deletes appointment by id
     *
     * @param appointmentId id of the appointment to delete
     */
    public static void deleteAppointment(int appointmentId) {
        LOCK.writeLock().lock();
        String sql = "DELETE FROM appointment WHERE id = ?";
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, appointmentId);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            LOCK.writeLock().unlock();
        }
    }

    /**
     * View all appointment of a patient
     *
     * @param patientId id of the patients
     * @return list of appointments of a patient
     */
    public static List<AppointmentModel> getAppointmentsByPatientId(int patientId) {
        LOCK.readLock().lock();
        List<AppointmentModel> appointments = new ArrayList<>();
        String sql = "SELECT * FROM appointment WHERE patientId = ?";
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, patientId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                appointments.add(new AppointmentModel(
                        resultSet.getInt("id"),
                        Date.valueOf(resultSet.getString("appointmentDate")),
                        Time.valueOf(resultSet.getString("appointmentTime")),
                        resultSet.getInt("doctorId"),
                        resultSet.getInt("patientId")
                ));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            LOCK.readLock().unlock();
        }
        return appointments;
    }

    /**
     * View appointments of a specific doctor
     *
     * @param doctorId id of the doctor
     * @return list of appointments of a doctor
     */
    public static List<AppointmentModel> getAppointmentsByDoctorId(int doctorId) {
        LOCK.readLock().lock();
        List<AppointmentModel> appointments = new ArrayList<>();
        String sql = "SELECT * FROM appointment WHERE doctorId = ?";
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, doctorId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                appointments.add(new AppointmentModel(
                        resultSet.getInt("id"),
                        Date.valueOf(resultSet.getString("appointmentDate")),
                        Time.valueOf(resultSet.getString("appointmentTime")),
                        resultSet.getInt("doctorId"),
                        resultSet.getInt("patientId")
                ));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            LOCK.readLock().unlock();
        }
        return appointments;
    }

    /**
     * View all medical records
     *
     * @return a list of medical records
     */
    public static List<MedicalRecordModel> getAllMedicalRecords() {
        LOCK.readLock().lock();
        List<MedicalRecordModel> records = new ArrayList<>();
        String sql = """
                SELECT mr.id, mr.appointmentId, mr.bill, t.id AS treatmentId, t.name AS treatmentName, 
                       t.description AS treatmentDescription, t.type AS treatmentType
                FROM medicalRecord mr
                JOIN treatment t ON mr.treatmentId = t.id
                """;
        try (Connection connection = getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            while (resultSet.next()) {
                // Fetch the treatment based on result set
                Treatment treatment = buildTreatmentFromResultSet(resultSet);
                // Create and add the medical record model
                records.add(new MedicalRecordModel(
                        resultSet.getInt("id"),
                        resultSet.getInt("appointmentId"),
                        treatment.getDescription(),
                        treatment,
                        resultSet.getDouble("bill")
                ));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            LOCK.readLock().unlock();
        }
        return records;
    }

    /**
     * View all medical records of a patient
     *
     * @param patientId id of patient
     * @return a list of medical records of a patient
     */
    public static List<MedicalRecordModel> getMedicalRecordsByPatientId(int patientId) {
        LOCK.readLock().lock();
        List<MedicalRecordModel> records = new ArrayList<>();
        String sql = """
                SELECT mr.id, mr.appointmentId, mr.bill, t.id AS treatmentId, t.name AS treatmentName, 
                       t.description AS treatmentDescription, t.type AS treatmentType
                FROM medicalRecord mr
                JOIN appointment a ON mr.appointmentId = a.id
                JOIN treatment t ON mr.treatmentId = t.id
                WHERE a.patientId = ?
                """;
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, patientId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                // Fetch the treatment based on result set
                Treatment treatment = buildTreatmentFromResultSet(resultSet);
                // Create and add the medical record model
                records.add(new MedicalRecordModel(
                        resultSet.getInt("id"),
                        resultSet.getInt("appointmentId"),
                        treatment.getDescription(),
                        treatment,
                        resultSet.getDouble("bill")
                ));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            LOCK.readLock().unlock();
        }
        return records;
    }

    /**
     * Helper method to be able to access treatment detail
     *
     * @param resultSet retrieved data from database
     * @return details of treatment
     * @throws SQLException
     */
    private static Treatment buildTreatmentFromResultSet(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt("treatmentId");
        String name = resultSet.getString("treatmentName");
        String description = resultSet.getString("treatmentDescription");
        String type = resultSet.getString("treatmentType");

        if ("Medicine".equalsIgnoreCase(type)) {
            int doctorId = resultSet.getInt("doctorId");
            return new Medicine(id, name, description, doctorId);
        } else if ("Operation".equalsIgnoreCase(type)) {
            String surgeonName = resultSet.getString("surgeonName");
            Date date = Date.valueOf(resultSet.getString("operationDate"));
            return new Operation(id, name, description, surgeonName, date);
        } else {
            return new Treatment(id, name, description);
        }
    }
}



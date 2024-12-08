package org.example.controller;

import org.example.model.*;

import java.sql.*;
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
                    id INTEGER PRIMARY KEY,
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
     * Adds a doctor
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
     * @param treatment
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
     * @param medicine medicine to add
     */
    private static void insertMedicine(Medicine medicine)  {
        String sql = "INSERT INTO medicine (id, doctorId) VALUES (?, ?)";

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, medicine.getId());
            statement.setLong(2, medicine.getDoctorId());
            statement.executeUpdate();
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    private static void insertOperation(Operation operation)  {
        String sql = "INSERT INTO operation (id, surgeonName, operationDate) VALUES (?, ?, ?)";

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, operation.getId());
            statement.setString(2, operation.getSurgeonName());
            statement.setString(3, operation.getDate().toString());
            statement.executeUpdate();
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Adds a medical record
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
}

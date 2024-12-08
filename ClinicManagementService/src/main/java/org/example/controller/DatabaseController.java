package org.example.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseController {
    public static final String BASE_URL = "jdbc:sqlite:./src/main/resources/database/data.db";
    private static Connection getConnection() {
        Connection connection;

        try {
            connection = DriverManager.getConnection(BASE_URL);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return connection;
    }

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

    // General SQL executor
    private static void executeSQL(String sql) {
        try (Connection connection = getConnection();
             Statement statement = connection.createStatement()) {
            statement.execute(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

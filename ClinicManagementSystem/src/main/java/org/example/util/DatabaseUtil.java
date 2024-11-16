package org.example.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseUtil {
    public static final String BASE_URL = "jdbc:sqlite:./src/main/resources/database/data.db";
    public static final String CREATE_DOCTOR_TABLE_SQL = """
            CREATE TABLE IF NOT EXISTS Doctor (
                id INTEGER PRIMARY KEY,
                lastName TEXT NOT NULL,
                firstName TEXT NOT NULL,
                phoneNumber TEXT NOT NULL,
                address TEXT NOT NULL
            )""";
    public static final String CREATE_PATIENT_TABLE_SQL = """
            CREATE TABLE IF NOT EXISTS Patient (
                id INTEGER PRIMARY KEY,
                lastName TEXT NOT NULL,
                firstName TEXT NOT NULL,
                phoneNumber TEXT NOT NULL,
                address TEXT NOT NULL
            )""";
    public static final String CREATE_APPOINTMENT_TABLE_SQL = """
            CREATE TABLE IF NOT EXISTS Appointment (
                id INTEGER PRIMARY KEY,
                appointmentDate TEXT NOT NULL,
                appointmentTime TEXT NOT NULL,
                doctorID INTEGER NOT NULL,
                patientID INTEGER NOT NULL,
                FOREIGN KEY (doctorID) REFERENCES Doctor (id),
                FOREIGN KEY (patientID) REFERENCES Patient (id)
            )""";
    public static final String CREATE_MEDICAL_RECORD_TABLE_SQL = """
            CREATE TABLE IF NOT EXISTS MedicalRecord (
                id INTEGER PRIMARY KEY,
                appointmentID INTEGER NOT NULL,
                diagnosis TEXT NOT NULL,
                treatment TEXT NOT NULL,
                bill REAL NOT NULL,
                FOREIGN KEY (appointmentID) REFERENCES Appointment (id)
            )""";

    public static Connection connect() {
        Connection connection;
        try {
            connection = DriverManager.getConnection(BASE_URL);
        } catch (SQLException e) {
            throw new RuntimeException("Failed to connect to the database at " + BASE_URL, e);
        }
        return connection;
    }

    /**
     * execute the commands that are passed
     * @param statementStr the ddl statement that will be passed
     */
    public static void executeDDL(String statementStr) {
        try (Connection conn = connect();
             Statement stmt = conn.createStatement()) {
            stmt.execute(statementStr);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void createTable(String statementStr) {
        if (!statementStr.toUpperCase().contains("CREATE")) {
            System.out.println("error to create table");
            return;
        }
        executeDDL(statementStr);
    }

    public void dropTable(String statementStr) {
        if (!statementStr.toUpperCase().contains("DROP")) {
            System.out.println("error to drop table");
            return;
        }
        executeDDL(statementStr);
    }

    public static void initializeDatabase() {
        executeDDL(CREATE_DOCTOR_TABLE_SQL);
        executeDDL(CREATE_PATIENT_TABLE_SQL);
        executeDDL(CREATE_APPOINTMENT_TABLE_SQL);
        executeDDL(CREATE_MEDICAL_RECORD_TABLE_SQL);
        System.out.println("Database initialized successfully.");
    }
}

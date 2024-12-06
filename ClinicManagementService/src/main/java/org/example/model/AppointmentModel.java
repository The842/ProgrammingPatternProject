package org.example.model;

import java.sql.Time;
import java.util.Date;
import java.util.Objects;

public class AppointmentModel {
    private int appointmentId;
    private Date appointmentDate;
    private Time appointmentTime;
    private int doctorID;
    private int patientID;

    public AppointmentModel(int id, Date appointmentDate, Time appointmentTime, int doctorID, int patientID) {
        this.appointmentId = id;
        this.appointmentDate = appointmentDate;
        this.appointmentTime = appointmentTime;
        this.doctorID = doctorID;
        this.patientID = patientID;
    }

    public int getId() {
        return appointmentId;
    }

    public void setId(int id) {
        this.appointmentId = id;
    }

    public Date getAppointmentDate() {
        return appointmentDate;
    }

    public void setAppointmentDate(String appointmentDate) {
        this.appointmentDate = appointmentDate;
    }

    public Time getAppointmentTime() {
        return appointmentTime;
    }

    public void setAppointmentTime(Time appointmentTime) {
        this.appointmentTime = appointmentTime;
    }

    public int getDoctorID() {
        return doctorID;
    }

    public void setDoctorID(int doctorID) {
        this.doctorID = doctorID;
    }

    public int getPatientID() {
        return patientID;
    }

    public void setPatientID(int patientID) {
        this.patientID = patientID;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AppointmentModel that = (AppointmentModel) o;
        return appointmentId == that.appointmentId && doctorID == that.doctorID && patientID == that.patientID && Objects.equals(appointmentDate, that.appointmentDate) && Objects.equals(appointmentTime, that.appointmentTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(appointmentId, appointmentDate, appointmentTime, doctorID, patientID);
    }

    @Override
    public String toString() {
        return "AppointmentModel{" +
                "id=" + appointmentId +
                ", appointmentDate=" + appointmentDate +
                ", appointmentTime=" + appointmentTime +
                ", doctorID=" + doctorID +
                ", patientID=" + patientID +
                '}';
    }

}

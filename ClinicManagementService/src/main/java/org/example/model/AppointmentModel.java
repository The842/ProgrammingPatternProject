package org.example.model;

import lombok.*;

import java.sql.Time;
import java.util.Date;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode
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

}

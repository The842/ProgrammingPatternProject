package org.example.model;

import lombok.*;

@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Treatment {
    private String name;
    private String description;
    private DoctorModel doctor;

    public Treatment(String name, String description) {
        this.name = name;
        this.description = description;
    }
}

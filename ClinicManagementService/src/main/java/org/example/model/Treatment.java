package org.example.model;

import lombok.*;

@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Treatment {
    private int id;
    private String name;
    private String description;
    private DoctorModel doctor;

    public Treatment(int id,String name, String description) {
        this.id=id;
        this.name = name;
        this.description = description;
    }
}

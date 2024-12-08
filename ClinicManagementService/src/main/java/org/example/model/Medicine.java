package org.example.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class Medicine extends Treatment {
    private DoctorModel doctor;

    public Medicine(String name, String description, DoctorModel doctor) {
        super(name, description);
        this.doctor = doctor;
    }
}

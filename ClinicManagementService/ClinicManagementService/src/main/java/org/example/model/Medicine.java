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
    private int doctorId;

    public Medicine(int id,String name, String description, int doctorId) {
        super(id,name, description);
        this.doctorId = doctorId;
    }
}

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

    public Treatment(String name, String description) {
        this.name = name;
        this.description = description;
    }
}

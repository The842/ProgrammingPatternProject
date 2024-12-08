package org.example.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@ToString
public class Operation extends Treatment {
    private String surgeonName;
    private Date date;

    public Operation(String name, String description, String surgeonName, Date date) {
        super(name, description);
        this.surgeonName = surgeonName;
        this.date = date;
    }
}

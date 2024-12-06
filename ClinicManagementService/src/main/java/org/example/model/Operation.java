package org.example.model;

import java.util.Date;

public class Operation extends Treatment {
    private String surgeonName;
    private Date date;

    public Operation(String name, String description, String surgeonName, Date date) {
        super(name, description);
        this.surgeonName = surgeonName;
        this.date = date;
    }

    public String getSurgeonName() {
        return surgeonName;
    }

    public void setSurgeonName(String surgeonName) {
        this.surgeonName = surgeonName;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Operation{" +
                "surgeonName='" + surgeonName + '\'' +
                ", date=" + date +
                '}';
    }
}

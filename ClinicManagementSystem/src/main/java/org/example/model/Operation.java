package org.example.model;

import java.util.Date;

public class Operation {

    private String name;
    private String description;
    private String surgeonName;
    private Date date;

    public Operation(String name, String description, String surgeonName, Date date) {
        this.name = name;
        this.description = description;
        this.surgeonName = surgeonName;
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", surgeonName='" + surgeonName + '\'' +
                ", date=" + date +
                '}';
    }
}

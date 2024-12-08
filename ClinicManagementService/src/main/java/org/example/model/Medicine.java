package org.example.model;

public class Medicine extends Treatment {
    private DoctorModel doctor;

    public Medicine(String name, String description, DoctorModel doctor) {
        super(name, description);
        this.doctor = doctor;
    }

    public DoctorModel getDoctor() {
        return doctor;
    }

    public void setDoctor(DoctorModel doctor) {
        this.doctor = doctor;
    }

    @Override
    public String toString() {
        return "Medicine{" +
                "doctor=" + doctor +
                '}' + super.toString();
    }
}

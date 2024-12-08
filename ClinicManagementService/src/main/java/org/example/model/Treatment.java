package org.example.model;

public class Treatment {
    private String name;
    private String description;
  //  private DoctorModel doctor;

    public Treatment(String name, String description) {
        this.name = name;
        this.description = description;
       // this.doctor = doctor;
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

//    public DoctorModel getDoctor() {
//        return doctor;
//    }

//    public void setDoctor(DoctorModel doctor) {
//        this.doctor = doctor;
//    }

    @Override
    public String toString() {
        return "Treatment{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
             //   ", doctor=" + doctor +
                '}';
    }
}

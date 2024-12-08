package org.example.model;

public class UserFactory {

    public static UserModel getUser(String type, String lastName, int id, String firstName, String phoneNumber, String address) {
        UserModel user = switch (type.toLowerCase()) {
            case "doctor" -> new DoctorModel(lastName, id, firstName, phoneNumber, address);
            case "patient" -> new PatientModel(lastName, id, firstName, phoneNumber, address);
            default -> throw new IllegalArgumentException("Invalid user type: " + type);
        };
        return user.createUser(lastName, id, firstName, phoneNumber, address);
    }
}

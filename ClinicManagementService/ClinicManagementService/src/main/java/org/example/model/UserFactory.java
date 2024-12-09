package org.example.model;

public class UserFactory {

    /**
     * This method create user of type doctor and patient with factory pattern
     * @param type is the type of user
     * @param lastName is the last name of the user
     * @param id is the id of the user
     * @param firstName is the first name of the user
     * @param phoneNumber is the phone number of the user
     * @param address address of the user
     * @return the creation of the user
     */
    public static UserModel getUser(String type, String lastName, int id, String firstName, String phoneNumber, String address) {
        UserModel user = switch (type.toLowerCase()) {
            case "doctor" -> new DoctorModel(lastName, id, firstName, phoneNumber, address);
            case "patient" -> new PatientModel(lastName, id, firstName, phoneNumber, address);
            default -> throw new IllegalArgumentException("Invalid user type: " + type);
        };
        return user.createUser(lastName, id, firstName, phoneNumber, address);
    }
}

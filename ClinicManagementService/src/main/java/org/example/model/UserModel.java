package org.example.model;

import lombok.*;

import java.util.Objects;

@Setter
@Getter
@EqualsAndHashCode
@ToString
@NoArgsConstructor
public abstract class UserModel implements Login {
    private int id;
    private static int count;
    private String lastName;
    private String firstName;
    private String phoneNumber;
    private String address;

    public UserModel(String lastName, int id, String firstName, String phoneNumber, String address) {
        this.lastName = lastName;
        this.id = count++;
        this.firstName = firstName;
        this.phoneNumber = phoneNumber;
        this.address = address;
    }

    public abstract UserModel createUser(String lastName, int id, String firstName, String phoneNumber, String address);

    public abstract boolean isUserValid(int id, String lastName);


    /**
     * This method check if the id is positive
     *
     * @param id is the id
     * @return true if the id is positive and false if negative
     */
    boolean isValidId(int id) {

        return id <= 0;
    }

    /**
     * This method checks if the last name is not null, not empty and are letters
     * @param lastName is the last name
     * @return true if the last name is valid or false if it is not valid.
     */
    boolean isValidLastName(String lastName) {
        if (lastName == null || lastName.isEmpty()) {
            return false;
        }

        for (int i = 0; i < lastName.length(); i++) {
            char c = lastName.charAt(i);
            if (!Character.isLetter(c)) {
                return false;
            }
        }

        return true;
    }
}

package org.example.model;

import java.util.Objects;

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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserModel userModel = (UserModel) o;
        return id == userModel.id && Objects.equals(lastName, userModel.lastName) && Objects.equals(firstName, userModel.firstName) && Objects.equals(phoneNumber, userModel.phoneNumber) && Objects.equals(address, userModel.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, lastName, firstName, phoneNumber, address);
    }

    @Override
    public String toString() {
        return "UserModel{" +
                "id=" + id +
                ", lastName='" + lastName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", address='" + address + '\'' +
                '}';
    }

    public abstract boolean isUserValid(int id, String lastName);


    /**
     * This method check if the id is positive
     *
     * @param id is the id
     * @return true if the id is positive and false if negative
     */
    boolean isValidId(int id) {
        return id > 0;
    }

    /**
     * This method checks if the last name is not null, not empty and are letters
     *
     * @param lastName is the last name
     * @return true if the last name is valid or false if it is not valid.
     */
    boolean isValidLastName(String lastName) {
        if (lastName != null && !lastName.isEmpty() && lastName.matches("[a-zA-Z]+")) {
            return true;
        } else {
            return false;
        }
    }
}

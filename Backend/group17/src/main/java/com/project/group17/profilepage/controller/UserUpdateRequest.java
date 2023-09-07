package com.project.group17.profilepage.controller;

/**
 * Represents a request to update user details.
 */
public class UserUpdateRequest {

    private String firstname;
    private String lastname;
    private String email;
    private String city;
    private String province;
    private String streetAddress;
    private String gender;
    private String age;
    private String phoneNumber;

    /**
     * Sets the user's first name.
     */
    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    /**
     * Sets the user's last name.
     */
    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    /**
     * Sets the user's email address.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Sets the user's city of residence.
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * Sets the user's province or state of residence.
     */
    public void setProvince(String province) {
        this.province = province;
    }

    /**
     * Sets the user's street address.
     */
    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }

    /**
     * Sets the user's gender.
     */
    public void setGender(String gender) {
        this.gender = gender;
    }

    /**
     * Sets the user's age.
     */
    public void setAge(String age) {
        this.age = age;
    }

    /**
     * Sets the user's phone number.
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    /**
     * Gets the user's first name.
     */
    public String getFirstname() {
        return firstname;
    }

    /**
     * Gets the user's last name.
     */
    public String getLastname() {
        return lastname;
    }

    /**
     * Gets the user's email address.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Gets the user's city of residence.
     */
    public String getCity() {
        return city;
    }

    /**
     * Gets the user's province or state of residence.
     */
    public String getProvince() {
        return province;
    }

    /**
     * Gets the user's street address.
     */
    public String getStreetAddress() {
        return streetAddress;
    }

    /**
     * Gets the user's gender.
     */
    public String getGender() {
        return gender;
    }

    /**
     * Gets the user's age.
     */
    public String getAge() {
        return age;
    }

    /**
     * Gets the user's phone number.
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * Returns a string representation of the object.
     */
    @Override
    public String toString() {
        return "UserUpdateRequest{" +
                "firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", email='" + email + '\'' +
                ", city='" + city + '\'' +
                ", province='" + province + '\'' +
                ", streetAddress='" + streetAddress + '\'' +
                ", gender='" + gender + '\'' +
                ", age='" + age + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }
}

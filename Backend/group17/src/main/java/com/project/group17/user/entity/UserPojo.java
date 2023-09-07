package com.project.group17.user.entity;


/**
 * UserPojo is a Plain Old Java Object (POJO) representing a user.
 * It is used to hold and transfer user data.
 */
public class UserPojo {

    int userID;
    String firstName;
    String lastName;
    double similarity;
    private String email;
    private String age;
    private String city;
    private String province;
    private String streetAddress;
    private String profilePicBase64;
    private String gender;
    private String phoneNumber;

    /**
     * Gets the user's email.
     *
     * @return the user's email.
     */
    public String getEmail() {
        return email;
    }


    /**
     * Sets the user's email.
     *
     * @param email the user's email.
     */
    public void setEmail(String email) {
        this.email = email;
    }
    /**
     * Gets the user's age.
     *
     * @return the user's age.
     */
    public String getAge() {
        return age;
    }
    /**
     * Sets the user's age.
     *
     * @param age the user's age.
     */
    public void setAge(String age) {
        this.age = age;
    }
    /**
     * Gets the user's city.
     *
     * @return the user's city.
     */
    public String getCity() {
        return city;
    }
    /**
     * Sets the user's city.
     *
     * @param city the user's city.
     */
    public void setCity(String city) {
        this.city = city;
    }
    /**
     * Gets the user's province.
     *
     * @return the user's province.
     */
    public String getProvince() {
        return province;
    }
    /**
     * Sets the user's province.
     *
     * @param province the user's province.
     */
    public void setProvince(String province) {
        this.province = province;
    }
    /**
     * Gets the user's street address.
     *
     * @return the user's street address.
     */
    public String getStreetAddress() {
        return streetAddress;
    }
    /**
     * Sets the user's street address.
     *
     * @param streetAddress the user's street address.
     */
    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }
    /**
     * Gets the user's profile picture as a base64-encoded string.
     *
     * @return the user's profile picture as a base64-encoded string.
     */
    public String getProfilePicBase64() {
        return profilePicBase64;
    }
    /**
     * Sets the user's profile picture as a base64-encoded string.
     *
     * @param profilePicBase64 the user's profile picture as a base64-encoded string.
     */
    public void setProfilePicBase64(String profilePicBase64) {
        this.profilePicBase64 = profilePicBase64;
    }

    /**
     * Gets the user's gender.
     *
     * @return the user's gender.
     */
    public String getGender() {
        return gender;
    }
    /**
     * Sets the user's gender.
     *
     * @param gender the user's gender.
     */
    public void setGender(String gender) {
        this.gender = gender;
    }

    /**
     * Gets the user's phone number.
     *
     * @return the user's phone number.
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * Sets the user's phone number.
     *
     * @param phoneNumber the user's phone number.
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    /**
     * Gets the user's ID.
     *
     * @return the user's ID.
     */
    public int getUserID() {
        return userID;
    }
    /**
     * Sets the user's ID.
     *
     * @param userID the user's ID.
     */
    public void setUserID(int userID) {
        this.userID = userID;
    }
    /**
     * Gets the user's first name.
     *
     * @return the user's first name.
     */
    public String getFirstName() {
        return firstName;
    }
    /**
     * Sets the user's first name.
     *
     * @param firstName the user's first name.
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    /**
     * Gets the user's last name.
     *
     * @return the user's last name.
     */
    public String getLastName() {
        return lastName;
    }
    /**
     * Sets the user's last name.
     *
     * @param lastName the user's last name.
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    /**
     * Gets the similarity score between users.
     *
     * @return the similarity score.
     */
    public double getSimilarity() {
        return similarity;
    }
    /**
     * Sets the similarity score between users.
     *
     * @param similarity the similarity score.
     */
    public void setSimilarity(double similarity) {
        this.similarity = similarity;
    }
    /**
     * Generates a string representation of the UserPojo object.
     *
     * @return the string representation of the UserPojo object.
     */
    @Override
    public String toString() {
        return "UserPojo{" +
                "userID=" + userID +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", similarity=" + similarity +
                ", email='" + email + '\'' +
                ", age='" + age + '\'' +
                ", city='" + city + '\'' +
                ", province='" + province + '\'' +
                ", streetAddress='" + streetAddress + '\'' +
                ", profilePicBase64='" + profilePicBase64 + '\'' +
                ", gender='" + gender + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }
}


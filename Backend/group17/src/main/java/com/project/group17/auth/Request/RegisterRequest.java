package com.project.group17.auth.Request;

/**
 * This class represents a registration request containing the user's information.
 * It is used to register a new user in the system.
 */
public class RegisterRequest {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String age;
    private String city;
    private String province;
    private String streetAddress;
    private String profilePicBase64;
    private String gender;
    private String phoneNumber;

    /**
     * Constructs a RegisterRequest with the specified user information.
     *
     * @param firstName      The user's first name.
     * @param lastName       The user's last name.
     * @param email          The user's email address.
     * @param password       The user's password.
     * @param age            The user's age.
     * @param city           The user's city.
     * @param province       The user's province.
     * @param streetAddress  The user's street address.
     * @param profilePicBase64 The user's profile picture encoded in Base64.
     * @param gender         The user's gender.
     * @param phoneNumber    The user's phone number.
     */
    public RegisterRequest(String firstName, String lastName, String email, String password, String age, String city,
                           String province, String streetAddress, String profilePicBase64, String gender, String phoneNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.age = age;
        this.city = city;
        this.province = province;
        this.streetAddress = streetAddress;
        this.profilePicBase64 = profilePicBase64;
        this.gender = gender;
        this.phoneNumber = phoneNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getStreetAddress() {
        return streetAddress;
    }

    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }

    public String getProfilePicBase64() {
        return profilePicBase64;
    }

    public void setProfilePicBase64(String profilePicBase64) {
        this.profilePicBase64 = profilePicBase64;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}

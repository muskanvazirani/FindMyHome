package com.project.group17.user.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Collection;
import java.util.List;

/**
 * User is an entity class representing a user in the system. It implements UserDetails
 * for Spring Security integration.
 */
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String firstname;
    private String lastname;
    @Column(unique = true)
    private String email;
    //private String userName;
    private String password;
    private String age;
    private String city;
    private String province;
    private String streetAddress;
    // @Column(name = "file64", columnDefinition = "VARCHAR(MAX)") use this def if longblob not working
    @Lob
    @Basic(fetch = FetchType.LAZY)
    @Column(name = "file64", columnDefinition = "LONGBLOB")
    private String profilePicBase64;
    private String gender;
    private String phoneNumber;

    @Enumerated(EnumType.ORDINAL)
    private Role role;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @JsonIgnore
    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    /**
     * Gets the user's ID.
     *
     * @return the user's ID.
     */
    public Integer getId() {
        return id;
    }

    /**
     * Sets the user's ID.
     *
     * @param id the user's ID.
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Gets the user's first name.
     *
     * @return the user's first name.
     */
    public String getFirstname() {
        return firstname;
    }

    /**
     * Sets the user's first name.
     *
     * @param firstname the user's first name.
     */
    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    /**
     * Gets the user's last name.
     *
     * @return the user's last name.
     */
    public String getLastname() {
        return lastname;
    }

    /**
     * Sets the user's last name.
     *
     * @param lastname the user's last name.
     */
    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

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
     * Generates a string representation of the user object.
     *
     * @return the string representation of the user object.
     */
    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", age='" + age + '\'' +
                ", city='" + city + '\'' +
                ", province='" + province + '\'' +
                ", streetAddress='" + streetAddress + '\'' +
                ", gender='" + gender + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", role=" + role +
                '}';
    }
    /**
     * Sets the user's password.
     *
     * @param password the user's password.
     */
    public void setPassword(String password) {
        this.password = password;
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
     * @param address the user's street address.
     */
    public void setStreetAddress(String address) {
        this.streetAddress = address;
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
}

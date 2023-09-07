package com.project.group17.listings.entity;

import com.project.group17.user.entity.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

/**
 * Represents a listing in the application.
 */
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "listings")
public class ListingsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long listingId;
    private String type;
    private String address;
    private String utilities;
    private float rent;
    private String details;

    @ManyToOne
    @JoinColumn(name = "user")
    private User user;

    @Lob
    @Basic(fetch = FetchType.LAZY)
    @Column(name = "file64", columnDefinition = "LONGBLOB")
    private String profilePicBase64;

    @Lob
    @Basic(fetch = FetchType.LAZY)
    @Column(name = "sfile64", columnDefinition = "LONGBLOB")
    private String secondProfilePicBase64;

    /**
     * Gets the user who created the listing.
     *
     * @return The user who created the listing.
     */
    public User getUser() {
        return user;
    }

    /**
     * Sets the user who created the listing.
     *
     * @param user The user who created the listing.
     */
    public void setUser(User user) {
        this.user = user;
    }

    /**
     * Gets the listing ID.
     *
     * @return The listing ID.
     */
    public long getListingId() {
        return listingId;
    }

    /**
     * Sets the listing ID.
     *
     * @param listingId The listing ID.
     */
    public void setListingId(long listingId) {
        this.listingId = listingId;
    }

    /**
     * Gets the type of the listing.
     *
     * @return The type of the listing.
     */
    public String getType() {
        return type;
    }

    /**
     * Sets the type of the listing.
     *
     * @param type The type of the listing.
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * Gets the address of the listing.
     *
     * @return The address of the listing.
     */
    public String getAddress() {
        return address;
    }

    /**
     * Sets the address of the listing.
     *
     * @param address The address of the listing.
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * Gets the utilities included in the listing.
     *
     * @return The utilities included in the listing.
     */
    public String getUtilities() {
        return utilities;
    }

    /**
     * Sets the utilities included in the listing.
     *
     * @param utilities The utilities included in the listing.
     */
    public void setUtilities(String utilities) {
        this.utilities = utilities;
    }

    /**
     * Gets the rent of the listing.
     *
     * @return The rent of the listing.
     */
    public float getRent() {
        return rent;
    }

    /**
     * Sets the rent of the listing.
     *
     * @param rent The rent of the listing.
     */
    public void setRent(float rent) {
        this.rent = rent;
    }

    /**
     * Gets the details of the listing.
     *
     * @return The details of the listing.
     */
    public String getDetails() {
        return details;
    }

    /**
     * Sets the details of the listing.
     *
     * @param details The details of the listing.
     */
    public void setDetails(String details) {
        this.details = details;
    }

    /**
     * Gets the profile picture (base64 encoded) of the listing.
     *
     * @return The profile picture (base64 encoded) of the listing.
     */
    public String getProfilePicBase64() {
        return profilePicBase64;
    }

    /**
     * Sets the profile picture (base64 encoded) of the listing.
     *
     * @param profilePicBase64 The profile picture (base64 encoded) of the listing.
     */
    public void setProfilePicBase64(String profilePicBase64) {
        this.profilePicBase64 = profilePicBase64;
    }

    /**
     * Gets the second profile picture (base64 encoded) of the listing.
     *
     * @return The second profile picture (base64 encoded) of the listing.
     */
    public String getSecondProfilePicBase64() {
        return secondProfilePicBase64;
    }

    /**
     * Sets the second profile picture (base64 encoded) of the listing.
     *
     * @param secondProfilePicBase64 The second profile picture (base64 encoded) of the listing.
     */
    public void setSecondProfilePicBase64(String secondProfilePicBase64) {
        this.secondProfilePicBase64 = secondProfilePicBase64;
    }
}
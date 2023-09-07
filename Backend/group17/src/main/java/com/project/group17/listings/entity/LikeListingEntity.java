package com.project.group17.listings.entity;

import com.project.group17.user.entity.User;
import jakarta.persistence.*;

/**
 * LikeListingEntity is a JPA entity class representing a liked listing.
 */
@Entity
@Table(name = "listings_liked")
public class LikeListingEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;

    @ManyToOne
    @JoinColumn(name = "user")
    private User user;

    @ManyToOne
    @JoinColumn(name = "listings")
    private ListingsEntity listingsEntity;

    /**
     * Returns the ID of the liked listing.
     *
     * @return The ID of the liked listing.
     */
    public long getId() {
        return id;
    }

    /**
     * Sets the ID of the liked listing.
     *
     * @param id The ID of the liked listing.
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * Returns the user who liked the listing.
     *
     * @return The user who liked the listing.
     */
    public User getUser() {
        return user;
    }

    /**
     * Sets the user who liked the listing.
     *
     * @param user The user who liked the listing.
     */
    public void setUser(User user) {
        this.user = user;
    }

    /**
     * Returns the ListingsEntity object representing the listing.
     *
     * @return The ListingsEntity object representing the listing.
     */
    public ListingsEntity getListingsEntity() {
        return listingsEntity;
    }

    /**
     * Sets the ListingsEntity object representing the listing.
     *
     * @param listingsEntity The ListingsEntity object representing the listing.
     */
    public void setListingsEntity(ListingsEntity listingsEntity) {
        this.listingsEntity = listingsEntity;
    }
}

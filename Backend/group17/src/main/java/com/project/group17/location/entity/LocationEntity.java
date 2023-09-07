package com.project.group17.location.entity;

import jakarta.persistence.*;
/**
 * LocationEntity is an entity class representing city locations.
 */
@Entity
@Table(name = "city_locations")
public class LocationEntity {

    @Id
    @Column(nullable = false)
    String city;

    @Column(nullable = false)
    String latitude;

    @Column(nullable = false)
    String longitude;

    /**
     * Default constructor for LocationEntity.
     */
    public LocationEntity() {
    }

    /**
     * Constructor for LocationEntity with parameters.
     *
     * @param city      The city name.
     * @param latitude  The latitude of the city.
     * @param longitude The longitude of the city.
     */
    public LocationEntity(String city, String latitude, String longitude) {
        this.city = city;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    /**
     * Returns the city name.
     *
     * @return The city name.
     */
    public String getCity() {
        return city;
    }

    /**
     * Sets the city name.
     *
     * @param city The city name.
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * Returns the latitude of the city.
     *
     * @return The latitude of the city.
     */
    public String getLatitude() {
        return latitude;
    }

    /**
     * Sets the latitude of the city.
     *
     * @param latitude The latitude of the city.
     */
    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    /**
     * Returns the longitude of the city.
     *
     * @return The longitude of the city.
     */
    public String getLongitude() {
        return longitude;
    }

    /**
     * Sets the longitude of the city.
     *
     * @param longitude The longitude of the city.
     */
    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }
}

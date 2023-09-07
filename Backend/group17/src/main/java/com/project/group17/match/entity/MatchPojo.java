package com.project.group17.match.entity;

/**
 * This class represents a data transfer object (DTO) for the MatchEntity.
 * It is used to transfer data between the client and server.
 */
public class MatchPojo {

    // User2's ID
    private Integer user2ID;

    /**
     * Gets the user2ID.
     *
     * @return The user2ID.
     */
    public Integer getUser2ID() {
        return user2ID;
    }

    /**
     * Sets the user2ID.
     *
     * @param user2ID The user2ID to set.
     */
    public void setUser2ID(Integer user2ID) {
        this.user2ID = user2ID;
    }
}

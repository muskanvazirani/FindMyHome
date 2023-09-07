package com.project.group17.match.entity;

import com.project.group17.user.entity.User;

import java.io.Serializable;
import java.util.Objects;

/**
 * This class represents the composite primary key for the MatchEntity.
 */
public class MatchId implements Serializable {

    private User user1;
    private User user2;

    /**
     * Default constructor.
     */
    public MatchId() {
    }

    /**
     * Determines if two MatchId objects are equal.
     *
     * @param o The object to compare this MatchId to.
     * @return true if the objects are equal, false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MatchId matchId = (MatchId) o;
        return Objects.equals(user1, matchId.user1) && Objects.equals(user2, matchId.user2);
    }

    /**
     * Generates a hash code for the MatchId object.
     *
     * @return The hash code.
     */
    @Override
    public int hashCode() {
        return Objects.hash(user1, user2);
    }
}

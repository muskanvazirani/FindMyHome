package com.project.group17.match.entity;

import com.project.group17.user.entity.User;
import jakarta.persistence.*;

/**
 * MatchEntity is a JPA entity representing a match between two users.
 */
@Entity
@IdClass(MatchId.class)
@Table(name = "matches")
public class MatchEntity {

    @Id
    @ManyToOne(optional = false)
    @JoinColumn(name = "liker", nullable = false)
    private User user1;

    @Id
    @ManyToOne(optional = false)
    @JoinColumn(name = "likee", nullable = false)
    private User user2;

    /**
     * Gets the first user (liker) in the match.
     *
     * @return The first user (liker) in the match.
     */
    public User getUser1() {
        return user1;
    }

    /**
     * Sets the first user (liker) in the match.
     *
     * @param user1 The first user (liker) to set.
     */
    public void setUser1(User user1) {
        this.user1 = user1;
    }

    /**
     * Gets the second user (likee) in the match.
     *
     * @return The second user (likee) in the match.
     */
    public User getUser2() {
        return user2;
    }

    /**
     * Sets the second user (likee) in the match.
     *
     * @param user2 The second user (likee) to set.
     */
    public void setUser2(User user2) {
        this.user2 = user2;
    }

    /**
     * Returns a string representation of the match.
     *
     * @return A string representation of the match.
     */
    @Override
    public String toString() {
        return "MatchEntity{" +
                "user1=" + user1 +
                ", user2=" + user2 +
                '}';
    }
}


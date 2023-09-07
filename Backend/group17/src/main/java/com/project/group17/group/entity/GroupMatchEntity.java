package com.project.group17.group.entity;

import com.project.group17.user.entity.User;
import jakarta.persistence.*;

/**
 * Represents a group match entity containing information about
 * the user and the group they are matched with.
 */
@Entity
@Table(name = "group_matches")
public class GroupMatchEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private long id;

    @ManyToOne
    @JoinColumn(name = "user", nullable = false)
    private User user;

    private long groupId;

    /**
     * Gets the user associated with this group match entity.
     *
     * @return The user.
     */
    public User getUser() {
        return user;
    }

    /**
     * Sets the user associated with this group match entity.
     *
     * @param user The user to set.
     */
    public void setUser(User user) {
        this.user = user;
    }

    /**
     * Gets the group ID.
     *
     * @return The group ID.
     */
    public long getGroupId() {
        return groupId;
    }

    /**
     * Sets the group ID.
     *
     * @param groupId The group ID to set.
     */
    public void setGroupId(long groupId) {
        this.groupId = groupId;
    }
}

package com.project.group17.group.entity;

import com.project.group17.user.entity.User;
import jakarta.persistence.*;

/**
 * Represents a group entity with information about the group and its members.
 */
@Entity
@Table(name = "groups")
public class GroupEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer index;
    private long groupId;

    @ManyToOne
    @JoinColumn(name = "user")
    private User user;

    private int total_groups;

    /**
     * Gets the index of the group entity.
     *
     * @return The index of the group entity.
     */
    public Integer getIndex() {
        return index;
    }

    /**
     * Sets the index of the group entity.
     *
     * @param index The index to set.
     */
    public void setIndex(Integer index) {
        this.index = index;
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

    /**
     * Gets the user associated with this group entity.
     *
     * @return The user.
     */
    public User getUser() {
        return user;
    }

    /**
     * Sets the user associated with this group entity.
     *
     * @param user The user to set.
     */
    public void setUser(User user) {
        this.user = user;
    }

    /**
     * Gets the total number of groups.
     *
     * @return The total number of groups.
     */
    public long getTotal_groups() {
        return total_groups;
    }

    /**
     * Sets the total number of groups.
     *
     * @param total_groups The total number of groups to set.
     */
    public void setTotal_groups(int total_groups) {
        this.total_groups = total_groups;
    }

    @Override
    public String toString() {
        return "GroupEntity{" +
                "index=" + index +
                ", groupId=" + groupId +
                ", user=" + user +
                ", total_groups=" + total_groups +
                '}';
    }
}

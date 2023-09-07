package com.project.group17.group.entity;

import com.project.group17.user.entity.UserPojo;

import java.util.List;

/**
 * Represents a group POJO (Plain Old Java Object) containing information about a group,
 * including its ID, average similarity score, and the list of users in the group.
 */
public class GroupPojo {

    private long groupID;
    private double avgSimilarity;
    private List<UserPojo> users;

    /**
     * Gets the group ID.
     *
     * @return The group ID.
     */
    public long getGroupID() {
        return groupID;
    }

    /**
     * Sets the group ID.
     *
     * @param groupID The group ID to set.
     */
    public void setGroupID(long groupID) {
        this.groupID = groupID;
    }

    /**
     * Gets the average similarity score.
     *
     * @return The average similarity score.
     */
    public double getAvgSimilarity() {
        return avgSimilarity;
    }

    /**
     * Sets the average similarity score.
     *
     * @param avgSimilarity The average similarity score to set.
     */
    public void setAvgSimilarity(double avgSimilarity) {
        this.avgSimilarity = avgSimilarity;
    }

    /**
     * Gets the list of users in the group.
     *
     * @return The list of users in the group.
     */
    public List<UserPojo> getUsers() {
        return users;
    }

    /**
     * Sets the list of users in the group.
     *
     * @param users The list of users to set.
     */
    public void setUsers(List<UserPojo> users) {
        this.users = users;
    }

    @Override
    public String toString() {
        return "GroupPojo{" +
                "groupID=" + groupID +
                ", avgSimilarity=" + avgSimilarity +
                ", users=" + users +
                '}';
    }
}

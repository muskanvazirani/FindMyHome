package com.project.group17.group.entity;

/**
 * Represents a group match POJO (Plain Old Java Object) containing information about
 * the user and the group they are matched with or requesting to match with.
 */
public class GroupMatchPojo {

    private long userId;
    private long groupId;

    /**
     * Gets the user ID.
     *
     * @return The user ID.
     */
    public long getUserId() {
        return userId;
    }

    /**
     * Sets the user ID.
     *
     * @param userId The user ID to set.
     */
    public void setUserId(long userId) {
        this.userId = userId;
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

package com.project.group17.group.repository;

import com.project.group17.group.entity.GroupMatchEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * GroupMatchRepository is an interface that extends JpaRepository to handle
 * the database operations for the GroupMatchEntity.
 */
public interface GroupMatchRepository extends JpaRepository<GroupMatchEntity, Long> {

    /**
     * Deletes the GroupMatchEntity by the given request ID.
     *
     * @param requestId The request ID to delete.
     */
    void deleteById(long requestId);

    /**
     * Retrieves a list of user IDs for the specified group ID.
     *
     * @param group_id The group ID to find user IDs for.
     * @return A list of user IDs for the specified group ID.
     */
    @Query(value = "SELECT user FROM group_matches WHERE group_id = ?1", nativeQuery = true)
    List<Integer> getUsersByGroupId(int group_id);

    /**
     * Retrieves the index of a GroupMatchEntity for a given user ID.
     *
     * @param user_id The user ID to find the index for.
     * @return The index of the GroupMatchEntity for the specified user ID.
     */
    @Query(value = "SELECT id FROM group_matches WHERE user = ?1", nativeQuery = true)
    long getIndexByUserId(long user_id);

}

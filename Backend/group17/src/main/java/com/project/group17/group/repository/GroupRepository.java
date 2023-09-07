package com.project.group17.group.repository;

import com.project.group17.group.entity.GroupEntity;
import com.project.group17.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * GroupRepository is an interface that extends JpaRepository to handle
 * the database operations for the GroupEntity.
 */
@Repository
public interface GroupRepository extends JpaRepository<GroupEntity, Long> {

    /**
     * Retrieves a list of GroupEntities for the specified user.
     *
     * @param user The user to find GroupEntities for.
     * @return A list of GroupEntities for the specified user.
     */
    List<GroupEntity> findByUser(User user);

    /**
     * Retrieves a list of GroupEntities for the specified group ID.
     *
     * @param groupID The group ID to find GroupEntities for.
     * @return A list of GroupEntities for the specified group ID.
     */
    List<GroupEntity> findByGroupId(Long groupID);

    /**
     * Retrieves the maximum group count from the "groups" table.
     *
     * @return The maximum group count.
     */
    @Query(value = "SELECT max(total_groups) FROM groups", nativeQuery = true)
    int getMaxGroupCount();

    /**
     * Retrieves the group ID for the specified user.
     *
     * @param user The user to find the group ID for.
     * @return The group ID for the specified user.
     */
    @Query(value = "SELECT group_id FROM groups WHERE user = ?1", nativeQuery = true)
    int getGroupId(int user);

    /**
     * Retrieves a list of user IDs for the specified group ID.
     *
     * @param group_id The group ID to find user IDs for.
     * @return A list of user IDs for the specified group ID.
     */
    @Query(value = "SELECT user FROM groups WHERE group_id = ?1", nativeQuery = true)
    List<Integer> getUsersByGroupId(int group_id);
}

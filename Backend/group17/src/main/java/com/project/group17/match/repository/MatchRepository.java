package com.project.group17.match.repository;

import com.project.group17.match.entity.MatchEntity;
import com.project.group17.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * This interface defines the MatchRepository, which extends JpaRepository for CRUD operations
 * on MatchEntity objects.
 */
@Repository
public interface MatchRepository extends JpaRepository<MatchEntity, Long> {

    /**
     * Finds all MatchEntity objects where user1 matches the given user.
     *
     * @param user2ID The user to search for.
     * @return A list of MatchEntity objects with the specified user1.
     */
    List<MatchEntity> findByUser1(User user2ID);

    /**
     * Finds all MatchEntity objects where user2 matches the given user.
     *
     * @param user1 The user to search for.
     * @return A list of MatchEntity objects with the specified user2.
     */
    List<MatchEntity> findByUser2(User user1);
}

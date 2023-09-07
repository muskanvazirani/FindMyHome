package com.project.group17.user.repository;

import com.project.group17.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * UserRepository interface for handling User entity data access operations.
 * Extends JpaRepository to inherit the basic CRUD functionality.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    /**
     * Finds a User entity by its email.
     *
     * @param email the email of the user to search for.
     * @return an Optional containing the User entity if found, otherwise empty.
     */
    Optional<User> findByEmail(String email);

    /**
     * Finds a User entity by its ID.
     *
     * @param id the ID of the user to search for.
     * @return an Optional containing the User entity if found, otherwise empty.
     */
    Optional<User> findById(Integer id);
}

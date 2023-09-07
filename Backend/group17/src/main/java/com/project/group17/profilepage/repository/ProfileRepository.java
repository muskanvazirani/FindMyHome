package com.project.group17.profilepage.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.project.group17.user.entity.User;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

@Repository
public class ProfileRepository  {

    @Autowired
    private EntityManager entityManager;

    /**
     * Saves the edited user details to the database.
     * @param user The user object containing the edited user details.
     */
    @Transactional
    public void saveEditUserDetails(User user) {
        entityManager.merge(user);
    }
}

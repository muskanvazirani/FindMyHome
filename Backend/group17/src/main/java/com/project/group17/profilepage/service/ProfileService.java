package com.project.group17.profilepage.service;

import com.project.group17.profilepage.controller.UserUpdateRequest;
import com.project.group17.user.entity.User;
import com.project.group17.profilepage.repository.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * The ProfileService class provides methods to update the user details in the database.
 */
@Service
public class ProfileService {

    @Autowired
    ProfileRepository profilerepo;

    /**
     * Saves the updated user details to the database.
     * @param updatedUser The updated user details sent from the client.
     * @param principalUser The authenticated user whose details are being updated.
     */
    public void saveEditUserDetails(UserUpdateRequest updatedUser  , User principalUser) {
        // Set the updated user details to the principal user object.
        principalUser.setFirstname(updatedUser.getFirstname());
        principalUser.setLastname(updatedUser.getLastname());
        principalUser.setGender(updatedUser.getGender());
        principalUser.setEmail(updatedUser.getEmail());
        principalUser.setStreetAddress(updatedUser.getStreetAddress());
        principalUser.setCity(updatedUser.getCity());
        principalUser.setProvince(updatedUser.getProvince());
        principalUser.setPhoneNumber(updatedUser.getPhoneNumber());
        principalUser.setAge(updatedUser.getAge());

        // Save the updated user details to the database.
        profilerepo.saveEditUserDetails(principalUser);
    }
}


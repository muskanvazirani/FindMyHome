package com.project.group17.profilepage.controller;


import com.project.group17.match.service.MatchService;

import com.project.group17.profilepage.service.ProfileService;
import com.project.group17.user.entity.User;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/v1")
public class ProfileController {

    @Autowired
    private MatchService matchService;

    /**
     * Handles GET requests to retrieve the current user's profile information.
     * @return a ResponseEntity with a Map of the user's information and preferences
     */
    @CrossOrigin
    @GetMapping("/profilepage")
    public ResponseEntity<Map<String, String>> getUserProfileInfo() {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return ResponseEntity.ok(matchService.getUserInfoAndPreferences(user));
    }

    @Autowired
    private ProfileService profileService;

    /**
     * Handles POST requests to save the user's edited profile information.
     * @param updatedUser the updated user information
     * @return a String indicating the operation's success
     */
    @CrossOrigin
    @PostMapping("/save")
    public String saveEditUserDetails(@RequestBody UserUpdateRequest updatedUser) {
        User principleUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        profileService.saveEditUserDetails(updatedUser, principleUser);
        return "SUCCESS";
    }

}
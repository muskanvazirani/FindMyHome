package com.project.group17.prefNames.controller;

import com.project.group17.prefNames.entity.PrefValueSaveReq;
import com.project.group17.prefNames.service.PrefValuesService;
import com.project.group17.user.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/preferences")
public class PrefValuesController {

    @Autowired
    PrefValuesService service;

    /**
     * Saves the user's preferences.
     *
     * @param userPref - a list of preference values to be saved for the user
     * @return ResponseEntity<String> - a response entity indicating the success of the operation
     */
    @CrossOrigin
    @PostMapping("/save")
    public ResponseEntity<String> saveUserPreferences(@RequestBody List<PrefValueSaveReq> userPref){
        // Get the authenticated user
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        // Call the saveUserPreferences method in the service to save the user's preferences
        return ResponseEntity.ok(service.saveUserPreferences(user.getId(), userPref));
    }

    /**
     * Returns a ResponseEntity object containing a list of preference values of the authenticated user.
     *
     * @return ResponseEntity<List<PrefValueSaveReq>> - a response entity containing a list of preference values
     */
    @CrossOrigin
    @GetMapping("/getUserPrefValues")
    public ResponseEntity<List<PrefValueSaveReq>> getUserPrefValues(){
        // Get the authenticated user
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        // Call the getUserPrefValuesById method in the service to get the user's preference values
        return ResponseEntity.ok(service.getUserPrefValuesById(user.getId()));
    }
}

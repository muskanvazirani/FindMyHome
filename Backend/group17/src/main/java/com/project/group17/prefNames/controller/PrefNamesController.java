package com.project.group17.prefNames.controller;

import com.project.group17.prefNames.entity.PrefNamesEntity;
import com.project.group17.prefNames.service.PrefNamesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * PrefNamesController is a REST controller that handles requests related to preference names.
 */
@RestController
@RequestMapping("/api/preferences")
public class PrefNamesController {

    // Autowire the PrefNamesService to handle preference name related operations
    @Autowired
    PrefNamesService service;

    /**
     * Get all preference names in the system.
     *
     * @return ResponseEntity containing a list of all PrefNamesEntity objects.
     */
    @CrossOrigin
    @GetMapping("/all")
    public ResponseEntity<List<PrefNamesEntity>> getAllPrefOptions() {
        return ResponseEntity.ok(service.getAllPrefOptions());
    }
}

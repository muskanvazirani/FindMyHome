package com.project.group17.prefNames.controller;

import com.project.group17.prefNames.entity.PrefOptionsEntity;
import com.project.group17.prefNames.service.PrefOptionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pref-option")
public class PrefOptionsController {

    @Autowired
    PrefOptionsService service;

    /**
     * Returns a ResponseEntity object containing a list of all preference options.
     *
     * @return ResponseEntity<List<PrefOptionsEntity>> - a response entity containing a list of preference options
     */
    @CrossOrigin
    @GetMapping("/all")
    public ResponseEntity<List<PrefOptionsEntity>> getAllPrefOptions(){
        // Call the getAllPrefOptions method in the service to get all preference options
        return ResponseEntity.ok(service.getAllPrefOptions());
    }
}

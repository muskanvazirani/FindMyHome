package com.project.group17.group.controller;

import com.project.group17.group.entity.GroupMatchPojo;
import com.project.group17.group.service.GroupMatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * This class provides a RESTful API for managing group match-related functionalities.
 */
@RestController
@RequestMapping("/api/v1")
public class GroupMatchController {

    @Autowired
    GroupMatchService groupMatchService;

    /**
     * Handles user requests to join a group.
     *
     * @param groupId A GroupMatchPojo object containing the group ID.
     * @return A ResponseEntity indicating the result of the request.
     */
    @CrossOrigin
    @RequestMapping(value = "/request-group", method = RequestMethod.POST)
    public @ResponseBody ResponseEntity addUserRequest(@RequestBody GroupMatchPojo groupId) {
        groupMatchService.addUserRequest(groupId);
        return ResponseEntity.ok().build();
    }

    /**
     * Gets a list of pending group requests.
     *
     * @return A ResponseEntity containing a list of group requests.
     */
    @CrossOrigin
    @GetMapping("/group-requests")
    public ResponseEntity<List<Map<String, String>>> groupRequests() {
        return groupMatchService.groupRequests();
    }

    /**
     * Approves a user request to join a group.
     *
     * @param userID A GroupMatchPojo object containing the user ID.
     */
    @CrossOrigin
    @RequestMapping(value = "/request-approval", method = RequestMethod.POST)
    public @ResponseBody void approveUserRequest(@RequestBody GroupMatchPojo userID) {
        groupMatchService.approveUserRequest(userID);
    }
}

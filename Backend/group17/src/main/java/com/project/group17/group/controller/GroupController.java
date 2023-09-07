package com.project.group17.group.controller;

import com.project.group17.group.entity.GroupDetailPojo;
import com.project.group17.group.entity.GroupPojo;
import com.project.group17.group.service.GroupService;
import com.project.group17.match.service.MatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * This class provides a RESTful API for managing user groups and group-related functionalities.
 */
@RestController
@RequestMapping("/api/v1")
public class GroupController {

    @Autowired
    GroupService groupService;
    @Autowired
    MatchService matchService;

    /**
     * Gets the group members for the logged-in user.
     *
     * @return A ResponseEntity containing a list of group members' information.
     */
    @GetMapping("/my-group")
    @CrossOrigin
    public ResponseEntity<List<Map<String, String>>> getGroupMembers(){
        return ResponseEntity.ok(matchService.getAllUserInfoAndPreferences(groupService.getGroupMembers()));
    }

    /**
     * Gets roommate requests for the logged-in user.
     *
     * @return A ResponseEntity containing a list of roommate requests' information.
     */
    @CrossOrigin
    @GetMapping("/my-roommate-request")
    public ResponseEntity<List<Map<String, String>>> getRoommateRequest(){
        return ResponseEntity.ok(matchService.getAllUserInfoAndPreferences(groupService.getRoommateRequest()));
    }

    /**
     * Gets all groups available for the logged-in user.
     *
     * @return A ResponseEntity containing a list of GroupPojo objects.
     */
    @GetMapping("/get-all-groups")
    @CrossOrigin
    public ResponseEntity<List<GroupPojo>> getGroups() {
        return groupService.getGroups();
    }

    /**
     * Gets a specific group's details.
     *
     * @param groupDetailPojo A GroupDetailPojo object containing the group ID.
     * @return A ResponseEntity containing a list of group users' information.
     */
    @PostMapping("/get-group")
    @CrossOrigin
    public ResponseEntity<List<Map<String, String>>> getGroup(@RequestBody GroupDetailPojo groupDetailPojo) {
        List<Map<String, String>> users =(matchService.getAllUserInfoAndPreferences(groupService.getGroupUsers(groupDetailPojo.getGroupId())));
        return ResponseEntity.ok(users);
    }
}

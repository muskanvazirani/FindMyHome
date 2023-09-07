package com.project.group17.match.controller;
import com.project.group17.match.entity.MatchPojo;
import com.project.group17.user.entity.User;
import com.project.group17.match.service.MatchService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

/**
 * MatchController is a REST API controller for managing user matches.
 */
@RestController
@RequestMapping("/api/v1")
public class MatchController {

    @Autowired
    private MatchService matchService;

    /**
     * Adds a match for the authenticated user.
     *
     * @param user2 The MatchPojo object containing user2's information.
     */
    @CrossOrigin
    @RequestMapping(value = "/match", method = RequestMethod.POST)
    public @ResponseBody void getUserId(@RequestBody MatchPojo user2) {
        matchService.getUserId(user2);
    }

    /**
     * Retrieves all matches for the authenticated user.
     *
     * @return A ResponseEntity containing a list of maps with match details.
     */
    @CrossOrigin
    @GetMapping("/get-all-matches")
    public ResponseEntity<List<Map<String, String>>> register() {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return ResponseEntity.ok(matchService.getRoommateList(user));
    }

    /**
     * Retrieves the list of users liked by the authenticated user.
     *
     * @return A ResponseEntity containing a list of maps with user details.
     */
    @CrossOrigin
    @GetMapping("/likes")
    public ResponseEntity<List<Map<String, String>>> likes() {
        return matchService.likes();
    }

}


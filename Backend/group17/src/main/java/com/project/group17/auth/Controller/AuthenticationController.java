package com.project.group17.auth.Controller;

import com.project.group17.auth.Request.AuthenticationRequest;
import com.project.group17.auth.Response.AuthenticationResponse;
import com.project.group17.auth.Service.AuthenticationService;
import com.project.group17.auth.Request.RegisterRequest;
import com.project.group17.user.entity.User;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

/**
 * This class handles authentication related requests and routes them to the appropriate service methods.
 * It is responsible for managing the registration and authentication process of users.
 */
@RestController
@RequestMapping("/api/v1/auth")
public class AuthenticationController {

    private final AuthenticationService service;

    /**
     * Constructs the AuthenticationController with the specified AuthenticationService.
     *
     * @param service The AuthenticationService to be used for handling authentication-related operations.
     */
    public AuthenticationController(AuthenticationService service) {
        this.service = service;
    }

    /**
     * Registers a new user with the given request details.
     *
     * @param request The RegisterRequest object containing user registration details.
     * @return ResponseEntity containing an AuthenticationResponse object with the user's authentication details.
     */
    @CrossOrigin
    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(
            @RequestBody RegisterRequest request
    ){
        return ResponseEntity.ok(service.register(request));
    }

    /**
     * Authenticates the user with the provided credentials in the request.
     *
     * @param request The AuthenticationRequest object containing the user's username and password.
     * @return ResponseEntity containing an AuthenticationResponse object with the user's authentication details.
     */
    @CrossOrigin
    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(
            @RequestBody AuthenticationRequest request
    ){
        return ResponseEntity.ok(service.authenticate(request));
    }

    /**
     * A demo endpoint for testing purposes. It returns the authenticated user's first name.
     *
     * @return ResponseEntity containing a String representing the user's first name.
     */
    @CrossOrigin
    @GetMapping("/demo")
    public ResponseEntity<String> sayHello(){
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return ResponseEntity.ok(user.getFirstname());
    }
}

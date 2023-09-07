package com.project.group17.auth.Service;

import com.project.group17.auth.Request.RegisterRequest;
import com.project.group17.auth.Request.AuthenticationRequest;
import com.project.group17.auth.Response.AuthenticationResponse;
import com.project.group17.config.Service.JwtService;
import com.project.group17.user.entity.Role;
import com.project.group17.user.entity.User;
import com.project.group17.user.repository.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * This class provides services for user registration and authentication.
 * It handles user registration, password encoding, and JWT token generation.
 */
@Service
public class AuthenticationService {

    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    /**
     * Constructs an AuthenticationService with the specified repository, password encoder, JWT service, and authentication manager.
     *
     * @param repository           The user repository.
     * @param passwordEncoder      The password encoder.
     * @param jwtService           The JWT service.
     * @param authenticationManager The authentication manager.
     */
    public AuthenticationService(UserRepository repository, PasswordEncoder passwordEncoder, JwtService jwtService, AuthenticationManager authenticationManager) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }

    /**
     * Registers a new user with the provided registration request data.
     *
     * @param request The registration request containing the user's information.
     * @return An authentication response containing the JWT token for the registered user.
     */
    public AuthenticationResponse register(RegisterRequest request) {
        var user = User.builder()
                .firstname(request.getFirstName())
                .lastname(request.getLastName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .age(request.getAge())
                .gender(request.getGender())
                .city(request.getCity())
                .province(request.getProvince())
                .streetAddress(request.getStreetAddress())
                .profilePicBase64(request.getProfilePicBase64())
                .phoneNumber(request.getPhoneNumber())
                .role(Role.USER)
                .build();
        repository.save(user);
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    /**
     * Authenticates a user with the provided authentication request data.
     *
     * @param request The authentication request containing the user's email and password.
     * @return An authentication response containing the JWT token for the authenticated user.
     */
    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        var user = repository.findByEmail(request.getEmail())
                .orElseThrow();
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

}

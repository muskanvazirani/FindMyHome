package com.project.group17.auth;

import com.project.group17.auth.Request.AuthenticationRequest;
import com.project.group17.auth.Request.RegisterRequest;
import com.project.group17.auth.Response.AuthenticationResponse;
import com.project.group17.auth.Service.AuthenticationService;
import com.project.group17.config.Service.JwtService;
import com.project.group17.user.entity.User;
import com.project.group17.user.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class AuthenticationServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private JwtService jwtService;

    @Mock
    private AuthenticationManager authenticationManager;

    @InjectMocks
    private AuthenticationService authenticationService;

    @Test
    void testRegister() {
        RegisterRequest request = new RegisterRequest("John","Doe","john.doe@test.com","password","25","Halifax","NS","Gaston Road","base64encodedimage","Male","8210180281");

        request.setFirstName("John");
        request.setLastName("Doe");
        request.setEmail("johndoe@example.com");
        request.setPassword("password123");
        request.setAge("30");
        request.setGender("male");
        request.setCity("New York");
        request.setProvince("NY");
        request.setStreetAddress("123 Main St");
        request.setProfilePicBase64("base64-encoded-string");
        request.setPhoneNumber("555-555-5555");

        User savedUser = new User();
        savedUser.setId((int)1);
        savedUser.setFirstname(request.getFirstName());
        savedUser.setLastname(request.getLastName());
        savedUser.setEmail(request.getEmail());
        savedUser.setPassword(request.getPassword());
        savedUser.setAge(request.getAge());
        savedUser.setGender(request.getGender());
        savedUser.setCity(request.getCity());
        savedUser.setProvince(request.getProvince());
        savedUser.setStreetAddress(request.getStreetAddress());
        savedUser.setProfilePicBase64(request.getProfilePicBase64());
        savedUser.setPhoneNumber(request.getPhoneNumber());

        when(userRepository.save(any(User.class))).thenReturn(savedUser);
        when(jwtService.generateToken(any(User.class))).thenReturn("jwt-token");
        when(passwordEncoder.encode(request.getPassword())).thenReturn("encoded-password");

        AuthenticationResponse response = authenticationService.register(request);

        assertNotNull(response);
        assertEquals("jwt-token", response.getToken());
    }

    @Test
    void testAuthenticate() {
        AuthenticationRequest request = new AuthenticationRequest("johndoe@example.com","password123");
        request.setEmail("johndoe@example.com");
        request.setPassword("password123");

        User user = new User();
        user.setId((int)1);
        user.setEmail(request.getEmail());
        user.setPassword("encoded-password");

        when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class))).thenReturn(null);
        when(userRepository.findByEmail(request.getEmail())).thenReturn(java.util.Optional.of(user));
        when(jwtService.generateToken(user)).thenReturn("jwt-token");

        AuthenticationResponse response = authenticationService.authenticate(request);

        assertNotNull(response);
        assertEquals("jwt-token", response.getToken());
    }
}

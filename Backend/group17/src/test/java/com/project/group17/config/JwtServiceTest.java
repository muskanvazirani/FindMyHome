package com.project.group17.config;

import com.project.group17.config.Service.JwtService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UserDetails;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class JwtServiceTest {

    private static final String USERNAME = "testuser";
    private static final String TOKEN = "token";
    private static final String SECRET_KEY = "635166546A576E5A7234743777217A25432A462D4A614E645267556B58703273";

    @Mock
    private UserDetails userDetails;

    @InjectMocks
    private JwtService jwtService;

    @BeforeEach
    void setUp() {
        when(userDetails.getUsername()).thenReturn(USERNAME);
    }

    @Test
    void testExtractUsername() {
        String token = jwtService.generateToken(userDetails);
        assertEquals(USERNAME, jwtService.extractUsername(token));
    }

    @Test
    void testGenerateToken() {
        String token = jwtService.generateToken(userDetails);
        assertNotNull(token);
    }

    @Test
    void testGenerateTokenWithExtraClaims() {
        Map<String, Object> extraClaims = new HashMap<>();
        extraClaims.put("role", "admin");
        String token = jwtService.generateToken(extraClaims, userDetails);
        assertNotNull(token);
    }

    @Test
    void testIsTokenValid() {
        String token = jwtService.generateToken(userDetails);
        assertTrue(jwtService.isTokenValid(token, userDetails));
    }

    @Test
    void testIsTokenInvalid() {
        String token = jwtService.generateToken(userDetails);
        when(userDetails.getUsername()).thenReturn("invaliduser");
        assertFalse(jwtService.isTokenValid(token, userDetails));
    }


}


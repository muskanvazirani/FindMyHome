package com.project.group17.config.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

/**
 * JwtService is responsible for managing JSON Web Token (JWT) operations.
 */
@Service
public class JwtService {
    private static final String SECRET_KEY = "635166546A576E5A7234743777217A25432A462D4A614E645267556B58703273";

    /**
     * Extracts the username from the given token.
     *
     * @param token The JWT token.
     * @return The extracted username.
     */
    public String extractUsername(String token){
        return extractClaim(token, Claims::getSubject);
    }

    /**
     * Extracts a specific claim from the given token.
     *
     * @param token The JWT token.
     * @param claimsResolver A function to extract a specific claim from Claims object.
     * @return The extracted claim.
     */
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver){
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    /**
     * Generates a JWT token for the given UserDetails.
     *
     * @param userDetails The UserDetails object.
     * @return The generated JWT token.
     */
    public String generateToken(UserDetails userDetails){
        return generateToken(new HashMap<>(), userDetails);
    }

    /**
     * Generates a JWT token with extra claims for the given UserDetails.
     *
     * @param extraClaims A map of extra claims to be added to the JWT token.
     * @param userDetails The UserDetails object.
     * @return The generated JWT token.
     */
    public String generateToken(
            Map<String, Object> extraClaims,
            UserDetails userDetails
    ) {
        return Jwts
                .builder()
                .setClaims(extraClaims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 30))
                .signWith(getSignInKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    /**
     * Validates if the given JWT token is valid for the given UserDetails.
     *
     * @param token The JWT token.
     * @param userDetails The UserDetails object.
     * @return true if the token is valid, false otherwise.
     */
    public boolean isTokenValid(String token, UserDetails userDetails){
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername())) && !isTokenExpired(token);
    }

    private boolean isTokenExpired(String token){
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    private Claims extractAllClaims(String token){
        return Jwts
                .parserBuilder()
                .setSigningKey(getSignInKey()) // Get locally computed signature
                .build()
                .parseClaimsJws(token) // Here the comparison of locally computed sign and claimed sign is performed
                .getBody();
    }

    /**
     * Generates the signing key for JWT tokens using the SECRET_KEY.
     *
     * @return The generated signing key.
     */
    private Key getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}

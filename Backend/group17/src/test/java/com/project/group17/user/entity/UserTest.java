package com.project.group17.user.entity;

import com.project.group17.user.entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collection;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {
    private User user;

    @BeforeEach
    void setUp() {
        user = User.builder()
                .id(1)
                .firstname("John")
                .lastname("Doe")
                .email("john.doe@example.com")
                .password("password")
                .age("30")
                .city("New York")
                .province("NY")
                .streetAddress("123 Main St")
                .profilePicBase64("base64_encoded_image")
                .gender("Male")
                .phoneNumber("123-456-7890")
                .build();
    }

    @Test
    void testGettersAndSetters() {
        assertEquals(1, user.getId());
        assertEquals("John", user.getFirstname());
        assertEquals("Doe", user.getLastname());
        assertEquals("john.doe@example.com", user.getEmail());
        assertEquals("password", user.getPassword());
        assertEquals("30", user.getAge());
        assertEquals("New York", user.getCity());
        assertEquals("NY", user.getProvince());
        assertEquals("123 Main St", user.getStreetAddress());
        assertEquals("base64_encoded_image", user.getProfilePicBase64());
        assertEquals("Male", user.getGender());
        assertEquals("123-456-7890", user.getPhoneNumber());

        user.setId(2);
        user.setFirstname("Jane");
        user.setLastname("Doe");
        user.setEmail("jane.doe@example.com");
        user.setPassword("new_password");
        user.setAge("25");
        user.setCity("Los Angeles");
        user.setProvince("CA");
        user.setStreetAddress("456 Main St");
        user.setProfilePicBase64("new_base64_encoded_image");
        user.setGender("Female");
        user.setPhoneNumber("987-654-3210");

        assertEquals(2, user.getId());
        assertEquals("Jane", user.getFirstname());
        assertEquals("Doe", user.getLastname());
        assertEquals("jane.doe@example.com", user.getEmail());
        assertEquals("new_password", user.getPassword());
        assertEquals("25", user.getAge());
        assertEquals("Los Angeles", user.getCity());
        assertEquals("CA", user.getProvince());
        assertEquals("456 Main St", user.getStreetAddress());
        assertEquals("new_base64_encoded_image", user.getProfilePicBase64());
        assertEquals("Female", user.getGender());
        assertEquals("987-654-3210", user.getPhoneNumber());
    }

    @Test
    void testAccountStatus() {
        assertTrue(user.isAccountNonExpired());
        assertTrue(user.isAccountNonLocked());
        assertTrue(user.isCredentialsNonExpired());
        assertTrue(user.isEnabled());
    }

    @Test
    void testUsername() {
        assertEquals("john.doe@example.com", user.getUsername());
    }

    @Test
    void testBuilder() {
        User user2 = User.builder()
                .id(2)
                .firstname("Jane")
                .lastname("Doe")
                .email("jane.doe@example.com")
                .password("password")
                .age("25")
                .city("Los Angeles")
                .province("CA")
                .streetAddress("456 Main St")
                .profilePicBase64("base64_encoded_image")
                .gender("Female")
                .phoneNumber("987-654-3210")
                .build();

        assertEquals(2, user2.getId());
        assertEquals("Jane", user2.getFirstname());
        assertEquals("Doe", user2.getLastname());
        assertEquals("jane.doe@example.com", user2.getEmail());
        assertEquals("password", user2.getPassword());
        assertEquals("25", user2.getAge());
        assertEquals("Los Angeles", user2.getCity());
        assertEquals("CA", user2.getProvince());
        assertEquals("456 Main St", user2.getStreetAddress());
        assertEquals("base64_encoded_image", user2.getProfilePicBase64());
        assertEquals("Female", user2.getGender());
        assertEquals("987-654-3210", user2.getPhoneNumber());
    }
}
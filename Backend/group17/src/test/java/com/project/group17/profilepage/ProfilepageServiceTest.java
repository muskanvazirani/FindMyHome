package com.project.group17.profilepage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import com.project.group17.profilepage.controller.UserUpdateRequest;
import com.project.group17.profilepage.repository.ProfileRepository;
import com.project.group17.profilepage.service.ProfileService;
import com.project.group17.user.entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class ProfilepageServiceTest {
    @Mock
    private ProfileRepository profileRepositoryMock;

    @InjectMocks
    private ProfileService profileService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void saveEditUserDetailsTest() {
        User principalUser = new User();
        UserUpdateRequest updateRequest = new UserUpdateRequest();
        updateRequest.setFirstname("John");
        updateRequest.setLastname("Doe");
        updateRequest.setGender("male");
        updateRequest.setEmail("john.doe@example.com");
        updateRequest.setStreetAddress("123 Main St");
        updateRequest.setCity("Anytown");
        updateRequest.setProvince("AB");
        updateRequest.setPhoneNumber("555-555-5555");
        updateRequest.setAge("30");

        profileService.saveEditUserDetails(updateRequest, principalUser);

        verify(profileRepositoryMock, times(1)).saveEditUserDetails(principalUser);
        assertEquals(updateRequest.getFirstname(), principalUser.getFirstname());
        assertEquals(updateRequest.getLastname(), principalUser.getLastname());
        assertEquals(updateRequest.getGender(), principalUser.getGender());
        assertEquals(updateRequest.getEmail(), principalUser.getEmail());
        assertEquals(updateRequest.getStreetAddress(), principalUser.getStreetAddress());
        assertEquals(updateRequest.getCity(), principalUser.getCity());
        assertEquals(updateRequest.getProvince(), principalUser.getProvince());
        assertEquals(updateRequest.getPhoneNumber(), principalUser.getPhoneNumber());
        assertEquals(updateRequest.getAge(), principalUser.getAge());
    }

}



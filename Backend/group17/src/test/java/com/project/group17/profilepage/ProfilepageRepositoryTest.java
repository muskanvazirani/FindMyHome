package com.project.group17.profilepage;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import com.project.group17.profilepage.repository.ProfileRepository;
import jakarta.persistence.EntityManager;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.project.group17.user.entity.User;

@ExtendWith(MockitoExtension.class)
public class ProfilepageRepositoryTest {

    @Mock
    private EntityManager entityManager;

    @InjectMocks
    private ProfileRepository profileRepository;

    @Test
    public void testSaveEditUserDetails() {
        // Create a mock user object
        User user = mock(User.class);

        // Call the method being tested
        profileRepository.saveEditUserDetails(user);

        // Verify that the entityManager's merge method was called with the correct user object
        verify(entityManager).merge(user);
    }

}


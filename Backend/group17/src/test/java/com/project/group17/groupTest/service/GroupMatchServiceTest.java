package com.project.group17.groupTest.service;
import com.project.group17.group.entity.GroupEntity;
import com.project.group17.group.entity.GroupMatchEntity;
import com.project.group17.group.entity.GroupMatchPojo;
import com.project.group17.group.repository.GroupMatchRepository;
import com.project.group17.group.repository.GroupRepository;
import com.project.group17.group.service.GroupMatchService;
import com.project.group17.match.service.MatchService;
import com.project.group17.user.entity.User;
import com.project.group17.user.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import java.util.Optional;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class GroupMatchServiceTest {

    @InjectMocks
    private GroupMatchService groupMatchService;

    @Mock
    private GroupMatchRepository groupMatchRepository;
    @Mock
    private Authentication authentication;

    @Mock
    private GroupRepository groupRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private MatchService matchService;


    @Test
    public void testAddUserRequest() {
        // Set up the test data
        GroupMatchPojo groupMatchPojo = new GroupMatchPojo();
        groupMatchPojo.setGroupId(1L);

        // Set up the mock SecurityContext with a User object as a principal
        User user = new User();
        Authentication auth = new UsernamePasswordAuthenticationToken(user, null);
        SecurityContext securityContext = mock(SecurityContext.class);
        when(securityContext.getAuthentication()).thenReturn(auth);
        SecurityContextHolder.setContext(securityContext);

        // Call the method to be tested
        groupMatchService.addUserRequest(groupMatchPojo);

        // Verify that the mocked methods were called with the expected parameters
        verify(groupMatchRepository).save(any(GroupMatchEntity.class));
    }

    @Test
    public void testGroupRequests() {
        // Set up the mock SecurityContext with a User object as a principal
        User user = new User();
        user.setId(1);
        Authentication auth = new UsernamePasswordAuthenticationToken(user, null);
        SecurityContext securityContext = mock(SecurityContext.class);
        when(securityContext.getAuthentication()).thenReturn(auth);
        SecurityContextHolder.setContext(securityContext);

        // Set up the behavior of the mocked repository
        when(groupRepository.getGroupId(1)).thenReturn(1);

        // Call the method to be tested
        groupMatchService.groupRequests();

        // Verify that the mocked methods were called with the expected parameters
        verify(groupRepository).getGroupId(1);
        verify(groupMatchRepository).getUsersByGroupId(1);
    }

    @Test
    public void testApproveUserRequest() {
        // Set up the test data
        GroupMatchPojo groupMatchPojo = new GroupMatchPojo();
        groupMatchPojo.setUserId(1L);

        // Set up the mock SecurityContext with a User object as a principal
        User user = new User();
        user.setId(1);
        Authentication auth = new UsernamePasswordAuthenticationToken(user, null);
        SecurityContext securityContext = mock(SecurityContext.class);
        when(securityContext.getAuthentication()).thenReturn(auth);
        SecurityContextHolder.setContext(securityContext);

        // Set up the behavior of the mocked repositories
        when(groupMatchRepository.getIndexByUserId(1L)).thenReturn(1L);
        when(userRepository.findById(1)).thenReturn(Optional.of(user));
        when(groupRepository.getGroupId(1)).thenReturn(1);
        when(groupRepository.getMaxGroupCount()).thenReturn(1);

        // Call the method to be tested
        groupMatchService.approveUserRequest(groupMatchPojo);

        // Verify that the mocked methods were called with the expected parameters
        verify(groupMatchRepository).getIndexByUserId(1L);
        verify(groupMatchRepository).deleteById(1L);
        verify(userRepository).findById(1);
        verify(groupRepository).getGroupId(1);
        verify(groupRepository).getMaxGroupCount();
        verify(groupRepository).save(any(GroupEntity.class));
    }
}

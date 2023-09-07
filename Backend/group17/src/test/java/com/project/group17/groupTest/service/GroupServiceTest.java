package com.project.group17.groupTest.service;

import com.project.group17.group.repository.GroupRepository;
import com.project.group17.group.service.GroupService;
import com.project.group17.user.entity.User;
import com.project.group17.user.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.test.context.support.WithMockUser;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class GroupServiceTest {

    @Mock
    private GroupRepository groupRepository;

    @InjectMocks
    private GroupService groupService;

    private User user;

    @BeforeEach
    public void setUp() {
        user = new User();
        user.setId(1);

        SecurityContext securityContext = mock(SecurityContext.class);
        Authentication authentication = mock(Authentication.class);

        when(securityContext.getAuthentication()).thenReturn(authentication);
        when(authentication.getPrincipal()).thenReturn(user);

        SecurityContextHolder.setContext(securityContext);
    }

    @Test
    @WithMockUser(username = "testUser", password = "testPassword")
    public void testGetRoommateRequest_noGroup() {
        when(groupRepository.getGroupId(anyInt())).thenReturn(0);

        List<User> result = groupService.getRoommateRequest();

        assertEquals(null, result);
        verify(groupRepository, times(1)).getGroupId(user.getId());
    }

}

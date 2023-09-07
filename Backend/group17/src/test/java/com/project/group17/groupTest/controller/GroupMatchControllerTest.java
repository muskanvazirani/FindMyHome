package com.project.group17.groupTest.controller;
import com.project.group17.group.controller.GroupMatchController;
import com.project.group17.group.entity.GroupMatchPojo;
import com.project.group17.group.service.GroupMatchService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class GroupMatchControllerTest {

    @InjectMocks
    private GroupMatchController groupMatchController;

    @Mock
    private GroupMatchService groupMatchService;


    private GroupMatchPojo groupMatchPojo;

    @BeforeEach
    public void setUp() {
        groupMatchPojo = new GroupMatchPojo();
        groupMatchPojo.setGroupId(1L);
        groupMatchPojo.setUserId(2L);
    }

    @Test
    public void testAddUserRequest() {
        doNothing().when(groupMatchService).addUserRequest(groupMatchPojo);

        ResponseEntity responseEntity = groupMatchController.addUserRequest(groupMatchPojo);

        assertEquals(200, responseEntity.getStatusCodeValue());
        verify(groupMatchService, times(1)).addUserRequest(groupMatchPojo);
    }

    @Test
    public void testGroupRequests() {
        List<Map<String, String>> groupRequests = new ArrayList<>();
        Map<String, String> request = new HashMap<>();
        request.put("userId", "2");
        request.put("username", "testUser");
        groupRequests.add(request);

        when(groupMatchService.groupRequests()).thenReturn(ResponseEntity.ok(groupRequests));

        ResponseEntity<List<Map<String, String>>> response = groupMatchController.groupRequests();

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(1, response.getBody().size());
    }

    @Test
    public void testApproveUserRequest() {
        doNothing().when(groupMatchService).approveUserRequest(groupMatchPojo);

        groupMatchController.approveUserRequest(groupMatchPojo);

        verify(groupMatchService, times(1)).approveUserRequest(groupMatchPojo);
    }
}

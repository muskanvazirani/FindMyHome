package com.project.group17.groupTest.controller;
import com.project.group17.group.controller.GroupController;
import com.project.group17.group.entity.GroupDetailPojo;
import com.project.group17.group.entity.GroupPojo;
import com.project.group17.group.service.GroupService;
import com.project.group17.match.service.MatchService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class GroupControllerTest {

    @InjectMocks
    private GroupController groupController;

    @Mock
    private GroupService groupService;

    @Mock
    private MatchService matchService;

    @Test
    public void testGetGroupMembers() {
        List<Map<String, String>> expectedMembers = List.of(Map.of("key", "value"));
        when(matchService.getAllUserInfoAndPreferences(groupService.getGroupMembers()))
                .thenReturn(expectedMembers);

        ResponseEntity<List<Map<String, String>>> response = groupController.getGroupMembers();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedMembers, response.getBody());
    }

    @Test
    public void testGetRoommateRequest() {
        List<Map<String, String>> expectedRoommateRequests = List.of(Map.of("key", "value"));
        when(matchService.getAllUserInfoAndPreferences(groupService.getRoommateRequest()))
                .thenReturn(expectedRoommateRequests);

        ResponseEntity<List<Map<String, String>>> response = groupController.getRoommateRequest();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedRoommateRequests, response.getBody());
    }

    @Test
    public void testGetGroups() {
        List<GroupPojo> expectedGroups = List.of(new GroupPojo());
        when(groupService.getGroups()).thenReturn(ResponseEntity.ok(expectedGroups));

        ResponseEntity<List<GroupPojo>> response = groupController.getGroups();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedGroups, response.getBody());
    }

    @Test
    public void testGetGroup() {
        GroupDetailPojo groupDetailPojo = new GroupDetailPojo();
        groupDetailPojo.setGroupId(1L);

        List<Map<String, String>> expectedUsers = List.of(Map.of("key", "value"));
        when(matchService.getAllUserInfoAndPreferences(groupService.getGroupUsers(groupDetailPojo.getGroupId())))
                .thenReturn(expectedUsers);

        ResponseEntity<List<Map<String, String>>> response = groupController.getGroup(groupDetailPojo);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedUsers, response.getBody());
    }
}

package com.project.group17.groupTest.repository;
import com.project.group17.group.entity.GroupEntity;
import com.project.group17.group.repository.GroupRepository;
import com.project.group17.group.service.GroupService;
import com.project.group17.user.entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Arrays;
import java.util.List;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class GroupRepositoryTest {

    @Mock
    private GroupRepository groupRepository;

    @InjectMocks
    private GroupService groupService;

    private User user1;
    private User user2;
    private GroupEntity groupEntity1;
    private GroupEntity groupEntity2;

    @BeforeEach
    public void setUp() {
        user1 = new User();
        user2 = new User();

        groupEntity1 = new GroupEntity();
        groupEntity1.setGroupId(1L);
        groupEntity1.setUser(user1);
        groupEntity1.setTotal_groups(1);

        groupEntity2 = new GroupEntity();
        groupEntity2.setGroupId(1L);
        groupEntity2.setUser(user2);
        groupEntity2.setTotal_groups(1);
    }

    @Test
    public void testFindByUser() {
        when(groupRepository.findByUser(user1)).thenReturn(Arrays.asList(groupEntity1));

        List<GroupEntity> result = groupRepository.findByUser(user1);

        assertEquals(1, result.size());
        assertEquals(groupEntity1.getGroupId(), result.get(0).getGroupId());

        verify(groupRepository, times(1)).findByUser(user1);
    }

    @Test
    public void testFindByGroupId() {
        when(groupRepository.findByGroupId(1L)).thenReturn(Arrays.asList(groupEntity1, groupEntity2));

        List<GroupEntity> result = groupRepository.findByGroupId(1L);

        assertEquals(2, result.size());

        verify(groupRepository, times(1)).findByGroupId(1L);
    }
}

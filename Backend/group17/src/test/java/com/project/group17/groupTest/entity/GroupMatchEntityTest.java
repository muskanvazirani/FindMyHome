package com.project.group17.groupTest.entity;
import com.project.group17.group.entity.GroupMatchEntity;
import com.project.group17.user.entity.User;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;


public class GroupMatchEntityTest {

    @Test
    public void testGettersAndSetters() {
        User user = new User();
        user.setId(1);

        GroupMatchEntity entity = new GroupMatchEntity();
        entity.setGroupId(1);
        entity.setUser(user);

        assertThat(entity.getGroupId()).isEqualTo(1);
        assertThat(entity.getUser()).isEqualTo(user);
    }
}


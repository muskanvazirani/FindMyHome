package com.project.group17.groupTest.entity;

import io.jsonwebtoken.lang.Assert;

import com.project.group17.group.entity.GroupEntity;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GroupEntityTest {
    @Test
    public void testGetIndex() {
        GroupEntity groupEntity = new GroupEntity();
        groupEntity.setIndex(1);
        assertEquals(1, groupEntity.getIndex());
    }

    @Test
    public void testGetGroupId() {
        GroupEntity groupEntity = new GroupEntity();
        groupEntity.setGroupId(2);
        assertEquals(2, groupEntity.getGroupId());
    }

}
package com.project.group17.prefValues.entity;

import com.project.group17.prefNames.entity.PrefValuesEntity;
import com.project.group17.user.entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class PrefValuesEntityTest {
    private PrefValuesEntity prefValuesEntity;

    @BeforeEach
    public void setUp() {
        prefValuesEntity = new PrefValuesEntity();
    }

    @Test
    public void testPrefValuesEntity() {
        Long prefValueId = 1L;
        User user = new User();
        user.setId(1);

        prefValuesEntity.setPrefValueId(prefValueId);
        prefValuesEntity.setUser(user);

        assertNotNull(prefValuesEntity);
        assertEquals(prefValueId, prefValuesEntity.getPrefValueId());
        assertEquals(user, prefValuesEntity.getUser());
    }
}

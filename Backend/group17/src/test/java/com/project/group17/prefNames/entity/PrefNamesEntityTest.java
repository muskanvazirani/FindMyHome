package com.project.group17.prefNames.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class PrefNamesEntityTest {

    @Test
    public void testPrefNamesEntity() {
        PrefNamesEntity prefNamesEntity = new PrefNamesEntity();

        assertNull(prefNamesEntity.getPrefId());
        assertNull(prefNamesEntity.getName());
        assertNull(prefNamesEntity.getType());
        assertNull(prefNamesEntity.getIs_required());
        assertNull(prefNamesEntity.getOptions());

        prefNamesEntity.setPrefId(1L);
        prefNamesEntity.setName("Location");
        prefNamesEntity.setType("Halifax");
        prefNamesEntity.setIs_required(true);
        prefNamesEntity.setOptions(null);

        assertEquals(1L, prefNamesEntity.getPrefId());
        assertEquals("Location", prefNamesEntity.getName());
        assertEquals("Halifax", prefNamesEntity.getType());
        assertEquals(true, prefNamesEntity.getIs_required());
        assertNull(prefNamesEntity.getOptions());
    }
}

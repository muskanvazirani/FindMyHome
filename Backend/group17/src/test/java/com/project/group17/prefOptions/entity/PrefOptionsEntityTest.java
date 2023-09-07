package com.project.group17.prefOptions.entity;

import com.project.group17.prefNames.entity.PrefNamesEntity;
import com.project.group17.prefNames.entity.PrefOptionsEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PrefOptionsEntityTest {

    private PrefOptionsEntity prefOptionsEntity;

    @BeforeEach
    public void setUp() {
        prefOptionsEntity = new PrefOptionsEntity();
    }

    @Test
    public void testPrefId() {
        Long prefId = 1L;
        prefOptionsEntity.setPrefId(prefId);
        assertEquals(prefId, prefOptionsEntity.getPrefId());
    }

    @Test
    public void testPrefName() {
        PrefNamesEntity prefName = new PrefNamesEntity();
        prefName.setPrefId(1L);
        prefName.setName("Test");
        prefName.setType("type1");
        prefName.setIs_required(true);

        prefOptionsEntity.setPrefName(prefName);
        assertEquals(prefName, prefOptionsEntity.getPrefName());
    }

    @Test
    public void testOption() {
        String option = "Option 1";
        prefOptionsEntity.setOption(option);
        assertEquals(option, prefOptionsEntity.getOption());
    }
}
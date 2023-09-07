package com.project.group17.prefValues.model;

import com.project.group17.prefNames.entity.PrefValueSaveReq;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class PrefValueSaveReqTest {
    private PrefValueSaveReq prefValueSaveReq;

    @BeforeEach
    public void setUp() {
        prefValueSaveReq = new PrefValueSaveReq();
    }

    @Test
    public void testPrefValueSaveReq() {
        Long prefNameId = 1L;
        Long prefOptionId = 2L;

        prefValueSaveReq.setPrefNameId(prefNameId);
        prefValueSaveReq.setPrefOptionId(prefOptionId);

        assertNotNull(prefValueSaveReq);
        assertEquals(prefNameId, prefValueSaveReq.getPrefNameId());
        assertEquals(prefOptionId, prefValueSaveReq.getPrefOptionId());
    }
}

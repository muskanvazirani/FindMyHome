package com.project.group17.prefValues.mapper;

import com.project.group17.prefNames.entity.PrefNamesEntity;
import com.project.group17.prefNames.entity.PrefValuesEntity;
import com.project.group17.prefNames.mapper.PrefValuesMapper;
import com.project.group17.prefNames.entity.PrefValueSaveReq;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class PrefValuesMapperTest {

    @Test
    public void testSaveReqToEntityMapper() {
        PrefValueSaveReq prefValueSaveReq = new PrefValueSaveReq();
        prefValueSaveReq.setPrefNameId(1L);
        prefValueSaveReq.setPrefOptionId(2L);

        int userId = 1;

        PrefValuesEntity prefValuesEntity = PrefValuesMapper.saveReqToEntityMapper(prefValueSaveReq, userId);

        assertNotNull(prefValuesEntity);
        assertEquals(userId, prefValuesEntity.getUser().getId());
        assertEquals(prefValueSaveReq.getPrefNameId(), prefValuesEntity.getPrefName().getPrefId());
        assertEquals(prefValueSaveReq.getPrefOptionId(), prefValuesEntity.getPrefOption().getPrefId());
    }

    @Test
    public void testEntityToGetPrefCRes() {
        PrefValuesEntity prefValuesEntity = new PrefValuesEntity();
        prefValuesEntity.setPrefValueId(1L);

        PrefNamesEntity prefNames = new PrefNamesEntity();
        prefNames.setPrefId(1L);
        prefValuesEntity.setPrefName(prefNames);

        PrefValueSaveReq prefValueSaveReq = PrefValuesMapper.entityToGetPrefCRes(prefValuesEntity);

        assertNotNull(prefValueSaveReq);
        assertEquals(prefNames.getPrefId(), prefValueSaveReq.getPrefNameId());
    }

}

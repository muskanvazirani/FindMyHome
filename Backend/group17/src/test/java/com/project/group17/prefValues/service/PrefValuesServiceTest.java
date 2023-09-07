package com.project.group17.prefValues.service;

import com.project.group17.prefNames.entity.PrefNamesEntity;
import com.project.group17.prefNames.repository.PrefValuesRepository;
import com.project.group17.prefNames.entity.PrefValuesEntity;
import com.project.group17.prefNames.entity.PrefValueSaveReq;
import com.project.group17.prefNames.service.PrefValuesService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PrefValuesServiceTest {

    @InjectMocks
    private PrefValuesService prefValuesService;

    @Mock
    private PrefValuesRepository prefValuesRepository;

    private List<PrefValueSaveReq> userPrefs;
    private List<Optional<PrefValuesEntity>> prefDB;

    @BeforeEach
    public void setUp() {
        userPrefs = new ArrayList<>();
        PrefValueSaveReq prefValueSaveReq = new PrefValueSaveReq();
        prefValueSaveReq.setPrefNameId(1L);
        prefValueSaveReq.setPrefOptionId(2L);
        userPrefs.add(prefValueSaveReq);

        // Add this part
        PrefValuesEntity prefValuesEntity = new PrefValuesEntity();
        PrefNamesEntity prefNamesEntity = new PrefNamesEntity();
        prefNamesEntity.setPrefId(1L);
        prefValuesEntity.setPrefName(prefNamesEntity);
        prefDB = new ArrayList<>();
        prefDB.add(Optional.of(prefValuesEntity));
    }

    @Test
    public void saveUserPreferencesTest() {
        when(prefValuesRepository.findByUserAndPrefName(any(), any())).thenReturn(Optional.empty());
        when(prefValuesRepository.saveAll(any())).thenReturn(new ArrayList<>());

        String result = prefValuesService.saveUserPreferences(1, userPrefs);
        assertEquals("Success", result);
    }

    @Test
    public void getUserPrefValuesByIdTest() {
        when(prefValuesRepository.getAllByUserId(anyInt())).thenReturn(prefDB);

        List<PrefValueSaveReq> prefValues = prefValuesService.getUserPrefValuesById(1);
        assertEquals(1, prefValues.size());
        verify(prefValuesRepository, times(1)).getAllByUserId(anyInt());
    }
}

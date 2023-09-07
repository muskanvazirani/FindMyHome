package com.project.group17.prefOptions.service;

import com.project.group17.prefNames.entity.PrefOptionsEntity;
import com.project.group17.prefNames.repository.PrefOptionsRepository;
import com.project.group17.prefNames.service.PrefOptionsService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PrefOptionsServiceTest {

    @InjectMocks
    private PrefOptionsService service;

    @Mock
    private PrefOptionsRepository repository;

    private List<PrefOptionsEntity> prefOptionsList;

    @BeforeEach
    public void setUp() {
        PrefOptionsEntity prefOption1 = new PrefOptionsEntity();
        prefOption1.setPrefId(1L);
        prefOption1.setOption("Option 1");

        PrefOptionsEntity prefOption2 = new PrefOptionsEntity();
        prefOption2.setPrefId(2L);
        prefOption2.setOption("Option 2");

        prefOptionsList = Arrays.asList(prefOption1, prefOption2);
    }

    @Test
    public void testGetAllPrefOptions() {
        when(repository.findAll()).thenReturn(prefOptionsList);

        List<PrefOptionsEntity> result = service.getAllPrefOptions();
        assertEquals(prefOptionsList, result);
    }
}

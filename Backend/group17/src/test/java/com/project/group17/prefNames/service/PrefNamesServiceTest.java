package com.project.group17.prefNames.service;

import com.project.group17.prefNames.entity.PrefNamesEntity;
import com.project.group17.prefNames.repository.PrefNamesRepository;
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
public class PrefNamesServiceTest {

    @InjectMocks
    private PrefNamesService service;

    @Mock
    private PrefNamesRepository repository;

    private List<PrefNamesEntity> prefNamesList;

    @BeforeEach
    public void setUp() {
        PrefNamesEntity prefName1 = new PrefNamesEntity();
        prefName1.setPrefId(1L);
        prefName1.setName("Location");
        prefName1.setType("Halifax");
        prefName1.setIs_required(true);

        PrefNamesEntity prefName2 = new PrefNamesEntity();
        prefName2.setPrefId(2L);
        prefName2.setName("Furnished");
        prefName2.setType("Yes");
        prefName2.setIs_required(false);

        prefNamesList = Arrays.asList(prefName1, prefName2);
    }

    @Test
    public void testGetAllPrefOptions() {
        when(repository.findAll()).thenReturn(prefNamesList);

        List<PrefNamesEntity> result = service.getAllPrefOptions();
        assertEquals(prefNamesList, result);
    }
}
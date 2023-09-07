package com.project.group17.prefNames.service;

import com.project.group17.prefNames.entity.PrefNamesEntity;
import com.project.group17.prefNames.repository.PrefNamesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PrefNamesService {

    @Autowired
    PrefNamesRepository repository;

    /**
     * Returns a list of all preference options.
     *
     * @return List<PrefNamesEntity> - a list of preference options
     */
    public List<PrefNamesEntity> getAllPrefOptions()
    {
        // Call the findAll method in the repository to get all preference options
        return repository.findAll();
    }
}

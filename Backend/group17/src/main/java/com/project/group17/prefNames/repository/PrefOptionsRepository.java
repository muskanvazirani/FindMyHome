package com.project.group17.prefNames.repository;

import com.project.group17.prefNames.entity.PrefOptionsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PrefOptionsRepository extends JpaRepository<PrefOptionsEntity, Integer> {

    // This repository interface inherits all the methods of the JpaRepository interface
}

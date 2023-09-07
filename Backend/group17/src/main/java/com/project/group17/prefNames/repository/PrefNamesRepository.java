package com.project.group17.prefNames.repository;

import com.project.group17.prefNames.entity.PrefNamesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * PrefNamesRepository is an interface for the data access layer to manage the "pref_names" table.
 * It extends JpaRepository to utilize default CRUD operations for the PrefNamesEntity.
 */
@Repository
public interface PrefNamesRepository extends JpaRepository<PrefNamesEntity, Long> {
}

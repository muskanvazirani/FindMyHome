package com.project.group17.prefNames.repository;

import com.project.group17.prefNames.entity.PrefValuesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PrefValuesRepository extends JpaRepository<PrefValuesEntity, Long> {

    /**
     * Finds a preference value by user ID and preference name ID.
     *
     * @param userId - the ID of the user associated with the preference value
     * @param prefNameId - the ID of the preference name associated with the preference value
     * @return Optional<PrefValuesEntity> - an Optional that may contain a PrefValuesEntity object that matches the specified user ID and preference name ID
     */
    @Query("select pv from PrefValuesEntity pv where pv.user.id = :userId and pv.prefName.prefId = :prefNameId")
    Optional<PrefValuesEntity> findByUserAndPrefName(Integer userId, Long prefNameId);

    /**
     * Returns a list of preference values for a given user ID.
     *
     * @param userId - the ID of the user associated with the preference values
     * @return List<Optional<PrefValuesEntity>> - a list of Optional objects that may contain PrefValuesEntity objects associated with the specified user ID
     */
    List<Optional<PrefValuesEntity>> getAllByUserId(Integer userId);
}

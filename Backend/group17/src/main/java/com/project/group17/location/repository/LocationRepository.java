package com.project.group17.location.repository;
import jakarta.persistence.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.project.group17.location.entity.LocationEntity;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

/**
 * LocationRepository is a repository class for managing LocationEntity objects.
 */
@Repository
public class LocationRepository {

    @Autowired
    private EntityManager entityManager;

    /**
     * Saves a LocationEntity to the database.
     *
     * @param location The LocationEntity to be saved.
     */
    @Transactional
    public void save(LocationEntity location) {
        entityManager.persist(location);
    }

    /**
     * Finds a LocationEntity by its city name.
     *
     * @param city The city name to search for.
     * @return The LocationEntity with the specified city name, or null if not found.
     */
    public LocationEntity findByCity(String city) {
        return entityManager.find(LocationEntity.class, city);
    }

    /**
     * Deletes all LocationEntity objects from the database.
     */
    // @Transactional
    // public void deleteAll() {
    //     Query query = entityManager.createQuery("DELETE FROM LocationEntity");
    //     query.executeUpdate();
    // }
}


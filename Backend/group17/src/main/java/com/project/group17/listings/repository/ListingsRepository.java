package com.project.group17.listings.repository;
import com.project.group17.listings.entity.ListingsEntity;
import com.project.group17.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * ListingsRepository is a repository interface for managing and querying {@link ListingsEntity} objects.
 * It extends JpaRepository, which provides basic CRUD operations.
 */
@Repository
public interface ListingsRepository extends JpaRepository<ListingsEntity, Long> {

    /**
     * Retrieves a list of ListingsEntity objects associated with the specified user.
     *
     * @param user The user_id of the ListingsEntity objects to be queried.
     * @return A list of ListingsEntity objects associated with the specified user.
     */
    @Query(value = "SELECT * FROM listings WHERE user = ?1", nativeQuery = true)
    List<ListingsEntity> getListingEntity(long user);

    /**
     * Finds a ListingsEntity object by its listingId.
     *
     * @param listingId The listingId of the ListingsEntity to be queried.
     * @return The ListingsEntity object with the specified listingId.
     */
    ListingsEntity findByListingId(Long listingId);

    /**
     * Retrieves all ListingsEntity objects in the database.
     *
     * @return A list of all ListingsEntity objects.
     */
    @Query(value = "SELECT * FROM listings", nativeQuery = true)
    List<ListingsEntity> getAllListings();

    /**
     * Finds a list of ListingsEntity objects associated with the specified user.
     *
     * @param user The User object associated with the ListingsEntity objects to be queried.
     * @return A list of ListingsEntity objects associated with the specified user.
     */
    List<ListingsEntity> findByUser(User user);
}

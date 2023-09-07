package com.project.group17.listings.repository;
import com.project.group17.listings.entity.LikeListingEntity;
import com.project.group17.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * LikeListingRepository is a repository interface for managing and querying {@link LikeListingEntity} objects.
 * It extends JpaRepository, which provides basic CRUD operations.
 */
@Repository
public interface LikeListingRepository extends JpaRepository<LikeListingEntity, Long> {

    /**
     * Deletes a LikeListingEntity by its id.
     *
     * @param id The id of the LikeListingEntity to be deleted.
     */
    void deleteById(long id);

    /**
     * Gets the id of a LikeListingEntity with the specified user_id and listingsId.
     *
     * @param user_id The user_id of the LikeListingEntity to be queried.
     * @param listingsId The listingsId of the LikeListingEntity to be queried.
     * @return The id of the found LikeListingEntity.
     */
    @Query(value = "SELECT id FROM listings_liked WHERE user = ?1 AND listings = ?2", nativeQuery = true)
    long getId(long user_id, long listingsId);

    /**
     * Finds a list of LikeListingEntity objects by the associated user.
     *
     * @param user The user for which the liked listings should be queried.
     * @return A list of LikeListingEntity objects associated with the specified user.
     */
    List<LikeListingEntity> findByUser(User user);

    @Query(value = "SELECT user FROM listings_liked WHERE listings = ?1", nativeQuery = true)
    List<Integer> getUser(long listing);


}


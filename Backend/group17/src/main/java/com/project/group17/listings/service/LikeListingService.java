package com.project.group17.listings.service;
import com.project.group17.listings.entity.LikeListingEntity;
import com.project.group17.listings.entity.LikeListingPojo;
import com.project.group17.listings.repository.LikeListingRepository;
import com.project.group17.listings.repository.ListingsRepository;
import com.project.group17.user.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

/**
 * LikeListingService is a service class for managing and processing like/unlike actions on {@link LikeListingEntity} objects.
 */
@Service
public class LikeListingService {

    @Autowired
    ListingsRepository listingsRepository;

    @Autowired
    LikeListingRepository likeListingRepository;

    /**
     * Adds a like to the specified listing.
     *
     * @param likeListingPojo The LikeListingPojo object containing the listingId to be liked.
     */
    public void likeListing(LikeListingPojo likeListingPojo) {
        LikeListingEntity entity = new LikeListingEntity();
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        entity.setUser(user);
        entity.setListingsEntity(listingsRepository.findByListingId(likeListingPojo.getListingId()));
        likeListingRepository.save(entity);
    }

    /**
     * Removes a like from the specified listing.
     *
     * @param listingId The listingId of the listing to be unliked.
     */
    public void unlikeListing(LikeListingPojo likeListingPojo) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        likeListingRepository.deleteById(likeListingRepository.getId(user.getId(), likeListingPojo.getListingId()));
    }
}

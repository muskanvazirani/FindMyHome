package com.project.group17.listings.service;
import com.project.group17.listings.entity.LikeListingEntity;
import com.project.group17.listings.repository.LikeListingRepository;
import com.project.group17.listings.entity.ListingsEntity;
import com.project.group17.listings.repository.ListingsRepository;
import com.project.group17.match.service.MatchService;
import com.project.group17.user.entity.User;
import com.project.group17.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * ListingsService is a service class for managing and processing {@link ListingsEntity} objects.
 */
@Service
public class ListingsService {

    @Autowired
    private ListingsRepository listingsRepository;

    @Autowired
    private LikeListingRepository likeListingRepository;

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private MatchService matchService;

    /**
     * Saves a listing to the database.
     *
     * @param listing The ListingsEntity object to be saved.
     * @return The saved ListingsEntity object.
     */
    public ListingsEntity saveListings(ListingsEntity listing) {
        return listingsRepository.save(listing);
    }

    /**
     * Retrieves all listings that are not liked by the current user.
     *
     * @return A list of ListingsEntity objects not liked by the current user.
     */
    public List<ListingsEntity> getAllListings() {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<LikeListingEntity> likeListingEntities = likeListingRepository.findByUser(user);
        List<ListingsEntity> allListings = listingsRepository.getAllListings();
        List<ListingsEntity> unlikedListings = new ArrayList<>();

        for (ListingsEntity listing : allListings) {
            boolean liked = false;
            for (LikeListingEntity like : likeListingEntities) {
                if (listing.equals(like.getListingsEntity())) {
                    liked = true;
                    break;
                }
            }
            if (!liked) {
                unlikedListings.add(listing);
            }
        }

        return unlikedListings;
    }

    /**
     * Retrieves listings posted by the current user.
     *
     * @return A list of ListingsEntity objects posted by the current user.
     */
    public List<ListingsEntity> getMyListings() {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return listingsRepository.findByUser(user);
    }

    /**
     * Retrieves listings liked by the current user.
     *
     * @return A list of ListingsEntity objects liked by the current user.
     */
    public List<ListingsEntity> getLikedListings() {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<LikeListingEntity> likeListingEntities = likeListingRepository.findByUser(user);
        List<ListingsEntity> listingsEntities = new ArrayList<>();
        for (int i = 0; i < likeListingEntities.size(); i++) {
            listingsEntities.add(likeListingEntities.get(i).getListingsEntity());
        }
        return listingsEntities;
    }

    /**
     * Returns a ResponseEntity object containing a list of maps representing the users who have liked the listings of the authenticated user.
     *
     * @return ResponseEntity<List<Map<String, String>>> - a response entity containing a list of maps of user information
     */
    public ResponseEntity<List<Map<String, String>>> getListingsLikedUsers() {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        //these are my listings
        List<ListingsEntity> myListings = getMyListings();
        //get the listings id
        List<Integer> users = new ArrayList<>();
        List<Long> myListingsId = null;
        for(int i = 0;i< myListings.size(); i++){
            users.addAll(likeListingRepository.getUser(myListings.get(i).getListingId()));
        }
        List<User> userList = new ArrayList<>();
        for (int i = 0; i < users.size(); i++) {
            Optional<User> optionalUser = userRepository.findById(users.get(i));
            optionalUser.ifPresent(userList::add);
        }
        return ResponseEntity.ok(matchService.getAllUserInfoAndPreferences(userList));
    }
}

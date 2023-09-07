package com.project.group17.listings.controller;
import com.project.group17.listings.entity.ListingsEntity;
import com.project.group17.listings.service.ListingsService;
import com.project.group17.user.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

/**
 * ListingsController is a class that handles HTTP requests related to listing operations.
 */
@RestController
@RequestMapping("/api/v1")
public class ListingsController {
    @Autowired
    private ListingsService listingsService;

    /**
     * Handles the HTTP POST request to add a new listing.
     *
     * @param listing The JSON object containing listing details.
     * @return A string message indicating the result of the operation.
     */
    @CrossOrigin
    @PostMapping("/listing")
    public String add(@RequestBody ListingsEntity listing) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        listing.setUser(user);
        listingsService.saveListings(listing);
        return "New listing is added";
    }

    /**
     * Handles the HTTP GET request to retrieve all listings.
     *
     * @return A list of ListingsEntity objects.
     */
    @CrossOrigin
    @GetMapping("/getAll")
    public List<ListingsEntity> list() {
        return listingsService.getAllListings();
    }

    /**
     * Handles the HTTP GET request to retrieve the listings posted by the user.
     *
     * @return A list of ListingsEntity objects posted by the user.
     */
    @CrossOrigin
    @GetMapping("/get-my-listings")
    public List<ListingsEntity> getMyListings() {
        return listingsService.getMyListings();
    }

    /**
     * Handles the HTTP GET request to retrieve the liked listings.
     *
     * @return A list of ListingsEntity objects liked by the user.
     */
    @CrossOrigin
    @GetMapping("/get-liked-listings")
    public List<ListingsEntity> getLikedListings() {
        return listingsService.getLikedListings();
    }

    /**
     * Returns a ResponseEntity object containing a list of maps representing the users who have liked the listings of the authenticated user.
     *
     * @return ResponseEntity<List<Map<String, String>>> - a response entity containing a list of maps of user information
     */
    @CrossOrigin
    @GetMapping("/get-liked-listingUsers")
    public ResponseEntity<List<Map<String, String>>> getListingsLikedUsers() {
        return listingsService.getListingsLikedUsers();
    }
}

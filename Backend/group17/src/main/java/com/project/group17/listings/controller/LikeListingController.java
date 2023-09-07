package com.project.group17.listings.controller;

import com.project.group17.listings.entity.LikeListingPojo;
import com.project.group17.listings.service.LikeListingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * LikeListingController is a class that handles HTTP requests related to liking and unliking listings.
 */
@RestController
@RequestMapping("/api/v1")
public class LikeListingController {
    @Autowired
    public LikeListingService likeListingService;

    /**
     * Handles the HTTP POST request to like a listing.
     *
     * @param likeListingPojo The JSON object containing listing ID and user ID.
     * @return A ResponseEntity indicating the result of the operation.
     */
    @CrossOrigin
    @RequestMapping(value = "/like-listing", method = RequestMethod.POST)
    public ResponseEntity likeListing(@RequestBody LikeListingPojo likeListingPojo) {
        System.out.println(likeListingPojo.getListingId());
        likeListingService.likeListing(likeListingPojo);
        return ResponseEntity.ok().build();
    }

    /**
     * Handles the HTTP POST request to unlike a listing.
     *
     * @param listingId The ID of the listing to be unliked.
     * @return A ResponseEntity indicating the result of the operation.
     */
    @CrossOrigin
    @RequestMapping(value = "/unlike-listing", method = RequestMethod.POST)
    public ResponseEntity unlikeListing(@RequestBody LikeListingPojo likeListingPojo) {
        System.out.println(likeListingPojo.getListingId());
        likeListingService.unlikeListing(likeListingPojo);
        return ResponseEntity.ok().build();
    }
}

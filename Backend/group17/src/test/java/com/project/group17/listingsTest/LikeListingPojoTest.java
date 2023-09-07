package com.project.group17.listingsTest;

import com.project.group17.listings.entity.LikeListingPojo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LikeListingPojoTest {
    private LikeListingPojo likeListingPojo;

    @BeforeEach
    public void setUp() {
        likeListingPojo = new LikeListingPojo();
        likeListingPojo.setListingId(1L);
    }

    @Test
    public void testListingId() {
        assertEquals(1L, likeListingPojo.getListingId());
    }
}

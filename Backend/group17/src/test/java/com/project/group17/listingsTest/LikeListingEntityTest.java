package com.project.group17.listingsTest;

import com.project.group17.listings.entity.LikeListingEntity;
import com.project.group17.listings.entity.ListingsEntity;
import com.project.group17.user.entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class LikeListingEntityTest {
    private LikeListingEntity likeListingEntity;
    private User user;
    private ListingsEntity listingsEntity;

    @BeforeEach
    public void setUp() {
        user = mock(User.class);
        when(user.getId()).thenReturn(1);
        when(user.getUsername()).thenReturn("testUser");

        listingsEntity = mock(ListingsEntity.class);
        when(listingsEntity.getListingId()).thenReturn(1L);
        when(listingsEntity.getType()).thenReturn("3BHK Apartment");

        likeListingEntity = new LikeListingEntity();
        likeListingEntity.setId(1L);
        likeListingEntity.setUser(user);
        likeListingEntity.setListingsEntity(listingsEntity);
    }

    @Test
    public void testId() {
        assertEquals(1L, likeListingEntity.getId());
    }

    @Test
    public void testUser() {
        assertEquals(user, likeListingEntity.getUser());
        assertEquals(Integer.valueOf(1), user.getId());
        assertEquals("testUser", user.getUsername());
    }

    @Test
    public void testListingsEntity() {
        assertEquals(listingsEntity, likeListingEntity.getListingsEntity());
        assertEquals(1L, listingsEntity.getListingId());
        assertEquals("3BHK Apartment", listingsEntity.getType());
    }
}

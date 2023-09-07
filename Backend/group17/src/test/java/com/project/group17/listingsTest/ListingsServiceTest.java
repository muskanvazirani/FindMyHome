package com.project.group17.listingsTest;

import com.project.group17.listings.entity.LikeListingEntity;
import com.project.group17.listings.entity.ListingsEntity;
import com.project.group17.listings.repository.LikeListingRepository;
import com.project.group17.listings.repository.ListingsRepository;
import com.project.group17.listings.service.ListingsService;
import com.project.group17.user.entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.Mockito;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import static org.mockito.Mockito.mock;

@ExtendWith(MockitoExtension.class)
public class ListingsServiceTest {

    @Mock
    private ListingsRepository listingsRepository;

    @Mock
    private LikeListingRepository likeListingRepository;

    @InjectMocks
    private ListingsService listingsService;

    private User user;
    private ListingsEntity listing1;
    private ListingsEntity listing2;
    private LikeListingEntity likeListingEntity;

    @BeforeEach
    void setUp() {
        user = new User();
        user.setId(1);
        user.setEmail("test@example.com");
        Authentication auth = new UsernamePasswordAuthenticationToken(user, null, new ArrayList<>());
        SecurityContext securityContext = mock(SecurityContext.class, Mockito.RETURNS_DEEP_STUBS);
        when(securityContext.getAuthentication()).thenReturn(auth);
        SecurityContextHolder.setContext(securityContext);

        listing1 = new ListingsEntity();
        listing1.setListingId(1L);
        listing1.setType("1BHK Apartment");
        listing1.setUser(user);

        listing2 = new ListingsEntity();
        listing2.setListingId(2L);
        listing2.setType("2BHK Apartment");
        listing2.setUser(user);

        likeListingEntity = new LikeListingEntity();
        likeListingEntity.setUser(user);
        likeListingEntity.setListingsEntity(listing1);
    }

    @Test
    void testSaveListings() {
        when(listingsRepository.save(listing1)).thenReturn(listing1);

        ListingsEntity savedListing = listingsService.saveListings(listing1);
        assertEquals(listing1, savedListing);
    }


    @Test
    void testGetMyListings() {
        List<ListingsEntity> myListings = Arrays.asList(listing1, listing2);

        when(listingsRepository.findByUser(user)).thenReturn(myListings);

        List<ListingsEntity> result = listingsService.getMyListings();
        assertEquals(2, result.size());
    }

    @Test
    void testGetAllListings() {
        List<ListingsEntity> myListings = Arrays.asList(listing1, listing2);
        List<LikeListingEntity> likeListingEntities = Arrays.asList(likeListingEntity);

        when(listingsRepository.getAllListings()).thenReturn(myListings);
        when(likeListingRepository.findByUser(user)).thenReturn(likeListingEntities);

        List<ListingsEntity> result = listingsService.getAllListings();
        assertEquals(1, result.size());
        assertEquals(listing2, result.get(0));
    }

    @Test
    void testGetLikedListings() {
        List<LikeListingEntity> likeListingEntities = Arrays.asList(likeListingEntity);

        when(likeListingRepository.findByUser(user)).thenReturn(likeListingEntities);

        List<ListingsEntity> result = listingsService.getLikedListings();
        assertEquals(1, result.size());
        assertEquals(listing1, result.get(0));
    }
}


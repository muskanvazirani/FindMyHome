package com.project.group17.listingsTest;
import com.project.group17.listings.entity.LikeListingEntity;
import com.project.group17.listings.entity.LikeListingPojo;
import com.project.group17.listings.entity.ListingsEntity;
import com.project.group17.listings.repository.LikeListingRepository;
import com.project.group17.listings.repository.ListingsRepository;
import com.project.group17.listings.service.LikeListingService;
import com.project.group17.user.entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.ArrayList;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

@ExtendWith(MockitoExtension.class)
public class LikeListingServiceTest {

    @Mock
    private ListingsRepository listingsRepository;

    @Mock
    private LikeListingRepository likeListingRepository;

    @InjectMocks
    private LikeListingService likeListingService;

    @Captor
    private ArgumentCaptor<LikeListingEntity> likeListingEntityArgumentCaptor;

    private User user;
    private ListingsEntity listing1;
    private LikeListingPojo likeListingPojo;

    @BeforeEach
    void setUp() {
        user = new User();
        user.setId(1);
        user.setEmail("test@example.com");
        Authentication auth = new UsernamePasswordAuthenticationToken(user, null, new ArrayList<>());
        SecurityContext securityContext = mock(SecurityContext.class, RETURNS_DEEP_STUBS);
        when(securityContext.getAuthentication()).thenReturn(auth);
        SecurityContextHolder.setContext(securityContext);

        listing1 = new ListingsEntity();
        listing1.setListingId(1L);
        listing1.setType("1BHK Apartment");
        listing1.setUser(user);

        likeListingPojo = new LikeListingPojo();
        likeListingPojo.setListingId(1L);
    }

    @Test
    void testLikeListing() {
        when(listingsRepository.findByListingId(likeListingPojo.getListingId())).thenReturn(listing1);

        likeListingService.likeListing(likeListingPojo);

        verify(likeListingRepository, times(1)).save(likeListingEntityArgumentCaptor.capture());
        LikeListingEntity capturedLikeListingEntity = likeListingEntityArgumentCaptor.getValue();

        assertEquals(user, capturedLikeListingEntity.getUser());
        assertEquals(listing1, capturedLikeListingEntity.getListingsEntity());
    }

    @Test
    void testUnlikeListing() {
        Long listingId = 1L;
        when(likeListingRepository.getId(user.getId(), listingId)).thenReturn(1L);

        likeListingService.unlikeListing(likeListingPojo);

        verify(likeListingRepository, times(1)).deleteById(1L);
    }
}
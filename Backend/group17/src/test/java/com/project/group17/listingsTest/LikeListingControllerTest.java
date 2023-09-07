package com.project.group17.listingsTest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.group17.config.Service.JwtService;
import com.project.group17.listings.controller.LikeListingController;
import com.project.group17.listings.entity.LikeListingPojo;
import com.project.group17.listings.service.LikeListingService;
import com.project.group17.user.entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import static org.mockito.ArgumentMatchers.any;

import java.util.ArrayList;

import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = LikeListingController.class)
@AutoConfigureMockMvc(addFilters = false)
public class LikeListingControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private JwtService jwtService;

    @MockBean
    private LikeListingService likeListingService;

    private LikeListingPojo likeListingPojo;

    @BeforeEach
    public void setUp() {

        User user = new User();
        user.setId(1);
        user.setEmail("test@example.com");
        Authentication auth = new UsernamePasswordAuthenticationToken(user, null, new ArrayList<>());
        SecurityContextHolder.getContext().setAuthentication(auth);

        likeListingPojo = new LikeListingPojo();
        likeListingPojo.setListingId(1L);
    }
    @Test
    @WithMockUser(roles = "USER")
    public void likeListingTest() throws Exception {
        mockMvc.perform(post("/api/v1/like-listing")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(likeListingPojo)))
                .andExpect(status().isOk());

        verify(likeListingService).likeListing(any(LikeListingPojo.class));
    }

    @Test
    @WithMockUser(roles = "USER")
    public void unlikeListingTest() throws Exception {
        Long listingId = 1L;

        mockMvc.perform(post("/api/v1/unlike-listing")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(likeListingPojo)))
                .andExpect(status().isOk());

        verify(likeListingService).unlikeListing(any(LikeListingPojo.class));
    }
}

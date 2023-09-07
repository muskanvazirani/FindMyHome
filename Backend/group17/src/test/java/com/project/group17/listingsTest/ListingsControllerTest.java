package com.project.group17.listingsTest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.group17.config.Service.JwtService;
import com.project.group17.listings.controller.ListingsController;
import com.project.group17.listings.entity.ListingsEntity;
import com.project.group17.listings.service.ListingsService;
import com.project.group17.user.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = ListingsController.class)
@AutoConfigureMockMvc(addFilters = false)
public class ListingsControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private JwtService jwtService;

    @MockBean
    private ListingsService listingsService;

    private List<ListingsEntity> listings;
    private ListingsEntity listing1;
    private ListingsEntity listing2;

    @BeforeEach
    void setUp() {

        User user = new User();
        user.setId(1);
        user.setEmail("test@example.com");
        Authentication auth = new UsernamePasswordAuthenticationToken(user, null, new ArrayList<>());
        SecurityContextHolder.getContext().setAuthentication(auth);

        listing1 = new ListingsEntity();
        listing1.setListingId(1L);
        listing1.setType("1BHK Apartment");

        listing2 = new ListingsEntity();
        listing2.setListingId(2L);
        listing2.setType("2BHK Apartment");

        listings = Arrays.asList(listing1, listing2);
    }

    @Test
    @WithMockUser(roles = "USER")
    public void testAddListing() throws Exception {
        String requestBody = "{\"listingId\":1,\"type\":\"Studio Apartment\",\"address\":null,\"utilities\":null,\"rent\":1234.0,\"details\":null,\"file64\":null,\"sfile64\":null}";

        mockMvc.perform(post("/api/v1/listing")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isOk())
                .andExpect(content().string("New listing is added"));
    }
    @Test
    @WithMockUser
    void testGetAllListings() throws Exception {
        when(listingsService.getAllListings()).thenReturn(listings);

        mockMvc.perform(get("/api/v1/getAll")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(listings)));
    }

    @Test
    @WithMockUser
    void testGetMyListings() throws Exception {
        when(listingsService.getMyListings()).thenReturn(listings);

        mockMvc.perform(get("/api/v1/get-my-listings")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(listings)));
    }

    @Test
    @WithMockUser
    void testGetLikedListings() throws Exception {
        when(listingsService.getLikedListings()).thenReturn(listings);

        mockMvc.perform(get("/api/v1/get-liked-listings")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(listings)));
    }
}

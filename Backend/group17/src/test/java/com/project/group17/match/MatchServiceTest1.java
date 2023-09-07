package com.project.group17.match;

import com.project.group17.group.repository.GroupRepository;
import com.project.group17.group.service.GroupService;
import com.project.group17.location.entity.LocationEntity;
import com.project.group17.location.service.LocationService;
import com.project.group17.match.entity.MatchEntity;
import com.project.group17.match.entity.MatchPojo;
import com.project.group17.match.repository.MatchRepository;
import com.project.group17.match.service.MatchService;

import com.project.group17.prefNames.service.PrefValuesService;
import com.project.group17.user.entity.User;
import com.project.group17.user.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
public class MatchServiceTest1 {
    @InjectMocks
    private MatchService matchService;

    @Mock
    private LocationService locationService;

    @Mock
    private PrefValuesService prefValuesService;

    @Mock
    private GroupRepository groupRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private GroupService groupService;

    @Mock
    private MatchRepository matchRepository;

    @Mock
    private Authentication authentication;

    private User user1;
    private User user2;
    private MatchPojo matchPojo;
    private MatchEntity matchEntity;


    @BeforeEach
    public void setUp() {

        MockitoAnnotations.openMocks(this);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        when(locationService.getLocationEntity("Timberlea")).thenReturn(new LocationEntity("Timberlea", "44.66175625881382", "-63.75207725181231"));
        when(locationService.getLocationEntity("Halifax South End")).thenReturn(new LocationEntity("Halifax South End", "44.635132918937806", "-63.57985798361541"));
//        when(locationService.getLocationEntity("Halifax West End")).thenReturn(new LocationEntity("Halifax West End", "44.65192658618188", "-63.61298862922718"));
//        when(locationService.getLocationEntity("Halifax North End")).thenReturn(new LocationEntity("Halifax North End", "44.65955846444604", "-63.60183063993835"));
//        when(locationService.getLocationEntity("Halifax East End")).thenReturn(new LocationEntity("Halifax East End", "44.64768282753042", "-63.57290569703296"));
//        when(locationService.getLocationEntity("Downtown Halifax")).thenReturn(new LocationEntity("Downtown Halifax", "44.64768282753042", "-63.57290569703296"));
//        when(locationService.getLocationEntity("Sackville")).thenReturn(new LocationEntity("Sackville", "44.768394409225046", "-63.69103839346116"));
//        when(locationService.getLocationEntity("Hammonds Plains")).thenReturn(new LocationEntity("Hammonds Plains", "44.73669884252296", "-63.79540849239174"));
//        when(locationService.getLocationEntity("Lakeside")).thenReturn(new LocationEntity("Lakeside", "44.636653411230085", "-63.69982531272396"));
//        when(locationService.getLocationEntity("Armdale")).thenReturn(new LocationEntity("Armdale", "44.6301063409465", "-63.608053784657024"));
//        when(locationService.getLocationEntity("Clayton Park")).thenReturn(new LocationEntity("Clayton Park", "44.660464449196354", "-63.64642900811929"));
//        when(locationService.getLocationEntity("Dartmouth")).thenReturn(new LocationEntity("Dartmouth", "44.665958693547246", "-63.568494743677874"));
//        when(locationService.getLocationEntity("Cole Harbour")).thenReturn(new LocationEntity("Cole Harbour", "44.6729459579599", "-63.47782982298076"));

        user1 = new User();
        user1.setId(1);
        user1.setFirstname("John");
        user1.setLastname("Doe");
        user1.setCity("New York");
        user1.setProvince("NY");

        user2 = new User();
        user2.setId(2);
        user2.setFirstname("Jane");
        user2.setLastname("Doe");
        user2.setCity("Los Angeles");
        user2.setProvince("CA");

        matchPojo = new MatchPojo();
        matchPojo.setUser2ID(user2.getId());

        matchEntity = new MatchEntity();
        matchEntity.setUser1(user1);
        matchEntity.setUser2(user2);
    }


    @Test
    public void testGetSimilarityScore() {
        Map<String, String> user1Prefs = new HashMap<>();
        user1Prefs.put("Furnished", "Yes");
        user1Prefs.put("Lease Length", "6 months");
        user1Prefs.put("Parking", "Street");
        user1Prefs.put("Gender", "Male");
        user1Prefs.put("Pets Policy", "No pets");
        user1Prefs.put("Meal", "Vegetarian");
        user1Prefs.put("Drinker", "Social drinker");
        user1Prefs.put("Smoker", "Non-smoker");
        user1Prefs.put("Move-in Date", "ASAP");
        user1Prefs.put("Overall Rent", "Less than 1500/month");
        user1Prefs.put("Rent Contribution", "Less than $500/month");
        user1Prefs.put("Max Roommates", "2");
        user1Prefs.put("Location", "Halifax South End");

        Map<String, String> user2Prefs = new HashMap<>(user1Prefs);

        double similarityScore = matchService.getSimilarityScore(user1Prefs, user2Prefs);
        assertEquals(1.0, similarityScore, 0.01);

        user2Prefs.put("Furnished", "No");
        user2Prefs.put("Pets Policy", "Pets allowed");
        user2Prefs.put("Drinker", "Non-drinker");
        user2Prefs.put("Move-in Date", "Within the next month");
        user2Prefs.put("Overall Rent", "1500-2000/month");
        user2Prefs.put("Rent Contribution", "$500-$1000/month");
        user2Prefs.put("Max Roommates", "3");
        user2Prefs.put("Location", "Timberlea");

        similarityScore = matchService.getSimilarityScore(user1Prefs, user2Prefs);
        assertNotEquals(1.0, similarityScore, 0.01);
    }
}


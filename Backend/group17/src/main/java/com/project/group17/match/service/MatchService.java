package com.project.group17.match.service;
import java.util.*;
import com.project.group17.group.entity.GroupEntity;
import com.project.group17.group.repository.GroupRepository;
import com.project.group17.group.service.GroupService;
import com.project.group17.match.entity.MatchEntity;
import com.project.group17.match.entity.MatchPojo;
import com.project.group17.match.repository.MatchRepository;
import com.project.group17.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import com.project.group17.location.service.LocationService;
import com.project.group17.prefNames.entity.PrefValuesEntity;
import com.project.group17.prefNames.service.PrefValuesService;
import com.project.group17.location.entity.LocationEntity;
import com.project.group17.user.entity.User;

/**
 * Service class for handling matching logic.
 */

@Service
public class MatchService {

    @Autowired
    private LocationService locationService;

    @Autowired
    private PrefValuesService prefValuesService;
    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    GroupService groupService;

    @Autowired
    MatchRepository matchRepository;

    /**
     * Retrieves user preferences for all users.
     *
     * @return A map containing user preferences for each user.
     */
    public Map<User, Map<String, String>> getUserPreferences() {
        List<PrefValuesEntity> prefValuesEntities = prefValuesService.findAll();
        Map<User, Map<String, String>> userPreferences = new HashMap<>();
        for (PrefValuesEntity prefValueEntity : prefValuesEntities) {
            String prefName = prefValueEntity.getPrefName().getName();
            String prefValue = "";
            if (prefValueEntity.getPrefOption() != null){
                prefValue = prefValueEntity.getPrefOption().getOption();
            }
            User user = prefValueEntity.getUser();
            if (userPreferences.containsKey(user)) {
                userPreferences.get(user).put(prefName, prefValue);
            } else {
                Map<String, String> mp = new HashMap<>();
                mp.put(prefName, prefValue);
                userPreferences.put(user, mp);
            }
        }
        return userPreferences;
    }

    /**
     * Retrieves user information and preferences for a specific user.
     *
     * @param user User object for which information and preferences are retrieved.
     * @return A map containing user information and preferences.
     */
    public Map<String, String> getUserInfoAndPreferences(User user) {
        Map<User, Map<String, String>> userPrefs = this.getUserPreferences();
        Map<String, String> principalPrefs = null;
        for (Map.Entry<User, Map<String, String>> entry : userPrefs.entrySet()) {
            // .equals() wont work for custom objects, using ID comparison
            if (user.getId().equals(entry.getKey().getId())) {
                principalPrefs = entry.getValue();
            }
        }
        if (principalPrefs == null) {
            System.out.println("User not found");
            return null;
        }
        principalPrefs.put("id", user.getId() + "");
        principalPrefs.put("firstName", user.getFirstname());
        principalPrefs.put("lastName", user.getLastname());
        principalPrefs.put("email", user.getEmail());
        principalPrefs.put("gender", user.getGender());
        principalPrefs.put("age", user.getAge());
        principalPrefs.put("phoneNumber", user.getPhoneNumber());
        principalPrefs.put("streetAddress", user.getStreetAddress());
        principalPrefs.put("city", user.getCity());
        principalPrefs.put("province", user.getProvince());
        principalPrefs.put("profilePicBase64", user.getProfilePicBase64());
        return principalPrefs;
    }

    /**
     * Retrieves a list of potential roommates for a given user.
     *
     * @param user User object for which potential roommates are retrieved.
     * @return A list of maps containing user information and preferences of potential roommates.
     */
    public List<Map<String, String>> getRoommateList(User user){
        List<GroupEntity> groups = groupRepository.findByUser(user);
        if(groups.size() != 0){
            return null;
        } else{
            Map<User, Map<String, String>> userPrefs = this.getUserPreferences();
            Map<User, Map<String, String>> userPrefsExludePrincipal = new HashMap<>();
            Map<String, String> principalPrefs = null;
            System.out.println("-----------------------------");

            for (Map.Entry<User, Map<String, String>> entry : userPrefs.entrySet()) {
                // .equals() wont work for custom objects, using ID comparison
                if (user.getId().equals(entry.getKey().getId())) {
                    principalPrefs = entry.getValue();
                } else {
                    userPrefsExludePrincipal.put(entry.getKey(), entry.getValue());
                }
            }
            for (Map.Entry<User, Map<String, String>> entry : userPrefsExludePrincipal.entrySet()) {
//                System.out.println(entry.getKey().getFirstname() + " " + entry.getKey().getId());
                //entry.getValue().forEach((pref, option) -> System.out.println(pref + " - " + option));
            }
//            System.out.println(principalPrefs);
            List<Map<String, String>> userInfoAndPreferences = new ArrayList<>();
            for (Map.Entry<User, Map<String, String>> entry : userPrefsExludePrincipal.entrySet()) {
                User u = entry.getKey();
                entry.getValue().put("SimilarityScore", "" + getSimilarityScore(principalPrefs, entry.getValue()));
                entry.getValue().put("id", u.getId() + "");
                entry.getValue().put("firstName", u.getFirstname());
                entry.getValue().put("lastName", u.getLastname());
                entry.getValue().put("city", u.getCity());
                entry.getValue().put("province", u.getProvince());
                entry.getValue().put("profilePicBase64", u.getProfilePicBase64());
                userInfoAndPreferences.add(entry.getValue());
            }

            return userInfoAndPreferences;
        }
    }

    /**
     * Retrieves user information and preferences for all users in the provided list.
     *
     * @param users List of User objects for which information and preferences are retrieved.
     * @return A list of maps containing user information and preferences for each user in the list.
     */
    public List<Map<String, String>> getAllUserInfoAndPreferences(List<User> users) {
        List<Map<String, String>> allUserInfoAndPreferences = new ArrayList<>();
        for(User user: users){
            Map<String, String> userInfoAndPref = getUserInfoAndPreferences(user);
            if (userInfoAndPref != null){
                allUserInfoAndPreferences.add(userInfoAndPref);
            }
        }
        return allUserInfoAndPreferences;
    }
    /**
     * Calculates the similarity score between two users.
     *
     * @param User1 A map containing user information and preferences for the first user.
     * @param User2 A map containing user information and preferences for the second user.
     * @return A double representing the similarity score between the two users.
     */
    public double getSimilarityScore(Map<String, String> User1, Map<String, String> User2) {
        String[] nominalKeys = { "Furnished", "Lease Length", "Parking", "Gender", "Pets Policy", "Meal", "Drinker",
                "Smoker" };
        String[] ordinalKeys = { "Move-in Date", "Overall Rent", "Rent Contribution", "Max Roommates" };
        Map<String, Integer> maxRankOrdinalValues = new HashMap<>();
        maxRankOrdinalValues.put("Move-in Date", 3);
        maxRankOrdinalValues.put("Overall Rent", 6);
        maxRankOrdinalValues.put("Rent Contribution", 4);
        maxRankOrdinalValues.put("Max Roommates", 4);
        Map<String, Map<String, Integer>> ordinalValues = new HashMap<>();
        Map<String, Integer> moveInDate = new HashMap<>();
        moveInDate.put("ASAP", 1);
        moveInDate.put("Within the next month", 2);
        moveInDate.put("Within the next three months", 3);
        ordinalValues.put("Move-in Date", moveInDate);

        Map<String, Integer> overallRent = new HashMap<>();
        overallRent.put("Less than 1500/month", 1);
        overallRent.put("1500-2000/month", 2);
        overallRent.put("2000-2500/month", 3);
        overallRent.put("3500-4000/month", 4);
        overallRent.put("4000-5000/month", 5);
        overallRent.put("More than 5000", 6);
        ordinalValues.put("Overall Rent", overallRent);

        Map<String, Integer> rentContribution = new HashMap<>();
        rentContribution.put("Less than $500/month", 1);
        rentContribution.put("$500-$1000/month", 2);
        rentContribution.put("$1000-$1500/month", 3);
        rentContribution.put("$1500+/month", 4);
        ordinalValues.put("Rent Contribution", rentContribution);

        Map<String, Integer> maxRoommates = new HashMap<>();
        maxRoommates.put("2", 1);
        maxRoommates.put("3", 2);
        maxRoommates.put("4", 3);
        maxRoommates.put("5", 4);
        ordinalValues.put("Max Roommates", maxRoommates);

        double ordinalScore = 0;
        for (String ordinalKey : ordinalKeys) {
            System.out.println(User1.get("firstName") + " " + User2.get("firstName"));
            int rank1 = ordinalValues.get(ordinalKey).get(User1.get(ordinalKey));
            int rank2 = ordinalValues.get(ordinalKey).get(User2.get(ordinalKey));
            ordinalScore += getOrdinalScore(rank1, rank2, maxRankOrdinalValues.get(ordinalKey));
        }
        ordinalScore /= ordinalKeys.length; // Get Average Ordinal Score.
        double nominalScore = 0;
        for (String nominalKey : nominalKeys) {
            String user1NominalValue = User1.get(nominalKey);
            String user2NominalValue = User2.get(nominalKey);
            nominalScore += getNominalScore(user1NominalValue, user2NominalValue);
        }
        nominalScore /= nominalKeys.length;
        double distance = getDistance(User1.get("Location"), User2.get("Location"));
        double locationScore = getLocationScore(distance);
        return (nominalScore + ordinalScore + locationScore) / 3;
    }

    private double getLocationScore(double distance) {
        /*
         * The formula is based on the intuition that as the distance between two points
         * increases,
         * their similarity decreases. By taking the inverse of the distance and adding
         * 1,
         * the formula ensures that the similarity score will be in the range of 0 to 1,
         * with higher scores indicating greater similarity.
         */
        double locationScore = 1 / (1 + distance);
        return locationScore;
    }

    private double getDistance(String user1City, String user2City) {
        /*
         * Apply Distance Formula
         */
        LocationEntity user1Location = this.locationService.getLocationEntity(user1City);
        LocationEntity user2Location = this.locationService.getLocationEntity(user2City);

        Double x1 = Double.parseDouble(user1Location.getLatitude());
        Double y1 = Double.parseDouble(user1Location.getLongitude());

        Double x2 = Double.parseDouble(user2Location.getLatitude());
        Double y2 = Double.parseDouble(user2Location.getLongitude());

        Double distance = Math.sqrt(((x2 - x1) * (x2 - x1)) + ((y2 - y1) * (y2 - y1)));

        return distance;
    }

    // Jaccard Similarity Index
    private int getNominalScore(String value1, String value2) {
        return value1.equals(value2) ? 1 : 0;
    }

    // simple matching coefficient
    private double getOrdinalScore(double rank1, double rank2, double n) {
        /*
         * The formula is also sometimes called the "percent agreement" formula,
         * because it calculates the percentage of times that two variables are in
         * agreement.
         */
        double similarity = 1 - (Math.abs(rank1 - rank2) / (n - 1));
        return similarity;
    }

    /**
     * Saves a user's like (match) action in the database.
     *
     * @param user2 MatchPojo object containing the user being liked.
     */
    public void getUserId(MatchPojo user2) {
        MatchEntity entity = new MatchEntity();
        User liker = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Optional<User> optionalLikee = userRepository.findById(user2.getUser2ID());
        User likee = optionalLikee.get();
        entity.setUser1(liker);
        entity.setUser2(likee);
        matchRepository.save(entity);
        List<MatchEntity> matches;
        matches = matchRepository.findByUser1(likee);
        for (int i = 0; i < matches.size(); i++) {
            if (matches.get(i).getUser2().getId().equals(liker.getId())){
                groupService.saveGroup(liker, likee);
                break;
            }
        }
    }
    /**
     * Get a list of users who the current user has liked.
     *
     * @return ResponseEntity containing a list of user information and preferences for the users the current user has liked.
     */
    public ResponseEntity<List<Map<String, String>>> likes() {
        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<MatchEntity> entities = null;
        entities = matchRepository.findByUser1(currentUser);
        List<User> likes = new ArrayList<>();
        for (int i = 0; i < entities.size(); i++) {
            likes.add(entities.get(i).getUser2());
        }
        return ResponseEntity.ok(getAllUserInfoAndPreferences(likes));
    }
}

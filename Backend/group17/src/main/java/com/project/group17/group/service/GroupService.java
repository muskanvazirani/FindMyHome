package com.project.group17.group.service;
import com.project.group17.group.entity.GroupEntity;
import com.project.group17.group.entity.GroupPojo;
import com.project.group17.match.entity.MatchEntity;
import com.project.group17.match.repository.MatchRepository;
import com.project.group17.user.entity.UserPojo;
import com.project.group17.group.repository.GroupRepository;
import com.project.group17.prefNames.entity.PrefValueSaveReq;
import com.project.group17.prefNames.service.PrefValuesService;
import com.project.group17.user.entity.User;
import com.project.group17.user.repository.UserRepository;
import org.springframework.aop.AopInvocationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;


import java.util.*;
/**
 * GroupService is a class that provides services related to group operations.
 */
@Service
public class GroupService {
    @Autowired
    GroupRepository groupRepository;

    @Autowired
    PrefValuesService prefValuesService;

    @Autowired
    MatchRepository matchRepository;

    @Autowired
    UserRepository userRepository;

    /**
     * Saves a group with two users.
     *
     * @param user1ID First user ID.
     * @param user2ID Second user ID.
     */
    public void saveGroup(User user1ID, User user2ID){

        List<GroupEntity> groups = groupRepository.findAll();
        if(groups.isEmpty()){
            GroupEntity groupEntity = new GroupEntity();
            groupEntity.setGroupId(1);
            groupEntity.setUser(user1ID);
            groupEntity.setTotal_groups(1);
            groupRepository.save(groupEntity);
            GroupEntity groupEntity1 = new GroupEntity();
            groupEntity1.setGroupId(1);
            groupEntity1.setUser(user2ID);
            groupEntity1.setTotal_groups(1);
            groupRepository.save(groupEntity1);
        }else{
            //else group count increases > new group is created
                GroupEntity groupEntity = new GroupEntity();
                int total_groups = groupRepository.getMaxGroupCount()+1;
                groupEntity.setGroupId(total_groups);
                groupEntity.setUser(user1ID);
                groupEntity.setTotal_groups(total_groups);
                groupRepository.save(groupEntity);
                GroupEntity groupEntity1 = new GroupEntity();
                groupEntity1.setGroupId(total_groups);
                groupEntity1.setUser(user2ID);
                groupEntity1.setTotal_groups(total_groups);
                groupRepository.save(groupEntity1);

        }
    }

    /**
     * Retrieves a list of users in a group.
     *
     * @param groupID The group ID.
     * @return A list of users in the group.
     */
    public List<User> getGroupUsers(Long groupID){
        List<GroupEntity> groupEntities = groupRepository.findByGroupId(groupID);
        List<User> groupUsers = new ArrayList<>();
        for (GroupEntity groupEntity: groupEntities){
            groupUsers.add(groupEntity.getUser());
        }
        return groupUsers;
    }

    /**
     * Retrieves all groups of a user.
     *
     * @param user The user object.
     * @return A list of GroupPojo objects.
     */
    public List<GroupPojo> getAllGroups(User user) {

        List<GroupEntity> groupEntities = groupRepository.findAll();

        List<PrefValueSaveReq> prefValueSaveReqsCurrentUser = prefValuesService.getUserPrefValuesById(user.getId());

        long similarity = 0;

        HashMap<Long, List<UserPojo>> groupedUsers = new HashMap<>();

        for (GroupEntity groupEntity: groupEntities)
        {
            UserPojo userPojo = new UserPojo();
            userPojo.setUserID(groupEntity.getUser().getId());
            userPojo.setFirstName(groupEntity.getUser().getFirstname());
            userPojo.setLastName(groupEntity.getUser().getLastname());
            userPojo.setEmail(groupEntity.getUser().getEmail());
            userPojo.setAge(groupEntity.getUser().getAge());
            userPojo.setCity(groupEntity.getUser().getCity());
            userPojo.setProvince(groupEntity.getUser().getProvince());
            userPojo.setStreetAddress(groupEntity.getUser().getStreetAddress());
            userPojo.setProfilePicBase64(groupEntity.getUser().getProfilePicBase64());
            userPojo.setGender(groupEntity.getUser().getGender());
            userPojo.setPhoneNumber(groupEntity.getUser().getPhoneNumber());

            if(groupedUsers.containsKey(groupEntity.getGroupId()))
            {
                groupedUsers.get(groupEntity.getGroupId()).add(userPojo);
            }
            else
            {
                groupedUsers.put(groupEntity.getGroupId(), new ArrayList<>());
                groupedUsers.get(groupEntity.getGroupId()).add(userPojo);
            }

            List<PrefValueSaveReq> prefValueSaveReqs1;
            prefValueSaveReqs1 = prefValuesService.getUserPrefValuesById(groupEntity.getUser().getId());
            int numPrefValues = prefValueSaveReqsCurrentUser.size();

            int count = 0;
            for(PrefValueSaveReq prefValueSaveReq: prefValueSaveReqs1)
            {
                if(count<prefValueSaveReqsCurrentUser.size()){
                    if(prefValueSaveReq.getPrefOptionId() == prefValueSaveReqsCurrentUser.get(count).getPrefOptionId() ) {
                        similarity++;
                    }
                }
                count++;
            }

            userPojo.setSimilarity(similarity/(double) numPrefValues);

        }

        List<GroupPojo> groupPojo = new ArrayList<>();

        for (Map.Entry<Long, List<UserPojo>> groupedUser : groupedUsers.entrySet()) {
            List<UserPojo> userList = groupedUser.getValue();
            Long groupID = groupedUser.getKey();

            double totalSimilarity = 0.0;
            for (UserPojo userPojo : userList) {
                totalSimilarity += userPojo.getSimilarity();
            }

            double avgSimilarity = totalSimilarity / groupedUser.getValue().size();

            GroupPojo currentGroupPojo = new GroupPojo();
            currentGroupPojo.setGroupID(groupID);
            currentGroupPojo.setUsers(userList);
            currentGroupPojo.setAvgSimilarity(avgSimilarity);

            groupPojo.add(currentGroupPojo);
        }

        return groupPojo;
    }


    /**
     * Retrieves roommate requests for the logged-in user.
     *
     * @return A list of users who have sent roommate requests.
     */
    public List<User> getRoommateRequest(){
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        try{
            Integer myGroupId = groupRepository.getGroupId(user.getId());
            return null;
        }catch (AopInvocationException aopInvocationException){
            List<MatchEntity> matches;
            List<User> myLikers = new ArrayList<>();
            try{
                matches = matchRepository.findByUser2(user);
                for(int i = 0; i<matches.size();i++){
                    myLikers.add(matches.get(i).getUser1());
                }
            }catch (NullPointerException e){
                return null;
            }
            return myLikers;
        }

    }


    /**
     * Retrieves a list of GroupPojo objects for the logged-in user.
     *
     * @return A ResponseEntity containing a list of GroupPojo objects.
     */
    public ResponseEntity<List<GroupPojo>> getGroups() {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        //check if user is in a group
        if(groupRepository.findByUser(user).isEmpty()){
            return ResponseEntity.ok(getAllGroups(user));
        }else{
            return null;
        }
    }


    /**
     * Retrieves a list of group members for the logged-in user's group.
     *
     * @return A list of users in the user's group.
     */
    public List<User> getGroupMembers(){
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<Integer> groupMembers;
        try{
            groupMembers = groupRepository.getUsersByGroupId(groupRepository.getGroupId(user.getId()));
        }catch (AopInvocationException aopInvocationException){
            return null;
        }
        groupMembers.remove(user.getId());
        List<User> myGroupMembers = new ArrayList<>();
        for(int i = 0; i<groupMembers.size(); i++){
            Optional<User> optionalUser =  userRepository.findById(groupMembers.get(i));
            optionalUser.ifPresent(myGroupMembers::add);
        }
        return myGroupMembers;
    }
}
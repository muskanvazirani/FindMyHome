package com.project.group17.prefNames.service;

import com.project.group17.prefNames.repository.PrefValuesRepository;
import com.project.group17.prefNames.entity.PrefValuesEntity;
import com.project.group17.prefNames.mapper.PrefValuesMapper;
import com.project.group17.prefNames.entity.PrefValueSaveReq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PrefValuesService {

    @Autowired
    PrefValuesRepository repository;

    /**
     * Saves the user's preferences in the database.
     *
     * @param userId - the ID of the user whose preferences are being saved
     * @param userPrefs - a list of PrefValueSaveReq objects containing the user's preferences to be saved
     * @return String - a string indicating whether the operation was successful
     */
    public String saveUserPreferences(int userId, List<PrefValueSaveReq> userPrefs){
        List<PrefValuesEntity> prefValuesEntities = new ArrayList<>();
        for(PrefValueSaveReq userPref: userPrefs){
            PrefValuesEntity prefValuesEntity = PrefValuesMapper.saveReqToEntityMapper(userPref, userId);
            int uId = prefValuesEntity.getUser().getId();
            Long prefValId = prefValuesEntity.getPrefValueId();
            Optional<PrefValuesEntity> dataFromDB = repository.findByUserAndPrefName(uId, prefValId);

            if(dataFromDB.isPresent()){
                prefValuesEntity.setPrefValueId(dataFromDB.get().getPrefValueId());
            }
            prefValuesEntities.add(PrefValuesMapper.saveReqToEntityMapper(userPref, userId));
        }
        repository.saveAll(prefValuesEntities);
        return "Success";
    }

    /**
     * Retrieves a list of the user's preferences from the database.
     *
     * @param userId - the ID of the user whose preferences are being retrieved
     * @return List<PrefValueSaveReq> - a list of PrefValueSaveReq objects containing the user's preferences
     */
    public List<PrefValueSaveReq> getUserPrefValuesById(int userId) {
        try {
            List<PrefValueSaveReq> prefRes = new ArrayList<>();
            List<Optional<PrefValuesEntity>> prefDB = repository.getAllByUserId(userId);
            if (!prefDB.isEmpty())
                for (Optional<PrefValuesEntity> pref : prefDB) {
                    if (pref.isPresent())
                        prefRes.add(PrefValuesMapper.entityToGetPrefCRes(pref.get()));
                }
            return prefRes;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println(e.getStackTrace());
            return new ArrayList<>();
        }
    }

    /**
     * Retrieves a list of all preference values from the database.
     *
     * @return List<PrefValuesEntity> - a list of all PrefValuesEntity objects in the database
     */
    public List<PrefValuesEntity> findAll(){
        return repository.findAll();
    }
}
